package com.input;/**
 * Created by APCS1 on 5/13/2016.
 */

import com.entity.Bullet;
import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    public int width, height;

    public MouseInput(Handler handler){
        this.handler = handler;
    }
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {

                if (e.getButton() == e.BUTTON1) {
                    if(Player.facing == 1)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, 5, false));
                    else if(Player.facing == 0)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, -5, false));

                }
                if (e.getButton() == e.BUTTON3) {
                    //special = true;
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == e.BUTTON1) {
            //fire = false;
        }
        if (e.getButton() == e.BUTTON3) {
            //special = false;

        }

    }
}
