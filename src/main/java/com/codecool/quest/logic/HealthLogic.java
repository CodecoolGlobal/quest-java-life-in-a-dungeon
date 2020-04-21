package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.*;


public class HealthLogic {

    public void decreaseLife(Player player, int playerDamage, Actor enemy, int enemyDamage){
        player.setHealth(player.getHealth() - playerDamage);

        enemy.setHealth(enemy.getHealth() - enemyDamage);
    }
}
