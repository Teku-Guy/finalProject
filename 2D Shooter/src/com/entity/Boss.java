package com.entity;

import com.entity.tiles.Tile;
import com.game.Main;
import com.input.Handler;

import java.awt.*;

/**
 * Created by APCS1 on 6/3/2016.
 */
public class Boss extends GameObject {
    private Player player;
    private Main main;

    private int phase = 0;
    private int facing = 0;
    private boolean still = false;
    private int counter = 0;
    private int shootCount = 0;
    private boolean jumping;
    private boolean shoot = false;
    private float gravity = 0.1f;
    private final float MAX_SPEED = 10;
    private int bossHealth = 1000;
    private Handler handler = new Handler();
    public static boolean isDead = false;


    public Boss(float x, float y, int width, int height, boolean isDead, Handler handler, Main main, ID id){
        super(x, y, width, height, id);
        this.handler = handler;
        this.main = main;
        this.isDead = isDead;
    }




    public void chasePlayer() {
        x += velX;
        y += velY;


        float diffX = x - Main.player.getX();
        float diffY = y - Main.player.getY();
        float distance = (float) Math.sqrt((x - Main.player.getX()) * (x - Main.player.getX()) + (y - Main.player.getY()) * (y - Main.player.getY()));

        velX = ((-1 / distance) * diffX);

        if (x == Main.player.getX() && y == Main.player.getX()) {
            still = true;
        }
        if (x < Main.player.getX()) {
            facing = 1;
        } else if (x > Main.player.getX()) {
            facing = 0;
        }

        velY += gravity;
        if ((y - 100) > Main.player.y && !jumping) {//&& distance >= 2) {
            velY = -5;
            jumping = true;
        } else if (y < Main.player.y) {
            jumping = false;
        }
        double playerY = Main.player.y + Main.player.height;
        double yOff = y + height;
        double yMax = yOff + 0.1;
        double yMin = yOff - 0.1;
        if(yMin < playerY && playerY < yMax){
            shoot = true;
            handler.addBullet(new BulletBoss(getX(), getY(), width, height, ID.Bullet, 5, 0, false));

        }


        y = Main.clamp((int) y, 0, Main.HEIGHT);
    }

    private void collision() {
        for (int i = 0; i < Handler.bullet.size(); i++) {
            Bullet tempBullet = Handler.bullet.get(i);
            if (tempBullet instanceof BulletBoss) {
                continue;
            }
            if (getBounds().intersects(tempBullet.getBounds())) {
                handler.clearBullet(tempBullet);
                bossHealth -= 100;
                if(bossHealth == 0){
                    handler.kill(this);
                }
                isDead = true;
                //System.out.println("Dead");
            }
        }
        for (int i = 0; i < handler.tile.size(); i++) {
            Tile tempTile = handler.tile.get(i);

            if (tempTile.getId() == ID.Tile) {
                Rectangle rect = getBounds();
                Rectangle tileRect = tempTile.getBounds();
                if (!rect.intersects(tileRect)) {
                    continue;
                }
                collideBottom(tileRect);
                collideTop(tileRect);
                collideLeft(tileRect);
                collideRight(tileRect);

            }
        }
    }

    private void collideLeft(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle left = new Rectangle(rect.x, rect.y+(15/2), 5, rect.height-15);
        if (left.intersects(tileRect)) {
            x = (float)(tileRect.getX() + (width-12));
            velX = 0;
            falling = true;
            jumping = false;

        }
    }

    private void collideRight(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle right = new Rectangle(rect.x + rect.width - 5, rect.y+(15/2), 5, rect.height-15);
        if (right.intersects(tileRect)) {
            x = ((float)tileRect.getX() - width);
            velX = 0;
            falling = true;
            jumping = false;
        }
    }
    private void collideBottom(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle bottom = new Rectangle(rect.x+(15/2), rect.y + rect.height - 5, rect.width-15, 5);
        if (bottom.intersects(tileRect)) {
            y = (float)tileRect.getY() - height;
            velY = 0;
            falling = true;
        }
    }

    private void collideTop(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle top = new Rectangle(rect.x+(15/2), rect.y, rect.width-15, 5);
        if (top.intersects(tileRect)) {
            y = (float)tileRect.getY() + (float)(height/1.125);
            velY = 1;
            falling = true;
            jumping = false;
            //collided = true;
        }
    }

    public void render(Graphics g) {
        counter++;
        if (counter % 15 == 0) {
            counter = 0;
            phase++;
            shootCount++;
            phase %= Main.ZWalkL.length;
            shootCount %= Main.BossShootL.length;
        }
        if (facing == 0) {
            if (still) {

                g.drawImage(Main.BWalkL[0].getBufferedImage(), (int) x, (int) y, null);

            } else {
                g.drawImage(Main.BWalkL[phase].getBufferedImage(), (int) x, (int) y, null);
            }
        } else if (facing == 1) {
            if (still) {
                g.drawImage(Main.BWalkR[0].getBufferedImage(), (int) x, (int) y, null);
            } else {
                g.drawImage(Main.BWalkR[phase].getBufferedImage(), (int) x, (int) y, null);

            }
           if(shoot){
               if(Main.player.getX() > getX()){
                   g.drawImage(Main.BossShootR[shootCount].getBufferedImage(), (int) x, (int) y, null);
            }

               if(Main.player.getX() < getX()){
                   g.drawImage(Main.BossShootL[shootCount].getBufferedImage(), (int) x, (int) y, null);
               }

        }







    }
}
    public void tick() {
        collision();
        chasePlayer();

    }

    public boolean isDead() {
        return isDead;
    }
}


