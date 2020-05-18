package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.vecmath.Vector2f;

public class Ball extends PhysicObject {

    private int radius;
    private Color color;
    double mass;

    public Ball(float x, float y, int radius, Color color) {
        this.center = new Vector2f(x, y);
        this.radius = radius;
        this.color = color;
        this.mass = 2;
        this.speed = new Vector2f(0f,0f);
        this.velocity = new Vector2f(20f,0f);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawObj(GraphicsContext gc) {

        gc.setFill(this.color);
        gc.fillOval(this.center.x, this.center.y, this.radius/2, this.radius/2);
    }
}
