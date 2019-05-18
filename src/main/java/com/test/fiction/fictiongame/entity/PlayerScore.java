package com.test.fiction.fictiongame.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="player_score")
//@SequenceGenerator(name = "PLAYER_SCORE_ID_SEQ", sequenceName = "PLAYER_SCORE_ID_SEQ", initialValue = 1, allocationSize = 1)
public class PlayerScore implements Serializable {

	@NaturalId
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name="num_attempt_attacks")
	private Integer numOfAttemptedAttack;
	
	@Column(name="num_hits")
	private Integer numOfHits;
	
	@Column(name="num_kills")
	private Integer numOfKills;
	
	@Column(name="num_first_hit_kills")
	private Integer numOfFirstHitKills;
	
	@Column(name="num_assists")
	private Integer numOfAssists;
	
	@Column(name="num_spell_cast")
	private Integer numOfSpellCast;
	
	@Column(name="spell_damage_done")
	private Integer spellDamageDone;
	
	@Column(name="time_played")
	private Long timePlayed;
	
	@Column(name="played_date")
	private Date playedOn;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private Match match;
	
	@ManyToOne(fetch = FetchTyae.EAGER)
	@JoinColumn(name = "id")
	private Player player;*/
	
	@Transient
	private Long playerId;
	
	@Transient
	private Boolean matchWon;
	
	@EmbeddedId
	private PlayerMatchEnitity playerMatchEntity;

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public Integer getNumOfAttemptedAttack() {
		return numOfAttemptedAttack;
	}

	public void setNumOfAttemptedAttack(Integer numOfAttemptedAttack) {
		this.numOfAttemptedAttack = numOfAttemptedAttack;
	}

	public Integer getNumOfHits() {
		return numOfHits;
	}

	public void setNumOfHits(Integer numOfHits) {
		this.numOfHits = numOfHits;
	}

	public Integer getNumOfKills() {
		return numOfKills;
	}

	public void setNumOfKills(Integer numOfKills) {
		this.numOfKills = numOfKills;
	}

	public Integer getNumOfFirstHitKills() {
		return numOfFirstHitKills;
	}

	public void setNumOfFirstHitKills(Integer numOfFirstHitKills) {
		this.numOfFirstHitKills = numOfFirstHitKills;
	}

	public Integer getNumOfAssists() {
		return numOfAssists;
	}

	public void setNumOfAssists(Integer numOfAssists) {
		this.numOfAssists = numOfAssists;
	}

	public Integer getNumOfSpellCast() {
		return numOfSpellCast;
	}

	public void setNumOfSpellCast(Integer numOfSpellCast) {
		this.numOfSpellCast = numOfSpellCast;
	}

	public Integer getSpellDamageDone() {
		return spellDamageDone;
	}

	public void setSpellDamageDone(Integer spellDamageDone) {
		this.spellDamageDone = spellDamageDone;
	}

	public Long getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(Long timePlayed) {
		this.timePlayed = timePlayed;
	}

	public PlayerMatchEnitity getPlayerMatchEntity() {
		return playerMatchEntity;
	}

	public void setPlayerMatchEntity(PlayerMatchEnitity playerMatchEntity) {
		this.playerMatchEntity = playerMatchEntity;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Date getPlayedOn() {
		return playedOn;
	}

	@PrePersist
	public void playedOn() {
		this.playedOn = new Date();
	}

	public Boolean getMatchWon() {
		return matchWon;
	}

	public void setMatchWon(Boolean matchWon) {
		this.matchWon = matchWon;
	}
	
}
