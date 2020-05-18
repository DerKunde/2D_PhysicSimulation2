package main;


import javafx.scene.paint.Color;
import physic.Ball;
import physic.PhysicObject;
import physic.Rectangle;

import javax.vecmath.Vector2f;
import java.util.ArrayList;

public class Simulation {


    public ArrayList<PhysicObject> objects;
    Vector2f gravity;
    Collision collision = new Collision();


    ArrayList<PhysicObject> objList;

    public Simulation(ArrayList<PhysicObject> objects, Vector2f gravity) {

        this.objects = objects;
        this.gravity = gravity;

        this.objects.add(new Rectangle(new Vector2f(100, 300), 50, 50));
    }

    public Simulation() {
        this.objects = new ArrayList<PhysicObject>();

        this.gravity = new Vector2f(0, 9.81f); //  m/s2
    }


    public static Simulation copy (Simulation simulation) {

        Simulation copy = new Simulation();
        for(PhysicObject obj : simulation.getObjects()) {
            if(obj instanceof Ball) {
                copy.getObjects().add(new Ball(obj.getCenter().x, obj.getCenter().y, ((Ball) obj).getRadius(), ((Ball) obj).getColor()));
            }
            if(obj instanceof Rectangle) {
                copy.getObjects().add(new Rectangle(obj.getCenter(), ((Rectangle) obj).getWidth(), ((Rectangle) obj).getHeight()));
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
    public void update3() {

        for(int i = 0; i < objects.size(); i++) {

            if(objects.get(i).isFixed()) {
                break;
            }

            PhysicObject ball = objects.get(i);

            double x = ball.getCenter().x;
            double y = ball.getCenter().y;

            Vector2f speed = ball.getSpeed();
            Vector2f velocity = ball.getVelocity();

            float velX = speed.x + velocity.x * (1/60f);
            float velY = speed.y + gravity.y * (1/60f);

            if(collision.checkCollision(ball)) {
                velY *= -0.8;
                velX *= 0.8;
            }

            float newX = (float) (x + speed.x * (1/60f) + 0.5f * velocity.x * Math.pow(1/60f, 2));
            float newY = (float) (y + speed.y * (1/60f) + 0.5f * gravity.y * Math.pow(1/60f, 2));

            ball.setCenter(new Vector2f(newX, newY));

            ball.setSpeed(new Vector2f(velX, velY));
            System.out.println("X: " + newX + "Y: " + newY);
            System.out.println("VelX: " + velX + "VelY: " +velY);

        }
    }

    public void update4() {


    }


    public void createBall(int mouseX, int mouseY) {
        objects.add(new Ball(mouseX, mouseY, 40, Color.RED));
    }

    private ArrayList<PhysicObject> getObjects() {
        return this.objects;
    }

    public void step() {
        update3();
    }
}
