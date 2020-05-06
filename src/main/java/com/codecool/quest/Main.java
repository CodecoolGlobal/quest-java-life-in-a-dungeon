package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.actors.Spider;
import com.codecool.quest.logic.items.Item;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.codecool.quest.logic.RemoveNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


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

    Text nameLabel = new Text("Name: ");
    Text healthLabel = new Text("");
    Integer inventoryLength = 0;
    Integer skullInventoryLength = 0;
    int shieldCount = 0;
    int swordCount = 0;
    int swordIndex = 2;
    int shieldIndex = 2;
    int keyIndex = 2;
    int skullIndex = 2;
    int skullRow = 6;

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

        NameCharacter.display(map);
        Text health = new Text("Health:");
        health.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ui.add(health, 0, 1);

        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(nameLabel, 0, 0);

        Text name = new Text("");
        name.setText(map.getPlayer().getName());
        ui.add(name, 1, 0);

        healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(healthLabel, 1, 1);

        Text inventory = new Text("Inventory:");
        inventory.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        ui.add(inventory, 0, 2);

        Text swords = new Text("Swords:");
        swords.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(swords, 0, 3);

        Text shields = new Text("Shields:");
        shields.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(shields, 0, 4);

        Text keys = new Text("Keys:");
        keys.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(keys, 0, 5);

        Text skulls = new Text("Skulls:");
        skulls.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ui.add(skulls, 0, 6);

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

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setLeft(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();

        createSkeletonTimers();
        createSpiderTimers();
        refreshTimer();
    }

    public static void drawItems(GraphicsContext newContext, String item) {
        Tiles.Tile tile = (Tiles.Tile) Tiles.getTileMap().get(item);
        newContext.drawImage(Tiles.getImage(), tile.x, tile.y, 32, 32, 1, 1, 32, 32);
    }


    public static void drawItem2(GraphicsContext newContext, String item) {
        Tiles.Tile tile = (Tiles.Tile) Tiles.getTileMap().get(item);
        newContext.drawImage(Tiles.getImage(), tile.x, tile.y, tile.w, tile.h, 1, 1, 32, 32);
    }

    public void makeCanvas(Canvas newName, String item, int row, int index) {
        newName = new Canvas(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
        ui.add(newName, index, row);
        drawItems(newName.getGraphicsContext2D(), item);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1, map);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1, map);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0, map);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0, map);
                refresh();
                break;
        }

        Fight fight = new Fight();
        boolean somethingBroke = fight.checkWhichKindOfFightYouFightWithEnemyIfYouHaveItemsInYourInventory(map.getPlayer());
        //ArrayList<Skeleton> skeletons = map.returnSkeletons();
        //for (Skeleton skeleton : skeletons){ fight.monsterAttack(skeleton, map.getPlayer());}
        if (somethingBroke) {
            int countCurrentShields = 0;
            for (Item item : map.getPlayer().getStuffedInventory()) {
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
        if (cell.getTileName().equals("exitDoor")) {
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
        mapDrawer();
        addItemToGraphicInv();
        addSkullToGraphicInv();
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void changeMapAndKeepInventory() {
        ArrayList<Item> oldInv = map.getPlayer().getStuffedInventory();
        int oldHealth = map.getPlayer().getHealth();
        if (MapLoader.getMapName().equals("map.txt")) {
            map = MapLoader.loadMap("map1.txt");
        } else if (MapLoader.getMapName().equals("map1.txt")) {
            map = MapLoader.loadMap("map.txt");
        }

        for (Item item : oldInv) {
            map.getPlayer().getStuffedInventory().add(item);
        }
        map.getPlayer().setHealth(oldHealth);
        createSpiderTimers();
        createSkeletonTimers();
    }

    private void createSpiderTimers() {
        ArrayList<Spider> spiders = map.returnSpiders();
        for (Spider spider : spiders) {
            createTimerForASpider(spider);
        }
    }

    private void createTimerForASpider(Spider spider){

        Timer spiderTimer = new Timer();
        TimerTask spiderTimerTask = new TimerTask() {

            @Override
            public void run() {
                if (spider.getTileName().equals("deathSkeleton")) {
                    spiderTimer.cancel();
                }else {
                    spider.moveRandomly();
                }
            }
        };
        spiderTimer.schedule(spiderTimerTask, 0, 2000);
    }

    private void createSkeletonTimers() {
        ArrayList<Skeleton> skeletons = map.returnSkeletons();
        for (Skeleton skeleton : skeletons) {
            createTimerForASkeleton(skeleton);
        }
    }

    private void createTimerForASkeleton(Skeleton skeleton){

        Timer skeletonTimer = new Timer();
        Fight skeletonFight = new Fight();
        TimerTask skeletonTimerTask = new TimerTask() {

            @Override
            public void run() {
                if (skeleton.getTileName().equals("deathSkeleton")) {
                    skeletonTimer.cancel();
                }else {
                    skeleton.moveRandomly();
                    skeletonFight.monsterAttack(skeleton, map.getPlayer());
                }
            }
        };
        skeletonTimer.schedule(skeletonTimerTask, 0, 600);
    }

    private void refreshTimer(){
        Timer refreshTimer = new Timer();
        TimerTask refreshTimerTask = new TimerTask(){
            @Override
            public void run(){
                refresh();
            }
        };
        refreshTimer.schedule(refreshTimerTask, 0, 400);
    }

    public void addSkullToGraphicInv(){
        if(skullInventoryLength < map.getPlayer().getSkullInventory().size()){
            if(skullInventoryLength % 3 == 0 && skullInventoryLength != 0){
                skullIndex = 2;
                skullRow++;
            }
            Canvas skullCanvas = new Canvas();
            makeCanvas(skullCanvas, "deathSkeleton", skullRow, skullIndex);
            skullIndex++;
            skullInventoryLength = map.getPlayer().getSkullInventory().size();
        }
    }

    public void addItemToGraphicInv(){
        if (inventoryLength < map.getPlayer().getStuffedInventory().size()) {
            switch (map.getPlayer().getStuffedInventory().get(map.getPlayer().getStuffedInventory().size() - 1).getTileName()) {
                case "sword":
                    Canvas swordCanvas = new Canvas();
                    makeCanvas(swordCanvas, "sword", 3, swordIndex);
                    swordIndex++;
                    swordCount++;
                    map.getPlayer().setAttackDamage(10);
                    break;
                case "shield":
                    Canvas shieldCanvas = new Canvas();
                    makeCanvas(shieldCanvas, "shield", 4, shieldIndex);
                    shieldIndex++;
                    shieldCount++;
                    break;
                case "key":
                    Canvas keyCanvas = new Canvas();
                    makeCanvas(keyCanvas, "key", 5, keyIndex);
                    keyIndex++;
                    String musicPath = "srcs/main/resources/look_at_my_horse.wav";
                    /*playAudio.playMusic(musicPath);*/
                    break;
            }
            inventoryLength = map.getPlayer().getStuffedInventory().size();
        }
    }

    public void mapDrawer() {
        int playerXPos = map.getPlayer().getX();
        int playerYPos = map.getPlayer().getY();
        int horizontalStartPos = -1; // Starting position of playing screen, in a horizontal way from left.
        int leftFromPlayer = 20;
        int rightFromPlayer = 20;
        int topFromPlayer = 12;
        int bottomFromPlayer = 12;

        // When player goes to the left side of the screen, you will see fewer images.
        if (playerXPos - 21 <= horizontalStartPos) {
            leftFromPlayer = playerXPos - 1;
            rightFromPlayer = playerXPos + 20 + (22 - leftFromPlayer);
        }


        for (int x = playerXPos - leftFromPlayer; x < playerXPos + rightFromPlayer; x++) {
            int verticalStartPos = -1; // Starting position of playing screen, in a vertical way from top.
            if (horizontalStartPos < 76){
                horizontalStartPos++;
            }

            // When player goes to the top side of the screen, you will see ewer images.
            if (playerYPos - 12 <= verticalStartPos) {
                topFromPlayer = playerYPos - 1;
                bottomFromPlayer = playerYPos + 12 + (12 - topFromPlayer);
            } else if (playerYPos + 12 >= verticalStartPos + map.getHeight()) {
                bottomFromPlayer = map.getHeight() - playerYPos;
                /*topFromPlayer--;*/
            }

            for (int y = playerYPos - topFromPlayer; y < playerYPos + bottomFromPlayer; y++) {
                verticalStartPos++;
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), horizontalStartPos, verticalStartPos);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), horizontalStartPos, verticalStartPos);
                } else if (cell.getDoor() != null) {
                    Tiles.drawTile(context, cell.getDoor(), horizontalStartPos, verticalStartPos);
                } else {
                    Tiles.drawTile(context, cell, horizontalStartPos, verticalStartPos);
                }
            }
        }
    }
}
