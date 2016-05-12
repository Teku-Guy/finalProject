package com.graphics;

/**
 * Created by jessus on 5/10/16.
 */
import java.awt.image.BufferedImage;

public class Sprite {



    public BufferedImage image;
    public int w, h;

    public Sprite(BufferedImage image, int w, int h){
        this.w =w;
        this.h = h;
        this. image = image;
    }

    public static Sprite fromSheet(SpriteSheet sheet, int x, int y, int w, int h) {
        return new Sprite(sheet.getSprite(x * w , y * h, w, h), w, h);
    }

    public BufferedImage getBufferedImage(){
        return image;
    }


}
