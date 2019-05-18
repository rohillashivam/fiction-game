package com.test.fiction.fictiongame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fiction.fictiongame.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	
}
