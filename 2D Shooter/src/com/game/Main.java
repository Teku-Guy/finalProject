package com.game; /**
 * Created by Gustavo & Jessus on 5/9/16.
 */

import com.entity.Grass;
import com.entity.*;
import com.entity.Tile;
import com.gui.Camera;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.*;

public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1671921912898282466L;

    public static int WIDTH, HEIGHT;
    private static Main main;

    private int frames;
    public static Window window;
    private Thread thread;
    private boolean running = false;

    private int enemyCount = 10;
    private int waveCount = 0;
    private int mapCounter = 0;

    public Handler handler;
    public Camera cam;
    private Menu menu;
    private Map map;
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
    public static Sprite[] LavaFlow = new Sprite[5];

    public static Sprite PJumpLeft;
    public static Sprite PJumpRight;
    public static Sprite Bullet;
    public static Sprite Grass;
    public static Sprite Stone;
    public static Sprite Floating;
    public static Sprite BoundR;
    public static Sprite BoundL;
    public static Sprite BoundT;
    public static Sprite BoundB;
    public static Sprite Lava;


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
    public static SpriteSheet LavaSheet;
    public static SpriteSheet BoundRSheet;
    public static SpriteSheet BoundLSheet;
    public static SpriteSheet BoundTSheet;
    public static SpriteSheet BoundBSheet;

    public static BufferedImage Background;

    private Sound backgroundMusic;


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
        cam = new Camera(0, 0);
        menu = new Menu(this, handler, hud);
        map = new Map();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(new MouseInput(handler));


        window = new Window("2D Shooter", this);



        player = (new Player(500, 600, 32, 32, handler, ID.Player));
        handler.addObject(player);
        hud = new HUD(player.getX()-17, player.getY());
        handler.makeWave(enemyCount);
            //handler.addObject(new Zombie(300, 100, 32, 32, false, handler, ID.Zombie));
            //handler.addObject(player);

        WIDTH = window.frame.getWidth();
        HEIGHT = window.frame.getHeight();

    }

    //Loads stuff
    public void init() {


        BulletSheet = new SpriteSheet("/res/blocks/Bullet.png");
        GrassSheet = new SpriteSheet("/res/blocks/Grass.png");
        StoneSheet = new SpriteSheet("/res/blocks/Stone.png");
        FloatingSheet = new SpriteSheet("/res/blocks/FloatingPlat.png");
        LavaSheet = new SpriteSheet("/res/blocks/Lava.png");

        Bullet = Sprite.fromSheet(BulletSheet, 0, 0, 64, 64);
        Grass = Sprite.fromSheet(GrassSheet, 0, 0, 96, 96);
        Stone = Sprite.fromSheet(StoneSheet, 0, 0, 64, 64);
        Floating = Sprite.fromSheet(FloatingSheet, 0, 0, 64, 64);

        for(int i = 0; i < LavaFlow.length; i++){
            LavaFlow[i] = Sprite.fromSheet(LavaSheet, i ,0,96,96);

        }

        System.out.println("Map Entities loaded!");

        PWalkRSheet = new SpriteSheet("/res/player/PWalkingRight.png");
        PWalkLSheet = new SpriteSheet("/res/player/PWalkingLeft.png");
        PJumpRSheet = new SpriteSheet("/res/player/PJumpRight.png");
        PJumpLSheet = new SpriteSheet("/res/player/PJumpLeft.png");
        PShootL = new SpriteSheet("/res/player/PShootL.png");
        PShootR = new SpriteSheet("/res/player/PShootR.png");




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

        ZWalkRSheet = new SpriteSheet("/res/enemies/ZWalkingRight.png");
        ZWalkLSheet = new SpriteSheet("/res/enemies/ZWalkingLeft.png");

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
        backgroundMusic = new Sound("/res/sounds/song.wav");
      //  backgroundMusic.loop();

        System.out.println("Sound loaded!");

        BoundRSheet = new SpriteSheet("/res/blocks/BoundsBottom.png");
        BoundLSheet = new SpriteSheet("/res/blocks/BoundsLeft.png");
        BoundTSheet = new SpriteSheet("/res/blocks/BoundsTop.png");
        BoundBSheet = new SpriteSheet("/res/blocks/BoundsBottom.png");

        BoundR = Sprite.fromSheet(BoundRSheet, 0, 0, 64, 64);
        BoundL = Sprite.fromSheet(BoundLSheet, 0, 0, 64, 64);
        BoundT = Sprite.fromSheet(BoundTSheet, 0, 0, 64, 64);
        BoundB = Sprite.fromSheet(BoundBSheet, 0, 0, 64, 64);

        System.out.println("Bounds loaded!");

       // map.readFile("/res/Map.txt");
        //map.createMap();


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
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ID.Player) {
                    cam.tick(handler.object.get(i));
                }
            }
            hud.tick();
            if (Handler.killCount >= enemyCount) {
                if (waveCount < 3) {
                    handler.clearEnemies();
                    enemyCount += 1;
                    waveCount++;
                    Handler.killCount = 0;
                    handler.makeWave(enemyCount);
                }
            } else if (gameState == STATE.Menu || gameState == STATE.End) {
                menu.tick();
            }
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
            g2d.translate((int)cam.getX(), (int)cam.getY());
            g.drawImage(Main.Background, 0, 0, null);


            handler.render(g);
            hud.render(g);
            g.setColor(Color.WHITE);
            //g.drawString("FPS: " + frames, 10, 15);


            g2d.translate((int)-cam.getX(), (int)-cam.getY());
        } else if (gameState == STATE.Menu ||
                gameState == STATE.GameMenu ||
                gameState == STATE.Help ||
                gameState == STATE.GHelp ||
                gameState == STATE.End) {
            menu.render(g);
        }

        //g.setColor(Color.WHITE);
        //g.drawString("2D Shooter", ((window.frame.getWidth()/2) - 30), 20);

        g.dispose();
        bs.show();
    }

    //This Set Bounds of an object
    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public int getEnemyCount(){
        return enemyCount;
    }
    public void setEnemyCount(int enemyCount){
        this.enemyCount = enemyCount;
    }




    public static void main(String[] args) {
        main = new Main();



        //mostly for ubuntu
        System.setProperty("sun.java2d.opengl", "true");
    }

}