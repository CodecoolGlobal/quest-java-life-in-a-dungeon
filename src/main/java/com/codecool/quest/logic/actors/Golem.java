package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Golem extends Actor {


    public Golem(Cell cell) {
        super(cell);
        this.setHealth(15);
        setAttackDamage(4);
    }

    @Override
    public String getTileName() {
        if (this.getHealth() <= 0){
            return "deathGolem";
        }
        return "golem";
    }

}
