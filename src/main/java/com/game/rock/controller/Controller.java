package com.game.rock.controller;

import com.game.rock.Singleton;
import com.game.rock.model.GameObject;
import com.game.rock.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    public GameView gameView;

    public Controller(){
        gameView = new GameView();
        setupObjects(1);
    }

    void setupObjects(int level){

        switch (level){
            case 1:
                // 7 correct options out of 9
                levelSetup(7);
                break;
            case 2:
                // 6 correct options out of 9
                levelSetup(6);

                break;
            case 3:
                // 5 correct options out of 9
                levelSetup(5);

                break;
            case 4:
                // 4 correct options out of 9
                levelSetup(4);
                break;
            case 5:
                // 3 correct options out of 9
                levelSetup(3);
                break;
        }
    }

    public String getRandomSelection(){
        String[] names = new String[]{
                "Igneous","Metamorphic","Sedementary"
        };
        return names[new Random().nextInt(names.length)];
    }

    public String getRandomObject(String str){
        int rand = new Random().nextInt(15)+1;
        String n = str+rand;
        if(duplicate.contains(n))
            return getRandomObject(str);
        else {
            duplicate.add(n);
            return n;
        }
    }

    List<String> duplicate = new ArrayList<>();
    ArrayList<GameObject> gameGameObjects = new ArrayList<>();
    public void levelSetup(int correct){
        duplicate.clear();
        gameGameObjects.clear();
        Singleton singleton = Singleton.getInstance();
        singleton.selection = getRandomSelection();
        singleton.guess = 3;
        singleton.selected = 3;
        for(int i = 1; i<=9; i++){
            String item = null;
            if(i < correct){
                item = getRandomItem(singleton.selection);
            }else{
                item = getRandomObject(getRandomSelection());
            }
            GameObject gameObject = new GameObject();
            gameObject.setName(item);
            System.out.println(item);
            gameObject.setImg(Singleton.getInstance().loadImage(item.concat(".png")));
            gameGameObjects.add(gameObject);
        }
        gameView.setImages(gameGameObjects);
        gameView.updateLabels(Singleton.getInstance().selection,
                Singleton.getInstance().guess+"",
                Singleton.getInstance().level+"");
    }
    String getRandomItem(String sel){
        int rand = new Random().nextInt(15)+1;
        String n = sel+rand;
        if(duplicate.contains(n))
            return getRandomItem(sel);
        else {
            duplicate.add(n);
            return n;
        }
    }

}
