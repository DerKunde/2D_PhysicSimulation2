package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.MainView;

/**
 * Die Klasse Controller verbindet das GUI mit dem Backend. Hier werden alle Inputs abgegriffen und an die entsprechnenden
 * Methoden im Backend weitergegeben.
 */
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

    /**
     * Erzeugt einen neuen Ball auf dem Canvas.
     * @param event
     */
    @FXML
    void create(ActionEvent event) {

        this.mainView.initialSimulation.createBall(100, 100, 50, 200);
        this.mainView.draw();

    }

    /**
     * Pausiert die Simulation
     * @param event
     */
    @FXML
    void pause(ActionEvent event) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.getSimulator().stop();
    }

    /**
     * Startet die Simulation neu mit allen Startwerten.
     * @param event
     */
    @FXML
    void restart(ActionEvent event) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();

        System.out.println("Reset!");
    }

    /**
     * Beendet die Applikation
     * @param event
     */
    @FXML
    void schließen(ActionEvent event) {
        Stage stage = (Stage) fensterSchließen.getScene().getWindow();
        stage.close();

    }

    /**
     * Starten die Simulation
     * @param event
     */
    @FXML
    void start(ActionEvent event) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    /**
     * Löscht alle hinzugefügten Elemente aus der Simulation.
     * @param event
     */
    @FXML
    void stop(ActionEvent event) {
        this.mainView.getSimulator().stop();
    }

}
