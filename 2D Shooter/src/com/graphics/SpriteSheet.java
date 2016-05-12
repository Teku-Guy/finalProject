package com.graphics;

/**
 * Created by jessus on 5/10/16.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int w, int h) {
        return sheet.getSubimage(x, y, w, h);
    }

}