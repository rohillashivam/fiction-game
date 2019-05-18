package com.test.fiction.fictiongame.vo.v1.dataObject;

import java.io.Serializable;
import java.util.List;

import com.test.fiction.fictiongame.entity.PlayerScore;

public class MatchTeamDataVO implements Serializable{
	
	private Long teamId;
	
	private List<PlayerScore> playerScoreList;
	
	private Boolean matchWon;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public List<PlayerScore> getPlayerScoreList() {
		return playerScoreList;
	}

	public void setPlayerScoreList(List<PlayerScore> playerScoreList) {
		this.playerScoreList = playerScoreList;
	}

	public Boolean getMatchWon() {
		return matchWon;
	}

	public void setMatchWon(Boolean matchWon) {
		this.matchWon = matchWon;
	}
	
}
