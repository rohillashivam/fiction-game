package com.test.fiction.fictiongame.service;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;

public interface PlayerAggService {

	PlayerAggregate createPlayerAggregate(Player player);
	PlayerAggregate updatePlayerAggregate(PlayerScore playerScore);
}
