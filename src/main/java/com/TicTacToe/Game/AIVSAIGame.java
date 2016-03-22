package com.TicTacToe.Game;

import javafx.scene.text.Text;

public class AIVSAIGame extends Game {

    public AIVSAIGame(Text[][] letters) {

	super(letters);

	redrawLetters();
    }

    public AIVSAIGame() {

	super();

    }

    /**
     * Gets the result and transforms it into a human readable string for adding
     * into the label
     * 
     * @return String - results
     */
    @Override
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
