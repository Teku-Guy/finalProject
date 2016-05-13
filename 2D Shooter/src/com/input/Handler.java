package com.input; /**
 * Created by Gustavo_Muratalla on 5/10/16.
 */

import com.entity.*;
import com.game.Main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public LinkedList<Tile> tiles = new LinkedList<>();


    public void tick(){
        //Loops through Linked List of tiles and calls each tick
        for(Tile tile : tiles){
            tile.tick();
        }
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
        for(int i = 0 ; i < tiles.size(); i++){
            Tile tempTile = tiles.get(i);
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
        tiles.add(newTile);
    }


    public void removeTile(Tile targetTile){
        tiles.remove(targetTile);
    }
    public void createSingleWall(int loop, boolean[]ledge, int location){
        if(loop < ledge.length && ledge[loop]){
            addTile(new Wall((location)*32,664, 32, 32, true, ID.wall,this));
            addTile(new Wall((location)*32,632, 32, 32, true, ID.wall,this));
            addTile(new Wall((location)*32,600, 32, 32, true, ID.wall,this));
            addTile(new Wall((location)*32,568, 32, 32, true, ID.wall,this));
        }
    }
    public void createMedWall(int loop, boolean[] ledge, int location){
        if(loop < ledge.length && ledge[loop]){
            addTile(new Wall((loop+location)*32,664, 32, 32, true, ID.wall,this));
            addTile(new Wall((loop+location)*32,632, 32, 32, true, ID.wall,this));
            addTile(new Wall((loop+location)*32,600, 32, 32, true, ID.wall,this));
            addTile(new Wall((loop+location)*32,568, 32, 32, true, ID.wall,this));
        }
    }
    public void createPlatforms(int loop, boolean[] plat, int location){
        if(loop < plat.length && plat[loop]){
            addTile(new Wall(loop*32, 400, 32, 32, true, ID.wall, this));
        }
        if(loop < plat.length && plat[loop]){
            addTile(new Wall((loop+location)*32, 240, 32, 32, true, ID.wall, this));
        }
    }
    public void createBounds(int loop){
        addTile(new Wall(loop*32, Main.HEIGHT*4-64, 32, 32, true, ID.wall, this));
        addTile(new Wall(loop*32, 0, 32, 32, true, ID.wall, this));


    }

    public void createLevel(){
        boolean[] plat = {
                false, false,false ,false ,false,
                false, false,false ,false ,false,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false,
                true, true, true, true,
                false, false,false ,false ,false};
        boolean[] ledge ={false,true,true,true,
                false,false};


        for(int i = 0; i <= Main.WIDTH *4 /32 * 10; i++){
            createBounds(i);
            createPlatforms(i, plat, 4);
            createPlatforms(i, plat, 50);
            createMedWall(i, ledge, 4);
            createMedWall(i, ledge, 10);
            createSingleWall(i, ledge, 20);
            createSingleWall(i, ledge, 30);
            createSingleWall(i, ledge, 40);
            createMedWall(i, ledge, 50);
            createMedWall(i, ledge, 80);
            createMedWall(i, ledge, 110);
            createMedWall(i, ledge, 140);


        }
    }
}

