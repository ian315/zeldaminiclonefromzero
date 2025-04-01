package functional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpriteSheet {

    public static BufferedImage spritesheet;
    public static Map<String, BufferedImage[]> playerSpriteSheet = new HashMap<>();
    public static Map<String, BufferedImage[]> bulletSheet = new HashMap<>();
    public static Map<String, BufferedImage[]> enemySpriteSheet = new HashMap<>();
    public static BufferedImage tileWall;

    public SpriteSheet() {
        try {
            spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResource("/spritesheet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerSpriteSheet.put("movements", getMovementSprites());
        bulletSheet.put("bullet", getBulletAnimation());
        enemySpriteSheet.put("movements", getEnemyAnimation());
        tileWall = getSpriteSheet(279,221, 16, 16);
    }
    public static BufferedImage getSpriteSheet(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }

    public static BufferedImage[] getMovementSprites() {
        BufferedImage[] movementAnimation = new BufferedImage[2];
        movementAnimation[0] = getSpriteSheet(0, 8, 16, 16);
        movementAnimation[1] = getSpriteSheet(19, 8, 16, 16);
        return movementAnimation;
    }

    public static BufferedImage[] getBulletAnimation() {
        BufferedImage[] bulletAnimation = new BufferedImage[2];
        bulletAnimation[0] = getSpriteSheet(0, 151, 8, 16);
        bulletAnimation[1] = getSpriteSheet(10, 151, 16 ,16);
        return bulletAnimation;
    }

    public static BufferedImage[] getEnemyAnimation() {
        BufferedImage[] movementAnimation = new BufferedImage[2];
        movementAnimation[0] = getSpriteSheet(279, 257, 16, 16);
        movementAnimation[1] = getSpriteSheet(297, 257, 16 ,16);
        return movementAnimation;
    }
}
