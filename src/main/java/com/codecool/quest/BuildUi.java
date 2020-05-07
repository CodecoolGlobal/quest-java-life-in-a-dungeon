package com.codecool.quest;

import com.codecool.quest.logic.GameMap;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BuildUi {

    public static void addToUi(GridPane ui, Text name, Text healthLabel, GameMap map){
        Text health = createText("Health:", 20);
        ui.add(health, 0, 1);
        Text skulls = createText("Skulls:", 15);
        ui.add(skulls, 0, 6);
        Text keys = createText("Keys:", 15);
        ui.add(keys, 0, 5);
        Text shields = createText("Shields:", 15);
        ui.add(shields, 0, 4);
        Text nameLabel = createText("Name: ", 15);
        ui.add(nameLabel, 0, 0);
        Text swords = createText("Swords:", 15);
        ui.add(swords, 0, 3);
        Text inventory = createText("Inventory", 18);
        ui.add(inventory, 0, 2);


        name.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        name.setFill(Color.ANTIQUEWHITE);
        name.setText(map.getPlayer().getName());
        ui.add(name, 1, 0);

        healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        healthLabel.setFill(Color.ANTIQUEWHITE);
        ui.add(healthLabel, 2, 1);
    }

    public static Text createText(String textName, int charSize){
        Text text = new Text(textName);
        text.setFont(Font.font("Arial", FontWeight.BOLD, charSize));
        text.setFill(Color.ANTIQUEWHITE);
        return text;
    }

    public static void createConstraints(GridPane ui) {
        ui.getColumnConstraints().add(new ColumnConstraints(80));
        ui.getColumnConstraints().add(new ColumnConstraints(18));
        ui.getColumnConstraints().add(new ColumnConstraints(30));
        ui.getColumnConstraints().add(new ColumnConstraints(30));
        ui.getColumnConstraints().add(new ColumnConstraints(30));
        ui.getRowConstraints().add(new RowConstraints(40));
        ui.getRowConstraints().add(new RowConstraints(20));
        ui.getRowConstraints().add(new RowConstraints(20));
        ui.getRowConstraints().add(new RowConstraints(40));
        ui.getRowConstraints().add(new RowConstraints(40));
        ui.getRowConstraints().add(new RowConstraints(40));
        ui.getRowConstraints().add(new RowConstraints(40));
    }


}
