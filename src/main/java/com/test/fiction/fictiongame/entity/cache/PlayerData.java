package com.test.fiction.fictiongame.entity.cache;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.test.fiction.fictiongame.entity.Matches;
import com.test.fiction.fictiongame.entity.PlayerScore;
import com.test.fiction.fictiongame.event.enums.Award;

@RedisHash("players")
public class PlayerData implements Serializable {

	@Id
	private Long id;
	
	private PlayerScore playerScore;

	private Award award;
	
	@Indexed
	private Long matchId;
	
	@Indexed
	private Long teamId;
	
	@Indexed
	private Long playerId;

	public PlayerScore getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(PlayerScore playerScore) {
		this.playerScore = playerScore;
	}

	public Long getId() {
		return id;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

}
