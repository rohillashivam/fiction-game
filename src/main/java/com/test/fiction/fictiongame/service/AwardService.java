package com.test.fiction.fictiongame.service;

import com.test.fiction.fictiongame.entity.PlayerScore;

public interface AwardService {
	
	/*Awards addAward(Awards awards);
	
	Awards updateAward(Awards awards);*/
	
	void assignAwardToPlayer(PlayerScore playerScore);
}
