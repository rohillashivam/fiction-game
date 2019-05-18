package com.test.fiction.fictiongame.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.event.enums.Award;
import com.test.fiction.fictiongame.exception.EmptyDataException;
import com.test.fiction.fictiongame.exception.WrongDataException;
import com.test.fiction.fictiongame.repositories.PlayerAggregateRepository;
import com.test.fiction.fictiongame.repositories.PlayerRepository;
import com.test.fiction.fictiongame.service.AwardService;

@Service
public class AwardServiceImpl implements AwardService {

	@Autowired
	private PlayerAggregateRepository playerAggRepo;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Override
	public void assignAwardToPlayer(PlayerScore playerScore) {
		if(playerScore != null && playerScore.getPlayerId() != null) {
			Optional<PlayerAggregate> playerAggOpt =playerAggRepo.
					findByPlayerId(playerScore.getPlayerId());

			if(playerAggOpt.isPresent()) {
				PlayerAggregate playerAgg = playerAggOpt.get();
				Player player = playerAgg.getPlayer();
				List<Award> awardList = player.getAchievementList();
				Boolean isAwarded = Boolean.FALSE;
				if(playerScore.getSpellDamageDone().intValue() > 500) {
					if(awardList == null)
						awardList = new ArrayList<>();
					awardList.add(Award.BRUISER);
					isAwarded = Boolean.TRUE;
				}

				if(playerAgg.getTotalNumberOfGamesPlayed().intValue() >= 1000) {
					if(awardList == null)
						awardList = new ArrayList<>();
					awardList.add(Award.VETERAN);
					isAwarded = Boolean.TRUE;
				} 

				if(playerAgg.getTotalNumberOfWins().intValue() >= 200) {
					if(awardList == null)
						awardList = new ArrayList<>();
					awardList.add(Award.BIGWINNER);
					isAwarded = Boolean.TRUE;
				}

				if(playerScore.getNumOfHits() > 0 && 
						((playerScore.getNumOfKills().intValue()/playerScore.getNumOfHits().intValue()) * 100) >= 75) {
					if(awardList == null)
						awardList = new ArrayList<>();
					awardList.add(Award.SHARPSHOOTER);
					isAwarded = Boolean.TRUE;
				}

				if(isAwarded != null && isAwarded.booleanValue()) {
					player.setAchievementList(awardList);
					playerRepo.save(player);
				}

			} else {
				throw new WrongDataException("Player with id :: "+playerScore.getPlayerId()+" doesn't exists");
			}
		} else {
			throw new EmptyDataException("PlayerScore or aligned player to that score data is empty while assigning awards");
		}
	}

}
