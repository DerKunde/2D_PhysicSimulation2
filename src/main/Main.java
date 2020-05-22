package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getClass().getResource("Scenebuilder.fxml"));
        //MainView mainView = new MainView();
        primaryStage.setTitle("Physic Simulation 2D");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        //mainView.draw();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
