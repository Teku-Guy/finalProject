package com.entity.tiles;/**
 * Created by Gustavo_Muratalla on 5/18/16.
 */

import java.awt.*;

import com.entity.ID;
import com.game.Main;

public class FloatPlat extends Tile {

    public FloatPlat(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.Floating.getBufferedImage(), x, y, width, height, null);

        Rectangle rect = getBounds();
        Rectangle top = new Rectangle(rect.x+(15/2), rect.y, rect.width-15, 5);
        Rectangle bottom = new Rectangle(rect.x+(15/2), rect.y + rect.height - 5, rect.width-15, 5);
        Rectangle left = new Rectangle(rect.x, rect.y+(15/2), 5, rect.height-15);
        Rectangle right = new Rectangle(rect.x + rect.width - 5, rect.y+(15/2), 5, rect.height-15);
    }
}
