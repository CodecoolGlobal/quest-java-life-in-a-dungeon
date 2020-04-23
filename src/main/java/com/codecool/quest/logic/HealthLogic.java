package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.*;


public class HealthLogic {

    public static void decreaseLife(Player player, int playerDamage, Actor enemy, int enemyDamage){
        player.setHealth(player.getHealth() - playerDamage);
        enemy.setHealth(enemy.getHealth() - enemyDamage);
    }

    public static void increaseLife(Player player, int health){
        if(player.getHealth() > 0){
            player.setHealth(player.getHealth() + health);
        }
    }
}
