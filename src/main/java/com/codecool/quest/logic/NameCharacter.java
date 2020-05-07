package com.codecool.quest.logic;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class NameCharacter {


        public static void display(GameMap map) {
            Stage popUpWindow = new Stage();

            popUpWindow.initModality(Modality.APPLICATION_MODAL);
            popUpWindow.setTitle("Codecool Quest");

            Label label1 = new Label("Give a name:");
            TextField textF = new TextField();

            Button button1 = new Button("Save");
            button1.setOnAction(e -> map.getPlayer().setName(textF.getText()));

            VBox layout= new VBox(10);


            layout.getChildren().addAll(label1, textF, button1);

            layout.setAlignment(Pos.CENTER);

            Scene scene1= new Scene(layout, 300, 250);

            popUpWindow.setScene(scene1);

            popUpWindow.showAndWait();

        }
    }

