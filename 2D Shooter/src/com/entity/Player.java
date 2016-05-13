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
import java.util.LinkedList;



public class Player extends GameObject {

    private KeyInput key;
    private Handler handler = new Handler();

    public int phase = 0;
    public static int facing = 0;

    public boolean walking = false;
    public boolean jumping = false;
    public boolean falling = true;
    public static boolean still = true;

    public Player(float x, float y, int width, int height, ID id){
        super(x, y, width, height, id);
    }




    public void render(Graphics g) {

        phase++;
        phase %= Main.PlayerWalkL.length;
         if(facing == 0) {
             if (jumping) {
                 g.drawImage(Main.PJumpLeft.getBufferedImage(), (int)x, (int)y, null);

             } else if (still) {

                    g.drawImage(Main.PlayerWalkL[0].getBufferedImage(), (int)x, (int)y, null);

             } else {
                 Thread imageLoad = new Thread();
                 imageLoad.start();
                 try{
                     imageLoad.sleep(100);
                     g.drawImage(Main.PlayerWalkL[phase].getBufferedImage(), (int)x, (int)y, null);
                 }catch(InterruptedException e){
                     System.out.println("Somethings up");
                 }

             }
         }
         else if(facing == 1){
                 if(jumping ){
                     g.drawImage(Main.PJumpRight.getBufferedImage(), (int)x,(int)y, null);

                 }else if(still){
                     g.drawImage(Main.PlayerWalkR[0].getBufferedImage(), (int)x, (int)y, null);
                 }
                 else{
                     Thread imageLoad = new Thread();
                     imageLoad.start();
                     try{
                         imageLoad.sleep(100);
                         g.drawImage(Main.PlayerWalkR[phase].getBufferedImage(), (int)x, (int)y, null);
                     }catch(InterruptedException e){
                         System.out.println("Somethings up!");
                     }

                 }

         }
    }
    public void move(){
        x += velX;
        y += velY;

        x = Main.clamp((int)x, 0, Main.WIDTH-31);
        y = Main.clamp((int)y, 0, Main.HEIGHT-53);
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

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Zombie){
                //collision code
                if(getBounds().intersects(tempObject.getBounds())){
                    //HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void tick() {
        move();
        collision();
        checkCollisionWithTile();
    }








}
