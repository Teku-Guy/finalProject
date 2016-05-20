package com.entity;/**
 * Created by jessus on 5/11/16.
 */
import java.awt.*;
import com.game.Main;

public class Grass extends Tile {

    public Grass(int x, int y, int width,int height, boolean solid, ID id){
        super(x, y,  width, height, solid, id);
    }

    public void render(Graphics g) {
        g.drawImage(Main.Grass.getBufferedImage(), x, y, width, height, null);

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

    public Rectangle getBounds(){
        return new Rectangle(getX()+17, getY()+height-10, width/2, 5);
    }
    public Rectangle getBoundsT(){
        return new Rectangle(getX()+17, getY()+(height-18)/2, width/2, 5);
    }
    public Rectangle getBoundsR(){
        return new Rectangle(getX()+17, getY()+30, 12, height/3);
    }
    public Rectangle getBoundsL(){
        return new Rectangle(getX() + 36, getY()+30, 12, height/3);
    }
}
