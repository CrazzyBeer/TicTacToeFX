package com.TicTacToe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage mainStage) {
		try {
			/**
			 * Main Controller
			 */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
			Pane root = loader.load();
			MainController mainController = loader.<MainController> getController();

			//Passing the mainStage so I could change the scene

			Scene mainScene = new Scene(root);
			mainScene.getStylesheets().add(getClass().getResource("/main.css").toExternalForm());
				
			mainController.setStage(mainStage);
			mainController.setMainScene(mainScene);
	
			
			mainStage.setScene(mainScene);
			mainStage.setMinWidth(500);
			mainStage.setMinHeight(500);
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
		
	}

}
