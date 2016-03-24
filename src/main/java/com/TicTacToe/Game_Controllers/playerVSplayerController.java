package com.TicTacToe.Game_Controllers;

import com.TicTacToe.Game.playerVSplayerGame;

/**
 * Handles all the actions done in the scene "Player VS Player"
 * Inherits all the methods from the superclass
 * 
 * @author Denis Rozimovschii
 *
 */
public class playerVSplayerController extends GameController {

    @Override
    public void initialize() {
	
	prepareLetters();
	playerLabel.setText("Player 1 moves");

	game = new playerVSplayerGame(letters);
    }

    /**
     * Restarts the game
     * 
     * @see com.TicTacToe.Game_Controllers.GameController#refreshPressed()
     */
    @Override
    public void refreshPressed() {
	game = new playerVSplayerGame(letters);
	playerLabel.setText(game.getResult());
    }
}
