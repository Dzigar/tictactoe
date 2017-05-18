package com.tictac.repository;

import java.util.Collection;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;

public interface GameRepository {

	/**
	 * Save Game instance into database
	 * @param game - instance of Game
	 */
	public void save(Game game);
	
	/**
	 * Update Game status 
	 * @param game - instance of Game
	 */
	public void updateStatus(Long gameId, GameStatus gameStatus);
	

	/**
	 * Find Game by id
	 * @return Game by id
	 */
	public Game findById(Long id);
	
	
	/**
	 * Find all Game
	 * @return Collection of Game
	 */
	public Collection<Game> findAll();
	
	/**
	 * Find last Game 
	 * @return last Game in table
	 */
	public Game findLast();
}
