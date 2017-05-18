package com.tictac.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6158002518896244479L;
	
	public Long id;
	public String name;
	public GameStatus status;
	public Collection<Turn> turns = new HashSet<>();
	
	public Game() {
	}
	
	public Game(String name, GameStatus status) {
		this.name = name;
		this.status = status;
	}

	public Game(Long id, String name, GameStatus status) {
		this(name, status);
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public GameStatus getStatus() {
		return status;
	}
}
