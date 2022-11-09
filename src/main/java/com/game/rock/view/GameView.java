package com.game.rock.view;

import com.game.rock.Singleton;
import com.game.rock.model.GameObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameView {

    private VBox root;
    private GridPane gridPane;

    private Label select;
    private Label guess;
    private Label level;

    public GameView(){
        initialize();
    }

    void initialize(){
        root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);

        root.setStyle(
                "-fx-background-color: white;"
        );

        select = Singleton.getInstance().getLabel("Select: ",Color.BLACK,18);
        guess = Singleton.getInstance().getLabel("Guess: ",Color.BLACK,18);
        level = Singleton.getInstance().getLabel("Level: ",Color.BLACK,18);

        HBox levelBox = Singleton.getInstance().getBox(Pos.TOP_LEFT);
        levelBox.getChildren().add(level);
        HBox guessBox = Singleton.getInstance().getBox(Pos.TOP_RIGHT);
        guessBox.getChildren().add(guess);
        HBox heading = Singleton.getInstance().getBox(Pos.CENTER);
        heading.getChildren().addAll(levelBox,guessBox);


        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true);
        gridPane.setMinWidth(Region.USE_PREF_SIZE);
        gridPane.setMinHeight(Region.USE_PREF_SIZE);
        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxWidth(Region.USE_PREF_SIZE);
        gridPane.setMaxHeight(Region.USE_PREF_SIZE);

        gridPane.setStyle(
                "-fx-background-color: lightgreen;\n"+
                        "-fx-border-color: #5cfafa;\n"+
                        "-fx-border-width: 1.5em;"
        );

        root.getChildren().addAll(heading,select,gridPane);
    }

    public void setImages(ArrayList<GameObject> obj){
        gridPane.getChildren().clear();
        int index = 0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setImage(obj.get(index).getImg());
                int finalIndex = index;
                imageView.setOnMouseClicked(e->{
                    Singleton.getInstance().playSound("click.mp3",false);
                    if(Singleton.getInstance().guess <= 0){
                        // game over..
                        Singleton.getInstance().playSound("over.mp3",false);
                        Singleton.getInstance().alertMsg("Game Over","Your Score: "+Singleton.getInstance().score);
                        return;
                    }
                    if(Singleton.getInstance().selected <=0){
                        if(Singleton.getInstance().level == 5){
                            Singleton.getInstance().playSound("complete.mp3",false);
                            Singleton.getInstance().alertMsg("You Won","You Have Finished the game.\n Your Score: "+Singleton.getInstance().score);
                            if(Singleton.getInstance().highScore != null) {
                                if (Integer.parseInt(Singleton.getInstance().highScore) < Singleton.getInstance().score)
                                    Singleton.getInstance().saveScore();
                            }else
                                Singleton.getInstance().saveScore();
                            return;
                        }
                        // next the next level
                        Singleton.getInstance().level++;
                        if(Singleton.getInstance().level % 2 == 0)
                            Singleton.getInstance().playSound("progress1.mp3",false);
                        else
                            Singleton.getInstance().playSound("progress2.mp3",false);

                        Singleton.getInstance().mainController.levelSetup(Singleton.getInstance().level);
                        return;
                    }

                    if(obj.get(finalIndex).getName().contains(Singleton.getInstance().selection)){
                        Singleton.getInstance().score += 10*11;
                    }else{
                        Singleton.getInstance().guess--;
                    }
                    Singleton.getInstance().selected--;
                    imageView.setDisable(true);
                    imageView.setStyle(
                            "-fx-border-color: black;\n"+
                            "-fx-border-width: 1.5em;"
                    );
                    updateLabels(Singleton.getInstance().selection,Singleton.getInstance().guess+"",Singleton.getInstance().level+"");
                });
                index++;
                gridPane.add(imageView,i,j);
            }
        }
    }

    public void updateLabels(String select, String guess, String levels){
        this.select.setText("Select any 3 "+select+" Rocks Below!");
        this.guess.setText("Guess: "+guess);
        this.level.setText("Level: "+levels);
    }

    public VBox getRoot() {
        return root;
    }
}
