package com.game; /**
 * Created by Gustavo & Jessus on 5/9/16.
 */

import com.entity.Grass;
import com.entity.*;
import com.entity.Tile;
import com.gui.Window;
import com.input.Handler;
import com.gui.Menu;
import com.input.KeyInput;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.entity.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Main extends Canvas implements Runnable{

    private static final long serialVersionUID = 1671921912898282466L;

    public static int WIDTH, HEIGHT;

    private int frames;
    private Window window;
    private Thread thread;
    private boolean running = false;


    private Handler handler;
    private Menu menu;
    public static Player player;
    public static Grass grass;


    public static Sprite[] PlayerJumpL = new Sprite[1];
    public static Sprite[] PlayerJumpR = new Sprite[1];
    public static Sprite[] PlayerWalkR = new Sprite[5];
    public static Sprite[] PlayerWalkL = new Sprite[5];
    public static Sprite[] ZWalkR = new Sprite[3];
    public static Sprite[] ZWalkL = new Sprite[3];

    public static Sprite PJumpLeft;
    public static Sprite PJumpRight;
    public static Sprite Bullet;
    public static Sprite Grass;
    public static Sprite Stone;
    public static Sprite Floating;

    public static SpriteSheet PJumpLSheet;
    public static SpriteSheet PJumpRSheet;
    public static SpriteSheet PWalkRSheet;
    public static SpriteSheet PWalkLSheet;
    public static SpriteSheet ZWalkRSheet;
    public static SpriteSheet ZWalkLSheet;
    public static SpriteSheet BulletSheet;
    public static SpriteSheet GrassSheet;
    public static SpriteSheet StoneSheet;
    public static SpriteSheet FloatingSheet;



    public enum STATE {
        Menu,
        Help,
        Game,
        End
    }

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


        BulletSheet = new SpriteSheet("/res/Bullet.png");
        GrassSheet = new SpriteSheet("/res/Grass.png");
        StoneSheet = new SpriteSheet("/res/Stone.png");
        FloatingSheet = new SpriteSheet("/res/FloatingPlat.png");

        Bullet = Sprite.fromSheet(BulletSheet, 0, 0, 64 ,64);
        Grass = Sprite.fromSheet(GrassSheet, 0, 0 , 64 ,64);
        Stone = Sprite.fromSheet(StoneSheet, 0 , 0, 64 ,64);
        Floating = Sprite.fromSheet(FloatingSheet, 0, 0, 64, 64);


        System.out.println("Map Entities loaded!");

        PWalkRSheet = new SpriteSheet("/res/PWalkingRight.png");
        PWalkLSheet = new SpriteSheet("/res/PWalkingLeft.png");
        PJumpRSheet = new SpriteSheet("/res/PJumpRight.png");
        PJumpLSheet = new SpriteSheet("/res/PJumpLeft.png");

        for(int i = 0; i < PlayerWalkR.length; i++){
            PlayerWalkR[i] = Sprite.fromSheet(PWalkRSheet, i, 0, 64, 64);
        }

        for(int i = 0; i < PlayerWalkL.length; i++){
            PlayerWalkL[i] =  Sprite.fromSheet(PWalkLSheet, i, 0, 64 ,64);
        }

        for(int i = 0; i < PlayerJumpR.length; i++){
            PlayerJumpR[i] = Sprite.fromSheet(PJumpRSheet, i, 0, 64 ,64);
        }

        for(int i = 0; i < PlayerJumpL.length; i++){
            PlayerJumpL[i] = Sprite.fromSheet(PJumpLSheet, i, 0, 64 ,64);
        }



        System.out.println("Player Sprites loaded!");

        ZWalkRSheet = new SpriteSheet("/res/ZWalkingRight.png");
        ZWalkLSheet = new SpriteSheet("/res/ZWalkingLeft.png");

        for(int i = 0; i < ZWalkR.length; i++){
            ZWalkR[i] = Sprite.fromSheet(ZWalkRSheet, i, 0, 64 ,64);
        }

        for(int i = 0; i < ZWalkL.length; i++){
            ZWalkL[i] = Sprite.fromSheet(ZWalkLSheet, i, 0, 64, 64);
        }
        System.out.println("Zombie Sprites loaded");


        player = new Player(100, 100, 32, 32, handler, ID.Player);
        handler.addObject(player);
        handler.clearEnemies();

        handler.addObject(new Zombie(300, 100, 32, 32, ID.Zombie));

        System.out.println("Player Object created!");

        //handler.createLevel();

        System.out.println("Level created!");








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
            thread.join();
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
        frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime  = now;
            while(delta >= 1){
                tick();
                delta--;
            }

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
    public void gameLoop(){

    }

    //Run every Second
    private void tick(){
        handler.tick();

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
        super.paint(g);

        Graphics g2d = (Graphics2D) g;

        //g.setColor(Color.BLACK);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.GREEN);
       // g.drawString("FPS: "+frames, 10,15);

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
    public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Main();
        //mostly for ubuntu
        System.setProperty("sun.java2d.opengl", "true");
    }
}

