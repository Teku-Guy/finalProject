package com.entity.tiles;/**
 * Created by Gustavo_Muratalla on 6/2/16.
 */
import java.awt.*;

import com.entity.ID;
import com.game.Main;

public class HealthPack extends Tile {

    public HealthPack(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.HP.getBufferedImage(), x, y, width, height, null);
    }

}

