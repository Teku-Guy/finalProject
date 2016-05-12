package com.graphics;

/**
 * Created by jessus on 5/10/16.
 */
import java.awt.image.BufferedImage;

public class Sprite {

    public SpriteSheet sheet;
    public BufferedImage image;
    public int x, y, w, h;

    public Sprite(SpriteSheet sheet, int x, int y, int w, int h){
        this.sheet = sheet;
        this.x = x;
        this.y = y;
        image = sheet.getSprite(x, y, w, h);
    }

    public BufferedImage getBufferedImage(){
        return image;
    }


}
