package com.game.gui;

import com.game.gui.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gustavo_Muratalla on 5/9/16.
 */
public class Window {

    private static final long serialVersionUID = 516290100757231509L;

    public Window(int width, int height, String title, Main main){
        JFrame frame = new JFrame(title);


        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocale(null);
        frame.add(main);
        frame.setVisible(true);
        main.start();
    }
}
