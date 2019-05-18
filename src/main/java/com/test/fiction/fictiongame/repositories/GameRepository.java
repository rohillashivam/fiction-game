package com.test.fiction.fictiongame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fiction.fictiongame.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
	
}
