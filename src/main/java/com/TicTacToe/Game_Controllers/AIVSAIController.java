package com.TicTacToe.Game_Controllers;

import com.TicTacToe.Game.AIVSAIGame;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Handles all the actions done in the scene "AI VS AI" Inherits methods from
 * superclass, but has a
 * 
 * @author Denis Rozimovschii
 *
 */
public class AIVSAIController extends GameController {

    @Override
    public void initialize() {

	prepareLetters();
	playerLabel.setText("AI_1 moves");

	game = new AIVSAIGame(letters);

    }

    /**
     * Adds the letters without giving to user the possibility to
     * interact with them
     * @see com.TicTacToe.Game_Controllers.GameController#prepareLetters()
     */
    @Override
    public void prepareLetters() {

	letters = new Text[4][4];
	for (int i = 0; i <= 4; i += 2) {
	    for (int j = 0; j <= 4; j += 2) {
		final int a = i / 2 + 1;
		final int b = j / 2 + 1;
		Text t = new Text();
		t.setWrappingWidth(135);
		t.getStyleClass().addAll("letter", "letterinactive");
		t.setId(Integer.toString(i) + Integer.toString(j));
		t.setOpacity(0);
		t.setText("X");
		GridPane.setRowIndex(t, i);
		GridPane.setColumnIndex(t, j);
		letters[a][b] = t;
		gridPane.getChildren().add(t);
	    }
	}
    }

    /**
     * Undo operation is unique for games where there is at last one
     * human-player, so it's not supported here, but will still be inherited
     * from the superclass, so we need to disable it
     */
    public void undoPressed() {
	throw new UnsupportedOperationException("No undo in this scene");
    }

    /**
     * Unique operation for this scene. It is not contained in the Superclass
     */
    public void nextPressed() {
	game.nextMove();
	playerLabel.setText(game.getResult());
    }

    /**
     * Restarts the game
     * 
     * @see com.TicTacToe.Game_Controllers.GameController#refreshPressed()
     */
    @Override
    public void refreshPressed() {
	game = new AIVSAIGame(letters);
	playerLabel.setText(game.getResult());
    }
}
