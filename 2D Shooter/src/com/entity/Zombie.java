package com.entity;

import java.awt.*;

/**
 * Created by jessus on 5/11/16.
 */
import com.game.Main;
import com.input.Handler;
import com.entity.ID;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class Zombie extends GameObject{

    public int frame = 0;

    public Zombie(int x, int y, int width, int height, ID id){
        super(x , y, width, height, id);
    }

    public void checkIfHit(){

    }
    public void chasePlayer(){

    }

    public void render(Graphics g) {


    }

    public void tick() {

        chasePlayer();

        checkIfHit();

    }

}
