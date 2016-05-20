package com.game;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;



/**
 * Created by APCS1 on 5/20/2016.
 */
public class Map {
    public List<String> map = new CopyOnWriteArrayList<String>();
    public List<Integer> parsedMap = new CopyOnWriteArrayList<Integer>();

    public void render(Graphics g){

    }




    public void readFile(String path) {
        try (Scanner s = new Scanner(getClass().getResourceAsStream(path))){


            while (s.hasNext()) {
                map.add(s.next());
            }



        }

    }
    public List<String> getMap(){
        return map;
    }

    public void createMap(){
        for(String s : map){
            int id = Integer.parseInt(s);
            parsedMap.add(id);
            System.out.println(parsedMap);
        }



    }

    }





