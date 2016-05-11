package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.Main;
import com.input.Handler;
import com.game.Main.STATE;

/**
 *
 * @author Gustavo_Muratalla
 */
public class Menu extends MouseAdapter{

    private Main main;
    private Handler handler;
    //private HUD hud;
    private Random r = new Random();

    public Menu(Main main, Handler handler){ //, HUD hud){
        this.main = main;
        //this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(this.main.gameState == STATE.Menu){
            //play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                main.gameState = STATE.Game;
                //handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemys();
                //handler.addObject(new BasicEnemy((Main.WIDTH /2)-48, -120, ID.BasicEnemy, handler));

            }

            //help Button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                main.gameState = STATE.Help;
            }

            //quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);
            }

        }

        else if(this.main.gameState == STATE.Help) {
           //back button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                main.gameState = STATE.Menu;
                return;
            }
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(main.gameState == STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arail", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("2D Shooter", ((Main.WIDTH / 2) - 30), 30);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Play", 270, 190);
            g.drawRect(210, 150, 200, 64);

            g.drawString("Help", 270, 290);
            g.drawRect(210, 250, 200, 64);

            g.drawString("Quit", 270, 390);
            g.drawRect(210, 350, 200, 64);
        }else if(main.gameState == STATE.Help){

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arail", 1, 30);
            Font fnt3 = new Font("arail", 1, 15);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys on your keyboard to move your Player and dodge enemies!", 30, 210);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);

        }else if(main.gameState == STATE.End){

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arail", 1, 30);
            Font fnt3 = new Font("arail", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 170, 70);

            //g.setFont(fnt3);
            // g.drawString("You lost! Your Score: " + ((int)hud.getScore()), 190, 210);

            g.setFont(fnt2);
            g.drawRect(110, 350, 200, 64);
            g.drawString("Try Again", 135, 390);

            g.setFont(fnt2);
            g.drawRect(330, 350, 200, 64);
            g.drawString("Quit", 395, 390);

        }

    }

}
