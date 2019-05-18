package com.test.fiction.fictiongame.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.fiction.fictiongame.event.enums.Award;

@Entity
@Table(name = "player")
@SequenceGenerator(name = "PLAYER_ID_SEQ", sequenceName = "PLAYER_ID_SEQ", initialValue = 1, allocationSize = 1)
public class Player implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_ID_SEQ")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@JsonIgnore
	@Column(name="modified_at")
	private Date modifiedAt;
	
	/*@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="player_achievement",
			joinColumns= { @JoinColumn(referencedColumnName="id", name="player_id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName="id", name="award_id")
				}
	)*/
	
	@ElementCollection(targetClass=Award.class)
	@CollectionTable(name="TBL_GAME_AWARDS",
			joinColumns= @JoinColumn(name="player_id"))
	@Enumerated(EnumType.STRING)
	@Column(name="award_id")
	private List<Award> achievementList;
	
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

	/*public List<Awards> getAchievementList() {
		return achievementList;
	}

	public void setAchievementList(List<Awards> achievementList) {
		this.achievementList = achievementList;
	}*/

	public Date getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	protected void createdOn() {
		this.createdAt = new Date();
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	@PreUpdate
	protected void modifiedOn() {
		this.modifiedAt = new Date();
	}

	public List<Award> getAchievementList() {
		return achievementList;
	}

	public void setAchievementList(List<Award> achievementList) {
		this.achievementList = achievementList;
	}

	/*public List<Match> getGamePlayedList() {
		return gamePlayedList;
	}

	public void setGamePlayedList(List<Match> gamePlayedList) {
		this.gamePlayedList = gamePlayedList;
	}*/
	
}
