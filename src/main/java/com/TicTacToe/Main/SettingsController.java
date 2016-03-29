package com.TicTacToe.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import com.TicTacToe.Helper_Classes.Logger;

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

    public String path;
    public void initialize() {
	
	initSaveDirectory();
	
	read();

	update();
	
    }

    public void initSaveDirectory() {
	
	path = System.getProperty("user.home") + "/TicTacToeFX";
	File loc = new File(path);

	if (!loc.isDirectory()) {
	    try {
		loc.mkdirs();
	    } catch (Exception e) {
		Logger.error("Error creating a directory at " + loc.getPath());
	    }
	}

	File file = new File(path + "/settings.txt");
	try {
	    
	    if (!file.exists()) {
		file.createNewFile();
		write(0.0, 50.0);
	    }		    
	    
	} catch (Exception e) {
	    Logger.error("Error creating a file at " + file.getPath());
	}
	
    }

    private void read() {

	File file = new File(path + "/settings.txt");
	Scanner sc = null;
	try {
	    sc = new Scanner(file).useLocale(Locale.ENGLISH);
	    
	    currentError = sc.nextDouble();
	    currentShuffle = sc.nextDouble();

	    sc.close();

	    update();
	} catch (Exception e) {
	    Logger.error("There was an error reading the settings file");
	    e.printStackTrace();
	}
    }

    private void write() {

	File file = new File(path + "/settings.txt");
	PrintWriter pw = null;
	
	try {
	    pw = new PrintWriter(new FileWriter(file));
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

    private void write(double currentError, double currentShuffle) {

	File file = new File(path + "/settings.txt");
	PrintWriter pw = null;
	
	try {
	    pw = new PrintWriter(new FileWriter(file));
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
