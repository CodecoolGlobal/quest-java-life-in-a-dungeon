package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;

public class Spider extends Actor {
    private Cell cell;


    public Spider(Cell cell) {
        super(cell);
        setHealth(20);
        setAttackDamage(1);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        if (this.getHealth() > 0) {
            return "spider";
        }
        return "floor";
    }

    @Override
    public void move(int dx, int dy, GameMap map) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (this.getTileName().equals("spider") && !nextCell.getTileName().matches("wall|empty|closedDoor") &&
                (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton|player|golem|deathGolem|deathSkeleton|spider"))){
            cell.setActor(null);
            new SpiderWeb(cell);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
