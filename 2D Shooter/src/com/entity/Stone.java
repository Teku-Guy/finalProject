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
        g.drawImage(Main.Stone.getBufferedImage(), x, y, null);

        /*Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g2d.draw(getBounds());
        g.setColor(Color.GREEN);
        g2d.draw(getBoundsT());
        g.setColor(Color.BLUE);
        g2d.draw(getBoundsR());
        g.setColor(Color.RED);
        g2d.draw(getBoundsL());*/
    }
}
