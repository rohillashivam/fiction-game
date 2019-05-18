package com.test.fiction.fictiongame.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerMatchEnitity;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.entity.cache.PlayerData;
import com.test.fiction.fictiongame.repositories.PlayerRepository;
import com.test.fiction.fictiongame.repositories.PlayerScoreRepository;
import com.test.fiction.fictiongame.repositories.cache.PlayerCacheRepository;

@Component
public class CacheLoader {

	private static final Logger logger = LoggerFactory.getLogger(CacheLoader.class);
	
	@Autowired
	private PlayerCacheRepository playerCacheRepo;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private PlayerScoreRepository playerScoreRepo;
	
	private static final List<Long> playerIdList = new LinkedList<>();
	
	private void retrieveDateFromCache() {
		List<Player> playerDate = new ArrayList<>();
		for (Long playerId : playerIdList) {
			/*PlayerMatchEnitity playerMatchEntity = new PlayerMatchEnitity();
			com.junglee.fiction.fictiongame.entity.cache.PlayerData playerData = new com.junglee.fiction.fictiongame.entity.cache.PlayerData();
			PlayerScore playerScore = new PlayerScore();
			playerMatchEntity.setPlayerId(playerId);
			playerScore.setPlayerMatchEntity(playerMatchEntity);
			playerData.setPlayerScore(playerScore);*/
			PlayerData playerData = new PlayerData();
			playerData.setPlayerId(playerId);
			/*Iterable<com.junglee.fiction.fictiongame.entity.cache.PlayerData> playerDataList = 
						playerCacheRepo.findAll(Example.of(playerData));*/
			List<PlayerData> playerDataList = playerCacheRepo.findByPlayerId(playerId); 
			playerDataList.forEach(playerDataObj -> {
				logger.info("player data --> playerId :: {}, matchId :: {}, teamId :: {}", 
						playerDataObj.getPlayerScore().getPlayerMatchEntity().getPlayerId(), 
						playerDataObj.getPlayerScore().getPlayerMatchEntity().getMatchId(),
						playerDataObj.getPlayerScore().getPlayerMatchEntity().getTeamId());
			});
		}
	}
	
	private void initializePlayerCache() {
		List<Player> playerList = playerRepo.findAll();
		if(playerList != null && !playerList.isEmpty()) {
			final List<com.test.fiction.fictiongame.entity.cache.PlayerData> playerDataList = new LinkedList<>();
			playerList.forEach(player -> {
				playerIdList.add(player.getId());
				List<PlayerScore> playerScoreData = playerScoreRepo.findByPlayerId(player.getId());
				if(playerScoreData != null && !playerScoreData.isEmpty()) {
					playerScoreData.forEach(playerScoreObj -> {
						com.test.fiction.fictiongame.entity.cache.PlayerData playerData = new 
									com.test.fiction.fictiongame.entity.cache.PlayerData();
						playerData.setPlayerScore(playerScoreObj);
						playerData.setPlayerId(player.getId());
						//playerCacheRepo.saveAll(playerDataList);
						playerCacheRepo.save(playerData);
						playerDataList.add(playerData);
					});
				}
			});
			//playerCacheRepo.saveAll(playerDataList);
			logger.info("size of playerDataList :: "+ playerDataList.size());
		}
		logger.info("for testing");
		retrieveDateFromCache();
	}
	
	@PostConstruct
	public void init() {
		initializePlayerCache();
	}
	
}
