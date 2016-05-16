package com.entity;

import com.game.Main;

import java.awt.*;

/**
 * Created by jessus on 5/14/16.
 */
public class Bullet extends GameObject{
    public float x, y;

    public int width, height;

    public float velY, velX;

    public ID id;

    public Bullet(float x, float y, int width, int height, ID id, int velX){
        super(x,y,width,height,id);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.velX = velX;
    }


    public float getX(){
        return x;
    }


    public float getY(){
        return y;
    }

    public void tick() {
        x += velX;
    }

    public void render(Graphics g) {
        g.drawImage(Main.Bullet.getBufferedImage(), (int)x, (int)y, null);
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
}
