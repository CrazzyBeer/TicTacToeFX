package com.TicTacToe.Game;

import com.TicTacToe.AI.AI_recursive;

import javafx.scene.text.Text;

public class playerVSplayerGame extends Game {

    public playerVSplayerGame(Text[][] letters) {

	super(letters);

	redrawLetters();
    }

    public playerVSplayerGame() {

	super();

    }

    /*
     * Only used in AIvsAI game
     * 
     * @see com.TicTacToe.Game.Game#nextMove()
     */
    public void nextMove() {
	throw new UnsupportedOperationException("Only supported in AIVSAIGame");
    }

    /**
     * Only used in AI games
     * 
     * @see com.TicTacToe.Game.Game#moveAI(AI_recursive)
     */
    public void moveAI(AI_recursive AI) {
	throw new UnsupportedOperationException("Only supported in AI Games");
    }

}
