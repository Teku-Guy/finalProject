package com.game; /**
 * Created by Gustavo on 5/9/16.
 */

import com.entity.ID;
import com.entity.Player;
import com.entity.tiles.Grass;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.gui.*;
import com.gui.Menu;
import com.gui.Window;
import com.input.Handler;
import com.input.KeyInput;
import com.input.MouseInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends Canvas implements Runnable {

    private static final long serialVersionUID = 1671921912898282466L;

    public static int WIDTH, HEIGHT;
    private static Main main;

    private int frames;
    public static Window window;
    private Thread thread;
    private boolean running = false;

    public int enemyCount = 10;
    public static int waveCount = 0;
    private int mapCounter = 0;

    public Handler handler;
    public Camera cam;
    private Menu menu;
    private HUD hud;
    public static Player player;
    public static Grass grass;

    public MapLoader loader = new MapLoader();
    public static BufferedImage level1;
    public static BufferedImage level2;
    public static BufferedImage level3;

    public static Sprite[] PlayerJumpL = new Sprite[1];
    public static Sprite[] PlayerJumpR = new Sprite[1];
    public static Sprite[] BossShootR = new Sprite[1];
    public static Sprite[] BossShootL = new Sprite[1];
    public static Sprite[] PlayerWalkR = new Sprite[5];
    public static Sprite[] PlayerWalkL = new Sprite[5];
    public static Sprite[] BWalkR = new Sprite[3];
    public static Sprite[] BWalkL = new Sprite[3];
    public static Sprite[] ZWalkR = new Sprite[3];
    public static Sprite[] ZWalkL = new Sprite[3];
    public static Sprite[] PlayerShootL = new Sprite[2];
    public static Sprite[] PlayerShootR = new Sprite[2];
    public static Sprite[] LavaFlow = new Sprite[5];

    public static Sprite PJumpLeft;
    public static Sprite PJumpRight;
    public static Sprite BShootRight;
    public static Sprite BShootLeft;
    public static Sprite Bullet;
    public static Sprite BBullet;
    public static Sprite Grass;
    public static Sprite HP;
    public static Sprite Stone;
    public static Sprite Floating;
    public static Sprite BoundR;
    public static Sprite BoundL;
    public static Sprite BoundT;
    public static Sprite BoundB;
    public static Sprite Lava;


    public static SpriteSheet PJumpLSheet;
    public static SpriteSheet PJumpRSheet;
    public static SpriteSheet BShootRSheet;
    public static SpriteSheet BShootLSheet;
    public static SpriteSheet PWalkRSheet;
    public static SpriteSheet PWalkLSheet;
    public static SpriteSheet ZWalkRSheet;
    public static SpriteSheet ZWalkLSheet;
    public static SpriteSheet BWalkRSheet;
    public static SpriteSheet BWalkLSheet;
    public static SpriteSheet PShootL;
    public static SpriteSheet PShootR;
    public static SpriteSheet BulletSheet;
    public static SpriteSheet BBulletSheet;
    public static SpriteSheet GrassSheet;
    public static SpriteSheet HPSheet;
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

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(new MouseInput(handler));


        window = new Window("2D Shooter", this);

        hud = new HUD(-17, -32);
        player = new Player(0, 0, 64, 64, handler, ID.Player);
        handler.addObject(player);
        handler.makeWave(enemyCount);
        //handler.addObject(new Zombie(300, 100, 32, 32, false, handler, ID.Zombie));
        //handler.addObject(player);

        WIDTH = window.frame.getWidth();
        HEIGHT = window.frame.getHeight();

    }

    //Loads stuff
    public void init() {

        level1 = loader.loadImage("/res/level1.png");
        level2 = loader.loadImage("/res/level2.png");
        level3 = loader.loadImage("/res/level3.png");

        BBulletSheet = new SpriteSheet("/res/enemies/BBullet.png");
        BulletSheet = new SpriteSheet("/res/player/Bullet.png");
        GrassSheet = new SpriteSheet("/res/blocks/Grass.png");
        HPSheet = new SpriteSheet("/res/blocks/HP.png");
        StoneSheet = new SpriteSheet("/res/blocks/Stone.png");
        FloatingSheet = new SpriteSheet("/res/blocks/FloatingPlat.png");
        LavaSheet = new SpriteSheet("/res/blocks/Lava.png");

        Bullet = Sprite.fromSheet(BulletSheet, 0, 0, 64, 64);
        BBullet = Sprite.fromSheet(BBulletSheet,0 ,0, 78, 42);
        Grass = Sprite.fromSheet(GrassSheet, 0, 0, 51, 54);
        HP = Sprite.fromSheet(HPSheet, 0, 0, 32, 32);
        Stone = Sprite.fromSheet(StoneSheet, 0, 0, 51, 54);
        Floating = Sprite.fromSheet(FloatingSheet, 0, 0, 51, 54);

        for (int i = 0; i < LavaFlow.length; i++) {
            LavaFlow[i] = new Sprite(LavaSheet.getSprite(i * 96, 0, 55, 57), 55, 57);
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
        for (int i = 0; i < PlayerShootR.length; i++) {
            PlayerShootR[i] = Sprite.fromSheet(PShootR, i, 0, 64, 64);
        }
        for (int i = 0; i < PlayerShootL.length; i++) {
            PlayerShootL[i] = Sprite.fromSheet(PShootL, i, 0, 64, 64);
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

        BWalkRSheet = new SpriteSheet("/res/enemies/BWalkingRight.png");
        BWalkLSheet = new SpriteSheet("/res/enemies/BWalkingLeft.png");
        BShootLSheet = new SpriteSheet("/res/enemies/BShootLeft.png");
        BShootRSheet = new SpriteSheet("/res/enemies/BShootRight.png");

        for (int i = 0; i < BWalkR.length; i++) {
            BWalkR[i] = Sprite.fromSheet(BWalkRSheet, i, 0, 192, 192);
        }

        for (int i = 0; i < BWalkL.length; i++) {
            BWalkL[i] = Sprite.fromSheet(BWalkLSheet, i, 0, 192, 192);
        }
        for (int i = 0; i < BossShootR.length; i++) {
            BossShootR[i] = Sprite.fromSheet(BShootRSheet, i, 0, 192, 192);
        }
        for (int i = 0; i < BossShootL.length; i++) {
            BossShootL[i] = Sprite.fromSheet(BShootLSheet, i, 0, 192, 192);
        }



        try {
            Background = ImageIO.read(getClass().getResourceAsStream("/res/Background.png"));
        } catch (IOException a) {

            System.out.println("Background image load failed!");
        }

        System.out.println("Background loaded!");
        backgroundMusic = new Sound("/res/sounds/backMusic_01.wav");
        //backgroundMusic.loop();

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

        //LoadImageLevel(level);

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
        changeLevel();
        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;




            while (delta >= 1) {
                tick();
              //  Sync.sync(100);
                delta--;
                frames++;
            }

            if (running)

                render();






            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }


    //Run every Second
    private void tick() {
        if (gameState == STATE.Game) {
            handler.tick();
            if (hud.HEALTH == 0) {
                gameState = STATE.End;
            }
            for (int i = 0; i < handler.object.size(); i++) {
                if (handler.object.get(i).getId() == ID.Player) {
                    cam.tick(handler.object.get(i));
                }
            }
            hud.tick();
            if (Handler.killCount >= enemyCount) {
                if (Handler.levelCount == 0) {
                    if (waveCount < 3) {
                        handler.clearEnemies();
                        enemyCount += 1;
                        waveCount++;
                        Handler.killCount = 0;
                        handler.makeWave(enemyCount);
                    }
                }
                if (Handler.levelCount == 1) {


                    if (waveCount < 3) {
                        handler.clearEnemies();
                        enemyCount += 1;
                        waveCount++;
                        Handler.killCount = 0;
                        handler.makeWave(enemyCount);
                    }
                }
                if (Handler.levelCount == 2) {

                    if (waveCount < 3) {
                        handler.clearEnemies();
                        enemyCount += 1;
                        waveCount++;
                        Handler.killCount = 0;
                        handler.makeWave(enemyCount);
                    }
                }
                if (waveCount == 3) {
                    Handler.levelCount++;
                    if(Handler.levelCount > 2){
                        throw new IllegalStateException("end of gamewa");
                    }
                    changeLevel();
                }

            } else if (gameState == STATE.Menu || gameState == STATE.End) {
                menu.tick();
            }
        }
    }

    private void changeLevel() {
        waveCount = 0;
        handler.tile.clear();
        if (handler.levelCount == 0) {
            handler.LoadImageLevel(Main.level1);
        } else if (handler.levelCount == 1) {
            handler.LoadImageLevel(Main.level2);
        } else if (handler.levelCount == 2) {
            handler.LoadImageLevel(Main.level3);
        } else {
           // throw new RuntimeException("too many level loads");
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


        if (gameState == STATE.Game) {
            g2d.translate((int) cam.getX(), (int) cam.getY());
            g.drawImage(Main.Background, -1801, -1350, null);

            handler.render(g);
            hud.render(g);


            g2d.translate((int) cam.getX(), (int) cam.getY());
            g.setColor(Color.WHITE);


            g2d.translate((int) -cam.getX(), (int) -cam.getY());
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

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }


    public static void main(String[] args) {
        main = new Main();


        //mostly for ubuntu
        System.setProperty("sun.java2d.opengl", "true");
    }

}