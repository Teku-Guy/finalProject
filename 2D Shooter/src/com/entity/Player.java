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
import com.sun.corba.se.impl.encoding.BufferManagerFactory;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import com.gui.HUD;


public class Player extends GameObject {

    private KeyInput key;
    private Handler handler = new Handler();

    public int phase = 0;
    public static int facing = 0;

    private int counter = 0 ;
    public static boolean still = true;
    public static boolean jumping = false;
    public static boolean shoot = false;
    public static boolean collided;

    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;


    public Player(float x, float y, int width, int height, Handler handler, ID id){
        super(x, y, width, height, id);
        this.handler = handler;

    }



    public void render(Graphics g) {

        counter++;
        if (counter % 10 == 0) {
            counter = 0;
            phase++;
            phase %= Main.PlayerWalkL.length;
        }

         if(facing == 0) {
             if (jumping) {
                 g.drawImage(Main.PJumpLeft.getBufferedImage(), (int)x, (int)y, null);

             } else if (still) {
                 g.drawImage(Main.PlayerWalkL[0].getBufferedImage(), (int)x, (int)y, null);

             } else {
                 g.drawImage(Main.PlayerWalkL[phase].getBufferedImage(), (int)x, (int)y, null);
             }
         }
         else if(facing == 1){ Player.shoot = true;
                 if(jumping){
                     g.drawImage(Main.PJumpRight.getBufferedImage(), (int)x,(int)y, null);

                 }else if(still){
                     g.drawImage(Main.PlayerWalkR[0].getBufferedImage(), (int)x, (int)y, null);
                 }
                 else{
                     g.drawImage(Main.PlayerWalkR[phase].getBufferedImage(), (int)x, (int)y, null);

                 }
         }


        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g2d.draw(getBounds());
        g.setColor(Color.GREEN);
        g2d.draw(getBoundsT());
        g.setColor(Color.BLUE);
        g2d.draw(getBoundsR());
        g.setColor(Color.RED);
        g2d.draw(getBoundsL());

    }
    public void move(){
        x += velX;
        y += velY;

        if (falling || jumping){
            velY += gravity;
        }
        if (velY > MAX_SPEED){
            velY = MAX_SPEED;
        }

        x = Main.clamp((int)x, 0, Main.WIDTH-31);
        y = Main.clamp((int)y, 0, Main.HEIGHT-53);
    }

   private void collision() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Zombie) {

                if (getBounds().intersects(tempObject.getBounds()) ||
                        getBoundsT().intersects(tempObject.getBounds()) ||
                        getBoundsL().intersects(tempObject.getBounds()) ||
                        getBoundsR().intersects(tempObject.getBounds()) ||

                        getBounds().intersects(tempObject.getBoundsT()) ||
                        getBoundsT().intersects(tempObject.getBoundsT()) ||
                        getBoundsL().intersects(tempObject.getBoundsT()) ||
                        getBoundsR().intersects(tempObject.getBoundsT()) ||

                        getBounds().intersects(tempObject.getBoundsL()) ||
                        getBoundsT().intersects(tempObject.getBoundsL()) ||
                        getBoundsL().intersects(tempObject.getBoundsL()) ||
                        getBoundsR().intersects(tempObject.getBoundsL()) ||

                        getBounds().intersects(tempObject.getBoundsR()) ||
                        getBoundsT().intersects(tempObject.getBoundsR()) ||
                        getBoundsL().intersects(tempObject.getBoundsR()) ||
                        getBoundsR().intersects(tempObject.getBoundsR())) {
                    HUD.HEALTH -= 1;
                }
            }
        }
        for(int i = 0; i < handler.tile.size(); i++) {
            for (int j = 0; j < handler.object.size(); j++) {
                Tile tempTile = handler.tile.get(i);
                //GameObject tempObject = handler.object.get(j);
                if (tempTile.getId() == ID.Tile) {
                    if (getBounds().intersects(tempTile.getBounds())) {
                        velY = 0;
                        falling = true;
                        jumping = false;
                        //collided = true;
                    }
                    else if(getBounds().intersects(tempTile.getBoundsR())){
                        velY = 0;
                        falling = true;
                        jumping = false;
                    }
                    else if(getBounds().intersects(tempTile.getBoundsL())){
                        velY = 0;
                        falling = true;
                        jumping = false;
                    }
                    else if(getBounds().intersects(tempTile.getBoundsB())){
                        velY = 0;
                        falling = true;
                        jumping = false;
                    }
                    else if(getBounds().intersects(tempTile.getBoundsT())){
                        velY = 0;
                        falling = true;

                    }
                }
            }
        }
    }

    public void shoot(){

    }

    public void tick() {
        move();
        collision();
    }








}
