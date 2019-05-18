package com.test.fiction.fictiongame.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Entity
@Table(name="matches")
@SequenceGenerator(name = "MATCH_ID_SEQ", sequenceName = "MATCH_ID_SEQ", initialValue = 1, allocationSize = 1)
@RedisHash("matches")
public class Matches implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATCH_ID_SEQ")
	@Indexed
	private Long id;
	
	@Column(name="scheduled_at")
	private Date scheduledAt;
	
	@ManyToOne
	@JoinColumn(name="game_id", nullable = false, 
		referencedColumnName="id")
	private Game game;
	
	@ManyToOne
	@JoinColumn(name="winning_team_id", nullable = false,
			referencedColumnName = "id")
	private Team winningTeam;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="player_score_id")
	private PlayerScore playerScoreList;*/
	
	/*@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	private List<Team> teamList;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getScheduledAt() {
		return scheduledAt;
	}

	@PrePersist
	protected void onCreate() {
		scheduledAt = new Date();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Team getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(Team winningTeam) {
		this.winningTeam = winningTeam;
	}

	/*public PlayerScore getPlayerScoreList() {
		return playerScoreList;
	}

	public void setPlayerScoreList(PlayerScore playerScoreList) {
		this.playerScoreList = playerScoreList;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}*/


}
