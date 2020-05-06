package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.actors.Actor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Skeleton extends Actor {
    private Cell cell;


    public Skeleton(Cell cell) {
        super(cell);
        this.cell = cell;
        setAttackDamage(2);
    }

    @Override
    public String getTileName() {
        if (this.getHealth() <= 0){
            return "deathSkeleton";
        }
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy, GameMap map) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (this.getTileName().equals("skeleton") && !nextCell.getTileName().matches("wall|empty|closedDoor|spiderWeb") &&
                (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton|player|golem|deathGolem|deathSkeleton|spider"))){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
