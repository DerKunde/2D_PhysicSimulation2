package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import main.Main;
import main.MainView;


public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;

        Button newBall = new Button("New Ball");
        Button step = new Button("Step");
        Button reset = new Button("Reset");
        Button start = new Button("Start Sim");
        Button stop = new Button("Stop");

        newBall.setOnAction(this::handleNewBall);
        start.setOnAction(this::handleStart);
        step.setOnAction(this::handleStep);
        reset.setOnAction(this::handleReset);
        stop.setOnAction(this::handleStop);




        this.getItems().addAll(newBall, start, stop, step, reset);
    }
        //test123

    private void handleStop(ActionEvent actionEvent) {
        this.mainView.getSimulator().stop();
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();

        System.out.println("Reset!");
    }

    private void handleNewBall(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
    }

    private void handleStep(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);

        mainView.getSimulation().step();
        mainView.draw();
    }

}
