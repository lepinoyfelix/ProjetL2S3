package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class AccueilEtudiantController {
    @FXML
    private Button BtnRetourConnexion;

    public void retourConnexion(ActionEvent event) throws IOException {
        Stage stage = (Stage) BtnRetourConnexion.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Connexion");
    }

}
