package org.uob.a1;

public class Score {
	/*
	The Score class stores and calculates the player’s score. It includes the starting score,
	the current score, the number of rooms visited, the number of puzzles solved and the
	score per puzzle. The required methods are:
		• public Score(int startingScore)
		• public void visitRoom()
		• public void solvePuzzle()
		• public double getScore() Calculates and returns the current score. The
			score is calculated as the starting score minus the number of rooms visited plus
			the number of solved puzzles times the score per puzzle.
	Declare the score per puzzle as follows: private final int PUZZLE_VALUE = 10;
	*/

	// attributes
	private int startingScore;
	private double currentScore;
	private int roomsVisited;
	private int numPuzzles;
	private final int PUZZLE_VALUE = 10;

	// constructor
	public Score(int startingScore) {
		this.startingScore = startingScore;
	}

	// methods
	public void visitRoom() {
		// update the number of rooms visited
        roomsVisited += 1;
	}

	public void solvePuzzle() {
		// update the number of puzzles solved
        numPuzzles += 1;
	}

	public double getScore() {
		// calculate the score
        currentScore = (startingScore - roomsVisited) + (numPuzzles * PUZZLE_VALUE);
		return currentScore;
	}
}