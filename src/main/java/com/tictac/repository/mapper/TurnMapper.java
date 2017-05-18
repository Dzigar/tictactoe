package com.tictac.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tictac.entity.Turn;

public class TurnMapper implements RowMapper<Turn> {

	private static volatile TurnMapper instance;

	private final String TURN_ID_COLUMN_LABEL = "turn_id";
	private final String GAME_ID_COLUMN_LABEL = "game_id";
	private final String TURN_NUMBER_COLUMN_LABEL = "turn_number";
	private final String TURN_POSITION_COLUMN_LABEL = "position";

	private TurnMapper() {
	}

	public static TurnMapper getInstance() {
		TurnMapper localInstance = instance;
		if (localInstance == null) {
			synchronized (TurnMapper.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new TurnMapper();
				}
			}
		}
		return localInstance;
	}

	@Override
	public Turn mapRow(ResultSet resultSet, int numberRow) throws SQLException {
		Long turnId = resultSet.getLong(TURN_ID_COLUMN_LABEL);
		Long gameId = resultSet.getLong(GAME_ID_COLUMN_LABEL);
		Integer turnNumber = resultSet.getInt(TURN_NUMBER_COLUMN_LABEL);
		Integer position = resultSet.getInt(TURN_POSITION_COLUMN_LABEL);
		
		return new Turn(turnId, gameId, turnNumber, position);
	}

}
