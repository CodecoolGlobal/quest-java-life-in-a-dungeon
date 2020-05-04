package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    DOOR("closedDoor"),
    OPENDOOR("openDoor"),
    EXIT("exitDoor"),
    TREE("tree"),
    GRASS("grass");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
