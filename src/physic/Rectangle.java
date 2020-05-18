package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import java.util.ArrayList;

public class Rectangle extends PhysicObject {

    Vector2f p01;
    Vector2f p02;
    Vector2f p03;
    Vector2f p04;

    ArrayList<Vector2f> verticies;

    int width;
    int height;


    public Rectangle(Vector2f center, int width, int height) {
        this.center = center;
        this.width = width;
        this.height = height;

        this.p01 = new Vector2f(center.x - width/2, center.y + height/2);
        this.p02 = new Vector2f(center.x - width/2, center.y - height/2);
        this.p03 = new Vector2f(center.x + width/2, center.y - height/2);
        this.p04 = new Vector2f(center.x + width/2, center.y + height/2);

        verticies = new ArrayList<>();
        verticies.add(p01);
        verticies.add(p02);
        verticies.add(p03);
        verticies.add(p04);

        this.velocity = new Vector2f(0,0);
        this.speed = new Vector2f(0,0);

        this.fixed = true;

    }


    public ArrayList<Vector2f> getVerticies() {
        return verticies;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void drawObj(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(this.center.x, this.center.y, width, height);

        gc.setFill(Color.RED);
        gc.fillOval(p01.x, p01.y, 10,10);
        gc.fillOval(p02.x, p02.y, 10,10);
        gc.fillOval(p03.x, p03.y, 10,10);
        gc.fillOval(p04.x, p04.y, 10,10);


    }



}
