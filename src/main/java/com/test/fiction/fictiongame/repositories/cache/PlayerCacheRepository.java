package com.test.fiction.fictiongame.repositories.cache;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.test.fiction.fictiongame.entity.cache.PlayerData;

public interface PlayerCacheRepository extends 
	CrudRepository<PlayerData, Long>, 
	QueryByExampleExecutor<PlayerData> {

	Page<PlayerData> findByPlayerId(Long id, Pageable page);
	
	Optional<PlayerData> findByIdAndMatchesId(Long id, Long matchId);
	
	List<PlayerData> findByPlayerId(Long id);
}
