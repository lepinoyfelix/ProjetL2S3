package Main;
import com.sun.glass.ui.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTest extends Application {

    @Override
    public void start(Stage primaryStageConnexion) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml"));
        primaryStageConnexion.setTitle("Connexion");
        primaryStageConnexion.setScene(new Scene(root, 1600, 900));
        primaryStageConnexion.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
