package com.input;/**
 * Created by APCS1 on 5/13/2016.
 */

import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private Handler handler;
    private KeyInput keyinput;

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int key = e.getClickCount();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //key Events for player 1

                if (e.getButton() == MouseEvent.MOUSE_CLICKED ) {
                    tempObject.setVelY(-10);
                    keyinput.keyDown[0] = true;
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
