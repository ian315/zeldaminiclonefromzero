package game;

import functional.EnemyRed;
import functional.Player;
import functional.SpriteSheet;
import worlds.World1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static Player player;
    public List<EnemyRed> enemies = new ArrayList<>();
    public static int WIDHT = 640, HEIGHT = 640;
    public static int SCALE = 3;
    public World1 world1;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDHT, HEIGHT));

        new SpriteSheet();
        player = new Player(32, 32);
        world1 = new World1();
        enemies.add(new EnemyRed(500, 32));
        enemies.add(new EnemyRed(500, 420));
    }

    public void tick() {
        player.processPlayerLogic();
        for(EnemyRed enemy : enemies) {
            enemy.processEnemyLogic();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
           this.createBufferStrategy(3);
           return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(new Color(0, 135 ,135));
        graphics.fillRect(0, 0, WIDHT * SCALE, HEIGHT * SCALE);

        player.render(graphics);
        world1.render(graphics);

        for(EnemyRed enemy : enemies) {
            enemy.render(graphics);
        }
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame =  new JFrame();
        frame.add(game);
        frame.setTitle("ZELDA MINI CLONE");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    // Metodo para rodar a 60FPS
    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}