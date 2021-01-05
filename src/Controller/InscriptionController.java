package Controller;

import AutreClasse.ConexionBDD;
import AutreClasse.EnvoiMailUtil;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;


public class InscriptionController {
    /*
    A modifier en fonction des BDD
     */
    String colonemailBDD = "Mail";
    String colonemdpBDD = "Mdp";
    String tableUser = "user";
    String coloneidrole = "idRole";

    /*
    Creation des bouttons
     */
    @FXML
    private Button ButtonInscription;
    @FXML
    private Button ButtonAnnuler;
    @FXML
    private Button ButtonInscriptionFinal;

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
    @FXML
    private TextField TextFieldCodeConfMail;

    /*
    Label qui on un msg variable
     */
    @FXML
    private Label LabelErreur;
    @FXML
    private Label LabelCodeConfMail;

    /*
Connexion classe BDD
 */
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /*
    Variable pour inscription
     */
    public InscriptionController() {
        connection = ConexionBDD.connectdb();

    }

    /*
    fonction verification mail (a modifier pour mettre univ tours)
     */

    public static boolean mailisValid(String mail) {
        String emailRegex = "^[A-Za-z0-9.-]{1,}" + "@" + "[A-Za-z0-9.-]*" + "univ-tours.fr"; //verfication que l'email est universitaire et * autorise caractére null
        //String emailRegex = "^[A-Za-z0-9.-]{1,}"+"@"+"[A-Za-z0-9.-]*"+"gmail.com";

        Pattern pat = Pattern.compile(emailRegex);
        if (mail == null)
            return false;
        return pat.matcher(mail).matches();

    }

    public static boolean mdpisValid(String mdp) {
        String mdpRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

        Pattern pat = Pattern.compile(mdpRegex);
        if (mdp == null)
            return false;
        return pat.matcher(mdp).matches();
    }

    public void ButtonInscriptionOnAction(ActionEvent event) throws IOException {
        String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
        String confMail = TextFieldConfMail.getText().toString(); //Récupération  de  confirmation de l'email
        String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp
        String confMdp = PasswordFieldConfMdp.getText().toString(); //Récupération  de  confirmation mdp
        if (mail.length() != 0) { //verification si TextFIeldMail n'es pas vide
            if (confMail.length() != 0) { //verification si TextFieldConfMail
                if (mail.equals(confMail)) { //verification que les 2 email sont identique
                    if (mailisValid(mail)) {
                        if (mdp.length() != 0) {
                            if (confMdp.length() != 0) {
                                if (mdp.equals(confMdp)) {
                                    if (mdpisValid(mdp)) {
                                        String sqlVerifictionMail = "SELECT * FROM " + tableUser + " WHERE " + colonemailBDD + " = ?"; //requet sql pour savoir si l'email  rentré existe déja

                                        try {
                                            preparedStatement = connection.prepareStatement(sqlVerifictionMail);
                                            preparedStatement.setString(1, mail);
                                            resultSet = preparedStatement.executeQuery();
                                            if (!resultSet.next()) {
                                                EnvoiMailUtil.EnvoiMail(mail);
                                                LabelCodeConfMail.setVisible(true);
                                                TextFieldCodeConfMail.setVisible(true);
                                                LabelErreur.setText("Veuillez regarder vos mail");
                                                String codeEnvoyerParMail = String.valueOf(EnvoiMailUtil.getNombreAleatoireVerificationMail());
                                                ButtonInscription.setVisible(false);
                                                ButtonInscriptionFinal.setVisible(true);

                                            } else {
                                                LabelErreur.setText("Un compte avec cette adresse mail existe déjà");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    } else {
                                        LabelErreur.setText("Veuillez verfier que votre mdp  contien une maj une min et un nombre et qu'il fait plus de 8 caractére");
                                    }
                                } else {
                                    LabelErreur.setText("Veuillez saisir 2 mdp  identique");
                                }
                            } else {
                                LabelErreur.setText("Veuilez confirmer votre mdp");
                            }
                        } else {
                            LabelErreur.setText("Veuilez saisir un mdp");
                        }
                    } else
                        LabelErreur.setText("Veuillez saisir une adresse mail de l'université de tours");

                } else {
                    LabelErreur.setText("Les 2 mail ne sont pas identique");
                }
            } else {
                LabelErreur.setText("veuillez confirmaer votre email");
            }
        } else {
            LabelErreur.setText("Veuillez rentrez un mail");
        }

    }

    public void ButtonInscriptionFinalOnAction(ActionEvent event) {
        String mail = TextFieldMail.getText().toString(); //Récupération  de  l'email
        String mdp = PasswordFieldMdp.getText().toString();//Récupération du Mdp
        String codeConf = TextFieldCodeConfMail.getText().toString();
        String codeEnvoyerParMail = String.valueOf(EnvoiMailUtil.getNombreAleatoireVerificationMail());
        String adresseEtudiant = "etu.univ-tours.fr";
        int idRoleProf = 1;
        int idRoleEleve = 2;


        if (codeEnvoyerParMail.equals(codeConf)) {
            LabelErreur.setText("nous procédons a l'engregistrement de votre profil");

            int index = mail.indexOf(adresseEtudiant);
            try {
                if (index == -1) {
                    String ajoutUtilisateur = "INSERT INTO " + tableUser + " ( " + colonemailBDD + " , " + colonemdpBDD + " , " + coloneidrole + " ) VALUES  (?, ?, ?)"; //inscription prof/personel
                    PreparedStatement preparedStatement = connection.prepareStatement(ajoutUtilisateur);
                    preparedStatement.setString(1, mail);
                    preparedStatement.setString(2, mdp);
                    preparedStatement.setInt(3, idRoleProf);
                    preparedStatement.executeUpdate();
                     /*
                    ouvre  connexion si l'inscription est lavidé
                    */
                    Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
                    stage.setScene(scene);
                    stage.show();
                    stage.setTitle("Connexion");
                } else {
                    String ajoutUtilisateur = "INSERT INTO " + tableUser + " ( " + colonemailBDD + " , " + colonemdpBDD + " , " + coloneidrole + " ) VALUES  (?, ?, ?)"; // inscription Eleve
                    PreparedStatement preparedStatement = connection.prepareStatement(ajoutUtilisateur);
                    preparedStatement.setString(1, mail);
                    preparedStatement.setString(2, mdp);
                    preparedStatement.setInt(3, idRoleEleve);
                    preparedStatement.executeUpdate();
                    /*
                    ouvre  connexion si l'inscription est lavidé
                    */
                    JOptionPane.showMessageDialog(null, "Inscription réussite");
                    Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
                    stage.setScene(scene);
                    stage.show();
                    stage.setTitle("Connexion");
                }

            } catch (Exception e) {
                new Exception(e);
            }

        } else {
            LabelErreur.setText("Code invalide veuillez le resaisir"); //a  améliorer pour la sécurité et eviter les prog qui teste tout les chifre
        }

    }

    public void ButtonAnnulerOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Connexion");
    }
}
