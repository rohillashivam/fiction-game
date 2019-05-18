package com.test.fiction.fictiongame.vo.v1;

import java.io.Serializable;

public class PlayerV1 implements Serializable {

	private Long id;
	
	private String name;
	
	private String teamId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
}
