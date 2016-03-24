package com.TicTacToe.Game_Controllers;

import com.TicTacToe.Game.playerVSAIGame;

/**
 * Handles all the actions done in the scene "Player VS AI"
 * Inherits methods from the superclass
 * 
 * @author Denis Rozimovschii
 *
 */
public class playerVSAIController extends GameController {

    @Override
    public void initialize() {

	prepareLetters();
	playerLabel.setText("Player moves");

	game = new playerVSAIGame(letters);

    }

    /**
     * When playing with an AI, undo button returns the game 2 positions backwards
     * Because the AI turn should be erased as well, for the player to make a new move
     */
    @Override
    public void undoPressed() {
	game.undo();
	if (game.getTurn() == 2)
	    game.undo();
	playerLabel.setText(game.getResult());
    }

    /**
     * Restarts the game
     * 
     * @see com.TicTacToe.Game_Controllers.GameController#refreshPressed()
     */
    @Override
    public void refreshPressed() {
	game = new playerVSAIGame(letters);
	playerLabel.setText(game.getResult());
    }
}
