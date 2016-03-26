package com.TicTacToe.AI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

import com.TicTacToe.Helper_Classes.Board;
import com.TicTacToe.Helper_Classes.Logger;
import com.TicTacToe.Helper_Classes.Pair;

/**
 * Selects the best move from the game tree
 * Uses recursion
 * @see com.TicTacToe.AI.AI#simulate(Board, int) for more information
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

	Vector<Pair<Integer, Integer>> winning = new Vector<Pair<Integer, Integer>>(); //All the winning moves
	Vector<Pair<Integer, Integer>> drawing = new Vector<Pair<Integer, Integer>>(); //All the drawing moves
	Vector<Pair<Integer, Integer>> losing = new Vector<Pair<Integer, Integer>>();  //All the losing moves

	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());

	for (int i = 1; i <= 3; i++) {
	    for (int j = 1; j <= 3; j++) {
		if (b.getValue(i, j) == 0) {
		    b.activate(i, j, myNum);
		    int res = simulate(b, nextTurn(myNum));
		    b.deactivate(i, j);
		    
		    //Adds the positions to the vectors depending on the result
		    if (res == 1)
			winning.add(new Pair<>(i, j));
		    if (res == 0)
			drawing.add(new Pair<>(i, j));
		    if (res == -1)
			losing.add(new Pair<>(i, j));
		}
	    }
	}
	// Makes an error, selects a random move from all the available ones, even losing moves
	if (rand.nextDouble() <= errorChance) {
	    drawing.addAll(losing);
	    drawing.addAll(winning);
	    int pos = rand.nextInt(drawing.size());
	    return drawing.elementAt(pos);
	}

	// Selects a random move from the winning ones and, if there is no winning move - selects from drawing
	// If there are no drawing positions, selects from the losing ones
	int pos = 0;
	if (rand.nextDouble() <= shuffleChance) {

	    if (winning.size() == 0) {
		if (drawing.size() == 0) {
		    pos = rand.nextInt(losing.size());
		    return losing.elementAt(pos);
		} else {
		    pos = rand.nextInt(drawing.size());
		    return drawing.elementAt(pos);
		}

	    } else {
		pos = rand.nextInt(winning.size());
		return winning.elementAt(pos);
	    }

	}
	
	//No randomness, select the first element from the best available list
	if (winning.size() == 0) {
	    if (drawing.size() == 0) {
		return losing.elementAt(0);
	    } else {
		return drawing.elementAt(0);
	    }
	} else {
	    return winning.elementAt(0);
	}
    }
    
    /**
     * Recursively plays min-maxing game. 
     * Each player tends to maximize his score
     * 1 - represents a win so THIS AI Always tends to find 1 in all the child nodes, it means a sure win
     * 0 - represents a draw, so this is the plan B in case 1 is not found
     * -1 - represents a lose, so THIS AI'S opponent will always try to find a -1 meaning a sure lose for THIS AI
     * @param board current game board
     * @param turn 1, 2 based on who moves now
     * @return 1, 0, -1 win of this AI, draw and respectively losing
     */
    public int simulate(Board board, int turn) {
	int gameResult = board.checkResult();
	int hasWin = 0;
	int hasDraw = 0;
	int hasLose = 0;

	if (gameResult == 0) {
	    // Game is not finished, continue the recursion
	    for (int i = 1; i <= 3; i++)
		for (int j = 1; j <= 3; j++) {
		    if (board.getValue(i, j) == 0) {

			board.activate(i, j, turn);
			int res = simulate(board, nextTurn(turn));
			board.deactivate(i, j);

			if (res == 0)
			    hasDraw++;
			else if (res == 1)
			    hasWin++;
			else
			    hasLose++;

		    }
		}

	    if (turn == myNum) {
		if (hasWin > 0)
		    return 1;
		if (hasDraw > 0)
		    return 0;
		return -1;
	    } else {
		if (hasLose > 0)
		    return -1;
		if (hasDraw > 0)
		    return 0;
		return 1;
	    }
	} else if (gameResult == 3) { // Game finished

	    return 0; // Game finished with a draw. Return 1 point

	} else { // gameResult == 1 || gameResult == 2

	    if (gameResult == myNum) {

		return 1; // Game finished with a win. Return 1.5 points
	    }

	    else {

		return -1; // Game finished with a lose. Return 0 points
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
