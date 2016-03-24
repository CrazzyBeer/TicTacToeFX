package com.TicTacToe.AI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

import com.TicTacToe.Helper_Classes.Board;
import com.TicTacToe.Helper_Classes.Logger;
import com.TicTacToe.Helper_Classes.Pair;

/**
 * Selects the best move with the highest (winPoints + drawPoints)/games,
 * If the program can win or lose in one move - it is forces to make or, respectively, avoid that move
 * Quite weak against blunders
 * @author Denis Rozimovschii
 *
 */
public class AI {
    protected Board b;
    protected int myNum; // 1 or 2. Represents the program's turn in the game
    protected double errorChance = 0.00;
    protected double shuffleChance = 0.25;

    public AI(int turn) {
	myNum = turn;
	b = new Board();
	read();
    }

    /**
     * From all available cells, select the most suitable
     * 
     * @param board
     *            search board
     * @param myNum
     *            program's turn in the game
     * @return
     */
    public Pair<Integer, Integer> makeMove(Board board, int myNum) {
	b.setBoard(board.getBoard());
	b.setTotalCells(board.getTotalCells());
	if (b.checkResult() > 0)
	    return null;

	Vector<Pair<Integer, Integer>> bestPairs = new Vector<Pair<Integer, Integer>>();
	double bestRes = -200;

	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());

	for (int i = 1; i <= 3; i++) {
	    for (int j = 1; j <= 3; j++) {
		if (b.getValue(i, j) == 0) {
		    b.activate(i, j, myNum);
		    Pair<Double, Double> res = simulate(b, nextTurn(myNum), 1);
		    b.deactivate(i, j);

		    double tempRes = res.x / res.y;
		    if (rand.nextDouble() <= errorChance) {
			bestPairs.add(new Pair <Integer, Integer> (i, j));
		    } else if (tempRes > bestRes) {
			bestRes = tempRes;
			bestPairs.clear();
			bestPairs.add(new Pair <Integer, Integer> (i, j));
		    } else if (tempRes == bestRes) {
			bestPairs.add(new Pair <Integer, Integer> (i, j));
		    }
		}
	    }
	}
	int pos = 0;
	if (rand.nextDouble() <= shuffleChance) {
	    pos = rand.nextInt(bestPairs.size());
	}
	return bestPairs.elementAt(pos);
    }

    public Pair<Double, Double> simulate(Board board, int turn, int moves) {
	int gameResult = board.checkResult();
	Pair<Double, Double> finalResult = new Pair<Double, Double>(0.0, 0.0);
	if (gameResult == 0) {
	    // Game is not finished, continue the recursion
	    for (int i = 1; i <= 3; i++)
		for (int j = 1; j <= 3; j++) {
		    if (board.getValue(i, j) == 0) {

			board.activate(i, j, turn);
			Pair<Double, Double> res = simulate(board, nextTurn(turn), moves + 1);
			board.deactivate(i, j);

			finalResult.x += res.x;
			finalResult.y += res.y;
		    }
		}

	    return finalResult; // Return the final sum
	} else if (gameResult == 3) {

	    return new Pair<Double, Double>(1.0, 1.0); // Game finished with a draw. Return 1 point
	} else {

	    if (gameResult == myNum) {
		if (moves == 1)
		    return new Pair<Double, Double>(100.0, 1.0);
		return new Pair<Double, Double>(1.5, 1.0); // Game finished with a win. Return 1.5 points
	    }

	    else {
		if (moves == 2)
		    return new Pair<Double, Double>(-110.0, 1.0);
		return new Pair<Double, Double>(0.0, 1.0); // Game finished with a lose. Return 0 points
	    }
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

    private void read() {
	InputStreamReader isReader = null;
	BufferedReader br = null;
	try {
	    isReader = new InputStreamReader(this.getClass().getResourceAsStream("/Settings/settings.txt"));
	    br = new BufferedReader(isReader);
	    errorChance = Double.parseDouble(br.readLine()) / 100;
	    shuffleChance = Double.parseDouble(br.readLine()) / 100;

	    br.close();

	} catch (Exception e) {
	    Logger.error("There was an error reading the settings file");
	    e.printStackTrace();
	}
    }
}
