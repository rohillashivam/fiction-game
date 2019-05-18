package com.test.fiction.fictiongame.service;

import com.test.fiction.fictiongame.vo.v1.GameV1;

public interface GameService {

	public GameV1 addGame(GameV1 game);

	public GameV1 updateGame(GameV1 game);
}
