package com.TicTacToe;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.TicTacToe.GameController.AIVSAIController;
import com.TicTacToe.GameController.playerVSAIController;
import com.TicTacToe.GameController.playerVSplayerController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
	public Label imageLabel;
	public Stage mainStage;
	public Scene mainScene;
	public Scene playerVSplayerScene;
	
	public void initialize() {

	}
	
	public void setStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	public void setGameScene(Scene gameScene) {
		this.playerVSplayerScene = gameScene;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/playerVSplayer.fxml"));
		Pane root = loader.load();
		playerVSplayerController playerVSplayerController = loader.<playerVSplayerController> getController();
		
		Scene playerVSplayerScene = new Scene(root);
		playerVSplayerScene.getStylesheets().add(getClass().getResource("/CSS/game.css").toExternalForm());
		
		playerVSplayerController.setStage(mainStage);
		playerVSplayerController.setMainSceneScene(mainScene);
		playerVSplayerController.setGameScene(playerVSplayerScene);
		
		mainStage.setScene(playerVSplayerScene);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void playerVsAIPressed() {
		/**
		 * Game Controller
		 */
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/playerVSAI.fxml"));
		Pane root = loader.load();
		playerVSAIController playerVSAIController = loader.<playerVSAIController> getController();
		
		Scene playerVSAIScene = new Scene(root);
		playerVSAIScene.getStylesheets().add(getClass().getResource("/CSS/game.css").toExternalForm());
		
		playerVSAIController.setStage(mainStage);
		playerVSAIController.setMainSceneScene(mainScene);
		playerVSAIController.setGameScene(playerVSAIScene);
		
		mainStage.setScene(playerVSAIScene);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void AIvsAIPressed() {
		/**
		 * Game Controller
		 */
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AIVSAI.fxml"));
		Pane root = loader.load();
		AIVSAIController AIVSAIController = loader.<AIVSAIController> getController();
		
		Scene AIVSAIScene = new Scene(root);
		AIVSAIScene.getStylesheets().add(getClass().getResource("/CSS/game.css").toExternalForm());
		
		AIVSAIController.setStage(mainStage);
		AIVSAIController.setMainSceneScene(mainScene);
		AIVSAIController.setGameScene(AIVSAIScene);
		
		mainStage.setScene(AIVSAIScene);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
