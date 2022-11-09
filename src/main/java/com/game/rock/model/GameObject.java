package com.game.rock.model;

import javafx.scene.image.Image;

public class GameObject {
    private String name;
    private Image img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
