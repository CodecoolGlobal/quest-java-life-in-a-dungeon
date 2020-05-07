package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Player extends Actor {
    String name;
    public Player(Cell cell) {
        super(cell);
        setAttackDamage(5);
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
