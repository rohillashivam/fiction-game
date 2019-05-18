package com.test.fiction.fictiongame.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.fiction.fictiongame.entity.PlayerMatchEnitity;
import com.test.fiction.fictiongame.entity.PlayerScore;

public interface PlayerScoreRepository extends JpaRepository<PlayerScore, PlayerMatchEnitity> {

	@Query("SELECT ps from PlayerScore ps WHERE ps.playerMatchEntity.playerId = :playerId")
	List<PlayerScore> findByPlayerId(Long playerId, Pageable page);
	
	@Query("SELECT ps from PlayerScore ps WHERE ps.playerMatchEntity.playerId = :playerId AND ps.playerMatchEntity.matchId = :matchId")
	PlayerScore findByPlayerIdAndMatchId(Long playerId, Long matchId);
	
	@Query("SELECT ps from PlayerScore ps WHERE ps.playerMatchEntity.playerId = :playerId")
	List<PlayerScore> findByPlayerId(Long playerId);
}
