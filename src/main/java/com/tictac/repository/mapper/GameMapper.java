package com.tictac.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;

public class GameMapper implements RowMapper<Game> {

	private static volatile GameMapper instance;
	
	private final String GAME_ID_COLUMN_LABEL = "id";
	private final String GAME_NAME_COLUMN_LABEL = "name";
	private final String GAME_STATUS_COLUMN_LABEL = "status";
	
	private GameMapper() {}

	public static GameMapper getInstance() {
		GameMapper localInstance = instance;
		if (localInstance == null) {
			synchronized (GameMapper.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new GameMapper();
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public Game mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Long id = resultSet.getLong(GAME_ID_COLUMN_LABEL);
		String name = resultSet.getString(GAME_NAME_COLUMN_LABEL);
		GameStatus status = GameStatus.valueOf(resultSet.getString(GAME_STATUS_COLUMN_LABEL));
		return new Game(id, name, status);
	}

}
