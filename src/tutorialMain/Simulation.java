package tutorialMain;


import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Simulation {


    public ArrayList<Ball> objects = new ArrayList<>();
    double gravity;
    Ball ball1;
    Ball ball2;

    public Simulation() {
        this.gravity = 10;
        this.ball1 = new Ball(0, 250, 30, Color.CORAL);
        this.ball2 = new Ball(350, 250, 30, Color.BEIGE);
        objects.add(ball1);
        objects.add(ball2);

        ball1.setSpeed(6);
        ball2.setSpeed(-2);

        ball2.setMass(1.5);
    }

    public void update() {
        int y = ball1.getY();

        double speed = ball1.getSpeed();
        speed += (gravity/60);

        if((y + ball1.getRadius()) >= 400) {
            speed *= -0.8;
        }

        if(speed == 0) {
            speed = speed - gravity;
        }
        
        ball1.setSpeed(speed);
        System.out.println(speed);


        int newY = (int) (y + speed);
        ball1.setY(newY);
    }

    public void update2() {
        int x1 = ball1.getX();
        int x2 = ball2.getX();

        double speed1 = ball1.getSpeed();
        double speed2 = ball2.getSpeed();


        if(x1 + 30 >= x2) {
            speed1 = -((2*((ball1.getMass() * ball1.getSpeed() + ball2.getMass() * -ball2.getSpeed()) / (ball1.getMass() + ball2.getMass())) - ball1.getSpeed()));
            speed2 = -((2*((ball1.getMass() * ball1.getSpeed() + ball2.getMass() * -ball2.getSpeed()) / (ball1.getMass() + ball2.getMass())) - -ball2.getSpeed()));
        }

        System.out.println(speed1 + "|" + speed2);
        ball1.setX((int) (x1 + speed1));
        ball2.setX((int) (x2 + speed2));


        ball1.setSpeed(speed1);
        ball2.setSpeed(speed2);
    }


    public void createBall(int mouseX, int mouseY) {
        objects.add(new Ball(mouseX, mouseY, 40, Color.RED));
    }
}
