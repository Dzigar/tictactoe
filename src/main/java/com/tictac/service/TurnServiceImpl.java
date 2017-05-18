package com.tictac.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tictac.component.GameLogic;
import com.tictac.entity.GameStatus;
import com.tictac.entity.Turn;
import com.tictac.repository.TurnRepository;

@Service
public class TurnServiceImpl implements TurnService{

	@Autowired
	private TurnRepository turnRepository;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameLogic gameLogic;
	
	@Override
	public void add(Turn turnDto) {
		turnRepository.save(turnDto);
		gameLogic.checkWinner(gameService.getById(turnDto.gameId));
	}

	@Override
	public Collection<Turn> getTurnsByGameId(Long gameId) {
		return turnRepository.findByGameId(gameId);
	}

	@Override
	public void removeGameTurns(Long gameId) {
		turnRepository.deleteByGameId(gameId);
		gameService.updateStatus(GameStatus.IN_PROGRESS, gameId);
	}
}
