package com.TicTacToe;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController {
	public Stage mainStage;
	public Scene mainScene;
	public Scene gameScene; 
	
	public VBox mainBox;
	public Pane canvasPane;
	public Canvas canvas;
	public GraphicsContext gc;
	
	public void initialize() {	
		canvas.widthProperty().set(mainBox.getPrefWidth());
		canvas.heightProperty().set(mainBox.getPrefHeight());
		
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillOval(225, 225, 50, 50);
		gc.save();
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
	
	public void backToMenuPressed() {
		mainStage.setScene(mainScene);
	}
	
}
