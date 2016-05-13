package com.entity;

import com.input.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class  GameObject {

    protected float x, y;
    protected int width, height;
    protected ID id;
    protected float velX, velY;

    public boolean solid;
    protected boolean walking = false;
    protected boolean jumping = false;
    protected boolean falling = true;
    protected static boolean still = true;

    public Handler handler;

    public GameObject(float x, float y, int width, int  height, boolean solid, ID id, Handler handler){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.solid= solid;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public Rectangle getBounds(){
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
    public Rectangle getBoundsTop(){
        return new Rectangle((int)getX()+10, (int)getY(), width-20, 5);
    }
    public Rectangle getBoundsBottom(){
        return new Rectangle((int)getX()+10, (int)getY()+height-5, width, 5);
    }
    public Rectangle getBoundsLeft(){
        return new Rectangle((int)getX(), (int)getY()+10, 5, height-20);
    }
    public Rectangle getBoundsRight(){
        return new Rectangle((int)getX()+width-5, (int)getY()+10, 5, height-20);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public float getVelX(){
        return velX;
    }

    public float getVelY(){
        return velY;
    }

    public static boolean isStill() {
        return still;
    }

    public static void setStill(boolean still) {
        GameObject.still = still;
    }

    public boolean isFalling() {
        return falling;
    }
}
