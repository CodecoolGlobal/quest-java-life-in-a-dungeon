package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import com.codecool.quest.logic.RemoveNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DoorOpen {

//    public static GridPane removeRowFromGridPane(int row, GridPane gridPane ) {
//        Set<Node> deleteNodes = new HashSet<>();
//        for (Node child : gridPane.getChildren()) {
//            // get index from child
//            Integer rowIndex = GridPane.getRowIndex(child);
//
//            // handle null values for index=0
//            int r = rowIndex == null ? 0 : rowIndex;
//
//            if (r == row) {
//                // collect matching rows for deletion
//                deleteNodes.add(child);
//            }
//        }
//        gridPane.getChildren().removeAll(deleteNodes);
//        return gridPane;
//    }


    public static boolean checkDoors(ArrayList<Item> ItemList, Player player, GridPane ui, int keyIndex) {
        Cell cell = player.getCell();
        ArrayList<String> aList = new ArrayList<>();
        for (Item items : ItemList) {
            aList.add(items.getTileName());
        }
        if (cell.getTileName().equals("exitDoor")) {
            EndGame.display("Congratulations!");
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


