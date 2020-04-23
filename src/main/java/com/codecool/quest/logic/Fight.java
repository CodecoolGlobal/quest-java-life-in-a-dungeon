package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import javafx.scene.layout.GridPane;
import com.codecool.quest.logic.RemoveNode;

import java.util.ArrayList;

public class Fight {

    // We have to check the names of inventory items.
    public boolean checkWhichKindOfFightYouFightWithEnemyIfYouHaveItemsInYourInventory(Player player) {
        Cell[] arrayCell = Cell.returnNeighbors(player.getCell());
        ArrayList<String> inventory = player.getStuffedInventory();
        for (Cell element : arrayCell) {
            if (element.getActor() != null && element.getActor().getHealth() > 0 && player.getHealth() > 0) {
                if (inventory.contains("sword") && !inventory.contains("shield")) {
                    specialFight(player, element, 2, 10);
                } else if (inventory.contains("sword") && inventory.contains("shield")) {
                    specialFightWithShield(player, element, 10, inventory);
                    return true;
                } else if (!inventory.contains("sword") && inventory.contains("shield")) {
                    specialFightWithShield(player, element, 5, inventory);
                    return true;
                } else {
                    specialFight(player, element, 2, 5);
                }
            }
        }
        return false;
    }

    public void specialFight(Player player, Cell cell, int playerDamage, int enemyDamage) {
        HealthLogic.decreaseLife(player, playerDamage, cell.getActor(), enemyDamage);
    }

    public void specialFightWithShield(Player player, Cell cell, int enemyDamage, ArrayList<String> inv) {
            HealthLogic.decreaseLife(player, 0, cell.getActor(), enemyDamage);
            inv.remove("shield");
    }
}
