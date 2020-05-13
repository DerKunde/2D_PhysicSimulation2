package main;


import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Simulation {


    public ArrayList<Ball> objects;
    float gravity;
    Collision collision = new Collision();
    Ball b1;
    Ball b2;

    public Simulation(ArrayList<Ball> objects, float gravity) {

        this.objects = objects;
        this.gravity = gravity;
    }

    public Simulation() {
        this.objects = new ArrayList<>();

        this.gravity = 981f; //  cm/s2

    }


    public static Simulation copy (Simulation simulation) {

        Simulation copy = new Simulation();
        int i = 0;
        for(Ball ball : simulation.getObjects()) {
            ArrayList<Ball> copyObjects = copy.getObjects();
            copyObjects.add(i, simulation.getObjects().get(i));
            i++;
        }

        copy.gravity = simulation.gravity;

        System.out.println(copy.objects.get(0).getCenterX());

        return copy;
    }

    public void update() {

        for(int i = 0; i < objects.size(); i++) {

            Ball ball = objects.get(i);

            double y = ball.getCenterY();

            float speed = ball.getSpeed() + gravity * (1/60f);
            System.out.println("Speed: " + speed);
            double newY = (y + (speed * (1/60f)) + (0.5f * gravity * Math.pow(1/60f, 2)));

            if(collision.getDistance(ball.getCenterX(), ball.getCenterY(), ball.getCenterX(), 400) - ball.getRadius()/2 <= 0 && speed > 0) {
                speed *= -0.8;
            }

            //System.out.println("Speed: " + speed);
            System.out.println("Y: " + newY);

            ball.setSpeed(speed);
            ball.setY(newY);
            // System.out.println(speed);
        }

    }

    public void update2() {

        Ball b1 = objects.get(0);
        Ball b2 = objects.get(1);


        double x1 = b1.getCenterX();
        double x2 = b2.getCenterX();

        double speed1 = b1.getSpeed();
        double speed2 = b2.getSpeed();


        if(x1 + b1.getRadius() >= x2) {
            speed1 = -((2*((b1.getMass() * b1.getSpeed() + b2.getMass() * -b2.getSpeed()) / (b1.getMass() + b2.getMass())) - b1.getSpeed()));
            speed2 = -((2*((b1.getMass() * b1.getSpeed() + b2.getMass() * -b2.getSpeed()) / (b1.getMass() + b2.getMass())) - -b2.getSpeed()));
        }

        if(x1 + b1.getRadius() > MainView.CANVAS_WIDTH || x1 - b1.getRadius() < 0) {
            speed1 = -speed1;
        }

        if(x2 + b2.getRadius() > MainView.CANVAS_WIDTH || x2 - b2.getRadius() < 0){
            speed2 = -speed2;
        }

        System.out.println(speed1 + "|" + speed2);
        b1.setX((int) (x1 + speed1));
        b2.setX((int) (x2 + speed2));


        //b1.setSpeed(speed1);
        //b2.setSpeed(speed2);

        System.out.println(objects.size());
    }

    public void update3() {

        for(int i = 0; i < objects.size(); i++) {

            Ball ball = objects.get(i);

            double x = ball.getCenterX();
            double y = ball.getCenterY();

            float speed = ball.getSpeed();

            double newX = x + (speed * (1/60f));
            double newY = y + (0.5f * gravity * Math.pow((1/60f), 2));

            ball.setX(newX);
            ball.setY(newY);
            System.out.println("X: " + newX + "Y: " + newY);

        }
    }


    public void createBall(int mouseX, int mouseY) {
        objects.add(new Ball(mouseX, mouseY, 40, Color.RED));
    }

    private ArrayList<Ball> getObjects() {
        return this.objects;
    }

    public void step() {
        update();
    }
}
