package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.vecmath.Vector2f;
import java.util.ArrayList;

public class Ball extends PhysicObject {

    private int radius;

    public Ball(Vector2f center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.mass = 2;
        this.speed = new Vector2f(0f,0f);
        this.velocity = new Vector2f(20f,-100f);

        this.axis = new ArrayList<>();
    }

    public Ball (float x, float y, int radius, double mass) {
        this.center = new Vector2f(x,y);
        this.radius = radius;
        this.mass = mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public void setX(float x) {
        this.center.x = x;
    }

    public void setY(float y) {
        this.center.y = y;
    }

    public void setSpeed(Vector2f speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void drawObj(GraphicsContext gc) {

        gc.setFill(this.color);
        gc.fillOval(this.center.x, this.center.y, this.radius/2, this.radius/2);
    }

    @Override
    public void calculateAxis() {

    }

    public void calculateAxis(Vector2f a ) {

        Vector2f axes = new Vector2f(this.center.x - a.x, this.center.y -a.y);
        axes.normalize();

        this.axis.add(axes);
    }

    @Override
    public Projection project(Vector2f axis) {

        float min = axis.dot(this.center) - this.radius/2;
        float max = axis.dot(this.center) + this.radius/2;

        Projection proj = new Projection(min, max);

        return proj;

    }

    @Override
    public void update() {

    }
}
