package com.gui;/**
 * Created by Gustavo_Muratalla on 5/18/16.
 */

import com.entity.GameObject;
import com.game.Main;

public class Camera {

    private float x, y;

    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player){
        x = - player.getX() + (float)Main.window.frame.getSize().getWidth()/2;
        y = - player.getY() + (float)Main.window.frame.getSize().getHeight()*((float)0.87);
    }

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getY(){
        return y;
    }

    public void setY(float y){
        this.y = y;
    }
}
