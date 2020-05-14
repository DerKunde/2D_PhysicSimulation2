package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

/// osindfoisdiofndfgdfg
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scenebuilder.fxml"));
            Scene scene = new Scene(root);



            
            primaryStage.setTitle("Tiny Machine 2D");
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

    /*public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.Scenebuilder.fxml"));
            GridPane personOverview = (GridPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setGridLinesVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
*/

