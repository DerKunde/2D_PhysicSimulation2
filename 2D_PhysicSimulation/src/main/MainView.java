package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import physic.Ball;
import physic.PhysicObject;
import physic.Rectangle;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * In der Klasse MainView wird das Zeichnen der Simulation behandelt. Außerdem werden hier die Simulationen bearbeitet.
 * Es gibt zwei Objekte der Klasse Simulation, initialSimulation speichert den Ausgangszustand ab um das Zurücksetzten zu ermöglichen.
 * Des weitern wird hier das Objekt simulator der Klasse Simulator erzeugt.
 */
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
    private int wertObj;

    public MainView() {
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        this.canvas.setOnMousePressed(this::handleDraw);


        this.getChildren().addAll(this.canvas);

        //Ball b1 = new Ball(new Vector2f(100,400), 30, Color.CORAL);
        //Ball b2 = new Ball(new Vector2f(200,300), 30, Color.NAVY);

        //PhysicObject r1 = new Rectangle(new Vector2f(0, 380), 400, 50,0);
        //PhysicObject r2 = new Rectangle(new Vector2f(500, 250), 300,50,0);

        ArrayList<PhysicObject> obj = new ArrayList<>(

        );

        this.initialSimulation = new Simulation(obj, new Vector2f(0,981f));
        this.simulation = Simulation.copy(this.initialSimulation);
    }

    /**
     * Mit einem Klick auf den Canvas kann eine neue Kugel erzeugt werden falls sie die Anwendung im EDITING Mode befindet.
     * @param mouseEvent
     */
    private void handleDraw(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        if(applicationState == EDITING) {
            this.initialSimulation.creatShape(mouseX, mouseY,wertObj);
            System.out.println(mouseX + ", " + mouseY);
            draw();
        }
    }

    /**
     * Die Methode draw überprüft den applicationState um zu entscheiden welche der beiden Simulationen gezeichnet werden
     * soll.
     */
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

    /**
     * Die übergeben Simulation wird gezeichnet.
     * @param simulationToDraw Zu zeichnende Simulation.
     */
    private void drawSimulation(Simulation simulationToDraw) {

        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        for(int i = 0; i < simulationToDraw.objects.size(); i++) {
            gc.save();
            simulationToDraw.objects.get(i).drawObj(gc);
            gc.restore();
        }

    }

    /**
     * Zeichnet eine einfache Orientierung für die Sprunghöhe.
     */
//    private void drawScale() {
//        GraphicsContext gc = this.canvas.getGraphicsContext2D();
//
//        for(int i = 400; i > 0; i -= 100) {
//            gc.setFill(Color.BLACK);
//            gc.fillRect(0, i, 30, 10);
//        }
//
//        for(int i = 0; i < 400; i += 100) {
//            gc.fillRect(i, 0, 10, 30);
//        }
//    }

    /**
     * Gibt die aktuelle Simulation zurück.
     * @return Aktuelle Simulation.
     */
    public Simulation getSimulation() {
        return this.simulation;
    }

    /**
     * Ändert den applicationState, erzeugt den Simulator und kopiert die initialSimulation die die simulation.
     * @param applicationState Status auf den die Anwendung gesetzt werden soll.
     */
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

    /**
     * @return gibt den Simulator zurück.
     */
    public Simulator getSimulator() {
        return simulator;
    }

    /**
     * @return gibt den Application State zurück.
     */
    public int getApplicationState() {
        return this.applicationState;
    }

    /**
     * Setzt den Status aktiv von dem Ausgewählten Togglebutton
     */
    public void setWert(int wert) {
        this.wertObj = wert;
    }

    public void clear(){
        this.initialSimulation.objects.clear();
        this.simulation.objects.clear();
        draw();
    }
}
