package com.entity;

import com.game.Main;

import java.awt.*;

/**
 * Created by jessus on 5/19/16.
 */
public class Lava extends Tile {
    private int counter = 0;
    private int phase = 0;

    public Lava(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);
    }

    public void render(Graphics g) {
        counter++;
        if(counter % 10 == 0) {
            counter = 0;
            phase++;
            phase %= Main.LavaFlow.length;
        }
        g.drawImage(Main.LavaFlow[phase].getBufferedImage(), x, y, width, height, null);
    }
}
