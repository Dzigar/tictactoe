package com.tictac.repository.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tictac.entity.Turn;
import com.tictac.repository.mapper.TurnMapper;

public class TurnExtractor implements ResultSetExtractor<Collection<Turn>>{

private static volatile TurnExtractor instance;
	
	private TurnExtractor() {}

	public static TurnExtractor getInstance() {
		TurnExtractor localInstance = instance;
		if (localInstance == null) {
			synchronized (TurnExtractor.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new TurnExtractor();
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public Collection<Turn> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		Collection<Turn> turns = new ArrayList<>();
		int rowNumber = 0;
		while (resultSet.next()) {
			Turn turn = TurnMapper.getInstance().mapRow(resultSet, rowNumber);
			turns.add(turn);
			rowNumber++;
		}
		return turns;
	}

}
