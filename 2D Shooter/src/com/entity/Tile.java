package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */
import java.awt.Graphics;


public abstract class Tile{
    public float x, y;

    public int width, height;

    public boolean solid;

    public float velY, velX;

    public ID id;


    public Tile(float x, float y, int width, int height,boolean solid, ID id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;



    }



    public abstract void render(Graphics g);

    public abstract void tick();

    public float getX(){
        return x;
    }


    public float getY(){
        return y;
    }


    public boolean isSolid(){
        return solid;
    }

    public ID getID(){
        return id;
    }



    public void setX(int x){
        this.x = x;
    }


    public void setY(int y){
        this.y = y;
    }


    public void setSolid(boolean solid){
        this.solid = solid;
    }


    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
}

