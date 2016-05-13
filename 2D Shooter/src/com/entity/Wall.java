package com.entity;/**
 * Created by gustavo on 5/12/2016.
 */

import com.input.Handler;

import java.awt.Graphics;

public class Wall extends Tile {

    private Grass grass;

    public Wall(float x, float y, int width, int height, boolean solid, ID id, Handler handler){
        super(x, y, width, height, solid, id);
    }

    public void render(Graphics g) {
        grass.render(g);
    }

    public void tick(){

    }
}
