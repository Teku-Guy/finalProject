package com.game; /**
 * Created by Gustavo & Jessus on 5/9/16.
 */

import com.gui.Window;
import com.input.Handler;
import com.gui.Menu;
import com.input.KeyInput;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Main extends Canvas implements Runnable{

    private static final long serialVersionUID = 1671921912898282466L;

    public static int WIDTH, HEIGHT;

    private Window window;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Menu menu;

    public static SpriteSheet PWalkR;
    public static SpriteSheet PWalkL;
    public static BufferedImage PJumpR;
    public static BufferedImage PJumpL;
    public static SpriteSheet ZWalkR;
    public static SpriteSheet ZWalkL;
    public static BufferedImage Bullet;

    public enum STATE {
        Menu,
        Help,
        Game,
        End
    };

    public static STATE gameState = STATE.Menu;

    public Main() {
        window = new Window("2D Shooter", this);
        handler = new Handler();
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        WIDTH = window.frame.getWidth();
        HEIGHT = window.frame.getHeight();
    }
    //Loads stuff
    public void init(){
       PWalkR = new SpriteSheet("/res/PWalkingRight.png");
       PWalkL = new SpriteSheet("/res/PWalkingLeft.png");
      try {
          PJumpL = ImageIO.read(getClass().getResource("/res/PJumpLeft.png"));
          PJumpR = ImageIO.read(getClass().getResource("/res/PJumpRight.png"));
          Bullet = ImageIO.read(getClass().getResource("/res/Bullet.png"));
      }catch(IOException io){
          System.out.println("Images Failed to load!");
      }
          System.out.println("Player Sprites loaded!");

        ZWalkR = new SpriteSheet("/res/ZWalkingRight.png");
        ZWalkL = new SpriteSheet("/res/ZWalkingLeft.png");
        System.out.println("Zombie Sprites loaded");
    }
    //Run the thread
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //Stop running the thread
    public synchronized void stop(){
        try{
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //When it is run the FPS Counter will keep Counting need the tik method
    public void run(){
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer =  System.currentTimeMillis();
        int frames = 0;
        running = true;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime  = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 10000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    //Run every Second
    private void tick(){

        if(gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }

    }

    //This renders the back ground in the JFrame
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game)
        {
            //hud.render(g);

        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

        //g.setColor(Color.WHITE);
        //g.drawString("2D Shooter", ((window.frame.getWidth()/2) - 30), 20);

        g.dispose();
        bs.show();
    }

    //This Could be used as the velocity max and min setter
    /*public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }*/

    public static void main(String[] args) {
        new Main();
    }
}

