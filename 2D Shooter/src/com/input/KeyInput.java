package com.input;

/**
 * Created by Gustavo_Muratalla on 5/10/16.
 */
import com.entity.Bullet;
import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public int tempPhase = 0;

    protected boolean[] keyDown = new boolean[5];
    private int width, height;


    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        keyDown[4] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //key Events for player 1

                if (key == KeyEvent.VK_W) {
                    keyDown[0] = true;
                    Player.jumping = true;
                    Player.still = false;
                }
                if (key == KeyEvent.VK_S) {
                    if (!Player.collided) {
                        keyDown[1] = true;
                        Player.still = false;
                    }
                }
                if (key == KeyEvent.VK_D) {
                    keyDown[2] = true;
                    Player.facing = 1;
                    Player.still = false;

                }
                if (key == KeyEvent.VK_A) {
                    keyDown[3] = true;
                    Player.facing = 0;
                    Player.still = false;
                }
                if(key == KeyEvent.VK_SPACE)
                    keyDown[4] = true;

                if(keyDown[0])
                    tempObject.setVelY(-15);

                if(keyDown[1])
                    tempObject.setVelY(1);

                if(keyDown[2])
                    tempObject.setVelX(3);

                if(keyDown[3])
                    tempObject.setVelX(-3);

                if(keyDown[4]) {
                    if (Player.facing == 1)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, 5, false));
                    else if (Player.facing == 0)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, -5, false));
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            //gameState = state.Menu;
            System.exit(0);
        }
    }


    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //key Events for player 1

                if(key == KeyEvent.VK_W)
                    keyDown[0] = false;
                    Player.jumping = false;
                    Player.still = true;
                if(key == KeyEvent.VK_S)
                    keyDown[1] = false;
                    Player.still = false;
                if(key == KeyEvent.VK_D){
                    keyDown[2] = false;
                    Player.still = true;
                }
                if(key == KeyEvent.VK_A) {
                    keyDown[3] = false;
                    Player.still = true;
                }
                if(key == KeyEvent.VK_SPACE){
                    keyDown[4] = false;
                    Player.shoot = false;
                    Player.still = true;
                }


                //vertical movement
               //if(keyDown[0] == false && !keyDown[1])
                   //tempObject .setVelY(0);
                //if(keyDown[2] == false && !keyDown[3])
                    //tempObject .setVelX(0);
            }
        }
    }

}

