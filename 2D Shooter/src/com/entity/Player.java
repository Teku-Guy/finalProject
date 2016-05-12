package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/10/16.
 */
import java.awt.Graphics;


import com.game.Main;
import com.input.Handler;
import com.entity.ID;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;


public class Player extends GameObject {
    public int phase = 0;
    public int facing = 0;

    public boolean walking = false;
    public boolean jumping = false;
    public boolean falling = true;
    public Player(int x, int y, int width, int height, ID id){
        super(x, y, width, height, id);
    }

    public void tick() {

    }


    public void render(Graphics g) {
         if(facing == 0) {
             if (jumping) {
                 g.drawImage(Main.PJumpLeft.getBufferedImage(), x, y, null);

             } else if (walking) {
                 g.drawImage(Main.PlayerWalkL[phase].getBufferedImage(), x, y, null);

             } else {
                 g.drawImage(Main.PlayerWalkL[3].getBufferedImage(), x, y, null);
             }
         }
         else if(facing == 1){
                 if(jumping ){
                     g.drawImage(Main.PJumpRight.getBufferedImage(), x,y, null);

                 }else if(walking){
                     g.drawImage(Main.PlayerWalkR[phase].getBufferedImage(), x, y, null);
                 }
                 else{
                     g.drawImage(Main.PlayerWalkR[3].getBufferedImage(), x, y, null);
                 }

         }
    }
    public void move(){
        x += velX;
        y += velY;
    }
    public void CheckCollision(){

    }


    public Rectangle getBounds(){
       return super.getBounds();
    }









}
