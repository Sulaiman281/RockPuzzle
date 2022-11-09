package com.game.rock;

import com.game.rock.view.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@SuppressWarnings("exports")
	public void start(Stage stage) {
		stage.setTitle("Rocks Game");
		Singleton.getInstance().scene = new Scene(new MainMenu().root);
		stage.setScene(Singleton.getInstance().scene);
		stage.setResizable(false);
		stage.setWidth(Singleton.getInstance().width);
		stage.setHeight(Singleton.getInstance().height);
		stage.show();
		Singleton.getInstance().playSound("background.mp3",true);
	}

	public static void main(String[] args) {
		launch();
	}

}