package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */


import com.game.Main;
import java.awt.image.BufferedImage;

public class Grass extends Tile {

    public Grass(float x, float y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height,  solid, id);



    }

    public void render(Graphics g) {
           g.drawImage(Main.Grass.getBufferedImage(), (int)x, (int)y, null);
    }

    @Override
    public void tick() {

    }
}
