package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        primaryStage.setTitle("Physic Simulation 2D");
        primaryStage.setScene(new Scene(mainView, 800, 800));
        primaryStage.show();

        mainView.draw();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
