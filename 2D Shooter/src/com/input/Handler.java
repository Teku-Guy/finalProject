package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;
import com.gui.Window;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Handler {

    public static LinkedList<GameObject> object = new LinkedList<GameObject>();
    public static LinkedList<Tile> tile = new LinkedList<Tile>();
    public static LinkedList<Bullet> bullet = new LinkedList<Bullet>();
    public static int killCount = 0;
    public static int points = 0;

    private Window window;

    private Bullet bull;
    private Main main;

    private int counter = 0;
    private Random r = new Random();
    public Tile maps[] = new Tile[10];

    public void tick(){


        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
        for(int i = 0; i < bullet.size(); i++){
            Bullet bulletObject = bullet.get(i);
            bulletObject.tick();

        }

    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
        for(int i = 0; i < bullet.size(); i++){
            Bullet tempBullet = bullet.get(i);
            tempBullet.render(g);
        }
        for(int i = 0 ; i < tile.size(); i++){
            Tile tempTile = tile.get(i);
            tempTile.render(g);
        }
        counter++;
        if(counter % 100 == 0){
            counter = 0;
            createLevel();
        }


    }

    public void makeWave(int enemy_count){
        for(int i = 0; i < enemy_count; i++){
            addObject(new Zombie(r.nextInt(Main.WIDTH+10), 600, 32, 32, false, this, main, ID.Zombie));
        }

    }

    public void clearEnemys(){
        for(int i = 0; i <object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId() != ID.Player){
                object.clear();

                //This removes the player after they lost
                if(Main.gameState != Main.STATE.End)
                    addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), 32, 32, this,ID.Player));
            }
        }
    }

    public void kill(Zombie z){
        object.remove(z);
        killCount++;
        points+=100;
        System.out.println(killCount);
    }

    public void clearBullet(Bullet b){
        bullet.remove(b);
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void addBullet(Bullet bullet){
        this.bullet.add(bullet);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }


    public void addTile(Tile newTile){
        tile.add(newTile);
    }


    public void createLevel(){


        for(int i = 0; i <= 100; i++) {

            addTile(new Grass((i*32), (int)(Main.window.frame.getHeight() * (.92)), 64, 64, true, ID.Tile));
        }
        for(int i = 0; i < 20; i++){
            addTile(new Stone((i*32), (int)(Main.window.frame.getHeight() * (.92)), 64,64, true, ID.Tile));
            /*addTile(new Stone((i*32 + 900), 945, 64,64, true, ID.Tile));
            addTile(new Stone((i * 32 + 950), 770, 64, 64, true, ID.Tile));
            addTile(new Stone((i * 32 + 1000), 740, 64, 64, true, ID.Tile));
            addTile(new Stone((i * 32 + 1150), 710, 64, 64, true, ID.Tile));
            addTile(new Stone((i * 32 + 1300), 680, 64, 64, true, ID.Tile));*/
        }


    }
}

