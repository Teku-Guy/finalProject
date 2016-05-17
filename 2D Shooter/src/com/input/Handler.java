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

    public void addEnemy(int enemy_count, int enemy_killed){
        for(int i = 0; i < enemy_count; i++){
            addObject(new Zombie(r.nextInt(300), 300, 32, 32, false, this, ID.Zombie));
        }
    }

    public void kill(Zombie zombie){
        object.remove(zombie);
    }

    public void clearEnemy(){
        for(int i = 0; i <object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getId() == ID.Zombie){
                object.remove(1);
            }
        }
    }

    public void clearBullet(){
        for(int i = 0; i < bullet.size(); i++) {
            Bullet tempBullet = bullet.get(i);
            if (tempBullet.getId() == ID.Bullet){
                bullet.remove(i);
            }
        }
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


        for(int i = 0; i <= 30; i++) {

            addTile(new Grass((i*32), Main.HEIGHT+700, 64, 64, true, ID.Tile));
        }


    }
}

