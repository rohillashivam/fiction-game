package com.test.fiction.fictiongame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fiction.fictiongame.entity.Matches;

public interface MatchRepository extends JpaRepository<Matches, Long> {

}
