package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */
import java.awt.Graphics;


import com.game.Main;
import com.input.Handler;
import com.entity.ID;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;


public abstract class Tile{
    public int x, y;

    public int width, height;

    public boolean solid;

    public int velY, velX;

    public ID id;


    public Tile(int x, int y, int width, int height, boolean solid,  ID id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;



    }



    public abstract void render(Graphics g);

    public int getX(){
        return x;
    }


    public int getY(){
        return y;
    }


    public boolean isSolid(){
        return solid;
    }

    public void setID(ID id){
        this.id = id;
    }
    public ID getId(){
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
        return new Rectangle(getX(), getY()+32, width, 5);
    }
    public Rectangle getBoundsT(){
        return new Rectangle(getX(), getY()+32, width, 5);
    }
    public Rectangle getBoundsB(){
        return new Rectangle((int)getX(), (int)getY() + 60, width - 20, 5);

    }
    public Rectangle getBoundsR(){
        return new Rectangle((int)getX(), (int)getY() + 38, 12, height/6);
    }
    public Rectangle getBoundsL(){
        return new Rectangle((int)getX() + 19, (int)getY() + 38, 12, height/6);

    }
}

