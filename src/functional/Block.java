package functional;

import java.awt.*;

public class Block extends Rectangle {

    public Block(int x, int y) {
        super(x, y, 32, 32);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(SpriteSheet.tileWall, x, y, 32, 32, null);
    }
}
