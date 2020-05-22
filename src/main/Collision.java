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

/**
 * In der Klasse Collision wird die Kollision von Objekten geprüft. Wenn eine Kollision festgestellt wurde wird entsprechend
 * auf diese Kollision reagiert.
 */
public class Collision {

    /**
     * Berechnet den Abstand zwischen zwei Punkten.
     * @param x1 x Koordinate Punkt 1
     * @param y1 y Koordiante Punkt 1
     * @param x2 x Koordinate Punkt 2
     * @param y2 y Koordinate Punkt 2
     * @return Entfernung der zwei übergebenen Punkte zueinander.
     */
    public double getDistance(double x1, double y1, double x2, double y2) {
        double xDistance = x2 - x1;
        double yDistance = y2 -y1;

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    /**
     * Überprüft die Kollision eines PhysicObjects mit dem Rändern des Canvas.
     * @param obj Objekt welches auf Kollision geprüft werden soll.
     * @return true wenn es zu einer Kollision kommt.
     */
    public boolean checkCollision(PhysicObject obj) {

        if(obj instanceof Ball) {
            double distance = getDistance(obj.getCenter().x, obj.getCenter().y, obj.getCenter().x, MainView.CANVAS_HEIGHT);
            if(distance - ((Ball) obj).getRadius()/2 <= 0 && ((Ball) obj).getSpeed().y >= 0) {
                return true;
            }
            distance = getDistance(obj.getCenter().x, obj.getCenter().y, MainView.CANVAS_WIDTH, obj.getCenter().y);
            if(distance - ((Ball) obj).getRadius()/2 <= 0 && ((Ball) obj).getSpeed().y >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Überprüft die Kollision zweier PhysicObjects miteinander. Über das Separating Axes Theorem wird festgestellt ob
     * es zu einer Kollision kommt.
     * @param obj1 Erstes Objekt.
     * @param obj2 Zweites Objekt
     * @return true wenn Kollision vorhanden ist.
     */
    public boolean checkCollision(PhysicObject obj1, PhysicObject obj2) {

        //Kollision zweier Rechtecke.
        if(obj1 instanceof Rectangle && obj2 instanceof Rectangle) {

            ArrayList<Vector2f> axes1 = obj1.getAxis();
            ArrayList<Vector2f> axes2 = obj2.getAxis();

            for(int i = 0; i < axes1.size(); i++) {
                Vector2f axis = axes1.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    return false;
                }
            }

            for(int i = 0; i < axes2.size(); i++) {
                Vector2f axis = axes2.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    return false;
                }
            }
            return true;
        }
        //Kollision eines Balles mit einem Rechteck.
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
                    return false;
                }
            }

            for(int i = 0; i < axes2.size(); i++) {
                Vector2f axis = axes2.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    return false;
                }
            }
            return true;
        }

        //Kollision eines Rechteckes mit einem Ball.
        if(obj1 instanceof Rectangle && obj2 instanceof Ball) {

            Vector2f centerBall = obj2.getCenter();
            ArrayList<Vector2f> vertices = ((Rectangle) obj1).getVerticies();

            double initialDistance = getDistance(centerBall.x,centerBall.y, vertices.get(0).x, vertices.get(0).y);
            Vector2f minVertex = vertices.get(0);

            for(int i = 1; i < vertices.size(); i++) {

                double distance = getDistance(centerBall.x, centerBall.y, vertices.get(i).x, vertices.get(i).y);

                if(distance < initialDistance) {
                    minVertex = vertices.get(i);
                }
            }

            ((Ball) obj2).calculateAxis(minVertex);

            ArrayList<Vector2f> axisRec = obj1.getAxis();
            ArrayList<Vector2f> axisBall = obj2.getAxis();

            for(int i = 0; i < axisRec.size(); i++) {
                Vector2f axis = axisRec.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    return false;
                }
            }

            for(int i = 0; i < axisBall.size(); i++) {
                Vector2f axis = axisBall.get(i);

                Projection p1 = obj1.project(axis);
                Projection p2 = obj2.project(axis);

                if(!p1.doesOverlap(p2)) {
                    return false;
                }
            }
            return true;

        }

        //Kollision zweier Bälle.
        if(obj1 instanceof Ball && obj2 instanceof Ball) {

            Vector2f center1 = obj1.getCenter();
            Vector2f center2 = obj2.getCenter();

            if(getDistance(center1.x, center1.y, center2.x, center2.y) <= ((Ball) obj1).getRadius()/2 + ((Ball) obj2).getRadius()/2) {
                return true;
            }
            return false;
        }

        return false;
    }
}