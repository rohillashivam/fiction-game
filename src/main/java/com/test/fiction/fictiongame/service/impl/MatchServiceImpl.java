package com.test.fiction.fictiongame.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.test.fiction.fictiongame.entity.Game;
import com.test.fiction.fictiongame.entity.Matches;
import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerMatchEnitity;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.entity.Team;
import com.test.fiction.fictiongame.event.type.MatchPlayed;
import com.test.fiction.fictiongame.exception.IncompleteDataException;
import com.test.fiction.fictiongame.exception.InvalidGameException;
import com.test.fiction.fictiongame.exception.InvalidTeamException;
import com.test.fiction.fictiongame.exception.PlayerNotMappedInTeamException;
import com.test.fiction.fictiongame.exception.SameTeamInSameMatchException;
import com.test.fiction.fictiongame.exception.UnEqualPlayersInTeamException;
import com.test.fiction.fictiongame.repositories.GameRepository;
import com.test.fiction.fictiongame.repositories.MatchRepository;
import com.test.fiction.fictiongame.repositories.PlayerScoreRepository;
import com.test.fiction.fictiongame.repositories.TeamRepository;
import com.test.fiction.fictiongame.service.MatchService;
import com.test.fiction.fictiongame.vo.v1.MatchV1;
import com.test.fiction.fictiongame.vo.v1.dataObject.MatchTeamDataVO;

@Service
public class MatchServiceImpl implements MatchService {
	
	private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private MatchRepository matchRepo;
	
	@Autowired
	private PlayerScoreRepository playerScoreRepo;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public MatchV1 addMatches(MatchV1 matchVO) {
		if(matchVO != null && matchVO.getGameId() != null) {
			Optional<Game> gameOpt = gameRepo.findById(matchVO.getGameId());
			if(!gameOpt.isPresent()) {
				throw new InvalidGameException("data is given for invalid game");
			}
			Game game = gameOpt.get();
			List<MatchTeamDataVO> matchTeamDataList = matchVO.getTeamMatchData();
			if(matchTeamDataList != null && matchTeamDataList.size() != 0) {
				// match is being played between 2 teams
				if(matchTeamDataList.size() > 0 && matchTeamDataList.size() <= 2) {
					Matches match = new Matches();
					if(matchTeamDataList.get(0).getTeamId().compareTo(matchTeamDataList.get(1).getTeamId()) == 0) {
						throw new SameTeamInSameMatchException("same team cannot play from both side");
					}
					if(matchTeamDataList.get(0).getPlayerScoreList() == null || 
							matchTeamDataList.get(0).getPlayerScoreList().isEmpty() || 
							matchTeamDataList.get(1).getPlayerScoreList() == null || 
							matchTeamDataList.get(1).getPlayerScoreList().isEmpty()) {
						throw new IncompleteDataException("player score is empty or null");
					}
					if(matchTeamDataList.get(0).getPlayerScoreList() != null && 
							matchTeamDataList.get(1).getPlayerScoreList() != null && 
								matchTeamDataList.get(0).getPlayerScoreList().size() !=
									matchTeamDataList.get(1).getPlayerScoreList().size()) {
						throw new UnEqualPlayersInTeamException("teams not having equal players");
					}
					
					validateTeamandPlayers(matchTeamDataList.get(0).getTeamId(),
								matchTeamDataList.get(0).getPlayerScoreList());
					logger.info("team 1 data validated successfully");
					
					validateTeamandPlayers(matchTeamDataList.get(1).getTeamId(), 
								matchTeamDataList.get(1).getPlayerScoreList());
					logger.info("team 2 data validated successfully");
					
					match.setGame(game);
					setWinningTeamInMatch(matchTeamDataList, match);
					matchRepo.save(match);
					logger.info("match created successfully");
					
					matchTeamDataList.forEach(matchTeamData -> {
						List<PlayerScore> playerScoreList = matchTeamData.getPlayerScoreList();
						playerScoreList.forEach(playerScore -> {
							PlayerMatchEnitity playerMatchEntity = new PlayerMatchEnitity();
							playerMatchEntity.setMatchId(match.getId());
							playerMatchEntity.setPlayerId(playerScore.getPlayerId());
							playerMatchEntity.setTeamId(matchTeamData.getTeamId());
							playerScore.setPlayerMatchEntity(playerMatchEntity);
							if(matchTeamData.getMatchWon() != null) {
								playerScore.setMatchWon(matchTeamData.getMatchWon());
							}
							playerScoreRepo.save(playerScore);
							applicationEventPublisher.publishEvent(new MatchPlayed(playerScore));
						});
					});
					logger.info("player score saved successfully in db");
				} else {
					// throw exception
				}
			} else {
				throw new IncompleteDataException("match team data is null or empty array");
			}
		}
		return null;
	}

	private void setWinningTeamInMatch(List<MatchTeamDataVO> matchTeamDataList, Matches match) {
		Long winningTeamId = null;
		if(matchTeamDataList.get(0).getMatchWon() != null && 
				matchTeamDataList.get(0).getMatchWon().booleanValue())
			winningTeamId = matchTeamDataList.get(0).getTeamId();
		else
			winningTeamId = matchTeamDataList.get(1).getTeamId();
		Optional<Team> teamOpt  = teamRepo.findById(winningTeamId);
		match.setWinningTeam(teamOpt.get());
	}

	private void validateTeamandPlayers(Long teamId, List<PlayerScore> playerScoreList)  {
		if(teamId != null) {
			Optional<Team> teamOpt = teamRepo.findById(teamId); 
			if(teamOpt.isPresent()) {
				List<Player> playerTeamOneList =  teamOpt.get().getPlayers();
				final Set<Long> playerSet = playerTeamOneList.stream().map(Player::getId).collect(Collectors.toSet());

				List<Long> playerTeamOneIdList = playerScoreList.stream().map(PlayerScore::getPlayerId).
						collect(Collectors.toList());

				for (Long playerTeamOneIds : playerTeamOneIdList) {
					if(!playerSet.contains(playerTeamOneIds)) {
						throw new PlayerNotMappedInTeamException("player is not mapped in team, incorrect data");
					}
				}
			} else {
				throw new InvalidTeamException("team doesn't exists");
			}
		} else {
			throw new InvalidTeamException("team id is absent");
		}
	}

}
