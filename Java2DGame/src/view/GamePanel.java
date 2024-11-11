package view;

import controller.KeyHandler;
import model.Player;
import model.Entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    final int ORIGINAL_TILE_SIZE = 12;
    final int SCALE = 4;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final int MAX_SCREEN_COLS = 16;
    final int MAX_SCREEN_ROWS = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLS;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;

    Thread gameThread;
    KeyHandler keyHandler;
    Player player;
    List<Entity> entities;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player(); // Initialize player here
        entities = new ArrayList<>();
        entities.add(player); // Add player to the list of entities
        // Add other entities to the list
        init();
        layoutComps();
        activateApp();
    }

    private void init() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
    }

    private void activateApp() {
        // Implement game activation logic here
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000L / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1_000_000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update(keyHandler.upPressed, keyHandler.downPressed, keyHandler.leftPressed, keyHandler.rightPressed);
        for (Entity entity : entities) {
            if (!(entity instanceof Player)) {
                entity.update();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Entity entity : entities) {
            g2.drawImage(entity.getCurrentFrame(), entity.x, entity.y, TILE_SIZE, TILE_SIZE, null);
        }

        g2.dispose();
    }
}