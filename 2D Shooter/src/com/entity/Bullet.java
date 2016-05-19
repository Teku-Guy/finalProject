package com.entity;/**
 * Created by jessus on 5/14/16.
 */

import com.game.Main;
import com.input.Handler;

import java.awt.*;

public class Bullet extends GameObject {
    public float x, y;

    public int width, height;

    public float velY, velX;

    public ID id;

    public boolean ifShotHits = false;
    private Handler handler;

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

    public void collisoinWithBlock() {
        for (int i = 0; i < Handler.tile.size(); i++)
            for (int j = 0; j < Handler.bullet.size(); j++) {
                Bullet tempBullet = Handler.bullet.get(j);
                if (tempBullet.getBounds().intersects(Handler.tile.get(i).getBounds())
                        || tempBullet.getBounds().intersects(Handler.tile.get(i).getBoundsT())
                        || tempBullet.getBounds().intersects(Handler.tile.get(i).getBoundsB())
                        || tempBullet.getBounds().intersects(Handler.tile.get(i).getBoundsR())
                        || tempBullet.getBounds().intersects(Handler.tile.get(i).getBoundsL()))
                    Handler.bullet.remove(j);
            }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) getX() + 20, (int) getY() + 25, 20, 15);
    }

    public void tick() {
        x += velX;
        y += velY;
        collisoinWithBlock();
    }

    public float getX() {
        return x;
    }

    public void hit() {
        this.ifShotHits = true;
    }

    public float getY() {
        return y;
    }
}
