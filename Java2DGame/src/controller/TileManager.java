package controller;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import model.Tile;
import view.GamePanel;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;


    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {

        try {
            
          //  tile[0] = new Tile();
          //  tile[0].image = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawTile(Graphics2D g2) {

        g2.drawImage(tile[0].image, 0, 0, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

    }

}
