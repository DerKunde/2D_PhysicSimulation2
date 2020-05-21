package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.MainView;

public class Controller {

    MainView mainView;

    @FXML
    StackPane mainViewContainer = new StackPane();

    @FXML
    TextField xInput = new TextField();
    TextField yInput = new TextField();

    public void initialize() {
        this.mainView = new MainView();
        this.mainView.draw();

        mainViewContainer.getChildren().add(mainView);
    }

    @FXML
    private Button pauseSimulation;

    @FXML
    private Button startSimulation;

    @FXML
    private Button stopSimulation;

    @FXML
    private Button restartSimulation;

    @FXML
    private Button createSimulation;

    @FXML
    private Button fensterSchließen;

    @FXML
    void create(ActionEvent event) {

        this.mainView.initialSimulation.createBall(100, 100, 50, 200);
        this.mainView.draw();

    }

    @FXML
    void pause(ActionEvent event) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.getSimulator().stop();
    }

    @FXML
    void restart(ActionEvent event) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();

        System.out.println("Reset!");
    }

    @FXML
    void schließen(ActionEvent event) {
        Stage stage = (Stage) fensterSchließen.getScene().getWindow();
        stage.close();

    }

    @FXML
    void start(ActionEvent event) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    @FXML
    void stop(ActionEvent event) {
        this.mainView.getSimulator().stop();
    }

}
