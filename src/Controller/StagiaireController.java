package Controller;

import AutreClasse.ConexionBDD;
import AutreClasse.EntrepriseNom;
import AutreClasse.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import static AutreClasse.User.Mail;

public class StagiaireController implements Initializable {
    @FXML
    private Button Bonjour;
    @FXML
    private Button BtnMenue;
    @FXML
    private AnchorPane AnchorPaneMenu;
    @FXML
    private Button btnCloseMenuAdmin;
    @FXML
    private Button btnAccueilEtudiant;
    @FXML
    private Button btnStagiaire;
    @FXML
    private Label LabelNombreCaractere;
    @FXML
    private TableColumn<EntrepriseNom, String> ColumnEntrepriseTEntreprise;
    @FXML
    private TableColumn<Stagiaire, String> ColumnDescription;
    @FXML
    private TableColumn<Stagiaire, String> ColumnNiveau;
    @FXML
    private TableColumn<Stagiaire, String> ColumnEtudiant;
    @FXML
    private TableColumn<Stagiaire, String> ColumnEntreprise;
    @FXML
    private TableView<Stagiaire> TableViewSatage;
    @FXML
    private TableView<EntrepriseNom> TableViewEntreprise;
    @FXML
    private TextField TefxtFieldEntreprise;
    @FXML
    private TextField TefxtFieldNomPrenom;
    @FXML
    private TextField TefxtFieldNiveau;
    @FXML
    private TextArea TextAreaDescription;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button BtnAjouter;
    @FXML
    private Button BtnMofifier;
    @FXML
    private Button BtnSupprimer;


    /*
Connexion classe BDD
*/
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public StagiaireController() {
        connection = ConexionBDD.connectdb();
    }

    ObservableList<EntrepriseNom> listEntrepriseNom;

    public static ObservableList<EntrepriseNom> getDataEntrepriseNom() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<EntrepriseNom> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT RAISON_SOCIALE FROM entreprise";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EntrepriseNom(rs.getString("RAISON_SOCIALE")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    ObservableList<Stagiaire> listStagiaire;

    public ObservableList<Stagiaire> getDataStage() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Stagiaire> list = FXCollections.observableArrayList();
        String NomP = NomPrenomConnexion(Mail);

        try {
            String CompetenceSQL = "SELECT E.Raison_Sociale, S.NomPrenom, S.Niveau,S.NoteDeStage  FROM stage S INNER JOIN entreprise E ON E.idEntreprise=S.idEntreprise Where S.NomPrenom like '" + NomP + "'";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Stagiaire(
                        rs.getString("Raison_Sociale"),
                        rs.getString("NomPrenom"),
                        rs.getString("Niveau"),
                        rs.getString("NoteDeStage")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public String NomPrenomConnexion(String text) {
        String Separateur = "@";
        String Separateur2 = ".";
        StringTokenizer ST = new StringTokenizer(text, Separateur);
        String A = ST.nextToken();
        StringTokenizer STNomPrenom = new StringTokenizer(A, Separateur2);
        String Prenom = upperCaseFirst(STNomPrenom.nextToken());
        String Nom = upperCaseFirst(STNomPrenom.nextToken());
        String NP = Prenom + " " + Nom;
        return NP;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ColumnEntrepriseTEntreprise.setCellValueFactory(new PropertyValueFactory<EntrepriseNom, String>("RAISON_SOCIALE"));
        listEntrepriseNom = getDataEntrepriseNom();
        TableViewEntreprise.setItems(listEntrepriseNom);

        ColumnEntreprise.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Raison_Sociale"));
        ColumnEtudiant.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("NomPrenom"));
        ColumnNiveau.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Niveau"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("NoteDeStage"));
        listStagiaire = getDataStage();
        TableViewSatage.setItems(listStagiaire);


        TefxtFieldNomPrenom.setText(NomPrenomConnexion(Mail));
        TextAreaDescription.setWrapText(true);

    }

    public void UpdateTable() {
        ColumnEntrepriseTEntreprise.setCellValueFactory(new PropertyValueFactory<EntrepriseNom, String>("RAISON_SOCIALE"));
        listEntrepriseNom = getDataEntrepriseNom();
        TableViewEntreprise.setItems(listEntrepriseNom);

        ColumnEntreprise.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Raison_Sociale"));
        ColumnEtudiant.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("NomPrenom"));
        ColumnNiveau.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Niveau"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("NoteDeStage"));
        listStagiaire = getDataStage();
        TableViewSatage.setItems(listStagiaire);


        TefxtFieldNomPrenom.setText(NomPrenomConnexion(Mail));
        TextAreaDescription.setWrapText(true);
    }

    int indexGetEventEntreprise = -1;

    @FXML
    public void getSelectEventEntreprise(javafx.scene.input.MouseEvent mouseEvent) {
        indexGetEventEntreprise = TableViewEntreprise.getSelectionModel().getSelectedIndex();
        if (indexGetEventEntreprise <= -1) {
            return;
        }
        TefxtFieldEntreprise.setText(ColumnEntrepriseTEntreprise.getCellData(indexGetEventEntreprise).toString());
    }

    public void BtnMenueOnAction(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(true);
        AnchorPaneMenu.setDisable(false);
        Disabletrue();


    }

    public void btnCloseMenuAdminOnAction(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(false);
        AnchorPaneMenu.setDisable(true);
        DisableFalse();


    }
    public void ClearTextField(){
        TefxtFieldEntreprise.clear();
        TefxtFieldNiveau.clear();
        TextAreaDescription.clear();
    }

    public void Disabletrue() {
        BtnAjouter.setDisable(true);
        LabelNombreCaractere.setDisable(true);
        TefxtFieldEntreprise.setDisable(true);
        TefxtFieldNomPrenom.setDisable(true);
        TefxtFieldNiveau.setDisable(true);
        TextAreaDescription.setDisable(true);
        Bonjour.setDisable(true);
        BtnMenue.setDisable(true);
        TableViewSatage.setDisable(true);
        TableViewEntreprise.setDisable(true);
    }

    public void DisableFalse() {
        BtnAjouter.setDisable(false);
        LabelNombreCaractere.setDisable(false);
        TefxtFieldEntreprise.setDisable(true);
        TefxtFieldNomPrenom.setDisable(true);
        TefxtFieldNiveau.setDisable(false);
        TextAreaDescription.setDisable(false);
        Bonjour.setDisable(false);
        BtnMenue.setDisable(false);
        TableViewSatage.setDisable(false);
        TableViewEntreprise.setDisable(false);
    }

    public void AccueilEtudiant() throws Exception {
        Stage stage = (Stage) btnAccueilEtudiant.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AccueilEtudiant.fxml")));
        stage.show();
        stage.setTitle("Accueil  Etudiant");
        stage.setScene(scene);
    }

    public void Stagiaire() throws Exception {
        Stage stage = (Stage) btnStagiaire.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Stagiaire.fxml")));
        stage.show();
        stage.setTitle("Stagiaire");
        stage.setScene(scene);
    }

    public void BonjourOnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Connexion");

    }

    public void Stage(ActionEvent event) throws Exception {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Stage.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Stage");

    }

    public void compterNbCaractere(javafx.scene.input.KeyEvent keyEvent) {
        int d = 499 - TextAreaDescription.getText().length();
        LabelNombreCaractere.setText(String.valueOf("Il vous reste : " + d + " caractére maximum"));
    }

    public static boolean NiveauEtudeIsValide(String NiveauEtude) {
        String emailRegex = "([LMD]{1}[1-7]{1})";

        Pattern pat = Pattern.compile(emailRegex);
        if (NiveauEtude == null)
            return false;
        return pat.matcher(NiveauEtude).matches();
    }

    public void AjouterStage() {
        TableViewEntreprise.setDisable(false);

        String Entreprise = TefxtFieldEntreprise.getText();
        String NomPrenom = TefxtFieldNomPrenom.getText();
        String Niveau = TefxtFieldNiveau.getText();
        String Description = TextAreaDescription.getText();
        String Separateur = " ";
        String MaChaine = NomPrenom;
        StringTokenizer ST = new StringTokenizer(MaChaine, Separateur);

        if (Entreprise.length() > 0) {
            if (Niveau.length() > 0) {
                if (NiveauEtudeIsValide(Niveau)) {
                    if (Description.length() > 0) {
                        if (Description.length() <= 500) {
                            String VerifidEntrepriseSQL = "SELECT idEntreprise FROM  entreprise WHERE Raison_Sociale like '" + Entreprise + "'";
                            try {
                                preparedStatement = connection.prepareStatement(VerifidEntrepriseSQL);
                                resultSet = preparedStatement.executeQuery();
                                if (resultSet.next()) {
                                    String idEntreprise = resultSet.getString("idEntreprise");
                                    String VerifidPerssoneSQL = "SELECT idPersonnes FROM  user WHERE Mail like '" + Mail + "'";
                                    try {
                                        preparedStatement = connection.prepareStatement(VerifidPerssoneSQL);
                                        resultSet = preparedStatement.executeQuery();
                                        if (resultSet.next()) {
                                            String idPersonne = resultSet.getString("idPersonnes");
                                            String VerifStageSQL = "SELECT IdStage FROM stage WHERE idEntreprise like '" + idEntreprise + "' AND idUser like '" + idPersonne + "'";
                                            try {
                                                preparedStatement = connection.prepareStatement(VerifStageSQL);
                                                resultSet = preparedStatement.executeQuery();
                                                if (!resultSet.next()) {
                                                    String InserStageSQL = "INSERT INTO stage (idEntreprise, idUser,Niveau, NoteDeStage,NomPrenom) VALUES (?,?,?,?,?)";
                                                    try {
                                                        preparedStatement = connection.prepareStatement(InserStageSQL);
                                                        preparedStatement.setString(1, idEntreprise);
                                                        preparedStatement.setString(2, idPersonne);
                                                        preparedStatement.setString(3, Niveau);
                                                        preparedStatement.setString(4, Description);
                                                        preparedStatement.setString(5, NomPrenom);
                                                        preparedStatement.execute();
                                                        JOptionPane.showMessageDialog(null, "Stage ajouter avec succes");
                                                        UpdateTable();
                                                        ClearTextField();


                                                    } catch (SQLException throwables) {
                                                        throwables.printStackTrace();
                                                    } catch (HeadlessException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Vous avez deja renseigné ce stage");
                                                    ClearTextField();
                                                }
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La taille maximum est de 500 caractére");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez decrire votre stage");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Votre année d'étude doit être sous la forme L1, L2, M1...");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez renseignez un le niveau d'étude dans le quel vous êtes");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une entreprise");
        }
    }

    int index = -1;

    @FXML
    public void getSelectStage(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableViewSatage.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TefxtFieldEntreprise.setText(ColumnEntreprise.getCellData(index).toString());
        TefxtFieldNomPrenom.setText(ColumnEtudiant.getCellData(index).toString());
        TefxtFieldNiveau.setText(ColumnNiveau.getCellData(index).toString());
        TextAreaDescription.setText(ColumnDescription.getCellData(index).toString());
        BtnMofifier.setDisable(false);
        BtnSupprimer.setDisable(false);
        TableViewEntreprise.setDisable(true);


    }

    public void ModifierOnAction(ActionEvent actionEvent) {
        String Entreprise = TefxtFieldEntreprise.getText();
        String NomPrenom = TefxtFieldNomPrenom.getText();
        String Niveau = TefxtFieldNiveau.getText();
        String Description = TextAreaDescription.getText();
        String Separateur = " ";
        String MaChaine = NomPrenom;
        StringTokenizer ST = new StringTokenizer(MaChaine, Separateur);

        if (Entreprise.length() > 0) {
            if (Niveau.length() > 0) {
                if (NiveauEtudeIsValide(Niveau)) {
                    if (Description.length() > 0) {
                        if (Description.length() <= 500) {
                            String VerifidEntrepriseSQL = "SELECT idEntreprise FROM  entreprise WHERE Raison_Sociale like '" + Entreprise + "'";

                            try {
                                preparedStatement = connection.prepareStatement(VerifidEntrepriseSQL);
                                resultSet = preparedStatement.executeQuery();
                                if (resultSet.next()) {
                                    String idEntreprise = resultSet.getString("idEntreprise");
                                    String VerifidPerssoneSQL = "SELECT idPersonnes FROM  user WHERE Mail like '" + Mail + "'";
                                    try {
                                        preparedStatement = connection.prepareStatement(VerifidPerssoneSQL);
                                        resultSet = preparedStatement.executeQuery();
                                        if (resultSet.next()) {
                                            String idPersonne = resultSet.getString("idPersonnes");
                                            String VerifStageSQL = "SELECT IdStage FROM stage WHERE idEntreprise like '" + idEntreprise + "' AND idUser like '" + idPersonne + "'";
                                            try {
                                                preparedStatement = connection.prepareStatement(VerifStageSQL);
                                                resultSet = preparedStatement.executeQuery();
                                                if (resultSet.next()) {
                                                    String InserStageSQL = "UPDATE stage SET idEntreprise = '" + idEntreprise + "' , " +
                                                            "idUser = '" + idPersonne + "' , " +
                                                            "Niveau = '" + Niveau + "' , " +
                                                            "NoteDeStage = '" + Description + "' , " +
                                                            "NomPrenom = '" + NomPrenom + "' WHERE idEntreprise = '" + idEntreprise + "' ";
                                                    preparedStatement = connection.prepareStatement(InserStageSQL);
                                                    preparedStatement.execute();
                                                        JOptionPane.showMessageDialog(null, "Stage Modifier avec succes");
                                                        UpdateTable();
                                                        ClearTextField();
                                                    BtnMofifier.setDisable(true);
                                                    BtnSupprimer.setDisable(true);
                                                    TableViewEntreprise.setDisable(false);

                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Vous avez deja renseigné ce stage");
                                                    ClearTextField();
                                                }
                                            } catch (SQLException throwables) {
                                                throwables.printStackTrace();
                                            }
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La taille maximum est de 500 caractére");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez decrire votre stage");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Votre année d'étude doit être sous la forme L1, L2, M1...");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez renseignez un le niveau d'étude dans le quel vous êtes");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner une entreprise");
        }

    }

    public void SupprimerOnAction(ActionEvent actionEvent) {
        String Entreprise = TefxtFieldEntreprise.getText();
        String NomPrenom = TefxtFieldNomPrenom.getText();
        String VerifidEntrepriseSQL = "SELECT idEntreprise FROM  entreprise WHERE Raison_Sociale like '" + Entreprise + "'";
        try {
            preparedStatement = connection.prepareStatement(VerifidEntrepriseSQL);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String idEntreprise = resultSet.getString("idEntreprise");
                String SupprimerStageSQL = "DELETE FROM stage Where idEntreprise like '"+idEntreprise+"' AND NomPrenom like '"+NomPrenom+"'";
                preparedStatement = connection.prepareStatement(SupprimerStageSQL);
                preparedStatement.execute();

                JOptionPane.showMessageDialog(null, "Votre stage a bien été supprimer");
                TableViewEntreprise.setDisable(false);         BtnMofifier.setDisable(true);
                BtnSupprimer.setDisable(true);



                UpdateTable();
                ClearTextField();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
