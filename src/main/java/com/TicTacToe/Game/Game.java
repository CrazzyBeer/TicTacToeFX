package com.TicTacToe.Game;

import java.util.Stack;

import com.TicTacToe.Board;
import com.TicTacToe.Pair;
import com.TicTacToe.AI.AI_recursive;

import javafx.scene.text.Text;

/**
 * Handles all the game methods
 * 
 * @author Denis Rozimovschii
 *
 */
public class Game {
    protected Text[][] letters; // Letters X and O on the grid
    protected Board currentBoard; // Game board [3][3]
    protected int playerTurn; // Current turn. Default = 1
    protected int result; // Game result. Default = 0

    protected Stack<Board> gameStack; // All the game boards

    protected AI_recursive AI1; // First AI
    protected AI_recursive AI2; // Second AI

    public Game(Text[][] letters) {

	this.letters = letters;
	playerTurn = 1;
	result = 0;
	currentBoard = new Board();
	gameStack = new Stack<Board>();
	gameStack.push(new Board());

	AI1 = new AI_recursive(1); // First AI goes first
	AI2 = new AI_recursive(2); // Goes second
    }

    public Game() {

	playerTurn = 1;
	result = 0;
	currentBoard = new Board();
	gameStack = new Stack<Board>();
	gameStack.push(new Board());

	AI1 = new AI_recursive(1); // First AI goes first
	AI2 = new AI_recursive(2); // Goes second
    }

    /**
     * Takes care of all the UI and result changes after the move
     * 
     * @param i
     *            vertical position
     * @param j
     *            horizontal position
     */
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

    }

    /**
     * If it's the first player turn - first AI moves else - second AI moves
     * Only used in AIvsAI game
     */
    public void nextMove() {
	if (getTurn() == AI1.getMyNum())
	    moveAI(AI1);
	else
	    moveAI(AI2);

    }

    /**
     * Gets the needed AI and searches for the best move Does the move if the
     * position was found
     * Only supported in AI games (AIVSAI, PlayerVSAI)
     * 
     * @param AI
     */
    public void moveAI(AI_recursive AI) {
	Pair <Integer, Integer> res = AI.makeMove(currentBoard, AI.getMyNum());
	if (res == null)
	    return;
	move(res.x, res.y);
    }

    protected void nextTurn() {
	playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    public int getTurn() {
	return playerTurn;
    }

    /**
     * Turns the move into a letter. 1 = X, 2 = O
     * 
     * @return String - letter
     */
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

    /**
     * Erases the last made move and returns the game table to the previous
     * state Deletes the top of the stack and the currentBoard becomes the board
     * from the top
     */
    public void undo() {
	if (gameStack.size() > 1) {
	    gameStack.pop();
	    currentBoard = gameStack.peek();
	    nextTurn();

	    redrawLetters();
	}
    }

    /**
     * Returns the result of the current board in Integer format 0 - game still
     * not finished 1 - player 1 wins 2 - player 2 wins 3 - draw
     * 
     * @return int - result
     */
    public int getIntResult() {
	result = currentBoard.checkResult();
	return result;
    }

    /**
     * Gets the result and transforms it into a human readable string for
     * adding into the label
     * 
     * @return String - results
     */
    public String getResult() {
	result = currentBoard.checkResult();
	if (result == 0) {
	    if (playerTurn == 1)
		return "Player 1 moves";
	    else
		return "Player 2 moves";
	} else if (result > 0 && result < 3) {
	    if (result == 1)
		return "Player 1 wins!";
	    else
		return "Player 2 wins!";
	} else {
	    return "Draw!";
	}
    }

    /**
     * Checks whether a given cell is filled with X or O
     * 
     * @param i
     * @param j
     * @return boolean
     */
    public boolean isActivated(int i, int j) {
	return (currentBoard.getValue(i, j) > 0);
    }

    /**
     * Checks whether the match is finished
     * 
     * @return
     */
    public boolean isFinished() {
	return (result > 0);
    }

    /**
     * Redraws all the letters according to their new status For each position,
     * checks it's status (color).
     * 1 - invisible not activated, sets the classes
     * corresponding to an invisible letter
     * 2 - activated, yellow
     * 3 - is one of the letters from the winning streak, red
     */
    protected void redrawLetters() {
	if (letters == null)
	    return;
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
