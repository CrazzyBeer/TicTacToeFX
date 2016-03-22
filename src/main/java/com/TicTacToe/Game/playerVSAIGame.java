package com.TicTacToe.Game;

import com.TicTacToe.Board;

import javafx.scene.text.Text;

/**
 * @author Denis
 *
 */
public class playerVSAIGame extends Game {

    public playerVSAIGame(Text[][] letters) {

	super(letters);
	
	redrawLetters();
    }

    public playerVSAIGame() {

	super();

    }

    /**
     * @see com.TicTacToe.Game.playerVSplayerGame#move(int, int)
     * 
     */
    @Override
    public void move(int i, int j) {

	Board newBoard = new Board();
	newBoard.setBoard(currentBoard.getBoard());
	newBoard.setColor(currentBoard.getColors());
	newBoard.setTotalCells(currentBoard.getTotalCells());
	newBoard.activate(i, j, playerTurn);
	gameStack.push(newBoard);
	currentBoard = newBoard;

	nextTurn();

	getResult(); // Updates the result after the move

	redrawLetters(); // Updates the letters after the move

	if (getTurn() == AI2.getMyNum())
	    moveAI(AI2); // AI moves, if it's his turn
    }

    /**
     * @see com.TicTacToe.Game.Game#nextMove()
     */
    public void nextMove() {
	throw new UnsupportedOperationException("Only supported in AIVSAIGame");
    }

    /**
     * Gets the result and transforms it into a human readable string for adding
     * into the label
     * 
     * @return String - results
     * @see com.TicTacToe.Game.Game#getResult()
     */
    @Override
    public String getResult() {
	result = currentBoard.checkResult();
	if (result == 0) {
	    if (playerTurn == 1)
		return "Player moves";
	    else
		return "AI moves";
	} else if (result > 0 && result < 3) {
	    if (result == 1)
		return "Player wins!";
	    else
		return "AI wins!";
	} else {
	    return "Draw!";
	}
    }

}
