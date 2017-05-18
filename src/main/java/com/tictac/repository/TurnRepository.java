package com.tictac.repository;

import java.util.Collection;

import com.tictac.entity.Turn;

public interface TurnRepository {

	/**
	 * Save Turn instance into database 
	 * @param turn - instance of {@link TurnDto} 
	 */
	public void save(Turn turn);
	
	/**
	 * Find Turns by Game id
	 * @param gameId - Game id
	 * @return Collection of Turn
	 */
	public Collection<Turn> findByGameId(Long gameId);
	
	/**
	 * Remove all Turns from database by Game id
	 * @param gameId - Game id
	 */
	public void deleteByGameId(Long gameId);
}
