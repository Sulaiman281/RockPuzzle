package com.game.rock.view;

import com.game.rock.Singleton;
import com.game.rock.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class MainMenu {

    public VBox root;

    public MainMenu(){
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.setStyle(
                "-fx-background-color: #5cfafa;"
        );
        ImageView imageView = new ImageView();
        imageView.setImage(Singleton.getInstance().loadImage("ROCKS!.png"));
        imageView.setFitWidth(375);
        imageView.setFitHeight(312.5);
        Button start = Singleton.getInstance().getButton("Start");
        start.setOnAction(e->{
            Singleton.getInstance().mainController = new Controller();
            Singleton.getInstance().scene.setRoot(Singleton.getInstance().mainController.gameView.getRoot());
        });
        Button about = Singleton.getInstance().getButton("About");
        Button highScore = Singleton.getInstance().getButton("High Score");
        highScore.setOnAction(e->{
           Singleton.getInstance().loadScore();
           Singleton.getInstance().alertMsg("High Score",
                   "Last High Score: "+Singleton.getInstance().highScore);
        });

        about.setOnAction(e->{
            Singleton.getInstance().alertMsg("About Me","Developed By:"+System.lineSeparator()+"Lavanya Arora");
        });

        root.getChildren().addAll(imageView,start,highScore,about);
    }
}
