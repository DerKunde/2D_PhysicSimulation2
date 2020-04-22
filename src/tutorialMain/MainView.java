package tutorialMain;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.event.ActionEvent;

public class MainView extends VBox {

    public static final int EDITING = 0;
    public static final int SIMULATION  = 1;

    private Button start;
    private Canvas canvas;

    private int canvasHeight = 400;
    private int canvasWidth = 400;

    private Simulation simulation;

    private int applicationState;

    public MainView() {
        this.start = new Button("Start");
        this.canvas = new Canvas(canvasWidth,canvasHeight);
        this.simulation = new Simulation();

        this.start.setOnAction(ActionEvent -> {
            simulation.update2();
            draw();
        });

        this.canvas.setOnMousePressed(this::handleDraw);


        this.getChildren().addAll(this.start, this.canvas);
    }

    private void handleDraw(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        simulation.createBall(mouseX, mouseY);
        System.out.println(mouseX + ", " + mouseY);
        draw();
    }

    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,450,450);

        for(int i = 0; i < simulation.objects.size(); i++) {
            int x = simulation.objects.get(i).getX();
            int y = simulation.objects.get(i).getY();
            int radius = simulation.objects.get(i).getRadius();
            Color color = simulation.objects.get(i).getColor();

            gc.setFill(color);
            gc.fillOval(x,y,radius,radius);
        }

    }
}
