package com.entity;/**
 * Created by jessus on 5/11/16.
 */
import com.game.Main;
import java.awt.*;

public class Stone extends Tile {

    public Stone(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height,  solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.Stone.getBufferedImage(), x, y, width, height, null);
    }
}
