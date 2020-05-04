package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import javafx.scene.layout.GridPane;
import com.codecool.quest.logic.RemoveNode;

import java.util.ArrayList;

public class Fight {


    // We have to check the names of inventory items.
    public Boolean checkWhichKindOfFightYouFightWithEnemyIfYouHaveItemsInYourInventory(Player player) {
        Cell[] arrayCell = Cell.returnNeighbors(player.getCell());
        ArrayList<Item> inventory = player.getStuffedInventory();
        ArrayList<String> inventory1 = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            inventory1.add(inventory.get(i).getTileName());
        }
        if (inventory1.contains("sword") && !inventory1.contains("shield")) {
            specialFight(player, arrayCell, 2, 10);
        } else if (inventory1.contains("sword") && inventory1.contains("shield")) {
            int index = inventory1.indexOf("shield");
            specialFightWithShield(player, arrayCell, 10, inventory, index);
            return true;
        } else if (!inventory1.contains("sword") && inventory1.contains("shield")) {
            int index = inventory1.indexOf("shield");
            specialFightWithShield(player, arrayCell, 5,inventory, index);
            return true;
        } else {
            specialFight(player, arrayCell, 2, 5);
        }
        return false;
    }


    public void specialFight(Player player, Cell[] arrayCell, int playerDamage, int enemyDamage) {
        for (Cell element : arrayCell) {
            if (element.getActor() != null && element.getActor().getHealth() > 0 && player.getHealth() > 0) {
                HealthLogic.decreaseLife(player, playerDamage, element.getActor(), enemyDamage);
            }
        }
    }

    public void specialFightWithShield(Player player, Cell[] arrayCell, int enemyDamage, ArrayList<Item> inv, int ind) {
        for (Cell element : arrayCell) {
            if (element.getActor() != null && element.getActor().getHealth() > 0 && player.getHealth() > 0) {
                HealthLogic.decreaseLife(player, 0, element.getActor(), enemyDamage);
                inv.get(ind).setHealth(inv.get(ind).getHealth()-1);
                if (inv.get(ind).getHealth() == 0) {
                    inv.remove(inv.get(ind));
                }
            }
        }
    }


}