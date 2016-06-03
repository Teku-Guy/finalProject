package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/10/16.
 */
import java.awt.Graphics;


import com.entity.tiles.Tile;
import com.input.KeyInput;
import com.game.Main;
import com.input.Handler;

import com.gui.HUD;


public class Player extends GameObject {

    private Handler handler = new Handler();

    public int phaseWalking = 0;
    public int phaseShoot = 0;
    public static int facing = 0;
    public KeyInput key;

    private int counter = 0;
    public static boolean still = true;
    public static int jump = 0;
    public static boolean jumping = false;
    public static boolean shoot = false;
    public static boolean walking = true;
    public static boolean collided;

    private float gravity = 0.1f;
    private final float MAX_SPEED = 10;


    public Player(float x, float y, int width, int height, Handler handler, ID id) {
        super(x, y, width, height, id);
        this.handler = handler;
    }


    public void render(Graphics g) {

        counter++;
        if (counter % 10 == 0) {
            counter = 0;
            phaseWalking++;
            phaseShoot++;
            phaseShoot %= Main.PlayerShootL.length;
            phaseWalking %= Main.PlayerWalkL.length;
        }

        if (facing == 0) {
            if (jumping) {
                g.drawImage(Main.PJumpLeft.getBufferedImage(), (int) x, (int) y, null);

            } else if (still) {
                g.drawImage(Main.PlayerWalkL[0].getBufferedImage(), (int) x, (int) y, null);

            }
            if (shoot) {
                g.drawImage(Main.PlayerShootL[phaseShoot].getBufferedImage(), (int) x, (int) y, null);
            } else if (walking) {
                g.drawImage(Main.PlayerWalkL[phaseWalking].getBufferedImage(), (int) x, (int) y, null);
            }
        } else if (facing == 1) {
            if (jumping) {
                g.drawImage(Main.PJumpRight.getBufferedImage(), (int) x, (int) y, null);

            } else if (still) {
                g.drawImage(Main.PlayerWalkR[0].getBufferedImage(), (int) x, (int) y, null);
            }

            if (shoot) {
                g.drawImage(Main.PlayerShootR[phaseShoot].getBufferedImage(), (int) x, (int) y, null);
            } else if (walking) {
                g.drawImage(Main.PlayerWalkR[phaseWalking].getBufferedImage(), (int) x, (int) y, null);

            }

        }


        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.cyan);
        g2d.draw(getBounds());

        Rectangle rect = getBounds();
        Rectangle top = new Rectangle(rect.x+(15/2), rect.y, rect.width-15, 5);
        Rectangle bottom = new Rectangle(rect.x+(15/2), rect.y + rect.height - 5, rect.width-15, 5);
        Rectangle left = new Rectangle(rect.x, rect.y+(15/2), 5, rect.height-15);
        Rectangle right = new Rectangle(rect.x + rect.width - 5, rect.y+(15/2), 5, rect.height-15);
    /*    g.setColor(Color.red);
        g2d.draw(top);
        g.setColor(Color.blue);
        g2d.draw(bottom);
        g.setColor(Color.green);
        g2d.draw(left);
        g.setColor(Color.white);
        g2d.draw(right);*/


    }

    public void move() {
        x += velX;
        y += velY;

        velY += gravity;
        if (velY > MAX_SPEED) {
            velY = MAX_SPEED;
        }


        //x = Main.clamp((int) x, 0, Main.WIDTH);
        y = Main.clamp((int) y, 0, Main.HEIGHT - 53);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Zombie) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= .5;
                }
            }
        }
        for (int i = 0; i < handler.tile.size(); i++) {
            Tile tempTile = handler.tile.get(i);
            Tile tempLava;
            Tile tempHealth;
            if(tempTile.getId() == ID.Lava){
                 tempLava = handler.tile.get(i);
                 Rectangle lavaRect = tempLava.getBounds();
                 if(getBounds().intersects(lavaRect)){
                     HUD.HEALTH -= .25;

                 }

            }
            if(tempTile.getId() == ID.HealthPack){
                tempHealth = handler.tile.get(i);
                Rectangle healthRect = tempHealth.getBounds();
                if(getBounds().intersects(healthRect)){
                    HUD.HEALTH = 100;
                    handler.tile.remove(i);
                    i--;
                    System.err.println("Removed health pack.");
                }
            }
            if (tempTile.getId() == ID.Bounds) {
                if (getBounds().intersects(tempTile.getBounds()) || getBounds().intersects(tempTile.getBounds()))
                    velX = 0;
                collided = true;
            }

            if (tempTile.getId() == ID.Tile) {
                Rectangle rect = getBounds();
                Rectangle tileRect = tempTile.getBounds();
                if (!rect.intersects(tileRect)) {
                    continue;
                }
                jump = 1;
                collideTop(tileRect);
                collideBottom(tileRect);
                collideLeft(tileRect);
                collideRight(tileRect);

            }
        }
    }

    private void collideLeft(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle left = new Rectangle(rect.x, rect.y+(15/2), 5, rect.height-15);
        if (left.intersects(tileRect)) {
            x = (float)(tileRect.getX() + (width -12));
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
            velY = 0;
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
            y = (float)tileRect.getY() + (float)(height);
            velY = 1;
            jump = 0;
            falling = true;
            jumping = false;
            //collided = true;
        }
    }

    public void tick() {
        move();
        collision();



    }


}
