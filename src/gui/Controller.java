package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.MainView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Die Klasse Controller verbindet das GUI mit dem Backend. Hier werden alle Inputs abgegriffen und an die entsprechnenden
 * Methoden im Backend weitergegeben.
 */
public class Controller {

    MainView mainView;
    ArrayList<ToggleButton> toggleButtons;
    int wert;

    @FXML
    StackPane mainViewContainer = new StackPane();

    @FXML
    TextField xInput = new TextField();
    TextField yInput = new TextField();

    public void initialize() {
        this.mainView = new MainView();
        this.mainView.draw();

        mainViewContainer.getChildren().add(mainView);

        toggleButtons = new ArrayList<ToggleButton>();
        toggleButtons.add(geradeLinie);
        toggleButtons.add(schreageLinie);
        toggleButtons.add(falltuer);
        toggleButtons.add(ball);
        toggleButtons.add(rechteck);
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
    private ToggleButton geradeLinie;

    @FXML
    private ToggleButton schreageLinie;

    @FXML
    private ToggleButton falltuer;

    @FXML
    private ToggleButton ball;

    @FXML
    private ToggleButton rechteck;

    @FXML
    private TextField weightString;

    @FXML
    private  TextField heightString;

    @FXML
    private TextField elasticityString;

    @FXML
    private TextField posXString;

    @FXML
    private TextField posYString;

    /**
     * Erzeugt einen neuen Ball auf dem Canvas.
     * @param event
     */
    @FXML
    void create(ActionEvent event) {
        /*//int weight = Integer.parseInt(weightString.getText());
        String heightText = heightString.getText();
        int height = Integer.parseInt(heightText);
        //int elasticity= Integer.parseInt(elasticityString.getText());
        String posX_Text = posXString.getText();
        int posX= Integer.parseInt(posX_Text);

        String posY_Text = posYString.getText();
        int posY= Integer.parseInt(posX_Text);*/

        //this.mainView.initialSimulation.createBall(posX, posY, height, Color.BLUE);
        this.mainView.initialSimulation.createBall(200, 200, 70, Color.BLUE);
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
    /**
     *Hier werden die Events der Togglebuttons überwacht
     * @param event
     */
    @FXML
    void toggleLinie(ActionEvent event){
        checkAktiv(geradeLinie);
        this.wert = 1;
        this.mainView.setWert(wert);
        System.out.println("Gerade Linie");
    }
    @FXML
    void toggleSchreage(ActionEvent event){
        checkAktiv(schreageLinie);
        this.wert= 2;
        this.mainView.setWert(wert);
        System.out.println("Schreage Linie");
    }
    @FXML
    void toggleFalltuer(ActionEvent event){
        checkAktiv(falltuer);
        this.wert= 3;
        this.mainView.setWert(wert);
        System.out.println("Falltuer");
    }
    @FXML
    void toggleBall(ActionEvent event){
        checkAktiv(ball);
        this.wert =4;
        this.mainView.setWert(wert);
        System.out.println("Ball");
    }
    @FXML
    void toggleRect(ActionEvent event){
        checkAktiv(rechteck);
        this.wert =5;
        this.mainView.setWert(wert);
        System.out.println("Rechteck");
    }
    /**
     * Hier wird geprüft ob bereits ein witerer Button aktiv ist dieser Wird dan deaktiviert
     */
    @FXML
    private void checkAktiv(ToggleButton button){
        for(int i=0;i<toggleButtons.size();i++){
            if(!toggleButtons.get(i).getId().equals(button.getId())){
                toggleButtons.get(i).setSelected(false);
            }
        }
    }

}
