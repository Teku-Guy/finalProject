package com.entity;/**
 * Created by jessus on 5/14/16.
 */

import com.game.Main;
import com.input.Handler;

import java.awt.*;

public class Bullet extends GameObject {

    public float velY, velX;

    public ID id;

    public boolean ifShotHits = false;


    public Bullet(float x, float y, int width, int height, ID id, int velX, int velY, boolean ifShotHits) {
        super(x, y, width, height, id);

        this.x = x;
        this.y = y;
        this.id = id;
        this.velX = velX;
        this.velY = velY;
        this.ifShotHits = ifShotHits;
    }

    public void render(Graphics g) {
        g.drawImage(Main.Bullet.getBufferedImage(), (int) x, (int) y, null);
    }

    public void collisionWithBlock() {
        for (int i = 0; i < Handler.tile.size(); i++)
            for (int j = 0; j < Handler.bullet.size(); j++) {
                Bullet tempBullet = Handler.bullet.get(j);
                if (tempBullet.getBounds().intersects(Handler.tile.get(i).getBounds())) {
                    Handler.bullet.remove(j);
                    j--;
                }
            }
    }

    public void tick() {
        x += velX;
        y += velY;
        collisionWithBlock();
    }

    public void hit() {
        this.ifShotHits = true;
    }

}
