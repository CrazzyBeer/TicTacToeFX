package com.TicTacToe.AI;

import java.util.Random;
import java.util.Vector;

import com.TicTacToe.Board;
import com.TicTacToe.Pair;

/**
 * Selects the a random move from all the available ones
 * 
 * @author Denis Rozimovschii
 *
 */
public class AI_random extends AI {
    protected Board b;
    protected int myNum; // 1 or 2. Represents the program's turn in the game
    protected double errorChance = 0.00;
    protected double shuffleChance = 0.25;

    public AI_random(int turn) {
	super(turn);
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
    @Override
    public Pair<Integer, Integer> makeMove(Board board, int myNum) {
	if (board.checkResult() > 0)
	    return null;

	Random rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	Vector<Pair<Integer, Integer>> bestPairs = new Vector<Pair<Integer, Integer>>();
	for (int i = 1; i <= 3; i++)
	    for (int j = 1; j <= 3; j++)
		if (board.getValue(i, j) == 0) {
		    bestPairs.add(new Pair<Integer, Integer>(i, j));
		}
	return bestPairs.elementAt(rand.nextInt(bestPairs.size()));
    }

}
