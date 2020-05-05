package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    String name;
    public Player(Cell cell) {
        super(cell);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTileName() {
        if (this.getHealth() <= 0){
            return "deathPlayer";
        }
        return "player";
    }
}
