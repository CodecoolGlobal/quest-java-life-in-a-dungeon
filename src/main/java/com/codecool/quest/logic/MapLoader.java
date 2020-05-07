package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.*;
import com.codecool.quest.logic.items.*;

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
                            map.setSkeleton(new Skeleton(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.setGolem(new Golem(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            map.setSpider(new Spider(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.SPIDERWEB);
                            new SpiderWeb(cell);
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
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "golemLetter", "These are the Golems: \n" +
                                    "Golems can't move but has a big attack damage(4)");
                            break;
                        case 'y':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "skeletonLetter", "These are the Skeletons: \n" +
                                    "They run randomly, fast and have a medium attack damage(2)");
                            break;
                        case 'z':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "itemsLetter", "These are the items that you can use during your adventure \n" +
                                    "Sword: increase your attack damage to 10 \n" +
                                    "Key: you need to pick up a key to be able to open a closed door. \n" +
                                    "Closed door: a closed door which can be opened with a key. \n" +
                                    "Shield: shield has 4 health, so it can helpful against monsters. \n" + "After it's lost it health you will lose your shield!");
                            break;
                        case 'q':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "spiderLetter", "These are the Spiders: \n" +
                                    "They move randomly, but slowly and have a quite low attack damage(1). \n" +
                                    "Spiders are peaceful and won't attack you unless you hit them first. \n" +
                                    "They also make webs whenever they move.");
                            break;
                        case 'n':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "exitLetter", "This is the end of each map: \n" +
                                    "(HINT: Skulls might also increase your health.)");
                            break;
                        case '1':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "firstMapMission", "You need to collect 3 skulls to accomplish this map! \n" +
                                    "Good luck!");
                            break;
                        case '2':
                            cell.setType(CellType.FLOOR);
                            new Letter(cell, "secondMapMission", "You need to collect 10 skulls to accomplish this map! \n" +
                                    "Good luck!");
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
