package com.input;/**
 * Created by Gustavo_Muratalla on 5/10/16.
 */
import com.entity.Bullet;
import com.entity.GameObject;
import com.entity.ID;
import com.entity.Player;
import com.game.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Main main;

    public int tempPhase = 0;

    public boolean[] keyDown = new boolean[5];
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

                if (key == KeyEvent.VK_W){
                    keyDown[0] = true;
                    if(Player.jump == 1) {
                        tempObject.setVelY(-7);
                        Player.jumping = true;
                        Player.still = true;
                        Player.jump = 0;
                    }
                }
                if (key == KeyEvent.VK_S) {
                    if (!Player.collided) {
                        keyDown[1] = true;
                        tempObject.setVelY(1);
                        Player.still = true;
                    }
                    Player.still = true;
                }
                if (key == KeyEvent.VK_D) {
                    keyDown[2] = true;
                    tempObject.setVelX(3);
                    Player.facing = 1;
                    if(keyDown[0] || Player.jumping) {
                        Player.still = true;
                        Player.walking = false;
                    } else if(!keyDown[0] || !Player.jumping && !Player.collided){
                        Player.still = false;
                        Player.walking = true;
                    }
                }
                if (key == KeyEvent.VK_A) {
                    keyDown[3] = true;
                    tempObject.setVelX(-3);
                    Player.facing = 0;
                    if(keyDown[0] || Player.jumping) {
                        Player.still = true;
                        Player.walking = false;
                    } else if(!keyDown[0] || !Player.jumping && !Player.collided){
                        Player.still = false;
                        Player.walking = true;
                    }
                }

                if(key == KeyEvent.VK_SPACE){
                    keyDown[4] = true;
                    Player.still = false;
                    Player.shoot = true;
                    if (Player.facing == 1)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, 5, 0, false));
                    else if (Player.facing == 0)
                        handler.addBullet(new Bullet(tempObject.getX(), tempObject.getY(), width, height, ID.Bullet, -5, 0, false));
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            main.gameState = Main.STATE.GameMenu;
            //System.exit(0);
        }
    }


    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //key Events for player 1

                if(key == KeyEvent.VK_W) {
                    if(Player.jump == 0) {
                        keyDown[0] = false;
                        Player.jumping = false;
                        Player.still = true;
                    }
                }
                if(key == KeyEvent.VK_SPACE){
                    keyDown[4] = false;
                    Player.shoot = false;
                    Player.still = true;
                }
                if(key == KeyEvent.VK_S) {
                    keyDown[1] = false;
                    Player.still = true;
                }
                if(key == KeyEvent.VK_D){
                    keyDown[2] = false;
                    Player.still = true;
                    Player.walking = false;
                }
                if(key == KeyEvent.VK_A) {
                    keyDown[3] = false;
                    Player.still = true;
                    Player.walking = false;
                }
                if(key == KeyEvent.VK_SPACE){
                    keyDown[4] = false;
                    Player.shoot = false;
                    Player.still = true;
                }
            }
        }
    }

}

