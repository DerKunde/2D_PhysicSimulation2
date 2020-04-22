package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Scanner;

public class World extends Canvas {

    Canvas canvas;
    GraphicsContext gc;

    public World(int widht, int height) {
        this.canvas = new Canvas(widht, height);
        gc = canvas.getGraphicsContext2D();
    }

    public GraphicsContext getGc() {
        return gc;
    }
}
