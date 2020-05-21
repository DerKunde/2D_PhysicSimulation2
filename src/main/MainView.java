package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public MainView() {
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        this.canvas.setOnMousePressed(this::handleDraw);


        this.getChildren().addAll(this.canvas);

        Ball b1 = new Ball(new Vector2f(100,400), 30, Color.CORAL);
        Ball b2 = new Ball(new Vector2f(200,300), 30, Color.NAVY);

        PhysicObject r1 = new Rectangle(new Vector2f(100, 300), 150, 50);
        PhysicObject r2 = new Rectangle(new Vector2f(120, 320), 80,80);

        ArrayList<PhysicObject> obj = new ArrayList<>(
                Arrays.asList(r1)
        );

        this.initialSimulation = new Simulation(obj, new Vector2f(0,981f));
        this.simulation = Simulation.copy(this.initialSimulation);
    }

    private void handleDraw(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        if(applicationState == EDITING) {
            this.initialSimulation.createBall(mouseX, mouseY);
            System.out.println(mouseX + ", " + mouseY);
            draw();
        }
    }

    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,MainView.CANVAS_WIDTH + 50,MainView.CANVAS_HEIGHT + 50);
        //drawScale();
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
        }
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public int getApplicationState() {
        return this.applicationState;
    }
}
