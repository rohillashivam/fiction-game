package com.test.fiction.fictiongame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.fiction.fictiongame.service.GameService;
import com.test.fiction.fictiongame.vo.v1.GameV1;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@PostMapping("/v1")
	@ResponseStatus(code=HttpStatus.CREATED)
	@ApiOperation(value="Add a new game", response=GameV1.class)
	public GameV1 addGame(@RequestBody GameV1 game) {
		return gameService.addGame(game);
	}
	
	@PutMapping("/v1")
	public GameV1 updateGame(@RequestBody GameV1 game) {
		
		return null;
	}
	
}
