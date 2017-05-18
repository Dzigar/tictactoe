package com.tictac.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;
import com.tictac.repository.extractor.GameExtractor;
import com.tictac.repository.mapper.GameMapper;

@Repository
public class GameRepositoryImpl implements GameRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Game game) {
        jdbcTemplate.update("INSERT INTO game(name, status) VALUES (?,?)", game.name, game.status.name());
	}
	
	@Override
	public void updateStatus(Long gameId, GameStatus status) {
		jdbcTemplate.update("UPDATE game SET status=? WHERE id=?", status.name(), gameId);
	}

	@Override
	public Game findLast() {
		return jdbcTemplate.queryForObject("select * from game g where g.id = (select max(id) FROM game)", GameMapper.getInstance());
	}
	
	@Override
	public Game findById(Long id) {
		return jdbcTemplate.queryForObject("select * from game g where g.id = ?", GameMapper.getInstance(), id);
	}

	@Override
	public Collection<Game> findAll() {
		return jdbcTemplate.query("select * from game", GameExtractor.getInstance());
	}
}
