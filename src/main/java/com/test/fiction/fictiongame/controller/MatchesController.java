package com.test.fiction.fictiongame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.fiction.fictiongame.service.MatchService;
import com.test.fiction.fictiongame.vo.v1.MatchV1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/matches")
public class MatchesController {

	@Autowired
	private MatchService matchService;
	
	@PostMapping(path="/v1", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="add a new match", response=MatchV1.class)
	@ApiResponse(code=200, message="when game added successfully")
	public MatchV1 addMatch(@RequestBody MatchV1 match) {
		return matchService.addMatches(match);
	}
}
