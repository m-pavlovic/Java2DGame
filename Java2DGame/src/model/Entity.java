package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {

    public int x, y, speed;
    public String direction;
    public BufferedImage[] spriteUpFrames, spriteDownFrames, spriteLeftFrames, spriteRightFrames;
    protected int frameIndex;
    protected long lastFrameTime;
    protected static final int FRAME_DURATION = 200;

    public Entity() {
        setDefaultValue();
    }

    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        frameIndex = 0;
        lastFrameTime = System.currentTimeMillis();
    }

    public void loadEntityImages(String upPath, String downPath, String leftPath, String rightPath) {
        try {
            spriteUpFrames = loadSprite(upPath);
            spriteDownFrames = loadSprite(downPath);
            spriteLeftFrames = loadSprite(leftPath);
            spriteRightFrames = loadSprite(rightPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage[] loadSprite(String path) throws IOException {
        BufferedImage spriteSheet = ImageIO.read(getClass().getResource(path));
        int frameWidth = spriteSheet.getWidth() / 4; // Assuming 4 frames in the sprite sheet
        int frameHeight = spriteSheet.getHeight();
        BufferedImage[] frames = new BufferedImage[4];

        for (int i = 0; i < 4; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        return frames;
    }

    public void update() {
        // Update frame index for animation
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime > FRAME_DURATION) {
            frameIndex = (frameIndex + 1) % 4; // Cycle through frames
            lastFrameTime = currentTime;
        }

        // Automatic movement logic for non-player entities can be added here
    }

    public BufferedImage getCurrentFrame() {
        switch (direction) {
            case "up":
                return spriteUpFrames[frameIndex];
            case "down":
                return spriteDownFrames[frameIndex];
            case "left":
                return spriteLeftFrames[frameIndex];
            case "right":
                return spriteRightFrames[frameIndex];
            default:
                return spriteDownFrames[frameIndex];
        }
    }
}