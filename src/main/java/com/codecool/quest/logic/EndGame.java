package com.codecool.quest.logic;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class EndGame {


        public static void display(String text) {
            Stage popUpWindow = new Stage();

            popUpWindow.initModality(Modality.APPLICATION_MODAL);
            popUpWindow.setTitle("Codecool Quest");

            Label label1= new Label(text + " Another game?");

            Button button1 = new Button("Yes");
            Button button2 = new Button("No");
            //button1.setOnAction(e -> MapLoader.loadMap());
            button2.setOnAction(e -> System.exit(0));

            VBox layout= new VBox(10);


            layout.getChildren().addAll(label1, button1, button2);

            layout.setAlignment(Pos.CENTER);

            Scene scene1= new Scene(layout, 300, 250);

            popUpWindow.setScene(scene1);

            popUpWindow.showAndWait();

        }
    }

