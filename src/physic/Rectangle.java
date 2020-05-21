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

        verticies = new ArrayList<>();

        this.velocity = new Vector2f(0,0);
        this.speed = new Vector2f(0,0);

        this.fixed = true;

        this.axis = new ArrayList<>();

        update();
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

    public void calculateAxis() {
        for(int i = 0; i < verticies.size(); i++) {

            Vector2f p1 = verticies.get(i);
            Vector2f p2 = verticies.get(i + 1 == verticies.size() ? 0 : i + 1);

            Vector2f axe = new Vector2f(p1.x - p2.x, p1.y - p2.y);
            Vector2f normal = new Vector2f(axe.y, -axe.x);
            normal.normalize();

            this.axis.add(normal);
        }

    }

    @Override
    public Projection project(Vector2f axis) {

        float min = axis.dot(verticies.get(0));
        float max = min;

        for(int i = 1; i < verticies.size(); i++) {
            float p = axis.dot(verticies.get(i));

            if(p < min) {
                min = p;
            }
            if(p > max) {
                max = p;
            }
        }
        Projection proj = new Projection(min, max);

        return proj;
    }

    @Override
    public void update() {

        this.p01 = new Vector2f(center.x - width/2, center.y + height/2);
        this.p02 = new Vector2f(center.x - width/2, center.y - height/2);
        this.p03 = new Vector2f(center.x + width/2, center.y - height/2);
        this.p04 = new Vector2f(center.x + width/2, center.y + height/2);

        verticies.add(0,p01);
        verticies.add(1,p02);
        verticies.add(2,p03);
        verticies.add(3,p04);

    }

    public void drawObj(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(this.center.x - width/2, this.center.y - height/2, width, height);

        gc.setFill(Color.RED);
        gc.fillOval(p01.x - 5, p01.y - 5, 10,10);

        gc.setFill(Color.YELLOW);
        gc.fillOval(p02.x - 5, p02.y - 5, 10,10);

        gc.setFill(Color.GREEN);
        gc.fillOval(p03.x - 5, p03.y - 5, 10,10);

        gc.setFill(Color.BLUE);
        gc.fillOval(p04.x - 5, p04.y - 5, 10,10);

        gc.setFill(Color.PAPAYAWHIP);
        gc.fillOval(center.x - 5, center.y - 5, 10,10);

        for(int i = 0; i < axis.size(); i++) {
            gc.setFill(Color.NAVY);
            gc.fillOval(axis.get(i).x - 5, axis.get(i).y - 5, 10, 10);
        }
    }

}
