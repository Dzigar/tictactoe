package com.tictac.repository.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tictac.entity.Game;
import com.tictac.repository.mapper.GameMapper;

public class GameExtractor implements ResultSetExtractor<Collection<Game>>{

	private static volatile GameExtractor instance;
	
	private GameExtractor() {}

	public static GameExtractor getInstance() {
		GameExtractor localInstance = instance;
		if (localInstance == null) {
			synchronized (GameExtractor.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new GameExtractor();
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public Collection<Game> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		Collection<Game> games = new HashSet<>();
		int rowNumber = 0;
		while (resultSet.next()) {
			Game game = GameMapper.getInstance().mapRow(resultSet, rowNumber);
			games.add(game);
			rowNumber++;
		}
		return games;
	}
}
