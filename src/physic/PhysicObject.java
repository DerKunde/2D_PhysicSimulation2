package physic;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector2f;

abstract public class PhysicObject {

    Vector2f velocity;
    Vector2f center;
    Vector2f speed;
    double mass;


    boolean fixed;

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
}
