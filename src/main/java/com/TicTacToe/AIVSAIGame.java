package com.TicTacToe;

import javafx.scene.text.Text;

public class AIVSAIGame extends Game {

	public AI_recursive AI1; //First AI
	public AI_recursive AI2; //Second AI

	public AIVSAIGame(Text[][] letters) {

		this.letters = letters;
		playerTurn = 1;
		result = 0;
		currentBoard = new Board();

		AI1 = new AI_recursive(1); //First AI goes first
		AI2 = new AI_recursive(2); //Goes second

	}
	
	public AIVSAIGame() {

		playerTurn = 1;
		result = 0;
		currentBoard = new Board();

		AI1 = new AI_recursive(1);
		AI2 = new AI_recursive(2);
	}
	
	/**
	 * Takes care of all the UI and result changes after the move
	 * @param i
	 * @param j
	 */
	public void move(int i, int j) {
		currentBoard.activate(i, j, playerTurn); 

		nextTurn();

		getResult(); //Updates the result after the move
 
		redrawLetters(); //Updates the letters after the move

	}
	/**
	 * If it's the first player turn - first AI moves
	 * else - second AI moves
	 */
	public void nextMove() {
		if (getTurn() == AI1.getMyNum()) moveAI(AI1);
		else moveAI(AI2);

	}
	
	/**
	 * Gets the needed AI and searches for the best move
	 * Does the move if the position was found
	 * @param AI
	 */
	public void moveAI(AI_recursive AI) {
		Pair res = AI.makeMove(currentBoard, AI.getMyNum());
		if (res == null)
			return;
		move(res.x, res.y);
	}


	/**
	 * Gets the result and transforms it into a human readable string 
	 * From adding into the label
	 * @return String - results
	 */
	public String getResult() {
		result = currentBoard.checkResult();
		if (result == 0) {
			if (playerTurn == 1)
				return "AI_1 moves";
			else
				return "AI_2 moves";
		} else if (result > 0 && result < 3) {
			if (result == 1)
				return "AI_1 wins!";
			else
				return "AI_2 wins!";
		} else {
			return "Draw!";
		}
	}

}
