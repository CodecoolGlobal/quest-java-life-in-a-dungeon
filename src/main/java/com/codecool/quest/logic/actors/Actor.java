package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.*;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    Inventory inventory = new Inventory();


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        try {
            Cell nextCell = cell.getNeighbor(dx, dy);

            if(nextCell.getActor() != null && nextCell.getActor().getTileName().equals("deathSkeleton")){
                HealthLogic.increaseLife((Player) cell.getActor(), 2);
            }
            if(cell.getActor().getTileName().equals("deathPlayer")){
                cell.setActor(nextCell.getActor());
                nextCell.setActor(this);
                cell = nextCell;
            } else if (!nextCell.getTileName().matches("wall|empty|closedDoor") && (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton"))) {
                if (nextCell.getItem() != null) {
                    inventory.getInventory().add(nextCell.getItem().getTileName());
                    cell.setActor(null);
                    nextCell.setItem(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                } else if (!nextCell.getTileName().matches("wall|empty") && (nextCell.getActor() == null || !nextCell.getActor().getTileName().matches("skeleton"))) {
                    cell.setActor(null);
                    nextCell.setActor(this);
                    cell = nextCell;
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds:" + e);
        }
    }

    public ArrayList<String> getStuffedInventory() {
        return inventory.getInventory();
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
}
