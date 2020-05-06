package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private MainView mainView;
    private Simulation simulation;

    public Simulator(MainView mainView, Simulation simulation) {
        this.simulation = simulation;
        this.mainView = mainView;
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1/60f), this::doStep));
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

}
