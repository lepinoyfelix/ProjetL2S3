package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInscription extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        primaryStage.setTitle("Inscription");
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }

}
