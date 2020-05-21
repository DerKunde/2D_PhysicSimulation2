package main;


import javafx.scene.paint.Color;
import physic.Ball;
import physic.Linie;
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

        this.objects.add(new Rectangle(new Vector2f(100, 300), 50, 50,Color.TRANSPARENT));
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
                copy.getObjects().add(new Rectangle(obj.getCenter(), ((Rectangle) obj).getWidth(), ((Rectangle) obj).getHeight(),((Rectangle)obj).getColor()));
            }
            if(obj instanceof Linie){
                //X1Y1 X2Y2 height Color
                copy.getObjects().add(new Linie(((Linie) obj).getX1(),((Linie) obj).getY1(), ((Linie) obj).getX2(),  ((Linie) obj).getY2(), ((Linie) obj).getSteigung(),((Linie) obj).getHeight(),((Linie) obj).getColor()));
            }
        }

        copy.gravity = simulation.gravity;

        return copy;
    }

   /* public void update() {

        for(int i = 0; i < this.objects.size(); i++) {

            Ball ball = this.objects.get(i);

            double y = ball.getCenterY();
            float velY = ball.getSpeed().y + gravity.y * (1/60f);
            System.out.println("Speed: " + velY);
            float newY = (float) (y + (velY * (1/60f)) + (0.5f * gravity.y * Math.pow(1/60f, 2)));

            Vector2f relVel = new Vector2f(0,0);
            relVel.y = velY - 0;

            if(collision.getDistance(ball.getCenterX(), ball.getCenterY(), ball.getCenterX(), 400) - ball.getRadius()/2 <= 0 && relVel.y > 0) {
                velY *= -0.8;
            }

            //System.out.println("Speed: " + speed);
            System.out.println("Y: " + newY);

            ball.setSpeed(new Vector2f(0, velY));
            ball.setY(newY);
            // System.out.println(speed);
        }

    }*/

    /*public void update2() {

        Ball b1 = (Ball) objects.get(0);
        Ball b2 = (Ball) objects.get(1);


        double x1 = b1.getCenter().x;
        double x2 = b2.getCenter().y;

        double speed1 = b1.getSpeed().x;
        double speed2 = b2.getSpeed().y;


        if(x1 + b1.getRadius() >= x2) {
            //speed1 = -((2*((b1.getMass() * speed1 + b2.getMass() * -speed2) / (b1.getMass() + b2.getMass())) - b1.getSpeed()));
            //speed2 = -((2*((b1.getMass() * b1.getSpeed() + b2.getMass() * -b2.getSpeed()) / (b1.getMass() + b2.getMass())) - -b2.getSpeed()));
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
   }*/

    /*public void update3() {

        for(int i = 0; i < objects.size(); i++) {

            if(objects.get(i).isFixed()) {
                break;
            }

            PhysicObject obj = objects.get(i);

            double x = obj.getCenter().x;
            double y = obj.getCenter().y;

            Vector2f speed = obj.getSpeed();
            Vector2f velocity = obj.getVelocity();

            float velX = speed.x + velocity.x * (1/60f);
            float velY = speed.y + gravity.y * (1/60f);

            if(collision.checkCollision(obj)) {
                velY *= -0.8;
                velX *= 0.8;
            }

            float newX = (float) (x + speed.x * (1/60f) + 0.5f * velocity.x * Math.pow(1/60f, 2));
            float newY = (float) (y + speed.y * (1/60f) + 0.5f * gravity.y * Math.pow(1/60f, 2));

            obj.setCenter(new Vector2f(newX, newY));

            obj.setSpeed(new Vector2f(velX, velY));
            System.out.println("X: " + newX + "Y: " + newY);
            System.out.println("VelX: " + velX + "VelY: " +velY);

        }
    }*/

    //Rollen auf gerader ebene
    public void update4() {
        Ball b1 = (Ball) this.objects.get(1);
        Linie linie = (Linie) this.objects.get(2);

        float xBall = b1.getCenter().x;
        float yBall = b1.getCenter().y;

        float x = (float) linie.getX1();
        float y = (float) linie.getY1();

        float endX = (float) linie.getX2();
        float endY = (float) ((float) linie.getY2()+linie.getSteigung());

        float steigung = (endY-y)/(endX-x);
        System.out.println(steigung);

        float newX = xBall+1f;
        float newY = yBall+steigung;

        System.out.println(/*"Steigung berechnet:" +steigung+*/" newX: "+x+", newY: "+newY+", EndX: "+endX+", EndY: "+endY);
        b1.setCenter(new Vector2f(newX,newY));
    }

    //Von Vika
    public void creatShape(int mouseX,int mouseY,int wertSim){
        //Gerade
        if(wertSim==1){
            objects.add(new Linie(mouseX,mouseY,mouseX+100,mouseY,0,3,Color.BLACK));
        }
        //Schräge mit Ball
        if(wertSim==2){
            objects.add(new Ball(mouseX,mouseY-25,50,Color.GREEN));
            objects.add(new Linie(mouseX,mouseY,mouseX+200,mouseY,60,2,Color.BLACK));
        }
        //Falltür oder was anderes
        if(wertSim==3){
            objects.add(new Linie(mouseX,mouseY,mouseX+100,mouseY,0,3,Color.RED));
        }
        //Ball
        if(wertSim==4){
            objects.add(new Ball(mouseX,mouseY,50,Color.RED));
        }
        //Rechteck
        if(wertSim==5){
            objects.add(new Rectangle(new Vector2f(mouseX,mouseY),50,50,Color.CHOCOLATE));
        }
        else {
            System.out.println("Kein Objekt ausgewählt!");
        }
    }

    //Hier wird der Ball auf dem Canvans erstellt
    public void createBall(int mouseX, int mouseY) {
        objects.add(new Ball(mouseX, mouseY, 40, Color.RED));
    }

    private ArrayList<PhysicObject> getObjects() {
        return this.objects;
    }

    public void step() {
        update4();
    }
}
