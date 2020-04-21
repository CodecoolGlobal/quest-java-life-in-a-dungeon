package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Door implements Drawable {
    private Cell cell;
    private boolean hasKey = false;

    public Door(Cell cell){
        this.cell = cell;
        this.cell.setDoor(this);
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean getHasKey(){
        return this.hasKey;
    }

    @Override
    public String getTileName() {
        return getHasKey() ? "openDoor" : "closedDoor";
    }
}
