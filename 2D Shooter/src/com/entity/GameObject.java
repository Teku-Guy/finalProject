package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected float x, y;
    protected int width, height;
    protected ID id;
    protected float velX, velY;



    public GameObject(float x, float y, int width, int  height, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public Rectangle getBounds() {
        return new Rectangle((int)getX(), (int)getY(), width, height);
    }
    public Rectangle getBoundsT(){
        return new Rectangle((int)getX() + 10, (int)getY(), width - 20, 5);
    }
    public Rectangle getBoundsB(){
        return new Rectangle((int)getX() + 10, (int)getY() + height -5, width - 20, 5);

    }
    public Rectangle getBoundsR(){
        return new Rectangle((int)getX() + width - 5, (int)getY() + 10, 5, height - 20);
    }
    public Rectangle getBoundsL(){
        return new Rectangle((int)getX() + 10, (int)getY() + height - 5, width - 20, 5);

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





}
