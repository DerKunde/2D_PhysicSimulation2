package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import main.MainView;
import sun.util.resources.CalendarData;

import java.awt.*;
import java.util.ArrayList;


public class Controller {

    MainView mainView;
    ArrayList<ToggleButton> toggleButtons;

    @FXML
    StackPane mainViewContainer = new StackPane();

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
    void create(ActionEvent event) {

    }

    @FXML
    void pause(ActionEvent event) {
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

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    int wert;
/*    @FXML
    void toggle(ActionEvent actionEvent){
        System.out.println(actionEvent.getSource());
        if(actionEvent.getSource()==geradeLinie){
            checkAktiv(geradeLinie);
            this.wert = 1;
            this.mainView.setWert(wert);
            System.out.println("Gerade Linie wurde ausgewählt.");
        }
        if(actionEvent.getSource()==schreageLinie){
            checkAktiv(schreageLinie);
            this.wert= 2;
            this.mainView.setWert(wert);
            System.out.println("Schreage Linie wurde ausgewählt");
        }
        if(actionEvent.getSource()==falltuer){
            checkAktiv(falltuer);
            this.wert= 3;
            this.mainView.setWert(wert);
            System.out.println("Falltuer wurde ausgewählt.");
        }
        if(actionEvent.getSource()==ball){
            checkAktiv(ball);
            this.wert =4;
            this.mainView.setWert(wert);
            System.out.println("Ball wurde ausgewählt.");
        }
        if(actionEvent.getSource()==rechteck){
            checkAktiv(rechteck);
            this.wert =5;
            this.mainView.setWert(wert);
            System.out.println("Recht-eck wurde ausgewählt.");
        }else {
            this.wert= 6;
            this.mainView.setWert(wert);
        }

    }*/

    @FXML
    void toggleLinie(ActionEvent event){
        checkAktiv(geradeLinie);
        this.wert = 1;
        this.mainView.setWert(wert);
        System.out.println("Gerade Linie wurde ausgewählt.");
    }
    @FXML
    void toggleSchreage(ActionEvent event){
        checkAktiv(schreageLinie);
        this.wert= 2;
        this.mainView.setWert(wert);
        System.out.println("Schreage Linie wurde ausgewählt");
    }
    @FXML
    void toggleFalltuer(ActionEvent event){
        checkAktiv(falltuer);
        this.wert= 3;
        this.mainView.setWert(wert);
        System.out.println("Falltuer wurde ausgewählt.");
    }
    @FXML
    void toggleBall(ActionEvent event){
        checkAktiv(ball);
        this.wert =4;
        this.mainView.setWert(wert);
        System.out.println("Ball wurde ausgewählt.");
    }
    @FXML
    void toggleRect(ActionEvent event){
        checkAktiv(rechteck);
        this.wert =5;
        this.mainView.setWert(wert);
        System.out.println("Recht-eck wurde ausgewählt.");
    }
    @FXML
    private void checkAktiv(ToggleButton button){
        System.out.println("Prüfe andere Buttons und gebe ID aus vom Button: "+ button.getId());
        for(int i=0;i<toggleButtons.size();i++){
            if(!toggleButtons.get(i).getId().equals(button.getId())){
                System.out.println("Wird deaktiviert!");
                toggleButtons.get(i).setSelected(false);
            }
            else{
                System.out.println("Ist ausgwählt");
            }
        }
    }


}
