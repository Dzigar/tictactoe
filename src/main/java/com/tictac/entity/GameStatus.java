package com.tictac.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GameStatus {
	IN_PROGRESS("In Progress"), X_WON("X won"), O_WON("O won"), DRAW("Draw");

	public final String text;

	private GameStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
