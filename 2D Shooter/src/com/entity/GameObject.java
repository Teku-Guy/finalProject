package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y, width, height;
    protected ID id;
    protected int velX, velY;



    public GameObject(int x, int y, int width, int  height, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
    public Rectangle getBoundsT(){
        return new Rectangle(getX() + 10, getY(), width - 20, 5);
    }
    public Rectangle getBoundsB(){
        return new Rectangle(getX() + 10, getY() + height -5, width - 20, 5);

    }
    public Rectangle getBoundsR(){
        return new Rectangle(getX() + width - 5, getY() + 10, 5, height - 20);
    }
    public Rectangle getBoundsL(){
        return new Rectangle(getX() + 10, getY() + height - 5, width - 20, 5);

    }




    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
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

    public int getVelX(){
        return velX;
    }

    public int getVelY(){
        return velY;
    }





}
