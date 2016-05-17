package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;
import com.gui.Window;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public static LinkedList<GameObject> object = new LinkedList<GameObject>();
    public static LinkedList<Tile> tile = new LinkedList<Tile>();
    public static LinkedList<Bullet> bullet = new LinkedList<Bullet>();

    private int counter = 0;
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

    public static void clearDeadEnemies(){
        for(int i = 0; i <object.size(); i++){
            if(object.get(i) instanceof Zombie && ((Zombie)object.get(i)).isDead()) {
                object.remove(i);
            }

                //This removes the player after they lost
                //if(Main.gameState != Main.STATE.End)
                    //addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));

        }
    }

    public void clearEnemy(){
        for(int i = 0; i <object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getId() == ID.Zombie){
                object.remove(1);
            }
        }
    }

    public static void clearBullet(){
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

