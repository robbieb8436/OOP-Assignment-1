package org.uob.a1;

import java.util.Scanner; 

public class Room {
	/*
	The Room class stores information about a Room, including a name, description, a
	symbol and a Position. The required methods are:
		• public Room(String name, String description, char symbol, Position
			position)
		• public String getName()
		• public String getDescription()
		• public char getSymbol()
		• public Position getPosition()
		The symbol is used when displaying the room on the map.
	*/

	// attributes
	private String name;
	private String description;
	private char symbol;
	private Position position;

	// constructor
	public Room(String name, String description, char symbol, Position position) {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
	}

	// getters and setters
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}

	public char getSymbol() {
		return this.symbol;
	}

	public Position getPosition() {
		return this.position;
	}

	// methods
	
}