package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<Tile> tile = new LinkedList<>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            Tile tempTile = tile.get(i);


            tempObject.tick();

        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            Tile tempTile = tile.get(i);

            tempObject.render(g);
            tempTile.render(g);
        }
    }

    public void clearEnemys(){
        for(int i = 0; i <object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId() != ID.Player){
                object.clear();

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
        //TODO

    }
}

