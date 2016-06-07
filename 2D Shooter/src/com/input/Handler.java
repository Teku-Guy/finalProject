package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.entity.tiles.*;
import com.game.Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    public static List<GameObject> object = new CopyOnWriteArrayList<GameObject>();
    public static List<Tile> tile = new CopyOnWriteArrayList<Tile>();
    public static List<Bullet> bullet = new CopyOnWriteArrayList<Bullet>();

    public static int killCount = 0;
    public static int points = 0;
    public static int levelCount = 2;

    private int readMapWidth = Main.WIDTH / 2;
    private int readMapHeight = Main.HEIGHT / 2;

    private Bullet bull;
    private Main main;

    public int counterp = 1;

    private int counter = 0;
    private Random r = new Random();
    public Tile maps[] = new Tile[10];

    public void tick() {


        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
        for (int i = 0; i < bullet.size(); i++) {
            Bullet bulletObject = bullet.get(i);
            bulletObject.tick();

        }


    }

    public void render(Graphics g) {
        /*System.out.println(levelCount);
        if (levelCount == 1) {
            LoadImageLevel(Main.level1);
        } else if (levelCount == 2) {
            LoadImageLevel(Main.level2);
        } else if (levelCount == 3) {
            LoadImageLevel(Main.level3);
        }*/

        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
        for (int i = 0; i < bullet.size(); i++) {
            Bullet tempBullet = bullet.get(i);
            tempBullet.render(g);
            limitBullets(tempBullet);
        }
        for (int i = 0; i < tile.size(); i++) {
            Tile tempTile = tile.get(i);
            tempTile.render(g);
        }


    }

    public void makeWave(int enemy_count) {
        for (int i = 0; i < (enemy_count); i++) {
            addObject(new Zombie(64 + r.nextInt(1000), (int) (Main.window.frame.getHeight() * (.89) - 96), 64, 64, false, this, main, ID.Zombie));
            addObject(new Zombie(100 + r.nextInt(1000), (int) (Main.window.frame.getHeight() * (.89) - 96), 64, 64, false, this, main, ID.Zombie));
        }
        if(levelCount == 2){
            addObject(new Boss(2960, (int) (Main.window.frame.getHeight() * (.89) - 96), 192, 192 ,false, this, main, ID.Boss));
        }
    }

    public void clearEnemies() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() != ID.Player) {
                object.remove(i);
                i--;

//                //This removes the player after they lost
//                if(Main.gameState != Main.STATE.End)
//                    addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), 32, 32, this,ID.Player));
            }
        }
    }

    public void kill(GameObject z) {
        object.remove(z);
        killCount++;
        points += 100;
        System.out.println(killCount);
    }

    public void clearBullet(Bullet b) {
        bullet.remove(b);
    }

    public void limitBullets(Bullet b) {
        if (bullet.size() > 10) {
            for (int i = 0; i < bullet.size(); i++)

                bullet.remove(i);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void addBullet(Bullet bullet) {
        this.bullet.add(bullet);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }


    public void addTile(Tile newTile) {
        tile.add(newTile);
    }

    public void LoadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        //System.out.println("Width: " + w + "Height: "+ h);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 0 && green == 255 && blue == 0)
                    addTile(new Grass((i * 51), (j * 54), 51, 54, true, ID.Tile));

                if (red == 0 && green == 0 && blue == 255)
                    addTile(new FloatPlat((i * 51), (j * 54), 51, 54, true, ID.Tile));

                if (red == 255 && green == 255 && blue == 255)
                    addTile(new Stone((i * 51), (j * 54), 51, 54, true, ID.Tile));

                if(red == 255 && green == 255 && blue == 0)
                    addTile(new HealthPack((i * 51), (j * 54), 32, 32, true, ID.HealthPack));

                if(red == 255 && green == 0 && blue == 0)
                    addTile(new Lava((i * 51), (j * 54), 51, 54, true, ID.Lava));

                if (counterp == 1) {
                    if (red == 100 && green == 100 && blue == 100) {
                        Main.player.setX(i * 51);
                        Main.player.setY(j * 54);
                        counterp--;
                    }
                }
            }
        }

    }

}




