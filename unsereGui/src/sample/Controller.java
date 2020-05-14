package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

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

    }

    @FXML
    void pause(ActionEvent event) {

    }

    @FXML
    void restart(ActionEvent event) {

    }

    @FXML
    void schließen(ActionEvent event) {
        Stage stage = (Stage) fensterSchließen.getScene().getWindow();
        stage.close();

    }

    @FXML
    void start(ActionEvent event) {

    }

    @FXML
    void stop(ActionEvent event) {

    }

}
