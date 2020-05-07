package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("spider", new Tile(28, 5));
        tileMap.put("sword", new Tile(1, 31));
        tileMap.put("shield", new Tile(6, 24));
        tileMap.put("deathSkeleton", new Tile(22, 23));
        tileMap.put("deathPlayer", new Tile(27, 6));
        tileMap.put("closedDoor", new Tile(3, 9));
        tileMap.put("openDoor", new Tile(6, 9));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("exitDoor", new Tile(1, 9));
        tileMap.put("tree", new Tile(0, 1));
        tileMap.put("grass", new Tile(0, 2));
        tileMap.put("golem", new Tile(30, 6));
        tileMap.put("deathGolem", new Tile(17, 24));
        tileMap.put("spiderWeb", new Tile(2, 15));
        tileMap.put("letter", new Tile(17, 27));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static Map<String, Tile> getTileMap() {
        return tileMap;
    }

    public static Image getImage() {
        return tileset;
    }
}
