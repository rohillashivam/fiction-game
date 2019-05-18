package com.test.fiction.fictiongame.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "player_score_agg")
public class PlayerAggregate implements Serializable {

	@JsonIgnore
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name="total_num_of_attempt_attack")
	private Long totalNumberOfAttemptedAttack = 0l;
	
	@Column(name="highest_num_attempted_attacks_match")
	private Long highestNumberOfAttemptedAttacksInMatch = 0l;
	
	@Column(name="lowest_num_attempted_attacks_match")
	private Long lowestNumberOfAttemptedAttacksInMatch = 0l;
	
	@Column(name="total_num_hits")
	private Long totalNumberOfHits = 0l;
	
	@Column(name="highest_num_hits_match")
	private Long highestNumberOfHitInMatch = 0l;
	
	@Column(name="lowest_num_hits_match")
	private Long lowestNumberOfHitInMatch = 0l;
	
	@Column(name="total_num_kills")
	private Long totalNumberOfKills = 0l;
	
	@Column(name="highest_num_kills_match")
	private Long highestNumOfKillsInMatch = 0l;
	
	@Column(name="lowest_num_kills_match")
	private Long lowestNumOfKillsInMatch = 0l;
	
	@Column(name="total_num_first_hit_kills")
	private Long totalNumberOfFirstHitKills = 0l;
	
	@Column(name="highest_num_first_hit_kills_match")
	private Long highestNumOfFirstHitKillsInMatch = 0l;

	@Column(name="lowest_num_first_hit_kills_match")
	private Long lowestNumOfFirstHitKillsInMatch = 0l;
	
	@Column(name="total_num_assists")
	private Long totalNumberOfAssists = 0l;
	
	@Column(name = "highest_num_assists_match")
	private Long highestNumberOfAssistInMatch = 0l;
	
	@Column(name = "lowest_num_assists_match")
	private Long lowestNumberOfAssistInMatch = 0l;
	
	@Column(name="total_num_of_spell_casts")
	private Long totalNumberOfSpellCasts = 0l;
	
	@Column(name="total_num_of_spell_damage_done")
	private Long totalNumberOfSpellDamageDone = 0l;
	
	@Column(name="total_time_played")
	private Long totalTimePlayed = 0l;
	
	@Column(name="highest_time_played_match")
	private Long highestTimePlayedInMatch = 0l;
	
	@Column(name="lowest_time_played_match")
	private Long lowestTimePlayedInMatch = 0l;
	
	@Column(name="total_num_wins")
	private Long totalNumberOfWins = 0l;
	
	@Column(name="total_num_lost")
	private Long totalNumberOfLost = 0l;
	
	@Column(name="total_num_game_played")
	private Long totalNumberOfGamesPlayed = 0l;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="player_id", unique = true, 
		referencedColumnName="id")
	private Player player;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getTotalNumberOfAttemptedAttack() {
		return totalNumberOfAttemptedAttack;
	}

	public void setTotalNumberOfAttemptedAttack(Long totalNumberOfAttemptedAttack) {
		this.totalNumberOfAttemptedAttack = totalNumberOfAttemptedAttack;
	}

	public Long getTotalNumberOfHits() {
		return totalNumberOfHits;
	}

	public void setTotalNumberOfHits(Long totalNumberOfHits) {
		this.totalNumberOfHits = totalNumberOfHits;
	}

	public Long getTotalNumberOfKills() {
		return totalNumberOfKills;
	}

	public void setTotalNumberOfKills(Long totalNumberOfKills) {
		this.totalNumberOfKills = totalNumberOfKills;
	}

	public Long getTotalNumberOfFirstHitKills() {
		return totalNumberOfFirstHitKills;
	}

	public void setTotalNumberOfFirstHitKills(Long totalNumberOfFirstHitKills) {
		this.totalNumberOfFirstHitKills = totalNumberOfFirstHitKills;
	}

	public Long getTotalNumberOfAssists() {
		return totalNumberOfAssists;
	}

	public void setTotalNumberOfAssists(Long totalNumberOfAssists) {
		this.totalNumberOfAssists = totalNumberOfAssists;
	}

	public Long getTotalNumberOfSpellCasts() {
		return totalNumberOfSpellCasts;
	}

	public void setTotalNumberOfSpellCasts(Long totalNumberOfSpellCasts) {
		this.totalNumberOfSpellCasts = totalNumberOfSpellCasts;
	}

	public Long getTotalNumberOfSpellDamageDone() {
		return totalNumberOfSpellDamageDone;
	}

	public void setTotalNumberOfSpellDamageDone(Long totalNumberOfSpellDamageDone) {
		this.totalNumberOfSpellDamageDone = totalNumberOfSpellDamageDone;
	}

	public Long getTotalTimePlayed() {
		return totalTimePlayed;
	}

	public void setTotalTimePlayed(Long totalTimePlayed) {
		this.totalTimePlayed = totalTimePlayed;
	}

	public Long getTotalNumberOfWins() {
		return totalNumberOfWins;
	}

	public void setTotalNumberOfWins(Long totalNumberOfWins) {
		this.totalNumberOfWins = totalNumberOfWins;
	}

	public Long getTotalNumberOfLost() {
		return totalNumberOfLost;
	}

	public void setTotalNumberOfLost(Long totalNumberOfLost) {
		this.totalNumberOfLost = totalNumberOfLost;
	}

	public Long getTotalNumberOfGamesPlayed() {
		return totalNumberOfGamesPlayed;
	}

	public void setTotalNumberOfGamesPlayed(Long totalNumberOfGamesPlayed) {
		this.totalNumberOfGamesPlayed = totalNumberOfGamesPlayed;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Long getHighestNumberOfAttemptedAttacksInMatch() {
		return highestNumberOfAttemptedAttacksInMatch;
	}

	public void setHighestNumberOfAttemptedAttacksInMatch(Long highestNumberOfAttemptedAttacksInMatch) {
		this.highestNumberOfAttemptedAttacksInMatch = highestNumberOfAttemptedAttacksInMatch;
	}

	public Long getHighestNumberOfHitInMatch() {
		return highestNumberOfHitInMatch;
	}

	public void setHighestNumberOfHitInMatch(Long highestNumberOfHitInMatch) {
		this.highestNumberOfHitInMatch = highestNumberOfHitInMatch;
	}

	public Long getHighestNumOfKillsInMatch() {
		return highestNumOfKillsInMatch;
	}

	public void setHighestNumOfKillsInMatch(Long highestNumOfKillsInMatch) {
		this.highestNumOfKillsInMatch = highestNumOfKillsInMatch;
	}

	public Long getHighestNumOfFirstHitKillsInMatch() {
		return highestNumOfFirstHitKillsInMatch;
	}

	public void setHighestNumOfFirstHitKillsInMatch(Long highestNumOfFirstHitKillsInMatch) {
		this.highestNumOfFirstHitKillsInMatch = highestNumOfFirstHitKillsInMatch;
	}

	public Long getLowestNumberOfAttemptedAttacksInMatch() {
		return lowestNumberOfAttemptedAttacksInMatch;
	}

	public void setLowestNumberOfAttemptedAttacksInMatch(Long lowestNumberOfAttemptedAttacksInMatch) {
		this.lowestNumberOfAttemptedAttacksInMatch = lowestNumberOfAttemptedAttacksInMatch;
	}

	public Long getLowestNumberOfHitInMatch() {
		return lowestNumberOfHitInMatch;
	}

	public void setLowestNumberOfHitInMatch(Long lowestNumberOfHitInMatch) {
		this.lowestNumberOfHitInMatch = lowestNumberOfHitInMatch;
	}

	public Long getLowestNumOfKillsInMatch() {
		return lowestNumOfKillsInMatch;
	}

	public void setLowestNumOfKillsInMatch(Long lowestNumOfKillsInMatch) {
		this.lowestNumOfKillsInMatch = lowestNumOfKillsInMatch;
	}

	public Long getLowestNumOfFirstHitKillsInMatch() {
		return lowestNumOfFirstHitKillsInMatch;
	}

	public void setLowestNumOfFirstHitKillsInMatch(Long lowestNumOfFirstHitKillsInMatch) {
		this.lowestNumOfFirstHitKillsInMatch = lowestNumOfFirstHitKillsInMatch;
	}

	public Long getLowestNumberOfAssistInMatch() {
		return lowestNumberOfAssistInMatch;
	}

	public void setLowestNumberOfAssistInMatch(Long lowestNumberOfAssistInMatch) {
		this.lowestNumberOfAssistInMatch = lowestNumberOfAssistInMatch;
	}

	public Long getHighestTimePlayedInMatch() {
		return highestTimePlayedInMatch;
	}

	public void setHighestTimePlayedInMatch(Long highestTimePlayedInMatch) {
		this.highestTimePlayedInMatch = highestTimePlayedInMatch;
	}

	public Long getLowestTimePlayedInMatch() {
		return lowestTimePlayedInMatch;
	}

	public void setLowestTimePlayedInMatch(Long lowestTimePlayedInMatch) {
		this.lowestTimePlayedInMatch = lowestTimePlayedInMatch;
	}

	public Long getHighestNumberOfAssistInMatch() {
		return highestNumberOfAssistInMatch;
	}

	public void setHighestNumberOfAssistInMatch(Long highestNumberOfAssistInMatch) {
		this.highestNumberOfAssistInMatch = highestNumberOfAssistInMatch;
	}
	
}
