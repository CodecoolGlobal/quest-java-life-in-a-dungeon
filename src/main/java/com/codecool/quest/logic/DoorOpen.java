package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import java.util.ArrayList;

public class DoorOpen {

    public static void checkDoors(ArrayList aList, Player player) {
        Cell cell = player.getCell();
        if(cell.getTileName().equals("exitDoor")) {
            EndGame.display("Congratulations!");
        }
        Cell[] arrayCell = {cell.getNeighbor(0, -1), cell.getNeighbor(0, 1), cell.getNeighbor(-1, 0), cell.getNeighbor(1, 0)};
        for (Cell element : arrayCell) {
            if (element.getDoor() != null && aList.contains("key")) {
                element.getDoor().setHasKey(true);
                element.setType(CellType.OPENDOOR);
            }
        }
    }

}

