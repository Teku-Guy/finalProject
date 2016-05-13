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

    private Handler handler;
    private Player player;

    private int phase = 0;
    private int facing = 0;


    private boolean walking = false;
    private boolean jumping = false;
    private boolean falling = true;
    private boolean still = false;

    public Zombie(float x, float y, int width, int height, ID id){
        super(x , y, width, height, id);
    }

    public void checkIfHit(){

    }
    public void chasePlayer(){
        x += velX;
        y += velY;

        float diffX = x - Main.player.getX() - 8;
        float diffY = y - Main.player.getY() - 8;
        float distance = (float) Math.sqrt( ( x - Main.player.getX() ) * ( x-Main.player.getX() ) + ( y-Main.player.getY() ) * ( y-Main.player.getY() ) );

        velX = ((-1/distance) * diffX);
        velY = ((-1/distance) * diffY);

        //handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        phase++;
        phase %= Main.ZWalkL.length;
        if(facing == 0) {
            if (still) {

                g.drawImage(Main.ZWalkL[0].getBufferedImage(), (int)x, (int)y, null);

            } else {
                Thread imageLoad = new Thread();
                imageLoad.start();
                try{
                    imageLoad.sleep(100);
                    g.drawImage(Main.ZWalkL[phase].getBufferedImage(), (int)x, (int)y, null);
                }catch(InterruptedException e){
                    System.out.println("Somethings up");
                }

            }
        }
        else if(facing == 1){
            if(still){
                g.drawImage(Main.ZWalkR[0].getBufferedImage(), (int)x, (int)y, null);
            }
            else{
                Thread imageLoad = new Thread();
                imageLoad.start();
                try{
                    imageLoad.sleep(100);
                    g.drawImage(Main.ZWalkR[phase].getBufferedImage(), (int)x, (int)y, null);
                }catch(InterruptedException e){
                    System.out.println("Somethings up!");
                }//

            }

        }
    }

    public void tick() {

        chasePlayer();

        checkIfHit();

    }

}
