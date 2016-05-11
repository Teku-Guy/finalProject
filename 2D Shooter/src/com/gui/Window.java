package com.gui;

import com.game.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gustavo_Muratalla on 5/9/16.
 */
public class Window{

    private static final long serialVersionUID = 516290100757231509L;

    public final JFrame frame;

    public Window(String title, Main main){
        frame = new JFrame(title);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen in window mode
        frame.setUndecorated(true); //Gets Rid of the Bar on top of the Window

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(main);
        frame.setVisible(true);

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame); //Take over the whole screen


        main.start();
    }
}