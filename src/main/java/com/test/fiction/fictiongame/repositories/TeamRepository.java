package com.test.fiction.fictiongame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fiction.fictiongame.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	
	public List<Team> findByPlayers_Id(Long playerId);

}
