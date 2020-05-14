package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import gui.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.spi.CalendarNameProvider;

public class MainView extends VBox {

    public static final int EDITING = 0;
    public static final int SIMULATING = 1;

    private Canvas canvas;

    public static final int CANVAS_HEIGHT = 400;
    public static final int CANVAS_WIDTH = 400;

    public Simulation initialSimulation;
    private Simulation simulation;

    private Simulator simulator;

    private int applicationState = EDITING;

    public MainView() {
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        Toolbar toolbar = new Toolbar(this);

        this.canvas.setOnMousePressed(this::handleDraw);


        this.getChildren().addAll(toolbar, this.canvas);

        Ball b1 = new Ball(100, 200, 30, Color.CORAL);
        Ball b2 = new Ball(300, 200, 30, Color.NAVY);
        b1.setSpeed(50f);
        b1.setMass(5);
        b2.setSpeed(0);
        b2.setMass(6);

        ArrayList<Ball> obj = new ArrayList<Ball>(
                Arrays.asList(b1)
        );

        this.initialSimulation = new Simulation(obj, 981f);
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
        gc.fillRect(0,0,450,450);
        drawScale();
        if(this.applicationState == EDITING) {
            drawSimulation(this.initialSimulation);
        } else {
            drawSimulation(this.simulation);
        }
    }

    private void drawSimulation(Simulation simulationToDraw) {

        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        for(int i = 0; i < simulationToDraw.objects.size(); i++) {
            double x = simulationToDraw.objects.get(i).getLayoutX();
            double y = simulationToDraw.objects.get(i).getLayoutY();
            int radius = simulationToDraw.objects.get(i).getRadius();
            Color color = simulationToDraw.objects.get(i).getColor();

            gc.setFill(color);
            gc.fillOval(x,y,radius,radius);
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

    public Simulator getSimulator() {
        return simulator;
    }

    public int getApplicationState() {
        return this.applicationState;
    }
}
