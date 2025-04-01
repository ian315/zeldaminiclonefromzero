package functional;

import game.Game;
import worlds.World1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyRed extends Rectangle {

    int speed = 2;
    public int up = 0, down = 0, right = 1, left = 0;

    public int currentFrames = 0;
    public int targetFrames = 15;
    public int currentAnimation = 0;

    public static List<Bullet> bullets = new ArrayList<>();

    public EnemyRed(int x, int y) {
        super(x, y, 32, 32);
    }

    public void processEnemyLogic() {
        chasePlayer();
        movementAnimation();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(SpriteSheet.enemySpriteSheet.get("movements")[currentAnimation], x, y, 32, 32, null);

        if (!bullets.isEmpty()){
            for (Bullet bullet : bullets) {
                bullet.render(graphics);
            }
        }
    }

    public void movementAnimation(){
        currentFrames++;
        if (currentFrames == targetFrames) {
            currentFrames = 0;
            currentAnimation++;
            if (currentAnimation == SpriteSheet.playerSpriteSheet.get("movements").length) {
                currentAnimation = 0;
            }
        }
    }

    private void chasePlayer() {
        Player player = Game.player;
        if (x < player.x && World1.hasntColide(x + speed, y)) {
            if (new Random().nextInt(100) < 50)
                x += speed;
        }
        if (x > player.x && World1.hasntColide(x - speed, y)) {
            if (new Random().nextInt(100) < 50)
                x -= speed;
        }
        if (y < player.y && World1.hasntColide(x, y + speed)) {
            if (new Random().nextInt(100) < 50)
                y += speed;
        }
        if (y > player.y && World1.hasntColide(x, y - speed)) {
            if (new Random().nextInt(100) < 50)
                y -= speed;
        }
    }
}
