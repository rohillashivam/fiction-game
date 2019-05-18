package com.test.fiction.fictiongame.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.fiction.fictiongame.entity.Game;
import com.test.fiction.fictiongame.exception.EmptyDataException;
import com.test.fiction.fictiongame.exception.InvalidGameException;
import com.test.fiction.fictiongame.repositories.GameRepository;
import com.test.fiction.fictiongame.service.GameService;
import com.test.fiction.fictiongame.vo.v1.GameV1;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepo;
	
	@Override
	public GameV1 addGame(GameV1 game) {
		if(game != null) {
			if(game.getId() == null ) {
				Game gameObj = new Game();
				if(!StringUtils.isEmpty(game.getName()))
					gameObj.setName(game.getName());
				else 
					throw new EmptyDataException("game name is not present");
				
				gameRepo.save(gameObj);
				game.setId(gameObj.getId());
			} else {
				return game;
			}
		}
		return null;
	}

	@Override
	public GameV1 updateGame(GameV1 game) {
		if(game != null) {
			if(game.getId() == null) {
				game = addGame(game);
			} else {
				Optional<Game> gameOpt = gameRepo.findById(game.getId());
				if(!gameOpt.isPresent()) {
					throw new InvalidGameException("game doesn't exists");
				} else {
					Game gameObj = gameOpt.get();
					if(!gameObj.getName().equals(game.getName())) {
						gameObj.setName(game.getName());
						gameRepo.save(gameObj);
					}
				}
			}
			
		}
		return game;
	}

}
