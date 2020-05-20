package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.vecmath.Vector2f;
import java.util.ArrayList;

public class Linie extends PhysicObject {

    int height;
    int width;
    Color color;

    double x1;
    double y1;
    double x2;
    double y2;

    public Linie(double x1,double y1,double x2,double y2, int height, Color color){
        this.height= height;
        this.color= color;
        //this.width = width;

        this.x1= x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void drawObj(GraphicsContext gc) {
        gc.setLineWidth(this.height);
        //gc.setFill(getColor());
        gc.setStroke(getColor());
        gc.strokeLine(this.x1,this.y1,this.x2,this.y2);
    }
}
