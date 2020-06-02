package main;


import javafx.scene.paint.Color;
import physic.Ball;
import physic.PhysicObject;
import physic.Rectangle;

import javax.vecmath.Vector2f;
import java.awt.*;
import java.util.ArrayList;

/**
 * Die Klass Simulation strukturiert den Ablauf der Simulation. In der Liste objects sind alle Objekte gespeichert die in der Simulation vorhanden sind.
 */
public class Simulation {


    public ArrayList<PhysicObject> objects;
    Vector2f gravity;
    Collision collision = new Collision();

    public Simulation(ArrayList<PhysicObject> objects, Vector2f gravity) {

        this.objects = objects;
        this.gravity = gravity;
    }

    public Simulation() {
        this.objects = new ArrayList<PhysicObject>();

        this.gravity = new Vector2f(0, 9.81f); //  m/s2
    }

    /**
     * Fertigt eine Kopie einer Simulation an
     * @param simulation Zu kopierende Simulation
     * @return Kopie der Simulation
     */
    public static Simulation copy (Simulation simulation) {

        Simulation copy = new Simulation();
        for(PhysicObject obj : simulation.getObjects()) {
            if(obj instanceof Ball) {
                copy.getObjects().add(new Ball(new Vector2f(obj.getCenter().x, obj.getCenter().y), ((Ball) obj).getRadius(),obj.getMass(), ((Ball) obj).getColor()));
            }
            if(obj instanceof Rectangle) {
                Rectangle copyRect = new Rectangle(obj.getCenter(), ((Rectangle) obj).getWidth(), ((Rectangle) obj).getHeight(), ((Rectangle) obj).getRotation());
                copyRect.calculateAxis();
                copy.getObjects().add(copyRect);
            }
        }

        copy.gravity = simulation.gravity;

        return copy;
    }

//    public void update() {
//
//        for(int i = 0; i < this.objects.size(); i++) {
//
//            Ball ball = this.objects.get(i);
//
//            double y = ball.getCenterY();
//
//            float velY = ball.getSpeed().y + gravity.y * (1/60f);
//            System.out.println("Speed: " + velY);
//            float newY = (float) (y + (velY * (1/60f)) + (0.5f * gravity.y * Math.pow(1/60f, 2)));
//
//            Vector2f relVel = new Vector2f(0,0);
//            relVel.y = velY - 0;
//
//            if(collision.getDistance(ball.getCenterX(), ball.getCenterY(), ball.getCenterX(), 400) - ball.getRadius()/2 <= 0 && relVel.y > 0) {
//                velY *= -0.8;
//            }
//
//            //System.out.println("Speed: " + speed);
//            System.out.println("Y: " + newY);
//
//            ball.setSpeed(new Vector2f(0, velY));
//            ball.setY(newY);
//            // System.out.println(speed);
//        }
//
//    }

//    public void update2() {
//
//        Ball b1 = objects.get(0);
//        Ball b2 = objects.get(1);
//
//
//        double x1 = b1.getCenterX();
//        double x2 = b2.getCenterX();
//
//        double speed1 = b1.getSpeed();
//        double speed2 = b2.getSpeed();
//
//
//        if(x1 + b1.getRadius() >= x2) {
//            speed1 = -((2*((b1.getMass() * b1.getSpeed() + b2.getMass() * -b2.getSpeed()) / (b1.getMass() + b2.getMass())) - b1.getSpeed()));
//            speed2 = -((2*((b1.getMass() * b1.getSpeed() + b2.getMass() * -b2.getSpeed()) / (b1.getMass() + b2.getMass())) - -b2.getSpeed()));
//        }
//
//        if(x1 + b1.getRadius() > MainView.CANVAS_WIDTH || x1 - b1.getRadius() < 0) {
//            speed1 = -speed1;
//        }
//
//        if(x2 + b2.getRadius() > MainView.CANVAS_WIDTH || x2 - b2.getRadius() < 0){
//            speed2 = -speed2;
//        }
//
//        System.out.println(speed1 + "|" + speed2);
//        b1.setX((int) (x1 + speed1));
//        b2.setX((int) (x2 + speed2));
//
//
//        //b1.setSpeed(speed1);
//        //b2.setSpeed(speed2);
//
//        System.out.println(objects.size());
//    }
//

    /**
     * Führt Berechnungen für alle physikalischen Objekte in der Simulation durch und ruft die Kollisionsabfrage auf.
     */
    public void update3() {

        ArrayList<Ball> balls = new ArrayList<>();

        for (PhysicObject b:objects) {
            if(b instanceof Ball){
                balls.add((Ball) b);
            }
        }

        for(int i = 0; i < balls.size(); i++) {
//            if(objects.get(i).isFixed()) {
//                break;
//            }

            PhysicObject obj = balls.get(i);

            double x = obj.getCenter().x;
            double y = obj.getCenter().y;

            Vector2f speed = obj.getSpeed();
            Vector2f velocity = obj.getVelocity();
            System.out.println(velocity);

            //Geschwindigkeit in X und Y Richtung wird Berechnet.
            float speedX = speed.x + velocity.x * (1/60f);
            float speedY = speed.y + gravity.y * (1/60f);

            //Kollision mit den Rändern des Canvas wird geprüft.
            if(collision.checkCollision(obj)) {
                speedY *= -0.8;
                speedX *= 0.8;
            }

            //Kollision der Objekte untereinander wird geprüft.
            ArrayList<PhysicObject> colliders = new ArrayList<>(objects);
            colliders.remove(obj);
            for(PhysicObject collider : colliders) {
                if(collision.checkCollision(obj,collider) && speedY > 0) {
                    if(collider instanceof Rectangle && ((Rectangle) collider).getRotation()!= 0){
                        double velocity_ = gravity.y*Math.sin(Math.toRadians(((Rectangle) collider).getRotation()));
                        speedX = (float) (velocity_*(1/60f)+speedX);
                        speedY = (float) (velocity_*(1/60f)+speedY);
                    }
                    speedY *= -0.8;
                    speedX *= 0.8;
                }

            }
//            if(collision.checkCollision(objects.get(i), objects.get(i + 1 == objects.size() ? 0 : i + 1)) && speedY > 0) {
//                speedY *= -0.8;
//                speedX *= 0.8;
//            }


            if(!obj.isFixed()) {
                float newX = (float) (x + speed.x * (1/60f) + 0.5f * velocity.x * Math.pow(1/60f, 2));
                float newY = (float) (y + speed.y * (1/60f) + 0.5f * gravity.y * Math.pow(1/60f, 2));

                obj.setCenter(new Vector2f(newX, newY));
                obj.setSpeed(new Vector2f(speedX, speedY));
            }

            obj.update();
        }
    }

    public void update4() {


    }

    /**
     * Hier Wird geschaut welchen Wert der param "wert" hat und dämentsprechend das Shape erstellt
     */
    public void creatShape(int mouseX,int mouseY,int wertSim){
        //Gerade
        if(wertSim==1){
            objects.add(new Rectangle(new Vector2f(mouseX,mouseY),200,3,0));
        }
        //Schräge mit Ball
        if(wertSim==2){
            objects.add(new Rectangle(new Vector2f(mouseX,mouseY),200,3,45));
        }
        //Falltür
        if(wertSim==3){
            objects.add(new Rectangle(new Vector2f(mouseX,mouseY),200,3,0));
        }
        //Ball
        if(wertSim==4){
            objects.add(new Ball(new Vector2f(mouseX,mouseY),50,50,Color.RED));
        }
        //Rechteck
        if(wertSim==5){
            objects.add(new Rectangle(new Vector2f(mouseX,mouseY),50,50,0));
        }
        else {
            System.out.println("Kein Objekt ausgewählt!");
        }
    }


   /* public void createBall(int mouseX, int mouseY) {
        PhysicObject ball = new Ball(new Vector2f(mouseX,mouseY), 50, Color.RED);
        this.objects.add(ball);
    }*/

    public void createBall(int posX, int posY, int mass, int radius, Color color) {
        objects.add(new Ball(new Vector2f(posX,posY), radius,mass, color));
    }

    private ArrayList<PhysicObject> getObjects() {
        return this.objects;
    }

    /**
     * Wird im Simulator aufgerufen für jeden Frame.
     */
    public void step() {
        update3();
    }
}
