package com.TicTacToe;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
	public Label imageLabel;
	public Stage mainStage;
	public Scene mainScene;
	public Scene gameScene;
	
	public void initialize() {

	}
	
	public void setStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
	
	public void iconClicked() {

		if(Desktop.isDesktopSupported())
		{
		    try {
				Desktop.getDesktop().browse(new URI("https://github.com/CrazzyBeer/TicTacToeFX"));
			} catch (IOException e) {
				Logger.error("Sorry, could not open the link");
				e.printStackTrace();
			} catch (URISyntaxException e) {
				Logger.error("Sorry, could not open the link");
				e.printStackTrace();
			}
		}
		
	}
	
	public void playerVsPlayerPressed() {
		
		/**
		 * Game Controller
		 */
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
		Pane root = loader.load();
		GameController gameController = loader.<GameController> getController();
		
		Scene gameScene = new Scene(root);
		gameScene.getStylesheets().add(getClass().getResource("/game.css").toExternalForm());
		
		gameController.setStage(mainStage);
		gameController.setMainSceneScene(mainScene);
		gameController.setGameScene(gameScene);
		
		mainStage.setScene(gameScene);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void playerVsAIPressed() {
		
	}
	public void AIvsAIPressed() {
		
	}
	
}
