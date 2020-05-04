package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.items.Door;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Shield;
import com.codecool.quest.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    static String mapName;

    public static String getMapName() {
        return mapName;
    }

    public static void setMapName(String mapName) {
        MapLoader.mapName = mapName;
    }

    public static GameMap loadMap(String playMap) {
        setMapName(playMap);
        InputStream is = MapLoader.class.getResourceAsStream("/" + playMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'i':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            new Door(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'e':
                            cell.setType(CellType.EXIT);
                            break;
                        case 'w':
                            cell.setType(CellType.TREE);
                            break;
                        case 'g':
                            cell.setType(CellType.GRASS);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
