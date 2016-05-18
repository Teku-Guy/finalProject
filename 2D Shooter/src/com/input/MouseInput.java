package com.input;/**
 * Created by APCS1 on 5/13/2016.
 */

import com.entity.Bullet;
import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Player player;

    public int width, height;

    public MouseInput(Handler handler){
        this.handler = handler;

    }
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                GameObject playerObj = handler.object.get(i);

                int mY = e.getY();
                int mX = e.getX();
                int originX = (int)playerObj.getX();
                int originY = (int)playerObj.getY();
                double angle = Math.atan2(mX - originX, mY - originY);
                double bulletVelocity = 10.0;
                double xVelocity = (bulletVelocity) * Math.cos(angle);
                double yVelocity = (bulletVelocity) * Math.sin(angle);
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject tempObject = handler.object.get(j);

                    if (tempObject.getId() == ID.Player) {

                        if (e.getButton() == e.BUTTON1) {
                            Player.still = false;
                            if (Player.facing == 1) {
                                if(playerObj.getVelX() > xVelocity) {
                                    Player.shoot = true;
                                    handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, (int) -xVelocity, (int) -yVelocity, false));
                                }
                            }
                            else if (Player.facing == 0){
                                if(playerObj.getVelX() > xVelocity) {
                                    Player.shoot = true;
                                    handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, (int) xVelocity, (int) yVelocity, false));
                                }
                            }

                        }
                        if (e.getButton() == e.BUTTON3) {
                            //special = true;
                        }
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == e.BUTTON1) {
            Player.shoot = false;
        }
        if (e.getButton() == e.BUTTON3) {
            //special = false;

        }

    }
}
