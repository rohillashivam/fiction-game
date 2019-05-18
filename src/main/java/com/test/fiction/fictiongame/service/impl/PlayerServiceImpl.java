package com.test.fiction.fictiongame.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.entity.Team;
import com.test.fiction.fictiongame.event.type.PlayerCreation;
import com.test.fiction.fictiongame.exception.DuplicatePlayerException;
import com.test.fiction.fictiongame.exception.EmptyDataException;
import com.test.fiction.fictiongame.exception.InvalidPlayerException;
import com.test.fiction.fictiongame.exception.InvalidTeamException;
import com.test.fiction.fictiongame.repositories.PlayerAggregateRepository;
import com.test.fiction.fictiongame.repositories.PlayerRepository;
import com.test.fiction.fictiongame.repositories.PlayerScoreRepository;
import com.test.fiction.fictiongame.repositories.TeamRepository;
import com.test.fiction.fictiongame.service.PlayerService;
import com.test.fiction.fictiongame.vo.v1.PlayerV1;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private PlayerAggregateRepository playerAggRepo;
	
	@Autowired
	private PlayerScoreRepository playerScoreRepo;

	@Override
	public PlayerV1 addPlayer(PlayerV1 player) {
		if(player != null) {
			if(player.getId() == null ) {
				Player playerObj = new Player();
				if(!StringUtils.isEmpty(player.getName()))
					playerObj.setName(player.getName());
				else 
					throw new EmptyDataException("Player name is not present");
				
				playerRepo.save(playerObj);
				
				if(!StringUtils.isEmpty(player.getTeamId())) {
					Long teamId = Long.parseLong(player.getTeamId());
					Optional<Team> teamData = teamRepo.findById(teamId);
					if(!teamData.isPresent()) {
						throw new InvalidTeamException("team doesn't exists");
					} else {
						Team team = teamData.get();
						team.getPlayers().add(playerObj);
						teamRepo.save(team);
						
					}
				}
				applicationEventPublisher.publishEvent(new PlayerCreation(playerObj.getId()));
				player.setId(playerObj.getId());
			} else {
				return player;
			}
		}
		return null;
	}

	@Override
	public PlayerV1 updatePlayer(PlayerV1 player) {
		if(player != null) {
			if(player.getId() == null) {
				player = addPlayer(player);
			} else {
				Optional<Player> playerOpt = playerRepo.findById(player.getId());
				if(!playerOpt.isPresent()) {
					throw new InvalidPlayerException("player doesn't exists");
				} else {
					Player playerObj = playerOpt.get();
					if(!StringUtils.isEmpty(player.getTeamId())) {
						throw new EmptyDataException("");
					} else {
						Long teamId = Long.parseLong(player.getTeamId());
						Optional<Team> teamOpt = teamRepo.findById(teamId);
						// not sure for updating team
						if(!StringUtils.isEmpty(player.getName())) {
							if(player.getName().equals(playerObj.getName())) {
								throw new DuplicatePlayerException("player name is same");
							} else {
								playerObj.setName(player.getName());
								playerRepo.save(playerObj);
								applicationEventPublisher.publishEvent(new PlayerCreation(playerObj));
							}
						}
					}
				}
			}
			
		}
		return null;
	}

	@Override
	public Player getPlayerById(Long id) {
		if(id != null ) {
			Optional<Player> playerOpt = playerRepo.findById(id);
			if(playerOpt.isPresent()) {
				return playerOpt.get();
			} else {
				throw new EmptyDataException("player is not mapped to player id :: "+id);
			}
		} else {
			throw new EmptyDataException("id is null");
		}
	}

	@Override
	public PlayerAggregate getPlayerStats(Long playerId) {
		if(playerId != null) {
			Optional<PlayerAggregate> playerOpt = playerAggRepo.findByPlayerId(playerId);
			if(playerOpt.isPresent()) {
				return playerOpt.get();
			} else {
				throw new EmptyDataException("player is not mapped to player id :: "+playerId);
			}
		} else {
			throw new EmptyDataException("player id is null");
		}
	}

	@Override
	public List<PlayerScore> getPlayerStatsForAllMatches(Long playerId, Integer pageNum, 
			Integer pageSize) {
		if(playerId != null) {
			return playerScoreRepo.findByPlayerId(playerId, PageRequest.of(pageNum, pageSize, 
					Direction.ASC, "playedOn"));
		} else {
			throw new EmptyDataException("player id is null");
		}
	}

	@Override
	public PlayerScore getPlayerStatsForMatch(Long playerId, Long matchId) {
		if(playerId != null) {
			if(matchId != null) {
				return playerScoreRepo.findByPlayerIdAndMatchId(playerId, matchId);
			} else {
				throw new EmptyDataException("match id is null");
			}
			
		} else {
			throw new EmptyDataException("player id is null");
		}
	}

}
