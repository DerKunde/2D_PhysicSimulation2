package main;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Overlay extends VBox {

    private Button startStop;


    public Overlay() {
        this.startStop = new Button("Start/Stop");

        this.getChildren().addAll(startStop);
        this.startStop.setOnAction(ActionEvent -> {
            System.exit(0);
        });
    }

    public Button getStartStop() {
        return this.startStop;
    }
}
