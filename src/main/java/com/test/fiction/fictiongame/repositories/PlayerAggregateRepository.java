package com.test.fiction.fictiongame.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fiction.fictiongame.entity.PlayerAggregate;

public interface PlayerAggregateRepository extends JpaRepository<PlayerAggregate, UUID> {

	Optional<PlayerAggregate> findByPlayerId(Long playerId);
}
