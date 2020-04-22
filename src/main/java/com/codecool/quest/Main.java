package com.codecool.quest;

import com.codecool.quest.logic.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    FileInputStream imageStream = new FileInputStream("./src/main/resources/sword.png");
    Image sword = new Image(imageStream, 30, 30, false, false);
    FileInputStream imageStream2 = new FileInputStream("./src/main/resources/shield.png");
    Image shield = new Image(imageStream2, 30, 30, false, false);
    FileInputStream imageStream1 = new FileInputStream("./src/main/resources/key.png");
    Image key = new Image(imageStream1, 35, 20, false, false);
    GridPane ui = new GridPane();

    Text healthLabel = new Text("");
    Integer inventoryLength = 0;
    int swordIndex = 2;
    int shieldIndex = 2;
    int keyIndex = 2;

    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Text health = new Text("Health:");
        health.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ui.add(health, 0, 0);

        healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(healthLabel, 1, 0);

        Text inventory = new Text("Inventory:");
        inventory.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        ui.add(inventory, 0, 1);

        Text swords = new Text("Swords:");
        swords.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(swords, 0, 3);

        Text shields = new Text("Shields:");
        shields.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(shields, 0, 4);

        Text keys = new Text("Keys:");
        keys.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(keys, 0, 5);

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

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
        Fight fight = new Fight();
        fight.standardFight(map.getPlayer());
        if (DoorOpen.checkDoors(map.getPlayer().getStuffedInventory(), map.getPlayer(), ui, keyIndex)){
            keyIndex--;
            inventoryLength--;
        }
        refresh();
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null ) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else if (cell.getDoor() != null){
                    Tiles.drawTile(context, cell.getDoor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        if (inventoryLength < map.getPlayer().getStuffedInventory().size()) {
            if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).equals("sword")) {
                    ui.add(new ImageView(sword), swordIndex, 3);
                    swordIndex++;

            }
            else if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).equals("shield")) {
                ui.add(new ImageView(shield), shieldIndex, 4);
                shieldIndex++;

            }
            else if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).equals("key")) {
                ui.add(new ImageView(key), keyIndex, 5);
                keyIndex++;
            }

            inventoryLength = map.getPlayer().getStuffedInventory().size();
            }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}

