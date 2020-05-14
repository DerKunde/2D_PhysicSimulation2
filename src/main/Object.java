package main;

import javax.vecmath.Vector2d;

public class Object {

    Vector2d velocity;
    double mass;
    int x;
    int y;

    public Vector2d getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
