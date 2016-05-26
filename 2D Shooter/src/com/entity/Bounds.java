package com.entity;

import com.game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by jessus on 5/18/16.
 */
public class Bounds extends Tile {

    public Bounds(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y, width, height, solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.BoundL.getBufferedImage(), x, y, width, height, null);

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g2d.draw(getBounds());

    }
}
