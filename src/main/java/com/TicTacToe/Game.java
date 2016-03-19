package com.TicTacToe;

import java.util.Stack;

import javafx.scene.text.Text;

public class Game {

	public Board currentBoard;
	public int playerTurn;
	public Stack<Board> gameStack;

	public Game() {

		playerTurn = 1;
		currentBoard = new Board();
		gameStack = new Stack<Board>();
		gameStack.push(currentBoard);

	}

	public void move(int i, int j) {
		Board newBoard = currentBoard.getCopy();
		newBoard.activate(i, j, playerTurn);
		currentBoard = newBoard.getCopy();
		
		gameStack.push(newBoard);
		newBoard = null;
		nextTurn();

		printStackTrace();
	}

	public void printStackTrace() {
		System.out.println("here");
		for (Board b : gameStack) {
			System.out.println(b.toString());
		}
	}

	public void undo() {
		if (gameStack.size() > 1) {
			gameStack.pop();
			currentBoard = gameStack.peek();
		}
	}

	public void nextTurn() {
		playerTurn = (playerTurn == 1) ? 2 : 1;
	}

	public int getTurn() {
		return playerTurn;
	}

	public String getStringTurn() {
		if (playerTurn == 1)
			return "X";
		else
			return "O";
	}

	public String getStringTurn(int val) {
		if (val == 1)
			return "X";
		else
			return "O";
	}

	public boolean isActivated(int i, int j) {
		return (currentBoard.getValue(i, j) > 0);
	}

	public void redrawLetters(Text[][] letters) {
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				int val = currentBoard.getValue(i, j);
				if (val > 0) {
					letters[i][j].setText(getStringTurn(val));
					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letteractive");
					letters[i][j].setOpacity(1);
				} else {
					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letterinactive");
					letters[i][j].setOpacity(0);
				}
			}
		}
	}

}
