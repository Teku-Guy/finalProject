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
    private String title = "2D Shooter";

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
            if(mouseOver(mx, my, 50, 450, 200, 64)){
                main.gameState = STATE.Game;
                //handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemys();
                //handler.addObject(new BasicEnemy((Main.WIDTH /2)-48, -120, ID.BasicEnemy, handler));

            }

            //help Button
            if(mouseOver(mx, my, 50, 550, 200, 64)){
                main.gameState = STATE.Help;
            }

            //quit button
            if(mouseOver(mx, my, 50, 650, 200, 64)){
                System.exit(1);
            }

        }

        else if(this.main.gameState == STATE.Help) {
           //back button
            if(mouseOver(mx, my, 50, 550, 200, 64)){
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
            Font fnt = new Font("arial", 1, 70);
            Font fnt2 = new Font("arail", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            int w2 = g.getFontMetrics().stringWidth(title) / 2;
            int h2 = g.getFontMetrics().getHeight();
            g.drawString(title, Main.WIDTH / 2 - w2, h2);
            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Play", 110, 490);
            g.drawRect(50, 450, 200, 64);

            g.drawString("Help", 110, 590);
            g.drawRect(50, 550, 200, 64);
            //Hello
            g.drawString("Quit", 110, 690);
            g.drawRect(50, 650, 200, 64);

        }else if(main.gameState == STATE.Help){

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arail", 1, 30);
            Font fnt3 = new Font("arail", 1, 15);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", Main.WIDTH / 2 - (g.getFontMetrics().stringWidth("Help") / 2), 100);

            g.setFont(fnt3);
            String s = "Use WASD keys on your keyboard to move your Player and Click your mouse to kill enemies!";
            int w = g.getFontMetrics().stringWidth(s) / 2;
            g.drawString(s, Main.WIDTH / 2 - w, 310);

            g.setFont(fnt2);
            g.drawRect(Main.WIDTH / 2-100, 550, 200, 64);
            g.drawString("Back", Main.WIDTH / 2 - (g.getFontMetrics().stringWidth("Back") / 2), 590);

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
