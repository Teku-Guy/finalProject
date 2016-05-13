package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public LinkedList<Tile> tile = new LinkedList<>();

    public void tick(){


        createLevel();
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }


    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
        for(int i = 0 ; i < tile.size(); i++){
            Tile tempTile = tile.get(i);
            tempTile.render(g);
        }


    }

    public void clearEnemies(){
        for(int i = 0; i <object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId() == ID.Zombie){
                object.remove(i);

                //This removes the player after they lost
                //if(Main.gameState != Main.STATE.End)
                    //addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
            }
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void addTile(Tile newTile){
        tile.add(newTile);
    }

    public void createLevel(){
        for(int i = 0; i <= 15; i++) {

            addTile(new Grass((i*32), 700, 64, 64, true, ID.Tile));

            //System.out.println(i);
        }


    }
}

