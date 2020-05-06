package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Door;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.actors.SpiderWeb;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private Door door;
    private SpiderWeb spiderWeb;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static Cell[] returnNeighbors(Cell cell){
        return new Cell[]{cell.getNeighbor(0, -1), cell.getNeighbor(0, 1), cell.getNeighbor(-1, 0), cell.getNeighbor(1, 0)};
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setSpiderWeb(SpiderWeb spiderWeb) {
        this.spiderWeb = spiderWeb;
    }

    public Actor getActor() {
        return actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Door getDoor(){
        return door;
    }

    public Item getItem() {
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
