package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.items.Item;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.codecool.quest.logic.RemoveNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    GameMap map = MapLoader.loadMap("map.txt");


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
    int shieldCount = 0;
    int swordCount = 0;
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

//        ui.add(newCanvas, 0,6);
//        GraphicsContext newContext = newCanvas.getGraphicsContext2D();
//        newContext.drawImage(Tiles.tileset, 1*34, 31*34, 32, 32,1,1, 32,32);

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
        borderPane.setLeft(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();

    }

    public static void drawItems(GraphicsContext newContext,String item) {
        Tiles.Tile tile = (Tiles.Tile) Tiles.getTileMap().get(item);
        newContext.drawImage(Tiles.getImage(), tile.x,tile.y, 32, 32, 1,1,32,32);
    }


    public static void drawItem2(GraphicsContext newContext, String item) {
        Tiles.Tile tile = (Tiles.Tile) Tiles.getTileMap().get(item);
        newContext.drawImage(Tiles.getImage(), tile.x, tile.y, tile.w, tile.h,1,1,32,32);
    }

    public void makeCanvas(Canvas newName, String item, int row, int index) {
        newName = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
        ui.add(newName, index, row);
        drawItems(newName.getGraphicsContext2D(), item);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(MapLoader.getMapName());
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
        boolean somethingBroke = fight.checkWhichKindOfFightYouFightWithEnemyIfYouHaveItemsInYourInventory(map.getPlayer());
        if (somethingBroke) {
            int countCurrentShields = 0;
            for (Item item : map.getPlayer().getStuffedInventory()){
                if (item.getTileName().equals("shield")) {
                    countCurrentShields++;
                }
            }
            if (shieldCount > countCurrentShields) {
                RemoveNode.removeNodeByRowColumnIndex(4, shieldIndex - 1, ui);
                shieldIndex--;
                shieldCount--;
                inventoryLength--;
            }
        }
        Cell cell = map.getPlayer().getCell();
        if(cell.getTileName().equals("exitDoor")) {
            changeMapAndKeepInventory();
        }
        if (DoorOpen.checkDoors(map.getPlayer().getStuffedInventory(), map.getPlayer(), ui, keyIndex)) {
            keyIndex--;
            inventoryLength--;
        }
        refresh();
    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int playerXPos = map.getPlayer().getX();
        int playerYPos = map.getPlayer().getY();
        int canvasYPos;
        int canvasXPos = 5;
        for (int x = playerXPos - 8; x < playerXPos + 8; x++) {
            canvasYPos = 5;
            canvasXPos++;
            for (int y = playerYPos - 8 ; y < playerYPos + 8; y++) {
                canvasYPos++;
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), canvasXPos, canvasYPos);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), canvasXPos, canvasYPos);
                } else if (cell.getDoor() != null) {
                    Tiles.drawTile(context, cell.getDoor(), canvasXPos, canvasYPos);
                } else {
                    Tiles.drawTile(context, cell, canvasXPos, canvasYPos);
                }
            }
        }
        if (inventoryLength < map.getPlayer().getStuffedInventory().size()) {
            if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).getTileName().equals("sword")) {
                Canvas swordCanvas = new Canvas();
                makeCanvas(swordCanvas, "sword", 3, swordIndex);
                swordIndex++;
                swordCount++;
            } else if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).getTileName().equals("shield")) {
                Canvas shieldCanvas = new Canvas();
                makeCanvas(shieldCanvas, "shield", 4 , shieldIndex);
                shieldIndex++;
                shieldCount++;
            } else if (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).getTileName().equals("key")) {
                Canvas keyCanvas = new Canvas();
                makeCanvas(keyCanvas, "key", 5, keyIndex);
                keyIndex++;
                String musicPath = "src/main/resources/look_at_my_horse.wav";
                playAudio.playMusic(musicPath);
            }
            inventoryLength = map.getPlayer().getStuffedInventory().size();
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void changeMapAndKeepInventory() {
        ArrayList<Item> oldInv = map.getPlayer().getStuffedInventory();
        int oldHealth = map.getPlayer().getHealth();
        //map = MapLoader.loadMap("map1.txt");
        if (MapLoader.getMapName().equals("map.txt")) {
            map = MapLoader.loadMap("map1.txt");
        }else if (MapLoader.getMapName().equals("map1.txt")) {
            map = MapLoader.loadMap("map.txt");
        }

        for (Item item : oldInv){
            map.getPlayer().getStuffedInventory().add(item);
        }
        map.getPlayer().setHealth(oldHealth);
    }
}
