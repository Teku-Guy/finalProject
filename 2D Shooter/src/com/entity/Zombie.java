package com.entity;/**
 * Created by jessus on 5/11/16.
 */

import com.game.Main;
import com.input.Handler;
import com.entity.ID;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

import java.awt.*;


public class Zombie extends GameObject{

    private Handler handler = new Handler();
    private Player player;

    private int phase = 0;
    private int facing = 0;
    private boolean still = false;
    private int counter = 0;
    private boolean jumping = false;
    private boolean falling = true;


    private float gravity = 0.1f;
    private final float MAX_SPEED = 10;

    public Zombie(float x, float y, int width, int height, ID id){
        super(x , y, width, height, id);
    }

    public void checkIfHit(){


    }
    public void chasePlayer(){
        x += velX;
        y += velY;

        float diffX = x - Main.player.getX();
        float diffY = y - Main.player.getY();
        float distance = (float) Math.sqrt( ( x - Main.player.getX() ) * ( x-Main.player.getX() ) + ( y-Main.player.getY() ) * ( y-Main.player.getY() ) );

       velX = ((-1/distance) * diffX);
        velY = ((-1/distance) * diffY);

        if(x == Main.player.getX() && y == Main.player.getX()){
            still = true;
        }
        if(x < Main.player.getX()){
            facing = 1;
        }
        else if(x > Main.player.getX()){
            facing = 0;
        }
        if(y > Main.player.getVelY()){
            jumping = true;
        }
        if (falling || jumping){
            velY += gravity;
        }





       // x = Main.clamp((int)x, 0, Main.WIDTH);
        y = Main.clamp((int)y, 0, Main.HEIGHT);


       // handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        counter++;
        if (counter % 15 == 0) {
            counter = 0;
            phase++;
            phase %= Main.ZWalkL.length;
        }
        if(facing == 0) {
            if (still) {

                g.drawImage(Main.ZWalkL[0].getBufferedImage(), (int)x, (int)y, null);

            } else {
                g.drawImage(Main.ZWalkL[phase].getBufferedImage(), (int)x, (int)y, null);
            }
        }
        else if(facing == 1){
            if(still){
                g.drawImage(Main.ZWalkR[0].getBufferedImage(), (int)x, (int)y, null);
            }
            else{
                g.drawImage(Main.ZWalkR[phase].getBufferedImage(), (int)x, (int)y, null);

            }

        }
    }

    public void tick() {
        chasePlayer();

        checkIfHit();

    }

}
