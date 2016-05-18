package com.game; /**
 * Created by Gustavo & Jessus on 5/9/16.
 */

import com.entity.Grass;
import com.entity.*;
import com.entity.Tile;
import com.gui.HUD;
import com.gui.Window;
import com.input.Handler;
import com.gui.Menu;
import com.input.KeyInput;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.entity.*;
import com.input.MouseInput;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1671921912898282466L;

    public static int WIDTH, HEIGHT;
    private static Main main;

    private int frames;
    public static Window window;
    private Thread thread;
    private boolean running = false;

    private int enemy_count = 5;
    private int enemy_killed = 0;

    public Handler handler;
    private Bullet bull;
    private Menu menu;
    private HUD hud;
    public static Player player;
    public static Grass grass;


    public static Sprite[] PlayerJumpL = new Sprite[1];
    public static Sprite[] PlayerJumpR = new Sprite[1];
    public static Sprite[] PlayerWalkR = new Sprite[5];
    public static Sprite[] PlayerWalkL = new Sprite[5];
    public static Sprite[] ZWalkR = new Sprite[3];
    public static Sprite[] ZWalkL = new Sprite[3];
    public static Sprite[] PlayerShootL = new Sprite[2];
    public static Sprite[] PlayerShootR = new Sprite[2];

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
    public static SpriteSheet PShootL;
    public static SpriteSheet PShootR;
    public static SpriteSheet BulletSheet;
    public static SpriteSheet GrassSheet;
    public static SpriteSheet StoneSheet;
    public static SpriteSheet FloatingSheet;
    public static BufferedImage Background;


    public enum STATE {
        Game,
        Menu,
        GameMenu,
        GHelp,
        Help,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Main() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(new MouseInput(handler));


        window = new Window("2D Shooter", this);

            handler.createLevel();
            player = (new Player(500, 600, 32, 32, handler, ID.Player));
            handler.addObject(player);
            handler.makeWave(enemy_count);
            //handler.addObject(new Zombie(300, 100, 32, 32, false, handler, ID.Zombie));
            //handler.addObject(player);

        WIDTH = window.frame.getWidth();
        HEIGHT = window.frame.getHeight();

    }

    //Loads stuff
    public void init() {


        BulletSheet = new SpriteSheet("/res/Bullet.png");
        GrassSheet = new SpriteSheet("/res/Grass.png");
        StoneSheet = new SpriteSheet("/res/Stone.png");
        FloatingSheet = new SpriteSheet("/res/FloatingPlat.png");

        Bullet = Sprite.fromSheet(BulletSheet, 0, 0, 64, 64);
        Grass = Sprite.fromSheet(GrassSheet, 0, 0, 96, 96);
        Stone = Sprite.fromSheet(StoneSheet, 0, 0, 64, 64);
        Floating = Sprite.fromSheet(FloatingSheet, 0, 0, 64, 64);


        System.out.println("Map Entities loaded!");

        PWalkRSheet = new SpriteSheet("/res/PWalkingRight.png");
        PWalkLSheet = new SpriteSheet("/res/PWalkingLeft.png");
        PJumpRSheet = new SpriteSheet("/res/PJumpRight.png");
        PJumpLSheet = new SpriteSheet("/res/PJumpLeft.png");
        PShootL = new SpriteSheet("/res/PShootL.png");
        PShootR = new SpriteSheet("/res/PShootR.png");




        PJumpLeft = Sprite.fromSheet(PJumpLSheet, 0, 0, 64, 64);
        PJumpRight = Sprite.fromSheet(PJumpRSheet, 0, 0, 64, 64);

        for (int i = 0; i < PlayerWalkR.length; i++) {
            PlayerWalkR[i] = Sprite.fromSheet(PWalkRSheet, i, 0, 64, 64);
        }

        for (int i = 0; i < PlayerWalkL.length; i++) {
            PlayerWalkL[i] = Sprite.fromSheet(PWalkLSheet, i, 0, 64, 64);
        }

        for (int i = 0; i < PlayerJumpR.length; i++) {
            PlayerJumpR[i] = Sprite.fromSheet(PJumpRSheet, i, 0, 64, 64);
        }

        for (int i = 0; i < PlayerJumpL.length; i++) {
            PlayerJumpL[i] = Sprite.fromSheet(PJumpLSheet, i, 0, 64, 64);
        }
        for(int i = 0; i < PlayerShootR.length; i++){
            PlayerShootR[i] = Sprite.fromSheet(PShootR,i ,0 ,64,64);
        }
        for(int i = 0; i < PlayerShootL.length; i++){
            PlayerShootL[i] = Sprite.fromSheet(PShootL,i , 0 , 64, 64);
        }


        System.out.println("Player Sprites loaded!");

        ZWalkRSheet = new SpriteSheet("/res/ZWalkingRight.png");
        ZWalkLSheet = new SpriteSheet("/res/ZWalkingLeft.png");

        for (int i = 0; i < ZWalkR.length; i++) {
            ZWalkR[i] = Sprite.fromSheet(ZWalkRSheet, i, 0, 64, 64);
        }

        for (int i = 0; i < ZWalkL.length; i++) {
            ZWalkL[i] = Sprite.fromSheet(ZWalkLSheet, i, 0, 64, 64);
        }
        System.out.println("Zombie Sprites loaded");


        try {
            Background = ImageIO.read(getClass().getResourceAsStream("/res/Background.png"));
        } catch (IOException a) {

            System.out.println("Background image load failed!");
        }

        System.out.println("Background loaded!");



    }

    //Run the thread
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();

        running = true;

    }

    //Stop running the thread
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //When it is run the FPS Counter will keep Counting need the tik method
    public void run() {
        init();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 10000;
                System.out.println("FPS: " + frames);
            }
        }
        stop();
    }


    //Run every Second
    private void tick() {
        if (gameState == STATE.Game) {
            handler.tick();
            hud.tick();
            if(Handler.killCount >= enemy_count){
                enemy_count += 2;
                Handler.killCount = 0;
                handler.makeWave(enemy_count);
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End) {
            menu.tick();
        }
    }

    //This renders the back ground in the JFrame
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        super.paint(g);

        Graphics g2d = (Graphics2D) g;

        //g.setColor(Color.BLACK);
        // g.setColor(Color.LIGHT_GRAY);

        //g.fillRect(0, 0, WIDTH, HEIGHT);


        if (gameState == STATE.Game) {
            g.drawImage(Main.Background, 0, 0, null);
            handler.render(g);
            hud.render(g);
            g.setColor(Color.WHITE);
            g.drawString("FPS: " + frames, 10, 15);
            g.drawString("Points : " + Handler.points , 10, 30);
        } else if (gameState == STATE.Menu || gameState == STATE.GameMenu || gameState == STATE.Help || gameState == STATE.GHelp || gameState == STATE.End) {
            menu.render(g);
        }

        //g.setColor(Color.WHITE);
        //g.drawString("2D Shooter", ((window.frame.getWidth()/2) - 30), 20);

        g.dispose();
        bs.show();
    }

    //This Could be used as the velocity max and min setter
    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public int getEnemy_count(){
        return enemy_count;
    }
    public void setEnemy_count(int enemy_count){
        this.enemy_count = enemy_count;
    }

    public int getEnemy_killed(){
        return enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed){
        this.enemy_killed = enemy_killed;
    }

    public static void main(String[] args) {
        main = new Main();

        //mostly for ubuntu
        System.setProperty("sun.java2d.opengl", "true");
    }

}