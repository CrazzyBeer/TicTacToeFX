package com.TicTacToe;

import java.util.Stack;

import javafx.scene.text.Text;

public class playerVSplayerGame {
	public Text[][] letters;
	public Board currentBoard;
	public int playerTurn;
	public int result;
	public int type;
	public Stack<Board> gameStack;
	
	public playerVSplayerGame(Text[][] letters) {

		this.letters = letters;
		playerTurn = 1;
		result = 0;
		currentBoard = new Board();
		gameStack = new Stack<Board>();
		gameStack.push(new Board());

	}

	public void move(int i, int j) {
		
		Board newBoard = new Board();
		newBoard.setBoard(currentBoard.getBoard());
		newBoard.setColor(currentBoard.getColors());
		newBoard.setTotalCells(currentBoard.getTotalCells());
		newBoard.activate(i, j, playerTurn);
		gameStack.push(newBoard);
		currentBoard = newBoard;
		nextTurn();
		getResult();
		redrawLetters();
		
	}

	public void undo() {
		if (gameStack.size() > 1) {
			gameStack.pop();
			currentBoard = gameStack.peek();
			nextTurn();
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
	public int getIntResult() {
		result = currentBoard.checkResult();
		return result;
	}

	public String getResult() {
		result = currentBoard.checkResult();
		if (result == 0) {
			return "Player " + playerTurn + " moves";
		} else if (result > 0 && result < 3){
			return "Player " + result + " wins";
		} else {
			return "Draw!";
		}
	}
	
	public boolean isActivated(int i, int j) {
		return (currentBoard.getValue(i, j) > 0);
	}

	public boolean isFinished() {
		return (result > 0);
	}

	public void redrawLetters() {
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				int col = currentBoard.getColor(i, j);
				int val = currentBoard.getValue(i, j);

				letters[i][j].setText(getStringTurn(val));

				if (col == 1) {

					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letteractive");
					letters[i][j].setOpacity(1);

				} else if (col == 2) {

					letters[i][j].getStyleClass().clear();
					letters[i][j].getStyleClass().addAll("letter", "letterwinning");
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
