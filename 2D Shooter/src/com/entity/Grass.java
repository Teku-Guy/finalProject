package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */


import com.game.Main;
import java.awt.image.BufferedImage;

public class Grass extends Tile {

    public Grass(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);



    }

    public void render(Graphics g) {
        g.drawImage(Main.Grass.getBufferedImage(), x, y, null);

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        g2d.draw(getBounds());
        g.setColor(Color.GREEN);
        g2d.draw(getBoundsL());
        g.setColor(Color.magenta);
        g2d.draw(getBoundsB());
        g.setColor(Color.BLUE);
        g2d.draw(getBoundsR());
    }
}
