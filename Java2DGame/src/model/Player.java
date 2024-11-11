package model;


public class Player extends Entity {


    public Player() {

        setDefaultValue();
        loadEntityImages("/images/playerUp.png", "/images/playerDown.png", "/images/playerLeft.png", "/images/playerRight.png");

    }

    @Override
    public void setDefaultValue() {
        super.setDefaultValue();
    }

    public void update(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {

        boolean isMoving = false;

        if (upPressed) {
            direction = "up";
            y -= speed;
            isMoving = true;
        } else if (downPressed) {
            direction = "down";
            y += speed;
            isMoving = true;
        } else if (leftPressed) {
            direction = "left";
            x -= speed;
            isMoving = true;
        } else if (rightPressed) {
            direction = "right";
            x += speed;
            isMoving = true;
        }

        if (isMoving) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFrameTime > FRAME_DURATION) {
                frameIndex = (frameIndex + 1) % 4; // Cycle through frames
                lastFrameTime = currentTime;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
