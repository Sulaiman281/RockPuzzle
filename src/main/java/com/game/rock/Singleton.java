package com.game.rock;

import com.game.rock.controller.Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Singleton {


    public int guess, score,level,selected;
    public String selection;

    public final int width = 800, height = 700;

    public Scene scene;

    public Controller mainController;

    public String highScore;

    private static final Singleton singleton = new Singleton();

    private Singleton(){
        guess = 0;
        score = 0;
        level = 1;
        selected = 0;
    }

    public static Singleton getInstance(){
        return singleton;
    }

    public Button getButton(String txt){
        Button btn = new Button(txt);
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD,18));
        btn.setStyle(
                "-fx-background-color: linear-gradient(#62D966,#B2E6B4);"
        );
        return btn;
    }
    public Label getLabel(String text, Color color, int font_size){
        Label label = new Label(text);
        label.setTextFill(color);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD,font_size));
        return label;
    }

    public void alertMsg(String title, String msg){
        Stage stage = new Stage();
        stage.setTitle(title);

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(30);
        box.setStyle(
                "-fx-background-color: #5cfafa;"
        );
        box.setPrefWidth(300);
        box.setPrefHeight(150);
        Label label = getLabel(msg,Color.WHITE,18);
        label.setMinWidth(Region.USE_COMPUTED_SIZE);
        label.setMinHeight(Region.USE_COMPUTED_SIZE);
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.setPrefHeight(Region.USE_COMPUTED_SIZE);
        box.getChildren().add(label);

        Button btn = getButton("Close");
        btn.setOnAction(e->{
            stage.close();
        });
        box.getChildren().add(btn);

        stage.setScene(new Scene(box));
        stage.showAndWait();
    }

    public void playSound(String sound,boolean loop){
        URL resource = getClass().getResource(sound);
        MediaPlayer myPlayer =new MediaPlayer(new Media(resource.toString()));
        myPlayer.setAutoPlay(true);
        if(loop){
            myPlayer.setVolume(0.3);
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(6),e->{
                myPlayer.seek(Duration.ZERO);
                myPlayer.play();
            });
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.getKeyFrames().addAll(keyFrame);
            timeline.play();
        }

        myPlayer.play();
    }

    public void saveScore() {
        String fileName = "Score.txt";
        File file = new File(fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(Singleton.getInstance().score + "");
            writer.flush();
            writer.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    public void loadScore(){
        String fileName = "Score.txt";
        File file = new File(fileName);
        if(file.exists()){
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                if(scanner.hasNext())
                    highScore = scanner.nextLine();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public HBox getBox(Pos pos){
        HBox hBox = new HBox();
        hBox.setAlignment(pos);
        return hBox;
    }

    public Image loadImage(String _file){
        return new Image(getClass().getResource(_file).toString());
    }
}
