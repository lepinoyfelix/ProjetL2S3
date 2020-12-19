package sample;
import javafx.fxml.FXMLLoader;
import sample.ConnexionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import DataBase.ConexionBDD;

import java.io.IOException;
import java.sql.*;

public class ConnexionController {

    /*
    Création des boutons
     */
    @FXML
    private Button ButtonConnexion;
    @FXML
    private Button ButtonInscription;
    @FXML
    private Button ButtonFermer;
    @FXML
    private TextField PasswordFieldMdp;
    @FXML
    private TextField TextFieldMail;
    @FXML
    private Label LabelErreurConnexion;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /*
    Connexion classe BDD
     */

    public ConnexionController() {
        connection = ConexionBDD.connectdb();
    }

    /*
    Création  action bouton Connexion
     */
    public void ButtonConnexionOnAction(ActionEvent event) {
        /*
        Verification email pas vide
         */
        if (TextFieldMail.getText().isEmpty()) {
            LabelErreurConnexion.setText("Rentrez mail");
            return;
        }
         /*
        Verification mdp pas vide
         */
        if (PasswordFieldMdp.getText().isEmpty()) {
            LabelErreurConnexion.setText("Rentrez mdp");
            return;
            /*
            si mdp  et mail pas vide
             */
        } else {
            String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
            String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp

            String sql = "SELECT * FROM Personne WHERE Mail = ? and Mdp = ?"; //Requet sql

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, mail);
                preparedStatement.setString(2, mdp);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    LabelErreurConnexion.setText("Mail ou Mdp invalide,  veuillez réessayez");
                } else {
                    LabelErreurConnexion.setText("Connexion ok");
                    //Fermeture de la fenttre
                    Stage stage = (Stage) ButtonConnexion.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /*
    Création  action bouton Inscription
     */
    public void ButtonInscriptionOnAction (ActionEvent event) throws IOException {
        // appel fenetre d'inscription
        Stage stage = (Stage) ButtonInscription.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    /*
    Création  action bouton Fermer
     */
    public void ButtonFermerOnAction (ActionEvent event){
        Stage stage = (Stage) ButtonInscription.getScene().getWindow();
        stage.close();
    }

}

