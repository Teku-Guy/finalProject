package com.input;

/**
 * Created by Gustavo_Muratalla on 5/10/16.
 */
import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public int tempPhase = 0;
    protected boolean[] keyDown = new boolean[4];


    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                //key Events for player 1

                if(key == KeyEvent.VK_W){
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                    Player.jumping = true;
                    Player.still = false;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                    Player.still = false;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(5);
                    keyDown[2] = true;
                    Player.facing = 1;
                    Player.still = false;

                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-5);
                    keyDown[3] = true;
                    Player.facing = 0;
                    Player.still = false;
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
                   // tempObject.setVelY(0);
                    Player.jumping = false;
                    keyDown[0] = false;
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


                //vertical movement
               if(keyDown[0] == false && !keyDown[1])
                   tempObject .setVelY(0);
                if(keyDown[2] == false && !keyDown[3])
                    tempObject .setVelX(0);
            }
        }
    }

}

