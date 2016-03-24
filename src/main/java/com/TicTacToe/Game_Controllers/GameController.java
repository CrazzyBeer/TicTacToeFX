package com.TicTacToe.Game_Controllers;

import com.TicTacToe.Game.Game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Superclass for all the controllers that involve the game
 * 
 * @author Denis Rozimovschii
 *
 */
public class GameController {
    public Stage mainStage; // Main stage passed to the controller
    public Scene mainScene; // Main scene passed to the controller
    public Scene gameScene; // This scene, passed from the main controller

    public VBox mainBox; // Main box of the scene
    public Label playerLabel; // Current move and results label
    public GridPane gridPane; // Main pane for game interactions
    public Text[][] letters; // All the 9 text objects
    public Game game; // The main game logic

    /**
     * Gets called the first when FXML is used and
     * Class loaded calls this controller
     */
    public void initialize() {

	prepareLetters();
	game = new Game(letters);

    }

    public void setStage(Stage mainStage) {
	this.mainStage = mainStage;
    }

    public void setMainSceneScene(Scene mainScene) {
	this.mainScene = mainScene;
    }

    public void setGameScene(Scene gameScene) {
	this.gameScene = gameScene;
    }

    /**
     * Creates the letters array and adds the custom classes to them Adds them
     * to the grid and adds all the needed mouse listeners.
     * mouseEntered and mouseExited only work when the letter is not activated
     */
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

		t.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent event) {
			if (!game.isActivated(a, b) && !game.isFinished()) {
			    game.move(a, b);
			    playerLabel.setText(game.getResult());
			}

		    }

		});

		t.setOnMouseEntered(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent event) {
			if (!game.isActivated(a, b) && !game.isFinished()) {
			    t.setText(game.getStringTurn());
			    t.setOpacity(1);
			}
		    }

		});

		t.setOnMouseExited(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent event) {
			if (!game.isActivated(a, b) && !game.isFinished()) {
			    t.setText(game.getStringTurn());
			    t.setOpacity(0);
			}

		    }

		});
	    }
	}
    }

    /**
     * Back button that sets the scene to be mainScene (mainMenu)
     */
    public void backToMenuPressed() {
	mainStage.setScene(mainScene);
    }

    /**
     * Operation that discards the last turn
     * Only possible in PlayerVsPlayer and PlayerVsAI, although overwritten in PlayerVSAI
     */
    public void undoPressed() {
	game.undo();
	playerLabel.setText(game.getResult());
    }

    /**
     * Restarts the game
     * Gets overwritten in each separate controller
     */
    public void refreshPressed() {
	game = new Game(letters);
	playerLabel.setText(game.getResult());
    }

}
