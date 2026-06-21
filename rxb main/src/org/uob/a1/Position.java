package org.uob.a1;

public class Position {
	/*
	The Position class stores a position in terms of an x and y value. The required methods
	are:
		• public Position(int x, int y)
	The x and y fields need to be declared as public so that other classes can access
	them directly.
	*/

	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
        this.y = y;
	}
}