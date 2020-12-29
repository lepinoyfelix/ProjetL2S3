package Controller;

import AutreClasse.ConexionBDD;
import AutreClasse.Entreprise;
import AutreClasse.GUIUtils;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class TableauEntrepriseController implements Initializable {

    @FXML
    private TableView<Entreprise> TableauEntreprise;
    @FXML
    private TableColumn<Entreprise, String> ColumnNom;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnNumerDeSiren;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnCodePostal;
    @FXML
    private TableColumn<Entreprise, String> ColumnVille;
    @FXML
    private TableColumn<Entreprise, String> ColumnAdresse;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnFax;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnTel;
    @FXML
    private TableColumn<Entreprise, String> ColumnSiteWeb;
    @FXML
    private TableColumn<Entreprise, String> ColumnAutreInfo;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnTaxeAprentissageDatePayment;
    @FXML
    private TableColumn<Entreprise, Integer> ColumnTaxeAprentissageMontant;
    @FXML
    private TableColumn<Entreprise, String> ColumnCompetence;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button ButtonModifier;
    @FXML
    private TextField TextFieldNomEntreprise;
    @FXML
    private TextField TextFieldNumSiren;
    @FXML
    private TextField TextFieldCodePostal;
    @FXML
    private TextField TextFieldAdresse;
    @FXML
    private TextField TextFieldVille;
    @FXML
    private TextField TextFieldFax;
    @FXML
    private TextField TextFieldTel;
    @FXML
    private TextField TextFieldSiteWeb;
    @FXML
    private TextField TextFieldAutreInfo;
    @FXML
    private CheckBox CheckBoxTaxeApprentissage;
    @FXML
    private TextField TextFieldDateTaxe;
    @FXML
    private TextField TextFieldMontantTaxe;

    /*
 Connexion classe BDD
  */
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public TableauEntrepriseController() {
        connection = ConexionBDD.connectdb();
    }

    public static boolean TextMajIsValide(String TextMaj) {
        String mdpRegex = "[A-Z0-9 ]*";

        Pattern pat = Pattern.compile(mdpRegex);
        if (TextMaj == null)
            return false;
        return pat.matcher(TextMaj).matches();
    }

    public static boolean NumSirenIsValide(String NumSiren) {
        String mdpRegex = "[0-9]{9}";

        Pattern pat = Pattern.compile(mdpRegex);
        if (NumSiren == null)
            return false;
        return pat.matcher(NumSiren).matches();
    }

    public static boolean CodePostaleIsValide(String CodePostale) {
        String mdpRegex = "[0-9]{5}";

        Pattern pat = Pattern.compile(mdpRegex);
        if (CodePostale == null)
            return false;
        return pat.matcher(CodePostale).matches();
    }

    public static boolean AdresseIsValide(String Adresse) {
        String mdpRegex = "[0-9]{0,4}[ ]{1}[0-9A-Z ]*";

        Pattern pat = Pattern.compile(mdpRegex);
        if (Adresse == null)
            return false;
        return pat.matcher(Adresse).matches();
    }

    public static boolean FaxIsValide(String Fax) {
        String mdpRegex = "([0-9]{10}||[0-9]{0})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Fax == null)
            return false;
        return pat.matcher(Fax).matches();
    }
    public static boolean TelValide(String Tel) {
        String mdpRegex = "([0-9]{10})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Tel == null)
            return false;
        return pat.matcher(Tel).matches();
    }


    ObservableList<Entreprise> listM;
    int index = -1;

    public static ObservableList<Entreprise> getDataEntreprise() {

        Connection connection = ConexionBDD.connectdb();
        ObservableList<Entreprise> list = FXCollections.observableArrayList();

        try {
            String EntrepriseSQL = "SELECT * FROM entreprise";
            PreparedStatement ps = connection.prepareStatement(EntrepriseSQL);
            ResultSet rs = ps.executeQuery();
            String CompetenceEntrepriseSQL = "SELECT L.Technologie, E.RAISON_SOCIALE FROM  lesdifferenttechnologie L INNER JOIN entreprisetechnologie ET ON L.IDTHECNOLOGIE=ET.IDTHECNOLOGIE INNER JOIN entreprise E ON ET.IDENTREPRISE = E.IDENTREPRISE";
            PreparedStatement psCompetenceEntreprise = connection.prepareStatement(CompetenceEntrepriseSQL);
            ResultSet rsCompetenceEntreprise = psCompetenceEntreprise.executeQuery();
            while (rs.next() && rsCompetenceEntreprise.next()) {
                list.add(new Entreprise(rs.getString("Raison_Sociale"),
                        Integer.parseInt(rs.getString("Num_SIREN")),
                        Integer.parseInt(rs.getString("Code_Postal")),
                        rs.getString("Adresse"),
                        rs.getString("Ville"),
                        Integer.parseInt(rs.getString("Fax")),
                        Integer.parseInt(rs.getString("Tel")),
                        rs.getString("Site_Web"),
                        rs.getString("Autre_Info"),
                        Integer.parseInt(rs.getString("Taxe_Apprentissage")),
                        Integer.parseInt(rs.getString("DateVErsementTaxeAprentissage")),
                        rsCompetenceEntreprise.getString("Technologie")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Raison_Sociale"));
        ColumnNumerDeSiren.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Num_SIREN"));
        ColumnCodePostal.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Code_Postal"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Ville"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Adresse"));
        ColumnFax.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Fax"));
        ColumnTel.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Tel"));
        ColumnSiteWeb.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Site_Web"));
        ColumnAutreInfo.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Autre_Info"));
        ColumnTaxeAprentissageDatePayment.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Taxe_Apprentissage"));
        ColumnTaxeAprentissageMontant.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("DateVErsementTaxeAprentissage"));
        ColumnCompetence.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Technologie"));

        listM = getDataEntreprise();
        TableauEntreprise.setItems(listM);
    }

    public void checkEvent(javafx.event.ActionEvent actionEvent) {
        if (CheckBoxTaxeApprentissage.isSelected()) {
            TextFieldDateTaxe.setVisible(true);
            TextFieldMontantTaxe.setVisible(true);
        } else {
            TextFieldDateTaxe.setVisible(false);
            TextFieldMontantTaxe.setVisible(false);
        }
    }

    public void AjouterEntreprise() {
        if (CheckBoxTaxeApprentissage.isSelected()) {
            Connection connection = ConexionBDD.connectdb();
            String AjouterEntrepriseSql = "INSERT INTO Entreprise (Raison_Sociale, Num_SIREN,Code_Postal,Ville,Adresse,Fax,Tel,Site_Web,Autre_Info,Taxe_Apprentissage,DateVErsementTaxeAprentissage) value(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(AjouterEntrepriseSql);
                preparedStatement.setString(1, TextFieldNomEntreprise.getText());
                preparedStatement.setString(2, TextFieldNumSiren.getText());
                preparedStatement.setString(3, TextFieldCodePostal.getText());
                preparedStatement.setString(4, TextFieldAdresse.getText());
                preparedStatement.setString(5, TextFieldVille.getText());
                preparedStatement.setString(6, TextFieldFax.getText());
                preparedStatement.setString(7, TextFieldTel.getText());
                preparedStatement.setString(8, TextFieldSiteWeb.getText());
                preparedStatement.setString(9, TextFieldAutreInfo.getText());
                preparedStatement.setString(10, TextFieldDateTaxe.getText());
                preparedStatement.setString(11, TextFieldMontantTaxe.getText());
                preparedStatement.execute();

                JOptionPane.showMessageDialog(null, "Entreprise ajouter avec suces");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Connection connection = ConexionBDD.connectdb();
            String AjouterEntrepriseSql = "INSERT INTO Entreprise (Raison_Sociale, Num_SIREN,Code_Postal,Ville,Adresse,Fax,Tel,Site_Web,Autre_Info) value(?,?,?,?,?,?,?,?,?)";
            String NomEntreprise = TextFieldNomEntreprise.getText();
            String NumSiren = TextFieldNumSiren.getText();
            String CodePostal = TextFieldCodePostal.getText();
            String Adresse = TextFieldAdresse.getText();
            String Ville = TextFieldVille.getText();
            String Fax = TextFieldFax.getText();
            String Tel = TextFieldTel.getText();
            if (NomEntreprise.length() > 0) {
                if (TextMajIsValide(NomEntreprise)) {
                    if (NumSiren.length() > 0) {
                        if (NumSirenIsValide(NumSiren)) {
                            if (CodePostal.length() > 0) {
                                if (CodePostaleIsValide(CodePostal)) {
                                    if (Adresse.length() > 0) {
                                        if (AdresseIsValide(Adresse)) {
                                            if (Ville.length() > 0) {
                                                if (TextMajIsValide(Ville)) {
                                                    if (FaxIsValide(Fax)) {
                                                        if(Tel.length() > 0){

                                                        }else{

                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Le Fax saise est invalide");
                                                    }
                                                }else {
                                                        JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le numero de siren ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Numero de SIREN obligatoire ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez rentrer Le nom de l'entreprise en MAJ");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer Le nom de l'entreprise ! ");
                }
                try {
                    preparedStatement = connection.prepareStatement(AjouterEntrepriseSql);
                    preparedStatement.setString(1, TextFieldNomEntreprise.getText());
                    preparedStatement.setString(2, TextFieldNumSiren.getText());
                    preparedStatement.setString(3, TextFieldCodePostal.getText());
                    preparedStatement.setString(4, TextFieldAdresse.getText());
                    preparedStatement.setString(5, TextFieldVille.getText());
                    preparedStatement.setString(6, TextFieldFax.getText());
                    preparedStatement.setString(7, TextFieldTel.getText());
                    preparedStatement.setString(8, TextFieldSiteWeb.getText());
                    preparedStatement.setString(9, TextFieldAutreInfo.getText());

                    preparedStatement.execute();

                    JOptionPane.showMessageDialog(null, "Entreprise ajouter avec suces");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
}
