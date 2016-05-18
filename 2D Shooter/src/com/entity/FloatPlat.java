package com.entity;/**
 * Created by Gustavo_Muratalla on 5/18/16.
 */

import java.awt.*;
import com.game.Main;

public class FloatPlat extends Tile {

    public FloatPlat(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.Floating.getBufferedImage(), x, y, width, height, null);
    }
}
