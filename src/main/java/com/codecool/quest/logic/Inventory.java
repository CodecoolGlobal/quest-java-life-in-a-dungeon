package com.codecool.quest.logic;

import com.codecool.quest.logic.items.Item;

import java.util.ArrayList;

public class Inventory {
    
    ArrayList<Item> inventory = new ArrayList<>();

    public ArrayList<Item> getInventory(){
        return inventory;
    }


}
