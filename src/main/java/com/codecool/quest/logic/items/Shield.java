package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Shield extends Item {
    public Shield(Cell cell) {
            super(cell);
        }
        @Override
        public String getTileName() {
            return "shield";
        }
}
