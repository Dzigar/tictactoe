package com.tictac.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;
import com.tictac.entity.Turn;
import com.tictac.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GameRESTController {
	
	@Autowired
	private GameService gameService;
	
	//-------------------Retrieve All Games--------------------------------------------------------
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Game>> getAll() {
		Collection<Game> games = gameService.getAll();
		return new ResponseEntity<Collection<Game>>(games, HttpStatus.OK);
	}
	
	//-------------------Retrieve single Game--------------------------------------------------------
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Game> getById(@PathVariable Long id) {
		Game game = gameService.getById(id);
		return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	
	//-------------------Create a Game--------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.PUT)
	public ResponseEntity<Game> createGame(@RequestParam String name) {
		Game g = gameService.save(new Game(name, GameStatus.IN_PROGRESS));
		return new ResponseEntity<Game>(g, HttpStatus.CREATED);
	}
	
	//-------------------Create Game's turn--------------------------------------------------------
	
	@RequestMapping(value="/add-turn", method = RequestMethod.PUT)
	public ResponseEntity<GameStatus> addTurn(@RequestBody Turn turn) {
		Game game = gameService.addTurn(turn);
		return new ResponseEntity<GameStatus>(game.status, HttpStatus.CREATED);
	}
	
	//-------------------Remove all Game's turns--------------------------------------------------------
	
	@RequestMapping(value="/{gameId}/remove-turns", method = RequestMethod.GET)
	public ResponseEntity<Void> removeGamesTurns(@PathVariable Long gameId) {
		gameService.removeTurns(gameId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
