package com.test.fiction.fictiongame.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.Team;
import com.test.fiction.fictiongame.exception.AlreadyExistsException;
import com.test.fiction.fictiongame.exception.DuplicatePlayerInTeamException;
import com.test.fiction.fictiongame.exception.WrongDataException;
import com.test.fiction.fictiongame.repositories.PlayerRepository;
import com.test.fiction.fictiongame.repositories.TeamRepository;
import com.test.fiction.fictiongame.service.TeamService;
import com.test.fiction.fictiongame.vo.v1.TeamV1;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Override
	public TeamV1 addTeam(TeamV1 teamVO) {
		if(teamVO != null) {
			Team team = new Team();
			if(teamVO.getId() != null) {
				Long teamId = teamVO.getId();
				Optional<Team> teamObj = teamRepo.findById(teamId);
				if(teamObj == null || (teamObj != null && !teamObj.isPresent())) {
					throw new AlreadyExistsException("team with this id already present");
				}
			}
			
			team.setName(teamVO.getName());
			List<Long> playerIdList = teamVO.getPlayerIdList();
			if(playerIdList != null) {
				List<Player> playerList = playerRepo.findAllById(playerIdList);
				team.setPlayerList(playerList);
			}
			teamRepo.save(team);
			teamVO.setId(team.getId());
		}
		return null;
	}

	@Override
	public TeamV1 updateTeam(TeamV1 teamVO) {
		if(teamVO != null) {
			if(teamVO.getId() == null) {
				addTeam(teamVO);
			} else {
				Optional<Team> teamOpt = teamRepo.findById(teamVO.getId());
				if(teamOpt.isPresent()) {
					Team team = teamOpt.get();
					if(team.getName() != null && !team.getName().equals(teamVO.getName())) {
						throw new WrongDataException("Team with this id and name doesn't exists");
					} else {
						/*if(playerIdList.size() <= 5 && playerIdList.size() >=3 ) {
							
						} else if(playerIdList.size() > 5){
							throw new TeamSizeBreachingLimitException("Team can't have more than 5 players");
						} else {
							throw new TeamSizeIsTooSmallException("Team can't have less than 3 players");
						}*/
						final Set<Long> playerIdSet = new HashSet<>(teamVO.getPlayerIdList());
						
						if(playerIdSet.size() < teamVO.getPlayerIdList().size()) {
							throw new DuplicatePlayerInTeamException("request contains duplicate player mapping");
						}
						List<Player> playerList = team.getPlayers();
						final List<Long> playerToBeRemovedFromTeam = new ArrayList<>();
						playerList.forEach(player -> {
							if(playerIdSet.contains(player.getId())) {
								playerIdSet.remove(player.getId());
							} else {
								playerToBeRemovedFromTeam.add(player.getId());
							}
						});
						
						// delete the players which are no more associated with team
						playerToBeRemovedFromTeam.forEach(toBeDeletedPlayerId -> {
							for(int i=0; i<playerList.size(); i++) {
								Player player = playerList.get(i);
								if(player.getId().compareTo(toBeDeletedPlayerId) == 0)
									playerList.remove(i);
							}
						});
						
						// add the required player
						playerIdSet.forEach(playerId -> {
							Optional<Player> playerOpt = playerRepo.findById(playerId);
							if(!playerOpt.isPresent()) {
								throw new WrongDataException("Player this id "+ playerId+"doesn't exists");
							} else {
								playerList.add(playerOpt.get());
							}
						});
						team.setPlayerList(playerList);
						teamRepo.save(team);
					}
				} else {
					throw new WrongDataException("Team with provided id doesn't exists");
				}
			}
		}
		
		return null;
	}

}
