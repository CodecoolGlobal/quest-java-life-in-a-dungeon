package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        if (this.getHealth() <= 0){
            return "deathPlayer";
        }
        return "player";
    }
}
