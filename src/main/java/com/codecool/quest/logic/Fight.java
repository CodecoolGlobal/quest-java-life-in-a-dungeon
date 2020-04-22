package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;
import java.util.ArrayList;

public class Fight {

    // We have to check the names of inventory items.
    public void checkWhichKindOfFightYouFightWithEnemyIfYouHaveItemsInYourInventory(Player player){
        Cell[] arrayCell = Cell.returnNeighbors(player.getCell());
        ArrayList<String> inventory = player.getStuffedInventory();
        if(inventory.contains("sword") && !inventory.contains("shield")){
            specialFight(player, arrayCell, 2, 10);
        } else if(inventory.contains("sword") && inventory.contains("shield")){
            specialFight(player, arrayCell, 0, 10);
        } else if(!inventory.contains("sword") && inventory.contains("shield")){
            specialFight(player, arrayCell, 0, 5);
        } else {
            specialFight(player, arrayCell, 2, 5);
        }
    }

    public void specialFight(Player player, Cell[] arrayCell, int playerDamage, int enemyDamage){
        for(Cell element: arrayCell){
            if(element.getActor() != null && element.getActor().getHealth() > 0 && player.getHealth() > 0){
                HealthLogic.decreaseLife(player, playerDamage, element.getActor(), enemyDamage);
            }
        }
    }


}
