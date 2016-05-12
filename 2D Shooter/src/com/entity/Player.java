package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/10/16.
 */
import java.awt.Graphics;

import com.input.KeyInput;
import com.game.Main;
import com.input.Handler;
import com.entity.ID;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.input.KeyInput;


public class Player extends GameObject {

    public int phase = 0;
    public static int facing = 0;


    public boolean walking = false;
    public boolean jumping = false;
    public boolean falling = true;
    public static boolean still = true;




    private KeyInput key;
    private Handler handler = new Handler();

    public Player(int x, int y, int width, int height, ID id){
        super(x, y, width, height, id);
    }




    public void render(Graphics g) {
        phase++;
        phase %= Main.PlayerWalkL.length;
         if(facing == 0) {
             if (jumping) {
                 g.drawImage(Main.PJumpLeft.getBufferedImage(), x, y, null);

             } else if (still) {

                    g.drawImage(Main.PlayerWalkL[0].getBufferedImage(), x, y, null);

             } else {
                 Thread imageLoad = new Thread();
                 imageLoad.start();
                 try{
                     imageLoad.sleep(100);
                     g.drawImage(Main.PlayerWalkL[phase].getBufferedImage(), x, y, null);
                 }catch(InterruptedException e){
                     System.out.println("Somethings up");
                 }

             }
         }
         else if(facing == 1){
                 if(jumping ){
                     g.drawImage(Main.PJumpRight.getBufferedImage(), x,y, null);

                 }else if(still){
                     g.drawImage(Main.PlayerWalkR[0].getBufferedImage(), x, y, null);
                 }
                 else{
                     Thread imageLoad = new Thread();
                     imageLoad.start();
                     try{
                         imageLoad.sleep(100);
                         g.drawImage(Main.PlayerWalkR[phase].getBufferedImage(), x, y, null);
                     }catch(InterruptedException e){
                         System.out.println("Somethings up!");
                     }

                 }

         }
    }
    public void move(){
        x += velX;
        y += velY;
    }
    public void checkCollisionWithEnemy(){
        for(GameObject object : handler.object){

        }

    }

    public void checkCollisionWithTile(){
        for(Tile t : handler.tile){

            if(!t.isSolid())
                continue;

            if(t.getID() == ID.Tile){

                if(getBoundsT().intersects(t.getBounds())){
                    setVelY(0);

                    if(jumping){
                        jumping = false;
                        falling = true;
                    }


                }
            }
            else if(!falling && jumping){
                falling = true;
            }

            if(getBoundsB().intersects(t.getBounds())){
                setVelY(0);

                if(falling)
                {
                    falling = false;
                }
                if(getBoundsL().intersects(t.getBounds())){
                    setVelX(0);

                    x = t.getX() + t.width;
                }
                if(getBoundsR().intersects(t.getBounds())){
                    setVelX(0);

                    x = t.getX() - t.width;

                }
            }


        }

    }




    public void tick() {
        move();
        checkCollisionWithTile();



    }








}
