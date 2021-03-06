package com.gui;/**
 * Created by Gustavo_Muratalla on 5/13/16.
 */
import com.entity.Player;
import com.game.Main;
import com.input.Handler;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static float HEALTH = 100;
    private Main main;
    private float greenValue = 255f;

    private float xOffset, yOffset;

    public HUD(float x, float y) {
        this.xOffset = x;
        this.yOffset = y;
    }

    public void tick() {
        HEALTH = Main.clamp(HEALTH, 0, 1000);
        greenValue = Main.clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect((int) (Main.player.getX() + xOffset), (int) (Main.player.getY() + yOffset), 100, 16);

        g.setColor(new Color(75, (int) greenValue, 0));
        g.fillRect((int)(Main.player.getX() + xOffset), (int) (Main.player.getY() + yOffset), (int) HEALTH, 16);

        g.setColor(Color.WHITE);
        g.drawRect((int) (Main.player.getX() + xOffset), (int) (Main.player.getY() + yOffset), 100, 16);

        g.drawString("Points : " + Handler.points , (int)(Main.player.getX() + xOffset), (int)(Main.player.getY() + yOffset)-10);

        //g.drawString("Score: "+ score, 15, 64);g.drawString("Points : " + Handler.points , 200, 300);
        //g.drawString("Level: "+ level, 15, 80);
    }
    public int getScore(){
        return Handler.points;
    }
}
