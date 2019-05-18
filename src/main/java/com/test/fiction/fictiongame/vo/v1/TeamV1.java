package com.test.fiction.fictiongame.vo.v1;

import java.io.Serializable;
import java.util.List;

public class TeamV1 implements Serializable {

	private Long id;
	
	private String name;
	
	private List<Long> playerIdList;

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

	public List<Long> getPlayerIdList() {
		return playerIdList;
	}

	public void setPlayerIdList(List<Long> playerIdList) {
		this.playerIdList = playerIdList;
	}
	
}
