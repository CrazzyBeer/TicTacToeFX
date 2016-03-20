package com.TicTacToe;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AIVSAIController {
	public Stage mainStage;
	public Scene mainScene;
	public Scene gameScene;

	public VBox mainBox;
	public Label playerLabel;
	public GridPane gridPane;
	public Text[][] letters;
	public AIVSAIGame game;
	
	public void initialize() {
		
		prepareLetters();
		game = new AIVSAIGame(letters);
		
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

	public void backToMenuPressed() {
		mainStage.setScene(mainScene);
	}
	
	public void nextPressed() {
		game.nextMove();
		game.redrawLetters();
		playerLabel.setText(game.getResult());
	}

	public void refreshPressed() {
		game = new AIVSAIGame(letters);
		game.redrawLetters();
		playerLabel.setText(game.getResult());
	}
}
