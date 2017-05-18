package com.tictac.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;


public class Turn implements Serializable, Comparable<Turn> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 707559601060974395L;
	
	public Long id;
	public Long gameId;
	public Integer turnNumber;
	public Integer position;
	
	public Turn() {
	}

	public Turn(Long id, Long gameId, Integer turnNumber, Integer position) {
		this.id = id;
		this.gameId = gameId;
		this.turnNumber = turnNumber;
		this.position = position;
	}
	
	@Override
	public int compareTo(Turn o) {
		return this.id.compareTo(o.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj instanceof Turn) {
			Turn turn = (Turn) obj;
			return new EqualsBuilder().append(id, turn.id).append(gameId, turn.gameId)
					.append(turnNumber, turn.turnNumber).append(position, turn.position).isEquals();
		}
		return false;
	}
}
