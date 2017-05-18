package com.tictac.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tictac.entity.Game;
import com.tictac.entity.GameStatus;
import com.tictac.entity.Turn;
import com.tictac.service.GameService;

@Component
public class GameLogic {

	@Autowired
	private GameService gameService;
	
	private final List<List<Integer>> wCombination = getWinnersCombinations();
	
	public GameStatus checkWinner(Game game) {
		GameStatus status = executeLogicWinner(game.turns);
		if (!GameStatus.IN_PROGRESS.equals(status))
			gameService.updateStatus(status, game.id);
		return status;
	}
	
	private GameStatus executeLogicWinner(Collection<Turn> turns) {
		GameStatus status = GameStatus.IN_PROGRESS;
		if (isWinner(turns, true)) {
			status = GameStatus.X_WON;
		} else if (isWinner(turns, false)) {
			status = GameStatus.O_WON;
		} else if (turns.size() == 9) {
			status = GameStatus.DRAW;
		}
		return status;
	}
	
	private boolean isWinner(Collection<Turn> turns, boolean tictac) {
		List<Integer> positions = getPositions(turns, tictac);
		for (List<Integer> combination : wCombination) {
			int n = 0;
			for (Integer i : combination) {
				if (positions.contains(i)) {
					n++;
				} else 
					break;
			}
			if (n == 3) 
				return true;
		}
		return false;
	}
	
	private List<Integer> getPositions(Collection<Turn> turns, boolean tictac) {
		List<Integer> positions = new ArrayList<Integer>();
		for(Turn t : turns) {
			if (tictac ? (t.turnNumber % 2) == 1 : (t.turnNumber % 2) == 0) {
				positions.add(t.position);
			}
		}
		return positions;
	}
	
	private List<List<Integer>> getWinnersCombinations() {
		List<List<Integer>> wCombination = new ArrayList<List<Integer>>();
		wCombination.add(Arrays.asList(1,2,3));
		wCombination.add(Arrays.asList(4,5,6));
		wCombination.add(Arrays.asList(7,8,9));
		wCombination.add(Arrays.asList(1,4,7));
		wCombination.add(Arrays.asList(2,5,8));
		wCombination.add(Arrays.asList(3,6,9));
		wCombination.add(Arrays.asList(1,5,9));
		wCombination.add(Arrays.asList(3,5,7));
		return wCombination;
	}
}