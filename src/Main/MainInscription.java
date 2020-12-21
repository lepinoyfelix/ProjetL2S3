package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInscription extends Application {

    @Override
    public void start(Stage primaryStageInscription) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Inscription.fxml"));
        primaryStageInscription.setTitle("Inscription");
        primaryStageInscription.setScene(new Scene(root, 520, 400));
        primaryStageInscription.show();
    }

}
