package com.entity;/**
 * Created by jessus on 5/14/16.
 */

import com.game.Main;

import java.awt.*;

public class Bullet extends GameObject{
    public float x, y;

    public int width, height;

    public float velY, velX;

    public ID id;

    public Bullet(float x, float y, int width, int height, ID id, int velX){
        super(x,y,width,height,id);

        this.x = x;
        this.y = y;
        this.id = id;
        this.velX = velX;
    }

    public void render(Graphics g) {
        g.drawImage(Main.Bullet.getBufferedImage(), (int)x, (int)y, null);

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g2d.draw(getBounds());
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX()+20, (int)getY()+25,  20, 15);
    }

    public void tick() {
        x += velX;
    }

    public float getX(){
        return x;
    }


    public float getY(){
        return y;
    }
}