// package snakey.example.snakey2d;
//package ooadfinal.snakey2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class App extends Application {
	// variable
	LoginMenu logMenu = new LoginMenu();


	public void start(Stage primaryStage) {
		// Setup debuglogger and register it on gameDB
		GameDB db = GameDB.get_instance();
		DebugLogger logger = DebugLogger.getLogger();

		logger.openLogger("log.txt");
		db.registerObserver(logger);

		// Setup DB
		db.setDBPath("jdbc:sqlite:snake.db");
		db.setupDB();

		try {

			primaryStage.setTitle("Snakey - 2D");
			primaryStage = logMenu.showMenu(primaryStage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
