package main;

import javafx.scene.paint.Color;

public class Ball {

    private double x;
    private double y;
    private int radius;
    private float speed;
    private Color color;
    double mass;

    public Ball(double x, double y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.mass = 2;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public double getCenterX() {
        return x;
    }

    public double getCenterY() {
        return y;
    }

    public double getLayoutX() {return x - radius/2;}

    public double getLayoutY() {return y - radius/2;}

    public float getSpeed() {
        return speed;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeed(float speed) {
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
}
