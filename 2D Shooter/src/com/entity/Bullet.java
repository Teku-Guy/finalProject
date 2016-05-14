package com.entity;

import com.game.Main;

import java.awt.*;

/**
 * Created by jessus on 5/14/16.
 */
public class Bullet {
    public int x, y;

    public int width, height;

    public int velY, velX;

    public ID id;

    public Bullet(int x, int y, int width, int height, ID id){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }


    public int getX(){
        return x;
    }


    public int getY(){
        return y;
    }

    public void render(Graphics g) {
        g.drawImage(Main.Bullet.getBufferedImage(), (int)x, (int)y, null);
    }
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), width, height);
    }
}
