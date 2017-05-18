package com.tictac.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;
import com.tictac.entity.Turn;
import com.tictac.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private TurnService turnService;
	
	@Override
	public Game getById(Long gameId) {
		Game game = gameRepository.findById(gameId);
		game.turns = turnService.getTurnsByGameId(gameId);
		return game;
	}
	
	@Override
	public Collection<Game> getAll() {
		return gameRepository.findAll();
	}
	
	@Override
	public Game save(Game game) {
		gameRepository.save(game);
		return gameRepository.findLast();
	}
	
	@Override
	public void updateStatus(GameStatus gameStatus, Long gameId) {
		gameRepository.updateStatus(gameId, gameStatus);
	}
	
	@Override
	public Game addTurn(Turn turn) {
		turnService.add(turn);
		return getById(turn.gameId);
	}

	@Override
	public void removeTurns(Long gameId) {
		turnService.removeGameTurns(gameId);
	}
}
