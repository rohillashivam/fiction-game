package com.test.fiction.fictiongame.service;

import java.util.List;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.vo.v1.PlayerV1;

public interface PlayerService {

	public PlayerV1 addPlayer(PlayerV1 player) ;
	
	public PlayerV1 updatePlayer(PlayerV1 player);
	
	public Player getPlayerById(Long id);

	public PlayerAggregate getPlayerStats(Long playerId);

	List<PlayerScore> getPlayerStatsForAllMatches(Long playerId, Integer pageNum, Integer pageSize);

	public PlayerScore getPlayerStatsForMatch(Long playerId, Long matchId);
}
