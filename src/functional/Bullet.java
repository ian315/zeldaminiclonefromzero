package functional;

import java.awt.*;

public class Bullet extends Rectangle {

    public int dir = 1;
    public int speed = 8;
    public int frames = 0;

    public int currentAnimation = 0;
    public int currentFrames = 0;
    public int targetFrames = 15;

    public Bullet(int x, int y, int dir) {
        super(x + 16, y + 16, 10, 10);
        this.dir = dir;
    }

    public void tick() {
        x += speed * dir;
        frames++;
        if (frames == 60) {
            Player.bullets.remove(this);
        }

        currentFrames++;
        if (currentFrames == targetFrames){
            currentFrames = 0;
            currentAnimation++;
            if (currentAnimation == SpriteSheet.bulletSheet.get("bullet").length){
                currentAnimation = 0;
            }
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(SpriteSheet.bulletSheet.get("bullet")[currentAnimation], x, y - 16/2, 16, 16,null);
    }
}