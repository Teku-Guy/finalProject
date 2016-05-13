package com;/**
 * Created by gustavo on 5/12/2016.
 */

import com.entity.GameObject;
import com.game.Main;

public class Camera {
    public float x, y;

    public void tick(GameObject player){
        setX(-player.getX()+ Main.WIDTH*2);
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
