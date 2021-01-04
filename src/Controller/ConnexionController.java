package Controller;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import AutreClasse.ConexionBDD;

import java.io.IOException;
import java.sql.*;

public class ConnexionController {
    /*
    A mdoifier suivant la bdd
     */
    String tableUser = "user";
    String tableRole = "role";
    String colonemailTableUtilisateur = "Mail";
    String coloneroleIDTableUtilisateur = "idRole";
    String colonemdpBDDTableUtilisateur = "Mdp";
    String coloneRoleTableRole = "role";
    String roleEtudiantTableRole= "Etudiant";
    String rolePresonelTableRole= "Personel";
    int idroleEtudiantTableRole = 2;
    int idrolePersonelTableRole =1;
    String coloneidRoleTableRole = "idRole";

    /*
    Création des boutons/label/text field
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

    /*
    Connexion classe BDD
     */
    Connection connection = null;
    Statement statement =null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
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

            String sql = "SELECT * FROM "+tableUser+" WHERE "+colonemailTableUtilisateur+" = ? and "+ colonemdpBDDTableUtilisateur +" = ?";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, mail);
                preparedStatement.setString(2, mdp);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    LabelErreurConnexion.setText("Mail ou Mdp invalide,  veuillez réessayez");
                } else {

                    LabelErreurConnexion.setText("Connexion ok");

                    String roleMailenCourDeConnexionsql = "SELECT " + coloneroleIDTableUtilisateur + " FROM " + tableUser + " WHERE " + colonemailTableUtilisateur + " like \"" + mail + "\"";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(roleMailenCourDeConnexionsql);
                    String roleMailConnexion = null;
                    int idRoleConnexion = 0;
                    while (resultSet.next()) {

                        idRoleConnexion = resultSet.getInt(coloneroleIDTableUtilisateur);

                        roleMailConnexion = "SELECT " + coloneRoleTableRole + " FROM " + tableRole + " WHERE " + coloneidRoleTableRole + " = " + idRoleConnexion;
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(roleMailConnexion);

                        while (resultSet.next()) {
                            roleMailConnexion = resultSet.getString(coloneRoleTableRole);
                        }

                    }
                    if (idRoleConnexion == idrolePersonelTableRole && roleMailConnexion.equals(rolePresonelTableRole)) {
                        System.out.println("Personel");
                        Stage stage = (Stage) ButtonConnexion.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml")));
                        stage.setScene(scene);
                        stage.show();
                        stage.setTitle("Accueil Personel");

                    } else if (idRoleConnexion == idroleEtudiantTableRole && roleMailConnexion.equals(roleEtudiantTableRole)) {
                        LabelErreurConnexion.setText("Etudiant");
                        Stage stage = (Stage) ButtonConnexion.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AccueilEtudiant.fxml")));
                        stage.setScene(scene);
                        stage.show();
                        stage.setTitle("Accueil Etudiant");

                    } else {
                        LabelErreurConnexion.setText("Probleme role pas reconeu");
                    }
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
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Inscription.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Inscription");

    }

    /*
    Création  action bouton Fermer
     */
    public void ButtonFermerOnAction (ActionEvent event){
        Stage stage = (Stage) ButtonInscription.getScene().getWindow();
        stage.close();
    }

}

