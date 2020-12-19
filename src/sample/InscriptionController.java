package  sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;



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
    Label qui on un msg variable
     */
    @FXML
    private Label LabelErreur;

    /*
    fonction verification mail (a modifier pour mettre univ tours)
     */

    public static boolean mailisValid(String mail) {
        String emailRegex = "^[A-Za-z0-9.-]{1,}"+"@"+"[A-Za-z0-9.-]*"+"univ-tours.fr"; //verfication que l'email est universitaire et * autorise caractére null

        Pattern pat = Pattern.compile(emailRegex);
        if (mail == null)
            return false;
        return pat.matcher(mail).matches();

    }

    public static boolean mdpisValid(String mdp){
        String mdpRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

        Pattern pat = Pattern.compile(mdpRegex);
        if (mdp == null)
            return false;
        return pat.matcher(mdp).matches();
    }

    public void ButtonInscriptionOnAction (ActionEvent event) throws IOException {
        String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
        String confMail = TextFieldConfMail.getText().toString(); //Récupération  de  confirmation de l'email
        String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp
        String confMdp = PasswordFieldConfMdp.getText().toString(); //Récupération  de  confirmation mdp

        if(mail.length()!=0){ //verification si TextFIeldMail n'es pas vide
            if(confMail.length() !=0){ //verification si TextFieldConfMail
                if(mail.equals(confMail)){ //verification que les 2 email sont identique
                    if (mailisValid(mail)){
                        if(mdp.length()!=0){
                            if(confMdp.length() != 0){
                                    if(mdp.equals(confMdp)){
                                        if(mdpisValid(mdp)){
                                            LabelErreur.setText("ok"); //A finir
                                        }else{
                                            LabelErreur.setText("Veuillez verfier que votre mdp  contien une maj une min et un nombre e t qu'il fait plus de 8 caractére");
                                        }
                                    }else{
                                        LabelErreur.setText("Veuillez saisir 2 mdp  identique");
                                    }
                            }else{
                                LabelErreur.setText("Veuilez confirmer votre mdp");
                            }
                        }else{
                         LabelErreur.setText("Veuilez saisir un mdp");
                        }
                    }
                    else
                        LabelErreur.setText("Veuillez saisir une adresse mail de l'université de tours");

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