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
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.LinkedList;
import java.util.Objects;


public class Player extends GameObject {

    private KeyInput key;
    private Handler handler = new Handler();

    public int frame = 0;
    public int frameDelay = 0;
    public boolean isWalking = false;

    private Handler handle;

    public static int facing = 0;

    private float gravity = 0.05f;
    private final float MAX_SPEED = 10;


    public Player(float x, float y, int width, int height,boolean solid, ID id, Handler handler){
        super(x, y, width, height, solid, id, handler);
        //this.handler = handler;
    }




    public void render(Graphics g) {

        //phase++;
        //phase %= Main.PlayerWalkL.length;
         if(facing == 0) {
             if (jumping) {
                 g.drawImage(Main.PJumpLeft.getBufferedImage(), (int)x, (int)y, null);

             } else if (still) {

                    g.drawImage(Main.PlayerWalkL[0].getBufferedImage(), (int)x, (int)y, null);

             } else {
                 Thread imageLoad = new Thread();
                 imageLoad.start();
                 //try{
                   //  imageLoad.sleep(100);
                     g.drawImage(Main.PlayerWalkL[frame].getBufferedImage(), (int)x, (int)y, null);
                 //}catch(InterruptedException e){
                  //   System.out.println("Somethings up");
                // }

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
                     //try{
                      //   imageLoad.sleep(100);
                         g.drawImage(Main.PlayerWalkR[frame].getBufferedImage(), (int)x, (int)y, null);
                     //}catch(InterruptedException e){
                     //    System.out.println("Somethings up!");
                     //}

                 }

         }


        Graphics2D g2d = (Graphics2D) g;

        //g.setColor(Color.black);
        //g2d.draw(getBounds());
        //g2d.draw(getBoundsTop());

    }
    public void move(){
        x += velX;
        y += velY;

        if (falling || jumping){
            velY+= gravity;
        }
        if (velY > MAX_SPEED){
            velY = MAX_SPEED;
        }

        for (Tile t: handler.tiles) {
            if(!t.isSolid())
                continue;
            if(t.getID() == ID.wall){
                if(getBoundsTop().intersects(t.getBounds())){
                    setVelY(0);

                    if (jumping) {
                        jumping = false;
                        gravity= 0.5f;
                        falling = true;
                    }
                }

                else if(!falling&& !jumping){
                    gravity = 0.5f;
                }
                if (getBoundsBottom().intersects(t.getBounds())){
                    setVelY(0);

                    if (falling)
                        falling = false;
                }

                if (getBoundsLeft().intersects(t.getBounds())){
                    setVelY(0);

                    x = t.getX() - t.width;
                }
            }
        }

        //x = Main.clamp((int)x, 0, Main.WIDTH-31);
        //y = Main.clamp((int)y, 0, Main.HEIGHT-53);
    }

    private void collision(){

        for (int i= 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            /*if(tempObject.getId() == ID.Player){
                if(getBounds().intersects(tempObject.getBounds())) {
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
            }*/
            if(tempObject.getId() == ID.Zombie){
                //collision code
                if(getBounds().intersects(tempObject.getBounds())){
                    //HUD.HEALTH -= 2;
                }
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x+22, (int)y+23, 25, height);
    }
    public Rectangle getBoundsTop(){
        return new Rectangle((int)x+22, (int)y,  25, 23);
    }

    public void tick() {
        move();
        collision();
    }








}
