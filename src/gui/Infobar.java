package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.MainView;

public class Infobar extends VBox {

    private MainView mainView;

    public Infobar(MainView mainView) {

        this.mainView = mainView;

        Label ballSpeed = new Label("Speed: ");
        Label ballRadius = new Label("Radius: ");


    }
}
