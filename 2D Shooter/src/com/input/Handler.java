package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();

    public Tile maps[] = new Tile[10];
    private int counter = 0;

    public void tick(){



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
        for(int i = 0; i < tile.size(); i++) {
            Tile tempTile = tile.get(i);
            tempTile.render(g);
        }
        counter++;
        if(counter % 100 == 0){
            counter = 0;
            createLevel();
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


    public void createLevel() {
        for (int i = 0; i <= 100; i++) {
            Grass grass = new Grass(i * 32, 975, 3000, 64, true, ID.Tile);
            addTile(grass);
        }
    }


}

