package functional;

import worlds.World1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

    int speed = 4;
    public boolean up, down, right, left;


    public int currentFrames = 0;
    public int targetFrames = 15;
    public int currentAnimation = 0;
    public int dir = 1;

    public static List<Bullet> bullets = new ArrayList<>();
    public boolean shoot = false;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void processPlayerLogic() {
        boolean moved = false;

        if(right && World1.hasntColide(x + speed, y)) {
           x += speed;
           moved = true;
           dir = 1;
        }

        if(left && World1.hasntColide(x - speed, y)) {
            x -= speed;
            moved = true;
            dir = -1;
        }

        if(up && World1.hasntColide(x, y - speed)) {
            y -= speed;
            moved = true;
        }

        if(down && World1.hasntColide(x, y + speed)) {
            y += speed;
            moved = true;
        }

        movementAnimation(moved);
        shoot();
    }

    private void shoot() {
        if(shoot) {
            shoot = false;
            bullets.add(new Bullet(x, y, dir));
        }

        for(int i= 0, size = bullets.size(); i < size; i++){
            bullets.get(i).tick();
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(SpriteSheet.playerSpriteSheet.get("movements")[currentAnimation], x, y, 32, 32, null);

        if (!bullets.isEmpty()){
            for (Bullet bullet : bullets) {
                bullet.render(graphics);
            }
        }
    }

    private void movementAnimation(boolean moved){
        if(moved) {
            currentFrames++;
            if (currentFrames == targetFrames) {
                currentFrames = 0;
                currentAnimation++;
                if (currentAnimation == SpriteSheet.playerSpriteSheet.get("movements").length) {
                    currentAnimation = 0;
                }
            }
        }
    }
}
