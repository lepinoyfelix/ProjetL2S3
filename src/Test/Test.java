package Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class Test extends Application {

    @Override
    public void start(Stage primaryStageTableauEntreprise) throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml"));
        final Scene scene = new Scene(root, 600, 400);
        primaryStageTableauEntreprise.setTitle("TableauEntreprise");
        primaryStageTableauEntreprise.setScene(scene);
        primaryStageTableauEntreprise.show();
        primaryStageTableauEntreprise.setMaximized(true);
        */
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml"));
        primaryStageTableauEntreprise.setTitle("Connexion");
        primaryStageTableauEntreprise.setScene(new Scene(root, 1600, 900));
        primaryStageTableauEntreprise.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
