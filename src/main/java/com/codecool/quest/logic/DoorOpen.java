package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DoorOpen {


    public static boolean checkDoors(ArrayList<Item> ItemList, Player player, GridPane ui, int keyIndex) {
        Cell cell = player.getCell();
        ArrayList<String> aList = new ArrayList<>();
        for (Item items : ItemList) {
            aList.add(items.getTileName());
        }
        Cell[] arrayCell = {cell.getNeighbor(0, -1), cell.getNeighbor(0, 1), cell.getNeighbor(-1, 0), cell.getNeighbor(1, 0)};
        for (Cell element : arrayCell) {
            if (element.getTileName().equals("closedDoor") && aList.contains("key")) {
                int indexForKey = aList.indexOf("key");
                element.getDoor().setHasKey(true);
                ItemList.remove(indexForKey);
                RemoveNode.removeNodeByRowColumnIndex(5, keyIndex - 1, ui);
                element.setType(CellType.OPENDOOR);
                return true;
            }
        }
        return false;
    }
}
