package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class  GameObject {

    protected float x, y;
    protected int width, height;
    protected ID id;
    protected float velX, velY;

    protected boolean walking = false;
    protected boolean jumping = false;
    protected boolean falling = true;



    public GameObject(float x, float y, int width, int  height, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public Rectangle getBounds(){
        return new Rectangle((int)getX()+23, (int)getY()+46, width-8, 11);
    }
    public Rectangle getBoundsT(){
        return new Rectangle((int)getX()+21, (int)getY(), width-5, 11);
    }
    public Rectangle getBoundsR(){
        return new Rectangle((int)getX()+ width - 9, (int)getY() + 15, width/4, height-2);
    }
    public Rectangle getBoundsL(){ return new Rectangle((int)getX() + width + 8, (int)getY()+15, width/4, height - 2); }




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

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
