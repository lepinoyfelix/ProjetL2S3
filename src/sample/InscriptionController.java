package  sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
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


public class InscriptionController{

    /*
    Creation des bouttons
     */
    @FXML
    private Button ButtonInscription;
    @FXML
    private Button ButtonAnnuler;

    /*
    Creation des TextFiled
     */
    @FXML
    private TextField TextFieldMail;
    @FXML
    private TextField TextFieldConfMail;
    @FXML
    private PasswordField PasswordFieldMdp;
    @FXML
    private PasswordField PasswordFieldConfMdp;

    /*
    Label qui  on un msg variable
     */
    @FXML
    private Label LabelErreur;

    public void ButtonInscriptionOnAction (ActionEvent event) throws IOException {
        String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
        String confMail = TextFieldConfMail.getText().toString(); //Récupération  de  confirmation de l'email
        String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp
        String confMdp = PasswordFieldConfMdp.getText().toString(); //Récupération  de  confirmation mdp

        if(mail.length()!=0){
            if(confMail.length() !=0){
                if(mail.equals(confMail)){ //verification que les 2 email sont identique


                }else{
                    LabelErreur.setText("Les 2 mail ne sont pas identique");
                }
            }else{
                LabelErreur.setText("veuillez confirmaer votre email");
            }
        }else{
            LabelErreur.setText("Veuillez rentrez un mail");
        }
    }


    public void ButtonAnnulerOnAction (ActionEvent event) throws IOException {
        Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Connexion.fxml")));
        stage.setScene(scene);
        stage.show();

    }

}