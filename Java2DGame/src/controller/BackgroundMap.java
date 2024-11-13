package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import view.GamePanel;

public class BackgroundMap {

    GamePanel gamePanel;
    BufferedImage mapImage;

    public BackgroundMap(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        getMapImage();
    }

    public void getMapImage() {
        try {
            mapImage = ImageIO.read(getClass().getResourceAsStream("/images/Pellet Town.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMap(Graphics2D g2) {
        int playerX = gamePanel.player.x;
        int playerY = gamePanel.player.y;
        int screenWidth = gamePanel.SCREEN_WIDTH;
        int screenHeight = gamePanel.SCREEN_HEIGHT;

        int drawX = screenWidth / 2 - playerX;
        int drawY = screenHeight / 2 - playerY;

        g2.drawImage(mapImage, drawX, drawY, null);
    }
}