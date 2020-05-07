package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;
import java.util.Random;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int attackDamage;

    Inventory inventory = new Inventory();


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy, GameMap map) {
        try {
            Cell nextCell = cell.getNeighbor(dx, dy);

            if(nextCell.getActor() != null && nextCell.getActor().getTileName().equals("deathSkeleton")){
                HealthLogic.increaseLife((Player) cell.getActor(), 1);
                inventory.getSkullInventory().add(nextCell.getActor());
            }
            if(nextCell.getActor() != null && nextCell.getActor().getTileName().equals("deathGolem")) {
                HealthLogic.increaseLife((Player) cell.getActor(), 3);
                inventory.getSkullInventory().add(nextCell.getActor());
            }
            if(cell.getActor().getTileName().equals("deathPlayer") || map.getPlayer().getName().matches("Csaba|Isti|Áron|Viktor|Máté")){
                cell.setActor(nextCell.getActor());
                nextCell.setActor(this);
                cell = nextCell;
            } else if (!nextCell.getTileName().matches("wall|empty|closedDoor|spiderWeb") && (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton|golem"))) {
                if (nextCell.getItem() != null) {
                    inventory.getInventory().add(nextCell.getItem());
                    cell.setActor(null);
                    nextCell.setItem(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                } else if (!nextCell.getTileName().matches("wall|empty|spiderWeb") && (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton|golem|spider"))) {
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds:" + e);
        }
    }

    public void moveRandomly(GameMap map){

        Random randomStep = new Random();
        int random = randomStep.nextInt(4);
        switch (random){
            case 0:
                move(1,0, map);
                break;
            case 1:
                move(0,1, map);
                break;
            case 2:
                move(-1,0, map);
                break;
            case 3:
                move(0,-1, map);
                break;
        }
    }

    public ArrayList<Item> getStuffedInventory() {
        return inventory.getInventory();
    }

    public ArrayList<Actor> getSkullInventory(){
        return inventory.getSkullInventory();
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}
