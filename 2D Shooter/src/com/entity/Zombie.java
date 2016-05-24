package com.entity;/**
 * Created by jessus on 5/11/16.
 */

import com.game.Main;
import com.input.Handler;

import java.awt.*;


public class Zombie extends GameObject{

    private Handler handler;
    private Player player;
    private Main main;

    private int phase = 0;
    private int facing = 0;
    private boolean still = false;
    private int counter = 0;
    private boolean jumping;
    public static boolean isDead = false;
    private boolean falling;


    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;

    public Zombie(float x, float y, int width, int height, boolean isDead, Handler handler, Main main, ID id){
        super(x , y, width, height, id);
        this.handler = handler;
        this.main = main;
        this.isDead = isDead;

    }

    public void chasePlayer(){
        x += velX;
        y += velY;


        float diffX = x - Main.player.getX();
        float diffY = y - Main.player.getY();
        float distance = (float) Math.sqrt( ( x - Main.player.getX() ) * ( x-Main.player.getX() ) + ( y-Main.player.getY() ) * ( y-Main.player.getY() ) );

        velX = ((-1/distance) * diffX);

        if(x == Main.player.getX() && y == Main.player.getX()){
            still = true;
        }
        if(x < Main.player.getX()){
            facing = 1;
        }
        else if(x > Main.player.getX()){
            facing = 0;
        }

        if(y < Main.player.y ){//&& distance >= 2) {
            for(int i = 0; i < Handler.object.size(); i++){
                if(Handler.object.get(i).getId() == ID.Zombie){
                    GameObject tempZombie = Handler.object.get(i);
                    tempZombie.velY = -1;
                }
            }
            jumping = true;

        }
        else if(y >= Main.player.y){
            for(int i = 0; i < Handler.object.size(); i++){
                if(Handler.object.get(i).getId() == ID.Zombie){
                    GameObject tempZombie = Handler.object.get(i);
                    tempZombie.velY += gravity;

                }
            }
            jumping = false;
        }



        y = Main.clamp((int)y, 0, Main.HEIGHT);
    }

    private void collision() {
        for (int i = 0; i < Handler.bullet.size(); i++) {
            Bullet tempBullet = Handler.bullet.get(i);
            if (getBounds().intersects(tempBullet.getBounds()) ||
                    getBoundsT().intersects(tempBullet.getBounds()) ||
                    getBoundsL().intersects(tempBullet.getBounds()) ||
                    getBoundsR().intersects(tempBullet.getBounds())) {
                handler.clearBullet(tempBullet);
                isDead = true;
                handler.kill(this);
                //System.out.println("Dead");
            }
        }
        for(int i = 0; i < handler.tile.size(); i++) {
            for (int j = 0; j < handler.object.size(); j++) {
                Tile tempTile = handler.tile.get(i);

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
                    else if(getBounds().intersects(tempTile.getBoundsT())){
                        velY = 0;
                        falling = true;

                    }
                }
            }
        }
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


        Graphics2D g2d = (Graphics2D) g;

        /*g.setColor(Color.white);
        g2d.draw(getBounds());
        g.setColor(Color.GREEN);
        g2d.draw(getBoundsT());
        g.setColor(Color.BLUE);
        g2d.draw(getBoundsR());
        g.setColor(Color.RED);
        g2d.draw(getBoundsL());*/
    }
    public void tick() {
        collision();
        chasePlayer();


    }
    public boolean isDead() {
        return isDead;
    }

}
