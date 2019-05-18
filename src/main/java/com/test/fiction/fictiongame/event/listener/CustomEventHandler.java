package com.test.fiction.fictiongame.event.listener;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.entity.cache.PlayerData;
import com.test.fiction.fictiongame.event.type.MatchPlayed;
import com.test.fiction.fictiongame.event.type.PlayerCreation;
import com.test.fiction.fictiongame.exception.EmptyEventSourceException;
import com.test.fiction.fictiongame.repositories.PlayerRepository;
import com.test.fiction.fictiongame.repositories.cache.PlayerCacheRepository;
import com.test.fiction.fictiongame.service.AwardService;
import com.test.fiction.fictiongame.service.PlayerAggService;

@EnableAsync(proxyTargetClass = true)
@Component
public class CustomEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomEventHandler.class);
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private PlayerAggService playerAggService;
	
	@Autowired
	private AwardService awardService;
	
	@Autowired
	private PlayerCacheRepository playerCacheRepo;
	
	//@Async
	@EventListener
	public void handleNewPlayerEvent(PlayerCreation playerCreationEvent) {
		if(playerCreationEvent.getSource() != null) {
			Long playerId = (Long)playerCreationEvent.getSource();
			logger.debug("player with id :: {}",playerId);
			Optional<Player> playerOpt = playerRepo.findById(playerId);
			Player player = playerOpt.get();
			PlayerAggregate playerAgg = playerAggService.createPlayerAggregate(player);
			logger.debug("player aggregate created with id :: {} for player id :: {}",
					playerAgg.getId(), player.getId());
		} else {
			throw new EmptyEventSourceException("event source for new player is empty");
		}
	}
	
	//@Async
	@EventListener
	public void handleMatchPlayedEvent(MatchPlayed matchPlayed) {
		logger.debug(" in method :: handleMatchPlayedEvent");
		if(matchPlayed.getSource() != null) {
			PlayerScore playerScore = (PlayerScore)matchPlayed.getSource();
			playerAggService.updatePlayerAggregate(playerScore);
			awardService.assignAwardToPlayer(playerScore);
			updateDataCache(playerScore);
		}
	}

	private void updateDataCache(PlayerScore playerScore) {
		PlayerData player = new PlayerData();
		player.setPlayerId(playerScore.getPlayerId());
		player.setPlayerScore(playerScore);
		player.setMatchId(playerScore.getPlayerMatchEntity().getMatchId());
		player.setTeamId(playerScore.getPlayerMatchEntity().getTeamId());
		playerCacheRepo.save(player);
		//player.setAward();
	}
}
