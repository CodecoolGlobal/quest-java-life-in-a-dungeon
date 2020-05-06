package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;

public class SpiderWeb extends Actor {

    public SpiderWeb(Cell cell){
        super(cell);
        this.setHealth(7);
    }

    @Override
    public String getTileName() {
        if(this.getHealth() > 0) {
            return "spiderWeb";
        }
        this.getCell().setType(CellType.FLOOR);
        return "floor";
    }
}
