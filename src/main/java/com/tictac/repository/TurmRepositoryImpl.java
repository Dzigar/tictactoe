package com.tictac.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tictac.entity.Turn;
import com.tictac.repository.extractor.TurnExtractor;

@Repository
public class TurmRepositoryImpl implements TurnRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Turn turn) {
		 jdbcTemplate.update("INSERT INTO turn(game_id, turn_number, position) VALUES (?,?,?)", turn.gameId, turn.turnNumber, turn.position);
	}

	@Override
	public Collection<Turn> findByGameId(Long gameId) {
		Collection<Turn> turns = jdbcTemplate.query("select t.* from turn t where t.game_id = ?", TurnExtractor.getInstance(), gameId);
		return turns;
	}
	
	@Override
	public void deleteByGameId(Long gameId) {
		 jdbcTemplate.update("delete from turn where game_id = ?", gameId);
	}
}
