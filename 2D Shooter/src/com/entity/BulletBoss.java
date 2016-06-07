package com.entity;

import com.game.Main;

import java.awt.*;

/**
 * Created by APCS1 on 6/6/2016.
 */
public class BulletBoss extends Bullet {

    public BulletBoss(float x, float y, int width, int height, ID id, int velX, int velY, boolean ifShotHits) {
        super(x, y, width, height, id, velX, velY, ifShotHits);
    }
    public void render(Graphics g) {
        g.drawImage(Main.BBullet.getBufferedImage(), (int) x, (int) y, width, height, null);
    }
}
