package com.TicTacToe;

/**
 * 
 * @author Denis Rozimovschii From all the possible moves, selects the one with
 *         the highest ratio (2*wins + draws)/games Where games is the number of
 *         possible games from that move Analyzes recursively all the available
 *         moves
 * 
 *         There is a fixed chance from the program to make a mistake
 *         (errorChance) And there is a fixed chance to change the move in case
 *         the ratios are equal (shuffleChance)
 */
public class AI_recursive {
	private Board b;
	private int myNum; //1 or 2. Represents the program's turn in the game
	private double errorChance = 0.05;
	private double shuffleChance = 0.15;

	public AI_recursive(int turn) {
		myNum = turn;
		b = new Board();
	}

	public Pair makeMove(Board board, int myNum) {
		b.setBoard(board.getBoard());
		b.setTotalCells(board.getTotalCells());
		if (b.checkResult() > 0)
			return null;
		Pair bestPos = new Pair();
		double bestVal = -1;

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				if (b.getValue(i, j) == 0) {
					b.activate(i, j, myNum);
					Pair res = simulate(b, nextTurn(myNum));
					b.deactivate(i, j);

					double tempVal = (double) res.x / (double) res.y;
					if (tempVal > bestVal) {
						bestVal = tempVal;
						bestPos.x = i;
						bestPos.y = j;
					} else if (tempVal == bestVal) {
						if (Math.random() < shuffleChance) {
							bestVal = tempVal;
							bestPos.x = i;
							bestPos.y = j;
						}
					} else {
						if (Math.random() < errorChance) {
							bestVal = tempVal;
							bestPos.x = i;
							bestPos.y = j;
						}
					}
				}
			}
		}
		return bestPos;
	}

	public Pair simulate(Board board, int turn) {
		int gameResult = board.checkResult();
		Pair finalResult = new Pair(0, 0);
		if (gameResult == 0) {
			// Game is not finished, continue the recursion
			for (int i = 1; i <= 3; i++)
				for (int j = 1; j <= 3; j++) {
					if (board.getValue(i, j) == 0) {

						board.activate(i, j, turn);
						Pair res = simulate(board, nextTurn(turn));
						board.deactivate(i, j);

						finalResult.x += res.x;
						finalResult.y += res.y;
					}
				}

			return finalResult; // Return the final sum
		} else if (gameResult == 3) {

			return new Pair(1, 1); // Game finished with a draw. Return 1 point
		} else {

			if (gameResult == myNum)
				return new Pair(2, 1); // Game finished with a win. Return 2 points

			else
				return new Pair(0, 1); // Game finished with a lose. Return 0 points
		}
	}
	
	public int nextTurn(int i) {
		return (i == 1) ? 2 : 1;
	}

	public int getMyNum() {
		return myNum;
	}

	public void setMyNum(int myNum) {
		this.myNum = myNum;
	}

}
