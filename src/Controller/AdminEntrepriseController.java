package Controller;

import AutreClasse.Competence;
import AutreClasse.ConexionBDD;
import AutreClasse.Entreprise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AdminEntrepriseController implements Initializable {

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
    private TableColumn<Entreprise, String> ColumnFax;
    @FXML
    private TableColumn<Entreprise, String> ColumnTel;
    @FXML
    private TableColumn<Entreprise, String> ColumnSiteWeb;
    @FXML
    private TableColumn<Entreprise, String> ColumnAutreInfo;
    @FXML
    private TableColumn<Entreprise, String> ColumnTaxeAprentissageDatePayment;
    @FXML
    private TableColumn<Entreprise, String> ColumnTaxeAprentissageMontant;
    @FXML
    private TableColumn<Competence, String> ColumnCompetence;
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
    @FXML
    private TableColumn<Competence, String> ColumnCompétenceTComp;
    @FXML
    private TableView<Competence> TableauCompetence;
    @FXML
    private TextField TextFieldCompetence;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane1;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane2;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPaneMenu;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPaneEntreprise;
    @FXML
    private Button ButtonMenu;
    @FXML
    private TextField TextFieldRecherche;
    @FXML
    private ChoiceBox<String> ChoiceBoxRecherche;
    @FXML
    private Label labelCatégorierecherche;
    @FXML
    private Button  BouttonPageEvenement;
    @FXML
    private Button  btnMenuAdmin;

    /*
 Connexion classe BDD
  */
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<Competence> listCompetence;


    public AdminEntrepriseController() {
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
        String mdpRegex = "([0-9]{0,4}[ ]{1}[0-9A-Z ]*)";

        Pattern pat = Pattern.compile(mdpRegex);
        if (Adresse == null)
            return false;
        return pat.matcher(Adresse).matches();
    }

    public static boolean FaxIsValide(String Fax) {
        String mdpRegex = "([0][0-9]*)";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Fax == null)
            return false;
        return pat.matcher(Fax).matches();
    }

    public static boolean TelIsValide(String Tel) {
        String mdpRegex = "([0][0-9]{9})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Tel == null)
            return false;
        return pat.matcher(Tel).matches();
    }

    public static boolean DateIsValide(String Date) {
        String mdpRegex = "([0-9]{2}[/][0-9]{2}[/][0-9]{4})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Date == null)
            return false;
        return pat.matcher(Date).matches();
    }

    public static boolean MontantTaxeIsValide(String MontantTaxe) {
        String mdpRegex = "([0-9]*[.][0-9]*)";
        Pattern pat = Pattern.compile(mdpRegex);
        if (MontantTaxe == null)
            return false;
        return pat.matcher(MontantTaxe).matches();
    }

    public static ObservableList<Competence> getDataCompetence() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Competence> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT Competence FROM Competence";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Competence(rs.getString("Competence")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
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
            String CompetenceEntrepriseSQL = "SELECT C.Competence, E.RAISON_SOCIALE FROM  Competence C INNER JOIN entreprise E ON C.idCompetence=E.idCompetence";
            PreparedStatement psCompetenceEntreprise = connection.prepareStatement(CompetenceEntrepriseSQL);
            ResultSet rsCompetenceEntreprise = psCompetenceEntreprise.executeQuery();
            while (rs.next() && rsCompetenceEntreprise.next()) {
                list.add(new Entreprise(
                        rs.getString("Raison_Sociale"),
                        Integer.parseInt(rs.getString("Num_SIREN")),
                        Integer.parseInt(rs.getString("Code_Postal")),
                        rs.getString("Adresse"),
                        rs.getString("Ville"),
                        rs.getString("Fax"),
                        rs.getString("Tel"),
                        rs.getString("Site_Web"),
                        rs.getString("Autre_Info"),
                        rs.getString("Taxe_Apprentissage"),
                        rs.getString("DateVersementTaxeAprentissage"),
                        rsCompetenceEntreprise.getString("Competence"),
                        Integer.parseInt(rs.getString("idEntreprise"))));

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
        ColumnFax.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Fax"));
        ColumnTel.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Tel"));
        ColumnSiteWeb.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Site_Web"));
        ColumnAutreInfo.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Autre_Info"));
        ColumnTaxeAprentissageMontant.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Taxe_Apprentissage"));
        ColumnTaxeAprentissageDatePayment.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("DateVersementTaxeAprentissage"));
        ColumnCompetence.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listM = getDataEntreprise();
        TableauEntreprise.setItems(listM);

        ColumnCompétenceTComp.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence = getDataCompetence();
        TableauCompetence.setItems(listCompetence);
        loadData();



    }

    public void UpdateTable() {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Raison_Sociale"));
        ColumnNumerDeSiren.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Num_SIREN"));
        ColumnCodePostal.setCellValueFactory(new PropertyValueFactory<Entreprise, Integer>("Code_Postal"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Ville"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Adresse"));
        ColumnFax.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Fax"));
        ColumnTel.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Tel"));
        ColumnSiteWeb.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Site_Web"));
        ColumnAutreInfo.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Autre_Info"));
        ColumnTaxeAprentissageMontant.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("Taxe_Apprentissage"));
        ColumnTaxeAprentissageDatePayment.setCellValueFactory(new PropertyValueFactory<Entreprise, String>("DateVersementTaxeAprentissage"));
        ColumnCompetence.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listM = getDataEntreprise();
        TableauEntreprise.setItems(listM);
        Entreprise_Search();
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
        String NomEntreprise = TextFieldNomEntreprise.getText();
        String NumSiren = TextFieldNumSiren.getText();
        String CodePostal = TextFieldCodePostal.getText();
        String Adresse = TextFieldAdresse.getText();
        String Ville = TextFieldVille.getText();
        String Fax = TextFieldFax.getText();
        String Tel = TextFieldTel.getText();
        String SiteWeb = TextFieldSiteWeb.getText();
        String AutreInfo = TextFieldAutreInfo.getText();
        String Competence = TextFieldCompetence.getText();
        String DateTaxe = TextFieldDateTaxe.getText();
        String MontantTaxe = TextFieldMontantTaxe.getText();


        if (SiteWeb.length() == 0) {
            SiteWeb = "NULL";
        }
        if (AutreInfo.length() == 0) {
            AutreInfo = "NULL";
        }
        if (Fax.length() == 0) {
            Fax = "0";
        }
        if (DateTaxe.length() == 0) {
            DateTaxe = "0";
        }
        if (MontantTaxe.length() == 0) {
            MontantTaxe = "0";
        }

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
                                                    if (Tel.length() > 0) {
                                                        if (TelIsValide(Tel)) {
                                                            if (Competence.length() > 0) {
                                                                String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                try {
                                                                    preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                    preparedStatement.setString(1, Competence);
                                                                    resultSet = preparedStatement.executeQuery();
                                                                    if (resultSet.next()) {
                                                                        String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                        try {
                                                                            preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                            preparedStatement.setString(1, Competence);
                                                                            resultSet = preparedStatement.executeQuery();
                                                                            if (resultSet.next()) {
                                                                                String idCompetence = resultSet.getString("idCompetence");
                                                                                if (CheckBoxTaxeApprentissage.isSelected()) {
                                                                                    if (DateTaxe.length() > 0) {
                                                                                        if (DateIsValide(DateTaxe)) {
                                                                                            if (MontantTaxe.length() > 0) {
                                                                                                if (MontantTaxeIsValide(MontantTaxe)) {
                                                                                                    String verfiEntrepriseExisteSQL = "SELECT * FROM entreprise WHERE Raison_Sociale = ? || Num_SIREN = ?";
                                                                                                    try {
                                                                                                        preparedStatement = connection.prepareStatement(verfiEntrepriseExisteSQL);
                                                                                                        preparedStatement.setString(1, NomEntreprise);
                                                                                                        preparedStatement.setString(2, NumSiren);
                                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                                        if (!resultSet.next()) {
                                                                                                            String AjouterEntrepriseSql = "INSERT INTO Entreprise (Raison_Sociale, Num_SIREN,Code_Postal,Adresse,Ville,Fax,Tel,Site_Web,Autre_Info,Taxe_Apprentissage,DateVersementTaxeAprentissage, idCompetence ) value(?,?,?,?,?,?,?,?,?,?,?,?)";

                                                                                                            try {

                                                                                                                preparedStatement = connection.prepareStatement(AjouterEntrepriseSql);
                                                                                                                preparedStatement.setString(1, NomEntreprise);
                                                                                                                preparedStatement.setString(2, NumSiren);
                                                                                                                preparedStatement.setString(3, CodePostal);
                                                                                                                preparedStatement.setString(4, Adresse);
                                                                                                                preparedStatement.setString(5, Ville);
                                                                                                                preparedStatement.setString(6, Fax);
                                                                                                                preparedStatement.setString(7, Tel);
                                                                                                                preparedStatement.setString(8, SiteWeb);
                                                                                                                preparedStatement.setString(9, AutreInfo);
                                                                                                                preparedStatement.setString(10, MontantTaxe);
                                                                                                                preparedStatement.setString(11, DateTaxe);
                                                                                                                preparedStatement.setString(12, idCompetence);
                                                                                                                preparedStatement.execute();


                                                                                                                JOptionPane.showMessageDialog(null, "Entreprise ajouter avec suces");
                                                                                                                UpdateTable();
                                                                                                                ClearTextField();


                                                                                                            } catch (Exception e) {
                                                                                                                e.printStackTrace();
                                                                                                            }
                                                                                                        } else {
                                                                                                            JOptionPane.showMessageDialog(null, "Une entreprise avec le meme nom et/ou la meme raison sociale existe déja");
                                                                                                        }
                                                                                                    } catch (Exception e) {
                                                                                                        e.printStackTrace();
                                                                                                    }
                                                                                                } else {
                                                                                                    JOptionPane.showMessageDialog(null, "Veuyer saisir un montant sous se format la : 00000.000000");
                                                                                                }
                                                                                            } else {
                                                                                                JOptionPane.showMessageDialog(null, "Veuillez saisir un montant pour la taxe d'aprentissage ou decochez la case ");
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                                                        }
                                                                                    } else {
                                                                                        JOptionPane.showMessageDialog(null, "La date de versement de la taxe d'aprentisage doit etre saisi ou decochez la case");
                                                                                    }
                                                                                } else {
                                                                                    String verfiEntrepriseExisteSQL = "SELECT * FROM entreprise WHERE Raison_Sociale = ? || Num_SIREN = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(verfiEntrepriseExisteSQL);
                                                                                        preparedStatement.setString(1, NomEntreprise);
                                                                                        preparedStatement.setString(2, NumSiren);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (!resultSet.next()) {
                                                                                            String AjouterEntrepriseSql = "INSERT INTO Entreprise (Raison_Sociale, Num_SIREN,Code_Postal,Adresse,Ville,Fax,Tel,Site_Web,Autre_Info,Taxe_Apprentissage,DateVersementTaxeAprentissage, idCompetence) value(?,?,?,?,?,?,?,?,?,?,?,?)";

                                                                                            try {

                                                                                                preparedStatement = connection.prepareStatement(AjouterEntrepriseSql);
                                                                                                preparedStatement.setString(1, NomEntreprise);
                                                                                                preparedStatement.setString(2, NumSiren);
                                                                                                preparedStatement.setString(3, CodePostal);
                                                                                                preparedStatement.setString(4, Adresse);
                                                                                                preparedStatement.setString(5, Ville);
                                                                                                preparedStatement.setString(6, Fax);
                                                                                                preparedStatement.setString(7, Tel);
                                                                                                preparedStatement.setString(8, SiteWeb);
                                                                                                preparedStatement.setString(9, AutreInfo);
                                                                                                preparedStatement.setString(10, "0");
                                                                                                preparedStatement.setString(11, "0");
                                                                                                preparedStatement.setString(12, idCompetence);
                                                                                                preparedStatement.execute();

                                                                                                JOptionPane.showMessageDialog(null, "Entreprise ajouter avec suces");
                                                                                                UpdateTable();


                                                                                                ClearTextField();

                                                                                            } catch (Exception e) {
                                                                                                e.printStackTrace();
                                                                                            }
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(null, "Une entreprise avec le meme nom et/ou la meme raison sociale existe déja");
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                    }
                                                                                }
                                                                            } else {
                                                                            }
                                                                        } catch (SQLException throwables) {
                                                                            throwables.printStackTrace();
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                    }

                                                                } catch (SQLException throwables) {
                                                                    throwables.printStackTrace();
                                                                }
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Le numero  de tel est invalide");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Le Tel est obligatoire");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Le Fax saise est invalide");
                                                }
                                            } else {
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
        Entreprise_Search();
    }

    public void SupprimerEntreprise(ActionEvent actionEvent) {
        String SupprimerEntrepriseSql = "DELETE FROM entreprise WHERE Raison_Sociale = ? AND Num_SIREN = ?";
        try {
            preparedStatement = connection.prepareStatement(SupprimerEntrepriseSql);
            preparedStatement.setString(1, TextFieldNomEntreprise.getText());
            preparedStatement.setString(2, TextFieldNumSiren.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "l'entreprise " + TextFieldNomEntreprise.getText() + " a été suprimer");
            UpdateTable();
            ClearTextField();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Entreprise_Search();
    }

    public void EditEntreprise(ActionEvent actionEvent) {

        String NomEntreprise = TextFieldNomEntreprise.getText();
        String NumSiren = TextFieldNumSiren.getText();
        String CodePostal = TextFieldCodePostal.getText();
        String Adresse = TextFieldAdresse.getText();
        String Ville = TextFieldVille.getText();
        String Fax = TextFieldFax.getText();
        String Tel = TextFieldTel.getText();
        String SiteWeb = TextFieldSiteWeb.getText();
        String AutreInfo = TextFieldAutreInfo.getText();
        String DateTaxe = TextFieldDateTaxe.getText();
        String MontantTaxe = TextFieldMontantTaxe.getText();
        String Competence = TextFieldCompetence.getText();
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
                                                    if (Tel.length() > 0) {
                                                        if (TelIsValide(Tel)) {
                                                            if (Competence.length() > 0) {
                                                                if (!MontantTaxe.equals("0") && !DateTaxe.equals("0")) {
                                                                    if (DateTaxe.length() > 0) {
                                                                        if (DateIsValide(DateTaxe)) {
                                                                            if (MontantTaxe.length() > 0) {
                                                                                if (MontantTaxeIsValide(MontantTaxe)) {


                                                                                    String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                                        preparedStatement.setString(1, Competence);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (resultSet.next()) {

                                                                                            String iDEntrepriseSQL = "SELECT idEntreprise FROM entreprise Where Raison_Sociale = ? AND Num_SIREN = ?";
                                                                                            try {
                                                                                                preparedStatement = connection.prepareStatement(iDEntrepriseSQL);
                                                                                                preparedStatement.setString(1, NomEntreprise);
                                                                                                preparedStatement.setString(2, NumSiren);
                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                if (resultSet.next()) {
                                                                                                    String idEntreprise = resultSet.getString("idEntreprise");
                                                                                                    String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                                                    try {
                                                                                                        preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                                                        preparedStatement.setString(1, Competence);
                                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                                        if (resultSet.next()) {
                                                                                                            String idCompetence = resultSet.getString("idCompetence");

                                                                                                            String EditEntrepriseSQL = "UPDATE entreprise SET Raison_Sociale = '" + NomEntreprise + "' , " +
                                                                                                                    "Num_SIREN = '" + NumSiren + "' , " +
                                                                                                                    "Code_Postal = '" + CodePostal + "' , " +
                                                                                                                    "Adresse = '" + Adresse + "' , " +
                                                                                                                    "Ville = '" + Ville + "' , " +
                                                                                                                    "Fax = '" + Fax + "' , " +
                                                                                                                    "Tel = '" + Tel + "' , " +
                                                                                                                    "Site_Web = '" + SiteWeb + "' , " +
                                                                                                                    "Autre_Info = '" + AutreInfo + "' , " +
                                                                                                                    "Taxe_Apprentissage = '" + MontantTaxe + "' , " +
                                                                                                                    "DateVersementTaxeAprentissage = '" + DateTaxe + "' , " +
                                                                                                                    "idCompetence  = '" + idCompetence + "' WHERE idEntreprise = '" + idEntreprise + "' ";
                                                                                                            preparedStatement = connection.prepareStatement(EditEntrepriseSQL);
                                                                                                            preparedStatement.execute();

                                                                                                            JOptionPane.showMessageDialog(null, "La modification de " + NomEntreprise + " a etait prise en compte");
                                                                                                            UpdateTable();
                                                                                                            ClearTextField();
                                                                                                        }
                                                                                                    } catch (SQLException throwables) {
                                                                                                        JOptionPane.showMessageDialog(null, "Erreur lors de la modification");
                                                                                                    }
                                                                                                }
                                                                                            } catch (SQLException throwables) {
                                                                                                throwables.printStackTrace();
                                                                                            }


                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                                        }

                                                                                    } catch (SQLException throwables) {
                                                                                        throwables.printStackTrace();
                                                                                    }
                                                                                } else {
                                                                                    JOptionPane.showMessageDialog(null, "Saisir un montant Valide");
                                                                                }
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(null, "Saisir un montant ");
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "Saisir une date Valide");
                                                                        }
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Saisir une date ");
                                                                    }
                                                                } else {
                                                                    String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                    try {
                                                                        preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                        preparedStatement.setString(1, Competence);
                                                                        resultSet = preparedStatement.executeQuery();
                                                                        if (resultSet.next()) {

                                                                            String iDEntrepriseSQL = "SELECT idEntreprise FROM entreprise Where Raison_Sociale = ? AND Num_SIREN = ?";
                                                                            try {
                                                                                preparedStatement = connection.prepareStatement(iDEntrepriseSQL);
                                                                                preparedStatement.setString(1, NomEntreprise);
                                                                                preparedStatement.setString(2, NumSiren);
                                                                                resultSet = preparedStatement.executeQuery();
                                                                                if (resultSet.next()) {
                                                                                    String idEntreprise = resultSet.getString("idEntreprise");
                                                                                    String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                                        preparedStatement.setString(1, Competence);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (resultSet.next()) {
                                                                                            String idCompetence = resultSet.getString("idCompetence");

                                                                                            String EditEntrepriseSQL = "UPDATE entreprise SET Raison_Sociale = '" + NomEntreprise + "' , " +
                                                                                                    "Num_SIREN = '" + NumSiren + "' , " +
                                                                                                    "Code_Postal = '" + CodePostal + "' , " +
                                                                                                    "Adresse = '" + Adresse + "' , " +
                                                                                                    "Ville = '" + Ville + "' , " +
                                                                                                    "Fax = '" + Fax + "' , " +
                                                                                                    "Tel = '" + Tel + "' , " +
                                                                                                    "Site_Web = '" + SiteWeb + "' , " +
                                                                                                    "Autre_Info = '" + AutreInfo + "' , " +
                                                                                                    "Taxe_Apprentissage = '" + MontantTaxe + "' , " +
                                                                                                    "DateVersementTaxeAprentissage = '" + DateTaxe + "' , " +
                                                                                                    "idCompetence  = '" + idCompetence + "' WHERE idEntreprise = '" + idEntreprise + "' ";
                                                                                            preparedStatement = connection.prepareStatement(EditEntrepriseSQL);
                                                                                            preparedStatement.execute();

                                                                                            JOptionPane.showMessageDialog(null, "La modification de " + NomEntreprise + " a etait prise en compte");
                                                                                            UpdateTable();
                                                                                            ClearTextField();
                                                                                        }
                                                                                    } catch (SQLException throwables) {
                                                                                        JOptionPane.showMessageDialog(null, "Erreur lors de la modification");
                                                                                    }
                                                                                }
                                                                            } catch (SQLException throwables) {
                                                                                throwables.printStackTrace();
                                                                            }


                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                        }

                                                                    } catch (SQLException throwables) {
                                                                        throwables.printStackTrace();
                                                                    }
                                                                }
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Le numero  de tel est invalide");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Le Tel est obligatoire");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Le Fax saise est invalide");
                                                }
                                            } else {
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
        Entreprise_Search();
    }


    @FXML
    public void getSelectEntreprise(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauEntreprise.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TextFieldNomEntreprise.setText(ColumnNom.getCellData(index).toString());
        TextFieldNumSiren.setText(ColumnNumerDeSiren.getCellData(index).toString());
        TextFieldCodePostal.setText(ColumnCodePostal.getCellData(index).toString());
        TextFieldAdresse.setText(ColumnAdresse.getCellData(index).toString());
        TextFieldVille.setText(ColumnVille.getCellData(index).toString());
        TextFieldFax.setText(ColumnFax.getCellData(index).toString());
        TextFieldTel.setText(ColumnTel.getCellData(index).toString());
        TextFieldSiteWeb.setText(ColumnSiteWeb.getCellData(index).toString());
        TextFieldAutreInfo.setText(ColumnAutreInfo.getCellData(index).toString());
        TextFieldCompetence.setText(ColumnCompetence.getCellData(index).toString());
        TextFieldDateTaxe.setText(ColumnTaxeAprentissageDatePayment.getCellData(index).toString());
        TextFieldMontantTaxe.setText(ColumnTaxeAprentissageMontant.getCellData(index).toString());
        if (TextFieldMontantTaxe.getText().equals("0")) {
            CheckBoxTaxeApprentissage.setSelected(false);
            TextFieldDateTaxe.setVisible(false);
            TextFieldMontantTaxe.setVisible(false);
        } else {
            CheckBoxTaxeApprentissage.setSelected(true);
            TextFieldDateTaxe.setVisible(true);
            TextFieldMontantTaxe.setVisible(true);
        }


    }

    @FXML
    public void getSelectCompetence(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauCompetence.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TextFieldCompetence.setText(ColumnCompétenceTComp.getCellData(index).toString());
    }

    public void ClearTextField() {
        TextFieldNomEntreprise.clear();
        TextFieldNumSiren.clear();
        TextFieldCodePostal.clear();
        TextFieldAdresse.clear();
        TextFieldVille.clear();
        TextFieldFax.clear();
        TextFieldTel.clear();
        TextFieldSiteWeb.clear();
        TextFieldAutreInfo.clear();
        TextFieldCompetence.clear();
        TextFieldDateTaxe.clear();
        TextFieldMontantTaxe.clear();
        CheckBoxTaxeApprentissage.setSelected(false);
        TextFieldDateTaxe.setVisible(false);
        TextFieldMontantTaxe.setVisible(false);
    }



    ObservableList listRecherche = FXCollections.observableArrayList();

    private void loadData() {
        listRecherche.removeAll(listRecherche);
        String a = "Nom Entreprise";
        String e = "Ville";
        String b = "Date";
        String c = "Competence";
        String d = "Tout";
        listRecherche.addAll(a, e, b, c, d);
        ChoiceBoxRecherche.getItems().addAll(listRecherche);
    }

    @FXML
    private void ButtonRechercheOnAction(ActionEvent event) {
        labelCatégorierecherche.setText(ChoiceBoxRecherche.getValue());
        Entreprise_Search();

    }

    ObservableList<Entreprise> ListRechecher;

    @FXML
    void Entreprise_Search() {
        ListRechecher = getDataEntreprise();
        TableauEntreprise.setItems(ListRechecher);

        FilteredList<Entreprise> filteredData = new FilteredList<>(ListRechecher, b -> true);
        TextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if(labelCatégorierecherche.getText().equals("") ||labelCatégorierecherche.getText().equals("Tout")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getRaison_Sociale().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (String.valueOf(person.getNum_SIREN()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (String.valueOf(person.getCode_Postal()).indexOf(lowerCaseFilter) != -1)
                        return true;
                    if (person.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getFax().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getTel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getSite_Web().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getAutre_Info().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getDateVersementTaxeAprentissage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getTaxe_Apprentissage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getCompetence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;


                });
            }else if(labelCatégorierecherche.getText().equals("Ville")){
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }else if(labelCatégorierecherche.getText().equals("Date")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getDateVersementTaxeAprentissage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            } else if(labelCatégorierecherche.getText().equals("Competence")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getCompetence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }else if(labelCatégorierecherche.getText().equals("Nom Entreprise")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getRaison_Sociale().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }
        });
        SortedList<Entreprise> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableauEntreprise.comparatorProperty());
        TableauEntreprise.setItems(sortedData);
    }
    @FXML
    public void RechercheAction(){
        System.out.println(labelCatégorierecherche.getText());
        if(labelCatégorierecherche.getText().equals("Tout")){
            System.out.println("2");
        }
    }
    public void BouttonPageEvenementOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)  BouttonPageEvenement.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/TableauEvenement.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Evenement");
    }

    @FXML
    Button btnMooveToEntreprise, btnMooveToEvenements, btnMooveToAddPersonne, btnMooveToAddCompetence, btnDeconnexion;

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

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEvenement.fxml")));
        stage.show();
        stage.setTitle("Admin_Event");
        stage.setScene(scene);
    }

    public void MooveToAddPersonne() throws Exception
    {
        Stage stage = (Stage) btnMooveToAddPersonne.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AjouterPersonneCompetence.fxml")));
        stage.show();
        stage.setTitle("Admin_Cours");
        stage.setScene(scene);
    }

    public void MooveToAddCompetence() throws Exception
    {
        Stage stage = (Stage) btnMooveToAddCompetence.getScene().getWindow();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AjouterPersonneCompetence.fxml")));
        stage.show();
        stage.setTitle("Admin_Cours");
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

    public void affichermenue(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(true);
        AnchorPaneEntreprise.setDisable(true);
        btnMenuAdmin.setVisible(false);
    }

    public void cachermenue(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(false);
        AnchorPaneEntreprise.setDisable(false);
        btnMenuAdmin.setVisible(true);
    }

}


