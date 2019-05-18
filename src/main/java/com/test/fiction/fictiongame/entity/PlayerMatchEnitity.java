package com.test.fiction.fictiongame.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.redis.core.index.Indexed;

@Embeddable
public class PlayerMatchEnitity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5948326024529179378L;

	@Indexed
	@Column(nullable=false)
	private Long playerId;
	
	@Indexed
	@Column(nullable=false)
	private Long matchId;
	
	@Indexed
	@Column(nullable = false)
	private Long teamId;

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
