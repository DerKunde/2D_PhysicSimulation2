package physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.vecmath.Vector2f;
import java.util.ArrayList;

/**
 * Beschreibt eine physikalische Kugel und erweitert die abstrakte Klasse PhysicObject.
 */
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

    public Ball (Vector2f center, int radius, double mass) {
        this.center = center;
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

    /**
     * Zeichnet das Objekt auf dem Canvas.
     * @param gc Der GraphicsContext des Canvas auf dem gezeichnet wird.
     */
    public void drawObj(GraphicsContext gc) {

        gc.setFill(this.color);
        gc.fillOval(this.center.x, this.center.y, this.radius/2, this.radius/2);
    }

    @Override
    public void calculateAxis() {

    }

    /**
     * Berechnet die Achse für die Kollisionsüberprüfung.
     * @param a Vertex eines Polygons der am nächsten zum Mittelpunkt des Balles ist.
     */
    public void calculateAxis(Vector2f a) {

        Vector2f axes = new Vector2f(this.center.x - a.x, this.center.y -a.y);
        axes.normalize();

        this.axis.add(axes);
    }

    /**
     * Projiziert den Ball auf die Achsen des SAT.
     * @param axis Achse auf die projiziert wird.
     * @return Berechnete Projektion.
     */
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
