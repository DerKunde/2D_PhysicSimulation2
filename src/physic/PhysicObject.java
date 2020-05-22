package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector2f;
import java.util.ArrayList;

/**
 * Die abstrakte Klasse PhysicObject beschreibt ein physikalisches Objekt. Hier werden alle nötigen Grundeigenschaften
 * festgelegt die ein Objekt haben muss um berechnungen durchführen zu können.
 */
abstract public class PhysicObject {

    Vector2f velocity;
    Vector2f center;
    Vector2f speed;
    double mass;

    ArrayList<Vector2f> axis;

    boolean fixed;

    Color color;


    public Vector2f getCenter() {
        return center;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public void setCenter(Vector2f center) {
        this.center = center;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpeed(Vector2f speed) {
        this.speed = speed;
    }

    public Vector2f getSpeed() {
        return speed;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public abstract void drawObj(GraphicsContext gc);

    public ArrayList<Vector2f> getAxis() {
        return axis;
    }

    public void setAxis(ArrayList<Vector2f> axis) {
        this.axis = axis;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void calculateAxis();

    public abstract Projection project(Vector2f axis);

    public abstract void update();
}
