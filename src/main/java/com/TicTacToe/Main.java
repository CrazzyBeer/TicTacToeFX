package com.TicTacToe;

import com.TicTacToe.Game.AIVSAIGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/main.fxml"));
	    Pane root = loader.load();
	    MainController mainController = loader.<MainController> getController();

	    // Passing the mainStage so I could change the scene

	    Scene mainScene = new Scene(root);
	    mainScene.getStylesheets().add(getClass().getResource("/CSS/main.css").toExternalForm());

	    mainController.setStage(mainStage);
	    mainController.setMainScene(mainScene);

	    mainStage.getIcons().add(new Image(getClass().getResource("/img/icon.png").toExternalForm()));
	    mainStage.setScene(mainScene);
	    mainStage.setMinWidth(500);
	    mainStage.setMinHeight(500);
	    //mainStage.setResizable(false);
	    mainStage.show();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	// Games simulation without UI
	if (args.length > 0) {
	    int games = Integer.parseInt(args[0]);
	    int w1 = 0;
	    int w2 = 0;
	    int d = 0;
	    for (int i = 0; i < games; i++) {
		AIVSAIGame t = new AIVSAIGame();
		while (!t.isFinished())
		    t.nextMove();
		int r = t.getIntResult();
		if (r == 1) {
		    w1++;
		}
		if (r == 2) {
		    w2++;
		}
		if (r == 3)
		    d++;
	    }
	    System.out.printf("From %d Games AI_1 WON %f , AI_2 WON %f and %f Draws \n", games,
		    (double) (w1 * 100) / games, (double) (w2 * 100) / games, (double) (d * 100) / games);

	} else {
	    //Game with UI
	    launch(args);
	}

    }

}
