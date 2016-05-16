package com.gui;/**
 * Created by Gustavo_Muratalla on 5/13/16.
 */
import com.game.Main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static float HEALTH = 100;
    private float greenValue = 255f;

    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = Main.clamp(HEALTH, 0, 1000);
        greenValue = Main.clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;


        //score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(20, 20, 200, 32);
        g.setColor(new Color(75, (int) greenValue, 0));
        g.fillRect(20, 20, (int) HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(20, 20, 200, 32);

        //g.drawString("Score: "+ score, 15, 64);
        //g.drawString("Level: "+ level, 15, 80);
    }
}