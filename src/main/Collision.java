package main;

import physic.Ball;
import physic.PhysicObject;
import physic.Projection;
import physic.Rectangle;

import javax.swing.*;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Collision {

    public double getDistance(double x1, double y1, double x2, double y2) {
        double xDistance = x2 - x1;
        double yDistance = y2 -y1;

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public boolean checkCollision(PhysicObject obj) {

        double distance = getDistance(obj.getCenter().x, obj.getCenter().y, obj.getCenter().x, MainView.CANVAS_HEIGHT);
        if(obj instanceof Ball) {
            if(distance - ((Ball) obj).getRadius()/2 <= 0 && ((Ball) obj).getSpeed().y >= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(PhysicObject obj1, PhysicObject obj2) {

        System.out.println("Collision Check Started");

        if(obj1 instanceof Rectangle && obj2 instanceof Rectangle) {

            ArrayList<Vector2f> axes1 = obj1.getAxis();
            ArrayList<Vector2f> axes2 = obj2.getAxis();

            for(int i = 0; i < axes1.size(); i++) {
                Vector2f axis = axes1.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    System.out.println("Axis1 " + i + " no overlap");
                    return false;
                }
            }

            for(int i = 0; i < axes2.size(); i++) {
                Vector2f axis = axes2.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    System.out.println("Axis2 " + i + " no overlap");
                    return false;
                }
            }
            return true;
        }

        if(obj1 instanceof Ball && obj2 instanceof Rectangle) {

            Vector2f center1 = obj1.getCenter();
            ArrayList<Vector2f> vertices = ((Rectangle) obj2).getVerticies();

            double initialDistance = getDistance(center1.x,center1.y, vertices.get(0).x, vertices.get(0).y);
            Vector2f minVertex = vertices.get(0);

            for(int i = 1; i < vertices.size(); i++) {

                double distance = getDistance(center1.x, center1.y, vertices.get(i).x, vertices.get(i).y);

                if(distance < initialDistance) {
                    minVertex = vertices.get(i);
                }
            }

            ((Ball) obj1).calculateAxis(minVertex);

            ArrayList<Vector2f> axes1 = obj1.getAxis();
            ArrayList<Vector2f> axes2 = obj2.getAxis();

            for(int i = 0; i < axes1.size(); i++) {
                Vector2f axis = axes1.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    System.out.println("Axis1 " + i + " no overlap");
                    return false;
                }
            }

            for(int i = 0; i < axes2.size(); i++) {
                Vector2f axis = axes2.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    System.out.println("Axis2 " + i + " no overlap");
                    return false;
                }
            }
            return true;
        }

        if(obj1 instanceof Rectangle && obj2 instanceof Ball) {


        }

        if(obj1 instanceof Ball && obj2 instanceof Ball) {

            Vector2f center1 = obj1.getCenter();
            Vector2f center2 = obj2.getCenter();

            if(getDistance(center1.x, center1.y, center2.x, center2.y) <= ((Ball) obj1).getRadius() + ((Ball) obj2).getRadius()) {
                return true;
            }
            return false;
        }

        return false;
    }
}