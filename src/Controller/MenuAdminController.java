package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuAdminController {

    @FXML
    Button btnMooveToEntreprise, btnMooveToEvenements, btnMooveToCours, btnMooveToStagiaires, btnMooveToAEtudiants, btnDeconnexion;

    public void MooveToEntreprise() throws Exception
    {
        Stage stage = (Stage) btnMooveToEntreprise.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml")));
        stage.show();
        stage.setTitle("Admin_Entreprise");
        stage.setScene(scene);

    }

    public void MooveToEvenements() throws Exception
    {
        Stage stage = (Stage) btnMooveToEvenements.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEvenements.fxml")));
        stage.show();
        stage.setTitle("Admin_Event");
        stage.setScene(scene);
    }
    public void MooveToCours() throws Exception
    {
        Stage stage = (Stage) btnMooveToCours.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminCours.fxml")));
        stage.show();
        stage.setTitle("Admin_Cours");
        stage.setScene(scene);
    }

    public void MooveToStagiaires() throws Exception
    {
        Stage stage = (Stage) btnMooveToStagiaires.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminStagiaires.fxml")));
        stage.show();
        stage.setTitle("Admin_Stagiaires");
        stage.setScene(scene);
    }

    public void MooveToAEtudiants() throws Exception
    {

        Stage stage = (Stage) btnMooveToAEtudiants.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminAEtudiant.fxml")));
        stage.show();
        stage.setTitle("Admin_Anciens_Etudiants");
        stage.setScene(scene);
    }

    public void Deconnexion() throws Exception
    {

        Stage stage = (Stage) btnDeconnexion.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.show();
        stage.setTitle("Connexion");
        stage.setScene(scene);
    }

}
