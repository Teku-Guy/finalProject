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

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g2d.draw(getBounds());

        Rectangle rect = getBounds();
        Rectangle top = new Rectangle(rect.x+(15/2), rect.y, rect.width-15, 5);
        Rectangle bottom = new Rectangle(rect.x+(15/2), rect.y + rect.height - 5, rect.width-15, 5);
        Rectangle left = new Rectangle(rect.x, rect.y+(15/2), 5, rect.height-15);
        Rectangle right = new Rectangle(rect.x + rect.width - 5, rect.y+(15/2), 5, rect.height-15);
        g.setColor(Color.red);
        g2d.draw(top);
        g2d.draw(bottom);
        g2d.draw(left);
        g2d.draw(right);
    }
}
