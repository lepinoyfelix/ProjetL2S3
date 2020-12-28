package Controller;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminEntrepriseController {

    @FXML
    private Label LabelTest;
    @FXML
    private javafx.scene.control.Button Button;
    @FXML
    Button btnMooveToEntreprise, btnMooveToCours, btnMooveToStagiaires, btnMooveToAEtudiants, btnMooveToEvenements;

    public void MooveToEntreprise() throws Exception
    {
        Stage stage = (Stage) btnMooveToEntreprise.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml")));
        stage.show();
        stage.setTitle("Admin_Entreprise");
        stage.setScene(scene);

    }
    /*public void btnMooveToCours() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("AdminEntreprise.fxml"));
        Stage window = (Stage) btnMooveToEntreprise.getScene().getWindow();
        window.setScene(new Scene(root, 1920, 1080));
    }*/
    public void MooveToEvenements() throws Exception
    {
        Stage stage = (Stage) btnMooveToEvenements.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEvenements.fxml")));
        stage.show();
        stage.setTitle("Admin_Event");
        stage.setScene(scene);
    }

}
