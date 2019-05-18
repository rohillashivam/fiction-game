package com.test.fiction.fictiongame.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.exception.DuplicatePlayerInAggregateException;
import com.test.fiction.fictiongame.exception.IncompleteDataException;
import com.test.fiction.fictiongame.repositories.PlayerAggregateRepository;
import com.test.fiction.fictiongame.service.PlayerAggService;

@Service
public class PlayerAggServiceImpl implements PlayerAggService {

	@Autowired
	private PlayerAggregateRepository playerAggRepo;
	
	@Override
	public PlayerAggregate createPlayerAggregate(Player player) {
		PlayerAggregate playerAgg = null;
		if(player != null) {
			if(player.getId() == null) {
				throw new IncompleteDataException("player id is null");
			} else {
				Optional<PlayerAggregate> playerAggOpt = playerAggRepo.
						findByPlayerId(player.getId());
				if(playerAggOpt.isPresent()) {
					throw new DuplicatePlayerInAggregateException("duplicate entry is "
							+ "tried to put for player with id :: "+player.getId());
				} else {
					playerAgg = new PlayerAggregate();
					playerAgg.setPlayer(player);
					playerAggRepo.save(playerAgg);
				}
			}
		} else {
			throw new IncompleteDataException("player is null");
		}
		return playerAgg;
	}

	@Override
	public PlayerAggregate updatePlayerAggregate(PlayerScore playerScore) {
		Optional<PlayerAggregate> playerAggOpt = playerAggRepo.findByPlayerId(playerScore.getPlayerId());
		if(playerAggOpt.isPresent()) {
			PlayerAggregate playerAgg = playerAggOpt.get();
			
			playerAgg.setTotalNumberOfAssists(playerAgg.getTotalNumberOfAssists() + 
					playerScore.getNumOfAssists());
			if(playerAgg.getHighestNumberOfAssistInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfAssists()))) < 0) 
				playerAgg.setHighestNumberOfAssistInMatch(new Long(playerScore.getNumOfAssists()));
			if(playerAgg.getLowestNumberOfAssistInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfAssists()))) > 0)
				playerAgg.setLowestNumberOfAssistInMatch(new Long(playerScore.getNumOfAssists()));
			
			playerAgg.setTotalNumberOfAttemptedAttack(playerAgg.getTotalNumberOfAttemptedAttack() + 
					playerScore.getNumOfAttemptedAttack());
			if(playerAgg.getHighestNumberOfAttemptedAttacksInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfAttemptedAttack()))) < 0)
				playerAgg.setHighestNumberOfAttemptedAttacksInMatch(new Long(playerScore.getNumOfAttemptedAttack()));
			if(playerAgg.getLowestNumberOfAttemptedAttacksInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfAttemptedAttack()))) > 0 )
				playerAgg.setLowestNumberOfAttemptedAttacksInMatch(new Long(playerScore.getNumOfAttemptedAttack()));
				
			
			playerAgg.setTotalNumberOfFirstHitKills(playerAgg.getTotalNumberOfFirstHitKills() + 
					playerScore.getNumOfFirstHitKills());
			if(playerAgg.getHighestNumOfFirstHitKillsInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfFirstHitKills()))) < 0)
				playerAgg.setHighestNumberOfHitInMatch(new Long(playerScore.getNumOfFirstHitKills()));
			if(playerAgg.getLowestNumOfFirstHitKillsInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfFirstHitKills()))) > 0 )
				playerAgg.setLowestNumOfFirstHitKillsInMatch(new Long(playerScore.getNumOfFirstHitKills()));
			
			
			playerAgg.setTotalNumberOfGamesPlayed(playerAgg.getTotalNumberOfGamesPlayed() + 1);
			
			playerAgg.setTotalNumberOfHits(playerAgg.getTotalNumberOfHits() + playerScore.getNumOfHits());
			if(playerAgg.getHighestNumberOfHitInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfHits()))) < 0)
				playerAgg.setHighestNumberOfHitInMatch(new Long(playerScore.getNumOfHits()));
			if(playerAgg.getLowestNumberOfHitInMatch().compareTo(
					Long.parseLong(String.valueOf(playerScore.getNumOfHits()))) > 0 )
				playerAgg.setLowestNumberOfHitInMatch(new Long(playerScore.getNumOfHits()));
			
			playerAgg.setTotalNumberOfKills(playerAgg.getTotalNumberOfKills() + playerScore.getNumOfKills());
			if(playerScore.getMatchWon() != null) {
				if(!playerScore.getMatchWon().booleanValue())
					playerAgg.setTotalNumberOfLost(playerAgg.getTotalNumberOfLost() + 1);
				else 
					playerAgg.setTotalNumberOfWins(playerAgg.getTotalNumberOfWins() + 1);
			}
			
			playerAgg.setTotalNumberOfSpellCasts(playerAgg.getTotalNumberOfSpellCasts() + 
					playerScore.getNumOfSpellCast());
			
			playerAgg.setTotalNumberOfSpellDamageDone(playerAgg.getTotalNumberOfSpellDamageDone() + 
					playerScore.getSpellDamageDone());
			
			playerAgg.setTotalTimePlayed(playerAgg.getTotalTimePlayed() + playerScore.getTimePlayed());
			if(playerAgg.getHighestTimePlayedInMatch().compareTo(playerScore.getTimePlayed()) < 0)
				playerAgg.setHighestTimePlayedInMatch(playerScore.getTimePlayed());
			if(playerAgg.getLowestTimePlayedInMatch().compareTo(playerScore.getTimePlayed()) > 0 )
				playerAgg.setLowestTimePlayedInMatch(playerScore.getTimePlayed());
			
			playerAggRepo.save(playerAgg);
			return playerAgg;
		}
		return null;
	}

}
