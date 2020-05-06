package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Golem;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.actors.Spider;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private ArrayList<Skeleton> skeletons = new ArrayList<>();
    private ArrayList<Spider> spiders = new ArrayList<>();
    private Player player;
    private Skeleton skeleton;
    private Golem golem;
    private Spider spider;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    public ArrayList<Skeleton> returnSkeletons(){
        return skeletons;
    }
    public ArrayList<Spider> returnSpiders(){
        return spiders;
    }

    public void setSkeleton(Skeleton skeleton) {
        skeletons.add(skeleton);
        this.skeleton = skeleton;
    }

    public void setGolem(Golem golem) {
        this.golem = golem;
    }

    public Golem getGolem() {
        return golem;
    }

    public Spider getSpider() {
        return spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
        spiders.add(spider);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
