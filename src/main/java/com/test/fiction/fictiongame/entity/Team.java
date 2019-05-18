package com.test.fiction.fictiongame.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.redis.core.RedisHash;

@Entity
@Table(name = "team")
@SequenceGenerator(name = "TEAM_ID_SEQ", sequenceName = "TEAM_ID_SEQ", initialValue = 1, allocationSize = 1)
@RedisHash("teams")
public class Team implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_ID_SEQ")
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="team_player_list",
			joinColumns= { @JoinColumn(referencedColumnName="id", name="team_id") },
			inverseJoinColumns = { @JoinColumn(referencedColumnName="id", name="player_id")})
	private List<Player> players;

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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayerList(List<Player> playerList) {
		this.players = playerList;
	}

	//private List<Match> matchesList;
}
