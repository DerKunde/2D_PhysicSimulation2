package main;

import physic.Ball;
import physic.PhysicObject;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;

public class Collision {

    public double getDistance(double x1, double y1, double x2, double y2) {
        double xDistance = x2 - x1;
        double yDistance = y2 -y1;

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public boolean checkCollision(PhysicObject obj) {

        double distance = getDistance(obj.getCenter().x, obj.getCenter().y, obj.getCenter().x, MainView.CANVAS_HEIGHT);
        System.out.println("Distance: " + distance);
        if(obj instanceof Ball) {
            System.out.println("Ball detected");
            if(distance - ((Ball) obj).getRadius()/2 <= 0 && ((Ball) obj).getSpeed().y >= 0) {
                return true;
            }
        }
        return false;
    }
}


