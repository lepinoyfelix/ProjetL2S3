package Main;
import com.sun.glass.ui.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainConnexion extends Application {

    @Override
    public void start(Stage primaryStageConnexion) throws Exception{
        Image image = new Image("/Images/UnivTours-logoAppli.png");
        primaryStageConnexion.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml"));
        primaryStageConnexion.setTitle("Connexion");
        primaryStageConnexion.setResizable(false);
        primaryStageConnexion.centerOnScreen();
        primaryStageConnexion.setScene(new Scene(root, 520, 400));
        primaryStageConnexion.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
