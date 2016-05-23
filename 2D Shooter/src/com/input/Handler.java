package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;
import com.gui.Window;

import java.awt.Graphics;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    public static List<GameObject> object = new CopyOnWriteArrayList<GameObject>();
    public static List<Tile> tile = new CopyOnWriteArrayList<Tile>();
    public static List<Bullet> bullet = new CopyOnWriteArrayList<Bullet>();

    public static int killCount = 0;
    public static int points = 0;

    private int readMapWidth = Main.WIDTH / 2;
    private int readMapHeight = Main.HEIGHT / 2;

    private Bullet bull;
    private Main main;

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
        tile.clear();
        createLevel();


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
            addObject(new Zombie(r.nextInt(1000), 300, 32, 32, false, this, main, ID.Zombie));
            addObject(new Zombie(100+r.nextInt(1000), 300, 32, 32, false, this, main, ID.Zombie));
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

    public void kill(Zombie z) {
        object.remove(z);
        killCount++;
        points += 100;
        System.out.println(killCount);
    }

    public void clearBullet(Bullet b) {
        bullet.remove(b);
    }

    public void limitBullets(Bullet b) {
        if (bullet.size() > 5) {
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


    public void createLevel() {
        for (int i = 0; i <= 50; i++) {

            addTile(new Grass((i * 32), (int) (Main.window.frame.getHeight() * (.92)), 96, 96, true, ID.Tile));
        }
        for (int i = 0; i <= 22; i++) {
           // addTile(new Stone(0, (i * 32), 96, 96, true, ID.Tile));
            //addTile(new Lava(0, (i * 32), 96, 96, true, ID.Tile));
        }
        for (int i = 0; i < 3; i++) {
            addTile(new Stone((i * 32), (int) (Main.window.frame.getHeight() * (.5)), 96, 96, true, ID.Tile));
            addTile(new Stone((i*32) + 200, (int) (Main.window.frame.getHeight() * (.5)), 96, 96, true, ID.Tile));
            addTile(new Stone((i*32) + 600, (int) (Main.window.frame.getHeight() * (.75)), 96, 96, true, ID.Tile));
            addTile(new Stone((i*32) + 800, (int) (Main.window.frame.getHeight() * (.4)), 96, 96, true, ID.Tile));
            addTile(new Stone((i*32) + 1000, (int) (Main.window.frame.getHeight() * (.2)), 96, 96, true, ID.Tile));
            addTile(new Stone((i*32) + 1200, (int) (Main.window.frame.getHeight() * (.8)), 96, 96, true, ID.Tile));


        }

    }
}




