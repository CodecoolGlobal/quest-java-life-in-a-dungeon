package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Player;

public class Fight {
    
    public void standardFight(Player player){
        HealthLogic healthlogic = new HealthLogic();
        Cell cell = player.getCell();
        Cell[] arrayCell = {cell.getNeighbor(0, -1), cell.getNeighbor(0, 1), cell.getNeighbor(-1, 0), cell.getNeighbor(1, 0)};
        for(Cell element: arrayCell){
            if(element.getActor() != null && element.getActor().getHealth() > 0 && player.getHealth() > 0){
                healthlogic.decreaseLife(player, 2, element.getActor(), 5);
            }
        }
    }
}
