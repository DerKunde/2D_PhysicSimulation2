package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import gui.Toolbar;
import physic.Ball;
import physic.PhysicObject;
import physic.Rectangle;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.Arrays;

public class MainView extends VBox {

    public static final int EDITING = 0;
    public static final int SIMULATING = 1;

    private Canvas canvas;

    public static final int CANVAS_HEIGHT = 500;
    public static final int CANVAS_WIDTH = 500;

    public Simulation initialSimulation;
    private Simulation simulation;

    private Simulator simulator;

    private int applicationState = EDITING;

    public int wertObj;

    public MainView() {
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.canvas.setOnMousePressed(this::handleDraw);

        this.getChildren().addAll(this.canvas);

        Ball b1 = new Ball(100, 200, 30, Color.CORAL);
        Ball b2 = new Ball(300, 200, 30, Color.NAVY);

        ArrayList<PhysicObject> obj = new ArrayList<>(
                Arrays.asList(b1)
        );

        this.initialSimulation = new Simulation(obj, new Vector2f(0,981f));
        this.simulation = Simulation.copy(this.initialSimulation);
    }

    //Befor der Ball erstellt wird wird diese Funktion aufgerufen
    private void handleDraw(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        //Hier ist einfach wenn der Modus Editing ist das man den Ball zeichnen kann
        if(applicationState == EDITING) {

            /*  TODO: Hier Könnte man statdessen eine Funktion einbinden die verschiedene Objekte zeichnet
                TODO: Am besten werden die Rechtecke eingefärbt so das man erkennt was ausgewählt ist*/

            this.initialSimulation.creatShape(mouseX,mouseY,this.wertObj);

            //CreatBall wird in Simulation erstellt
            //this.initialSimulation.createBall(mouseX, mouseY);

            System.out.println(mouseX + ", " + mouseY);
            draw();
        }
    }

    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,MainView.CANVAS_WIDTH + 50,MainView.CANVAS_HEIGHT + 50);
        drawScale();

        //Ist es editing dan speichere die simulation ansonsten aktiviere die Simulation
        if(this.applicationState == EDITING) {
            drawSimulation(this.initialSimulation);
        } else {
            drawSimulation(this.simulation);
        }
    }

    private void drawSimulation(Simulation simulationToDraw) {

        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        for(int i = 0; i < simulationToDraw.objects.size(); i++) {
            simulationToDraw.objects.get(i).drawObj(gc);
        }

    }

    private void drawScale() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        for(int i = 400; i > 0; i -= 100) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, i, 30, 10);
        }

        for(int i = 0; i < 400; i += 100) {
            gc.fillRect(i, 0, 10, 30);
        }
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setApplicationState(int applicationState) {

        if(applicationState == this.applicationState){
            return;
        }

        this.applicationState = applicationState;
        System.out.println("Current Application State: " + this.applicationState);
        if(applicationState == SIMULATING) {
            this.simulation = Simulation.copy(this.initialSimulation);
            this.simulator = new Simulator(this, this.simulation);
            System.out.println("Copy Made");
        }
    }

    public int getWert() {
        return wertObj;
    }

    public void setWert(int wert) {
        this.wertObj = wert;
        System.out.println("set Wert auf: "+ this.wertObj);
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public int getApplicationState() {
        return this.applicationState;
    }

}
