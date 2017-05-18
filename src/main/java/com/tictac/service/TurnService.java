package com.tictac.service;

import java.util.Collection;

import com.tictac.entity.Turn;

public interface TurnService {

	public void add(Turn turnDto);
	
	public Collection<Turn> getTurnsByGameId(Long gameId);

	public void removeGameTurns(Long gameId);

}
