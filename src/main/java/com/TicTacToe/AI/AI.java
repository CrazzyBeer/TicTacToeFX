package com.TicTacToe.AI;

import com.TicTacToe.Board;
import com.TicTacToe.Pair;
import com.TicTacToe.Tuple;

public class AI {
    protected Board b;
    protected int myNum; // 1 or 2. Represents the program's turn in the game
    protected double errorChance = 0.00;
    protected double shuffleChance = 0.25;

    public AI(int turn) {
	myNum = turn;
	b = new Board();
    }

    public Pair<Integer, Integer> makeMove(Board board, int myNum) {
	b.setBoard(board.getBoard());
	b.setTotalCells(board.getTotalCells());
	if (b.checkResult() > 0)
	    return null;
	Pair<Integer, Integer> bestPos = new Pair<Integer, Integer>();
	Tuple<Double, Double, Double> bestRes = new Tuple<Double, Double, Double>(-1.0, -1.0, -1.0);

	for (int i = 1; i <= 3; i++) {
	    for (int j = 1; j <= 3; j++) {
		if (b.getValue(i, j) == 0) {
		    b.activate(i, j, myNum);
		    Tuple<Double, Double, Double> res = simulate(b, myNum);
		    b.deactivate(i, j);

		    // System.out.printf("Move to %d %d ? - chance %f/%f \n", i, j, res.x, res.y);
		    if (Math.random() < errorChance) {
			bestRes = res;
			bestPos.x = i;
			bestPos.y = j;
		    } else if (res.x > bestRes.x) {
			bestRes = res;
			bestPos.x = i;
			bestPos.y = j;
		    } else if (res.x == bestRes.x && res.z > bestRes.z) {
			if (Math.random() < shuffleChance) {
			    bestRes = res;
			    bestPos.x = i;
			    bestPos.y = j;
			}
		    }
		}
	    }
	}
	// System.out.printf("Move to %d %d - chance %f \n\n", bestPos.x, bestPos.y, bestVal);
	return bestPos;
    }

    public Tuple<Double, Double, Double> simulate(Board board, int turn) {
	return null;
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
