package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;

public class Inventory {
    
    ArrayList<Item> inventory = new ArrayList<>();
    ArrayList<Actor> skullInventory = new ArrayList<>();

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public ArrayList<Actor> getSkullInventory() {
        return skullInventory;
    }
}
