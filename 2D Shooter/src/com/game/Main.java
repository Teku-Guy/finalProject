package com.game; /**
 * Created by Gustavo & Jessus on 5/9/16.
 */

import com.entity.Grass;
import com.entity.ID;
import com.entity.Tile;
import com.gui.Window;
import com.input.Handler;
import com.gui.Menu;
import com.input.KeyInput;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.entity.*;

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


    public static Sprite[] PlayerJumpL = new Sprite[1];
    public static Sprite[] PlayerJumpR = new Sprite[1];
    public static Sprite[] Bullets = new Sprite[1];
    public static Sprite[] GrassTile = new Sprite[1];
    public static Sprite[] StoneTile = new Sprite[1];
    public static Sprite[] FloatingTile = new Sprite[1];
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


        BulletSheet = new SpriteSheet("/res/Bullet.png");
        GrassSheet = new SpriteSheet("/res/Bullet.png");
        StoneSheet = new SpriteSheet("/res/Stone.png");
        FloatingSheet = new SpriteSheet("/res/FloatingPlat.png");
        Grass = new Sprite(GrassSheet, 1, 1, 1, 1);
        Bullet = new Sprite(BulletSheet, 1, 1, 1, 1);
        Stone = new Sprite(StoneSheet, 1, 1, 1, 1);
        Floating = new Sprite(FloatingSheet,1, 1, 1, 1);

        for(int i = 0; i < GrassTile.length; i++){
            GrassTile[i] = new Sprite(GrassSheet, i++, i, i, i);
        }

        for(int i = 0; i < StoneTile.length; i++){
            StoneTile[i] = new Sprite(StoneSheet, i++, i, i ,i);
        }

        for(int i = 0 ; i < FloatingTile.length; i++){
            FloatingTile[i] = new Sprite(FloatingSheet, i++, i, i ,i);
        }

        for(int i = 0; i < Bullets.length; i++){
            Bullets[i] = new Sprite(BulletSheet,i++, i, i ,i);
        }

        System.out.println("Map Entities loaded!");

        PWalkRSheet = new SpriteSheet("/res/PWalkingRight.png");
        PWalkLSheet = new SpriteSheet("/res/PWalkingLeft.png");
        PJumpRSheet = new SpriteSheet("/res/PJumpRight.png");
        PJumpLSheet = new SpriteSheet("/res/PJumpLeft.png");

        for(int i = 0; i < PlayerWalkR.length; i++){
            PlayerWalkR[i] = new Sprite(PWalkRSheet, i++, i, i , i);
        }

        for(int i = 0; i < PlayerWalkL.length; i++){
            PlayerWalkL[i] = new Sprite(PWalkLSheet, i++, i, i ,i);
        }

        for(int i = 0; i < PlayerJumpR.length; i++){
            PlayerJumpR[i] = new Sprite(PJumpRSheet, i++, i, i ,i);
        }

        for(int i = 0; i < PlayerJumpL.length; i++){
           PlayerJumpL[i] = new Sprite(PJumpLSheet, i++, i, i ,i);
        }

        PJumpRight = new Sprite(PJumpRSheet, 1, 1, 1, 1);
        PJumpLeft = new Sprite(PJumpLSheet, 1, 1, 1, 1);

        handler.addObject(new Player(200, 200, 50, 50, ID.Player));
        System.out.println("Player Sprites loaded!");

        ZWalkRSheet = new SpriteSheet("/res/ZWalkingRight.png");
        ZWalkLSheet = new SpriteSheet("/res/ZWalkingLeft.png");

        for(int i = 0; i < ZWalkR.length; i++){
            ZWalkR[i] = new Sprite(ZWalkRSheet, i++, i, i ,i);
        }

        for(int i = 0; i < ZWalkL.length; i++){
            ZWalkL[i] = new Sprite(ZWalkLSheet, i++, i, i ,i);
        }
        System.out.println("Zombie Sprites loaded");


        handler.addObject(new Player(200, 200, 50, 50, ID.Player));
        System.out.println("Player Object created!");

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

