package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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

    public static void removeNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {

        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (node instanceof ImageView && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                // use what you want to remove
                gridPane.getChildren().remove(node);
                break;
            }
        }
    }

    public static boolean checkDoors(ArrayList aList, Player player, GridPane ui, int keyIndex) {
        Cell cell = player.getCell();
        Cell[] arrayCell = {cell.getNeighbor(0, -1), cell.getNeighbor(0, 1), cell.getNeighbor(-1, 0), cell.getNeighbor(1, 0)};
        for (Cell element : arrayCell) {
            if (element.getTileName().equals("closedDoor") && aList.contains("key")) {
                element.getDoor().setHasKey(true);
                aList.remove("key");
                removeNodeByRowColumnIndex(5, keyIndex-1,ui);
//                removeRowFromGridPane(5, ui );
                element.setType(CellType.OPENDOOR);
                return true;
            }
        }
        return false;
    }
}

