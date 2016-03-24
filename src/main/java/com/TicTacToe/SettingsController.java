package com.TicTacToe;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsController {
    public Slider errorSlider;
    public Slider shuffleSlider;
    public Label errorLabel;
    public Label shuffleLabel;
    public double currentError;
    public double currentShuffle;
    public Stage mainStage;
    public Scene mainScene;

    public void initialize() {

	read();

	update();
    }

    private void read() {
	InputStreamReader isReader = null;
	BufferedReader br = null;
	try {
	    isReader = new InputStreamReader(this.getClass().getResourceAsStream("/Settings/settings.txt"));
	    br = new BufferedReader(isReader);
	    currentError = Double.parseDouble(br.readLine());
	    currentShuffle = Double.parseDouble(br.readLine());

	    br.close();

	    update();
	} catch (Exception e) {
	    Logger.error("There was an error reading the settings file");
	    e.printStackTrace();
	}
    }

    private void write() {
	PrintWriter pw = null;
	try {
	    pw = new PrintWriter(new File(this.getClass().getResource("/Settings/settings.txt").getPath()));
	    pw.println(currentError);
	    pw.println(currentShuffle);
	    pw.close();
	    update();
	} catch (Exception e) {
	    Logger.error("There was an error writing to the settings file");
	    e.printStackTrace();
	} finally {

	}
    }

    private void update() {
	errorLabel.setText(Double.toString(Math.round(currentError)) + "%");
	shuffleLabel.setText(Double.toString(Math.round(currentShuffle)) + "%");

	errorSlider.setValue(currentError);
	shuffleSlider.setValue(currentShuffle);
    }

    public void setStage(Stage mainStage) {
	this.mainStage = mainStage;
    }

    public void setMainSceneScene(Scene mainScene) {
	this.mainScene = mainScene;
    }

    public void backToMenuPressed() {

	mainStage.setScene(mainScene);
    }

    public void savePressed() {

	write();

    }

    public void errorSliderDragged() {

	currentError = errorSlider.getValue();

	update();

    }

    public void shuffleSliderDragged() {

	currentShuffle = shuffleSlider.getValue();

	update();
    }
}
