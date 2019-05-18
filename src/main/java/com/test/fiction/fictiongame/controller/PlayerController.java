package com.test.fiction.fictiongame.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.fiction.fictiongame.entity.Player;
import com.test.fiction.fictiongame.entity.PlayerAggregate;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.service.PlayerService;
import com.test.fiction.fictiongame.vo.v1.PlayerV1;

@RestController
@RequestMapping("/players")
public class PlayerController {
	
	private PlayerService playerService;
	
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping(path="/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerV1> addPlayers(@RequestBody PlayerV1 player) {
		if(player != null) {
			playerService.addPlayer(player);
		}
		return new ResponseEntity<PlayerV1>(player, HttpStatus.CREATED);
	}
	
	@PutMapping(name="/v1")
	public ResponseEntity<PlayerV1> updatePlayers(@RequestBody PlayerV1 player) {
		if(player != null) {
			if(player.getId() == null) {
				playerService.addPlayer(player);
				return new ResponseEntity<PlayerV1>(player, HttpStatus.CREATED);
			} else {
				
			}
		}
		return new ResponseEntity<PlayerV1>(player, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}")
	@ResponseBody
	public Player getPlayer(@PathVariable("id") Long playerId) {
		return playerService.getPlayerById(playerId);
	}
	
	@GetMapping(path="/{id}/stats")
	@ResponseBody
	public PlayerAggregate getPlayerStats(@PathVariable("id") Long playerId) {
		return playerService.getPlayerStats(playerId);
	}
	
	@GetMapping(path="/{id}/stats/matches/all")
	@ResponseBody
	public List<PlayerScore> getPlayerStatsForAllMatches(@PathVariable("id") Long playerId,
			@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
		return playerService.getPlayerStatsForAllMatches(playerId, pageNum, pageSize);
	}
	
	@GetMapping(path="/{id}/stats/matches/{matchId}")
	@ResponseBody
	public PlayerScore getPlayerStatsForAllMatches(@PathVariable("id") Long playerId, 
			@PathVariable("matchId") Long matchId) {
		return playerService.getPlayerStatsForMatch(playerId, matchId);
	}
	
}
