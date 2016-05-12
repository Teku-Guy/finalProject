package com.entity;

import com.game.Main;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */
public class FloatingPlatform extends Tile {
    public FloatingPlatform(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height,  solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.Grass.getBufferedImage(), x, y, null);
    }
}
