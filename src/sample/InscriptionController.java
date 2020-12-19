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
    Label qui  on un msg variable
     */
    @FXML
    private Label LabelErreur;

    public static boolean isValid(String mail) {
        String emailRegex = "^[_A-Za-z0-9._%+-]+@[_A-Za-z0-9._%+-]+\\.[A-Za-z]{2,6}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (mail == null)
            return false;
        return pat.matcher(mail).matches();

    }

    public void ButtonInscriptionOnAction (ActionEvent event) throws IOException {
        String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
        String confMail = TextFieldConfMail.getText().toString(); //Récupération  de  confirmation de l'email
        String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp
        String confMdp = PasswordFieldConfMdp.getText().toString(); //Récupération  de  confirmation mdp

        if(mail.length()!=0){ //verification si TextFIeldMail n'es pas vide
            if(confMail.length() !=0){ //verification si TextFieldConfMail
                if(mail.equals(confMail)){ //verification que les 2 email sont identique
                    if (isValid(mail))
                        System.out.print("Yes");
                    else
                        System.out.print("No");

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