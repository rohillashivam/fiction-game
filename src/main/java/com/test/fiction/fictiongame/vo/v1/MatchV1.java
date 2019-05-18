package com.test.fiction.fictiongame.vo.v1;

import java.io.Serializable;
import java.util.List;

import com.test.fiction.fictiongame.vo.v1.dataObject.MatchTeamDataVO;

public class MatchV1 implements Serializable {
	
	private Long gameId;

	private List<MatchTeamDataVO> teamMatchData;

	public List<MatchTeamDataVO> getTeamMatchData() {
		return teamMatchData;
	}

	public void setTeamMatchData(List<MatchTeamDataVO> teamMatchData) {
		this.teamMatchData = teamMatchData;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
}
