package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Letter extends Item {

    private String letterType;

    public Letter(Cell cell, String type, String content) {
        super(cell);
        this.setLetterType(type);
        setLetterContent(content);

    }

    @Override
    public String getTileName() {
        return "letter";
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }
}
