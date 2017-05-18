package com.tictac.service;

import java.util.Collection;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;
import com.tictac.entity.Turn;

public interface GameService {

	public Game getById(Long gameId);
	
	public Collection<Game> getAll();
	
	public Game save(Game game);
	
	public void updateStatus(GameStatus gameStatus, Long gameId);
	
	public Game addTurn(Turn turn);
	
	public void removeTurns(Long gameId);
}
