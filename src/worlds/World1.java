package worlds;

import functional.Block;
import functional.EnemyRed;
import game.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World1 {
    public static List<Block> blocksList = new ArrayList<>();


    public World1() {
        for(int x = 0; x < 20; x++) { //X representa a coordenada x do bloco
            blocksList.add(new Block(x * 32, 0));
        }
        for(int x = 0; x < 20; x++) { //X representa a coordenada x do bloco
            blocksList.add(new Block(x * 32, 640 - 32));
        }
        for(int y = 0; y < 20; y++) { //X representa a coordenada x do bloco
            blocksList.add(new Block(0, y * 32));
        }
        for(int y = 0; y < 20; y++) { //X representa a coordenada x do bloco
            blocksList.add(new Block(640 - 32, y * 32));
        }
    }

    public static boolean hasntColide(int x, int y) {
        for(int i = 0; i < blocksList.size(); i++) {
            Block actualBlock = blocksList.get(i);
            if(actualBlock.intersects(new Rectangle(x, y, 32, 32))) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics graphics) {
        for(int i = 0; i < blocksList.size(); i++) {
            blocksList.get(i).render(graphics);
        }
    }
}
