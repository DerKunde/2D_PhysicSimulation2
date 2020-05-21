package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Linie extends PhysicObject {

    double steigung;
    int height;
    int width;
    Color color;

    double x1;
    double y1;
    double x2;
    double y2;

    public Linie(double x1, double y1, double x2, double y2, double steigung, int height, Color color){
        this.height= height;
        this.color= color;
        this.steigung=steigung;
        //this.width = width;

        this.x1= x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }

    public double getSteigung() {
        return steigung;
    }

    public double getX1() {
        return x1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }


    public Color getColor() {
        return color;
    }



    public void drawObj(GraphicsContext gc) {
        gc.setLineWidth(this.height);
        //gc.setFill(getColor());
        gc.setStroke(getColor());
        gc.strokeLine(this.x1,this.y1,this.x2,this.y2+steigung);
    }
}
