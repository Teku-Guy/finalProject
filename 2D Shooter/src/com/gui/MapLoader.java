package com.gui;/**
 * Created by Gustavo_Muratalla on 5/26/16.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path){

        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return image;
    }
}
