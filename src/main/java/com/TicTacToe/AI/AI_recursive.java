package com.TicTacToe.AI;

import com.TicTacToe.Board;
import com.TicTacToe.Tuple;

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
public class AI_recursive extends AI {

    public AI_recursive(int turn) {
	super(turn);
	errorChance = 0.05;
	shuffleChance = 0.30;
    }

    @Override
    public Tuple<Double, Double, Double> simulate(Board board, int turn) {
	int gameResult = board.checkResult();
	Tuple<Double, Double, Double> finalResult = new Tuple<Double, Double, Double>(0.0, 0.0, 0.0);
	if (gameResult == 0) {
	    // Game is not finished, continue the recursion
	    for (int i = 1; i <= 3; i++)
		for (int j = 1; j <= 3; j++) {
		    if (board.getValue(i, j) == 0) {

			board.activate(i, j, turn);
			Tuple<Double, Double, Double> res = simulate(board, nextTurn(turn));
			board.deactivate(i, j);

			finalResult.x += res.x;
			finalResult.y += res.y;
			finalResult.z += res.z;
		    }
		}

	    return finalResult; // Return the final sum
	} else if (gameResult == 3) {

	    return new Tuple<Double, Double, Double>(0.0, 0.0, 1.0); // Game finished with a draw. Return 1 point
	} else {

	    if (gameResult == myNum)
		return new Tuple<Double, Double, Double>(1.0, 0.0, 0.0); // Game finished with a win. Return 2 points

	    else
		return new Tuple<Double, Double, Double>(0.0, 1.0, 0.0); // Game finished with a lose. Return 0 points
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
