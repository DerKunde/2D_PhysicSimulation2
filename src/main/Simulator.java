package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private MainView mainView;
    private Simulation simulation;

    private Duration deltaT;

    public Simulator(MainView mainView, Simulation simulation) {

        this.deltaT = Duration.seconds(1/60f);
        this.simulation = simulation;
        this.mainView = mainView;
        this.timeline = new Timeline(new KeyFrame(deltaT, this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void doStep(ActionEvent actionEvent) {
        this.simulation.step();
        this.mainView.draw();
    }

    public void start() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void setDeltaT(Duration deltaT) {
        this.deltaT = deltaT;
    }

    public Duration getDeltaT() {
        return deltaT;
    }
}
