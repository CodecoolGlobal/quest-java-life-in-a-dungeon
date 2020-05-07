package com.codecool.quest.logic;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {

    public void display(String content) {
        Stage popUpWindow = new Stage();

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Codecool Quest");

        Label label1= new Label(content);

        VBox layout= new VBox(10);

        layout.getChildren().addAll(label1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 550, 200);

        popUpWindow.setScene(scene1);

        popUpWindow.showAndWait();

    }
}
