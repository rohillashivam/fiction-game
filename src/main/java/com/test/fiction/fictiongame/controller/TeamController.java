package com.test.fiction.fictiongame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.fiction.fictiongame.service.TeamService;
import com.test.fiction.fictiongame.vo.v1.TeamV1;

@RestController
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	@PostMapping("/v1")
	public TeamV1 addTeam(@RequestBody TeamV1 team) {
		teamService.addTeam(team);
		return team;
	}
	
	@PutMapping("/v1")
	public TeamV1 updateGame(@RequestBody TeamV1 team) {
		teamService.updateTeam(team);
		return team;
	}
}
