package Controller;

import AutreClasse.*;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterPersonneCompetence implements Initializable {
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane1;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane2;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPaneMenu;
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPaneAddPersonne;
    @FXML
    private Button btnMenuAdmin;
    @FXML
    private TableView<Competence> TableViewCompetence;
    @FXML
    private TableColumn<Competence, String> ColumnCompetence;
    @FXML
    private TableView<PersonneNomPrenom> TableViewPersone;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnNom;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnPrenom;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnTel;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnEmail;
    @FXML
    private CheckBox CheckBoxPersone;
    @FXML
    private CheckBox CheckBoxCompetence;
    @FXML
    private Button BtnAjouter;
    @FXML
    private Button BtnModifierCompetence;
    @FXML
    private Button BtnModifierPersonne;
    @FXML
    private TextField TextFieldNom;
    @FXML
    private TextField TextFieldPrenom;
    @FXML
    private TextField TextFieldTel;
    @FXML
    private TextField TextFieldEmail;
    @FXML
    private TextField TextFieldCompetence;
    @FXML
    private Button BtnAutrePage;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnEntreprise;
    @FXML
    private TextField TextFieldSelectEntreprise;
    @FXML
    private TableView<EntrepriseNom> TableViewEntreprise;
    @FXML
    private TableColumn<EntrepriseNom, String> ColumnEntrepriseTentreprise;
    @FXML
    private Label LabelNom;
    @FXML
    private Label LabelPrenom;
    @FXML
    private Label LabelEmail;
    @FXML
    private Label LabelTel;
    @FXML
    private Label LabelComp;
    @FXML
    private Button BtnSupprimerPersonne;
    @FXML
    private Button BtnSupprimerCompetence;
    @FXML
    private TextField TextFieldRecherche;
    @FXML
    private  ChoiceBox<String> ChoiceBoxRecherche;
    @FXML
    private Label labelCatégorierecherche;
    @FXML
    private TableView<EntrepriseEvenement> TableViewEvenement;
    @FXML
    private TableColumn<EntrepriseEvenement, String> ColumnEvenementTEvenement;
    @FXML
    private TableColumn<EntrepriseEvenement, String> ColumnEntrepriseTEvenement;
    @FXML
    private TableView<NomEvenemnt> TableViewAfficherEvent;
    @FXML
    private TableColumn<NomEvenemnt, String> ColumnEventTEvent;
    @FXML
    private TextField TextFieldSelectEvent;
    @FXML
    private  CheckBox CheckBoxkEvententreprise;
    @FXML
    private Button BtnSupprimerEvenementEntreprise;
    @FXML
    private Button BtnModifierPersonneEvenementEntreprise;
    @FXML
    private TableView<Competence> TableViewCompetence2;
    @FXML
    private  TableColumn<Competence, String> ColumnCompetence2;
    @FXML
    private TextField ChoixCompetencePersonne;
    @FXML
    private TableColumn<PersonneNomPrenom, String> ColumnCompetenceTPersone;



    public void BouttonPageEvenementOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) BtnAutrePage.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEvenement.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Evenement");
    }


    /*
Connexion classe BDD
*/
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public AjouterPersonneCompetence() {
        connection = ConexionBDD.connectdb();
    }

    ObservableList<PersonneNomPrenom> listP;


    public static ObservableList<PersonneNomPrenom> getDataPersone() {

        Connection connection = ConexionBDD.connectdb();
        ObservableList<PersonneNomPrenom> list = FXCollections.observableArrayList();

        try {
            String PersoneSQL = "SELECT P.NomPersone, P.PrenomPersone, P.TelPersone,P.EmailPersonne, E.RAISON_SOCIALE, C.Competence FROM persone P INNER JOIN Entreprise E ON E.IDENTREPRISE = P.IDENTREPRISE INNER JOIN Competence C ON C.idCompetence = P.idCompetence ";
            PreparedStatement ps = connection.prepareStatement(PersoneSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PersonneNomPrenom(
                        rs.getString("NomPersone"),
                        rs.getString("PrenomPersone"),
                        rs.getString("TelPersone"),
                        rs.getString("EmailPersonne"),
                        rs.getString("Raison_Sociale"),
                        rs.getString("Competence")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    ObservableList<Competence> listCompetence;

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

    ObservableList<EntrepriseEvenement> listEntrepriseEvenement;

    public static ObservableList<EntrepriseEvenement> getDataEntrepriseEvenement() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<EntrepriseEvenement> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT E.RAISON_SOCIALE, Event.NomEvenement FROM Entreprise E INNER JOIN evenemntentreprise even ON E.IDENTREPRISE =even.idEntreprise INNER JOIN evenement Event ON  Event.idEvenement = even.idEvenement WHERE  Event.idtypeEvenement = 3";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EntrepriseEvenement(rs.getString("NomEvenement"),
                        rs.getString("RAISON_SOCIALE")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
    ObservableList<NomEvenemnt> listEvenement;

    public static ObservableList<NomEvenemnt> getDataNomEvenement() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<NomEvenemnt> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT NomEvenement FROM evenement WHERE idtypeEvenement =3 ";//
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NomEvenemnt(
                        rs.getString("NomEvenement")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    ObservableList<Competence> listCompetence2;

    public static ObservableList<Competence> getDataCompetence2() {
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


    ObservableList listRecherche = FXCollections.observableArrayList();
    private void loadData() {
        listRecherche.removeAll(listRecherche);
        String a = "Nom";
        String b = "Prenom";
        String c = "Numero";
        String d ="Entreprise";
        String e ="Competence";
        listRecherche.addAll(a, b,c,d,e);
        ChoiceBoxRecherche.getItems().addAll(listRecherche);
    }
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("NomPersone"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("PrenomPersone"));
        ColumnTel.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("TelPersone"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("EmailPersonne"));
        ColumnEntreprise.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("Raison_Sociale"));
        ColumnCompetenceTPersone.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("Competence"));
        listP = getDataPersone();
        TableViewPersone.setItems(listP);

        ColumnCompetence.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence = getDataCompetence();
        TableViewCompetence.setItems(listCompetence);

        ColumnEntrepriseTentreprise.setCellValueFactory(new PropertyValueFactory<EntrepriseNom, String>("RAISON_SOCIALE"));
        listEntrepriseNom = getDataEntrepriseNom();
        TableViewEntreprise.setItems(listEntrepriseNom);

        ColumnEvenementTEvenement.setCellValueFactory(new PropertyValueFactory<EntrepriseEvenement, String>("NomEvenement"));
        ColumnEntrepriseTEvenement.setCellValueFactory(new PropertyValueFactory<EntrepriseEvenement, String>("RAISON_SOCIALE"));
        listEntrepriseEvenement = getDataEntrepriseEvenement();
        TableViewEvenement.setItems(listEntrepriseEvenement);

        ColumnEventTEvent.setCellValueFactory(new PropertyValueFactory<NomEvenemnt, String>("NomEvenement"));
        listEvenement = getDataNomEvenement();
        TableViewAfficherEvent.setItems(listEvenement);

        ColumnCompetence2.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence2 = getDataCompetence2();
        TableViewCompetence2.setItems(listCompetence2);


        loadData();

    }

    public void UpdateTable() {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("NomPersone"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("PrenomPersone"));
        ColumnTel.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("TelPersone"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("EmailPersonne"));
        ColumnEntreprise.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("Raison_Sociale"));
        ColumnCompetenceTPersone.setCellValueFactory(new PropertyValueFactory<PersonneNomPrenom, String>("Competence"));
        listP = getDataPersone();
        TableViewPersone.setItems(listP);

        ColumnCompetence.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence = getDataCompetence();
        TableViewCompetence.setItems(listCompetence);

        ColumnEntrepriseTentreprise.setCellValueFactory(new PropertyValueFactory<EntrepriseNom, String>("RAISON_SOCIALE"));
        listEntrepriseNom = getDataEntrepriseNom();
        TableViewEntreprise.setItems(listEntrepriseNom);

        ColumnEvenementTEvenement.setCellValueFactory(new PropertyValueFactory<EntrepriseEvenement, String>("NomEvenement"));
        ColumnEntrepriseTEvenement.setCellValueFactory(new PropertyValueFactory<EntrepriseEvenement, String>("RAISON_SOCIALE"));
        listEntrepriseEvenement = getDataEntrepriseEvenement();
        TableViewEvenement.setItems(listEntrepriseEvenement);

        ColumnEventTEvent.setCellValueFactory(new PropertyValueFactory<NomEvenemnt, String>("NomEvenement"));
        listEvenement = getDataNomEvenement();
        TableViewAfficherEvent.setItems(listEvenement);

        ColumnCompetence2.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence2 = getDataCompetence2();
        TableViewCompetence2.setItems(listCompetence2);


        loadData();

    }

    public void clearTextfield() {
        TextFieldNom.clear();
        TextFieldPrenom.clear();
        TextFieldTel.clear();
        TextFieldEmail.clear();
        TextFieldCompetence.clear();
        TextFieldSelectEntreprise.clear();
        TextFieldSelectEvent.clear();
        ChoixCompetencePersonne.clear();

    }
    public void CheckEvententreprise(javafx.event.ActionEvent actionEvent){
        if (CheckBoxkEvententreprise.isSelected()) {
            BtnAjouter.setDisable(false);
            TextFieldCompetence.setVisible(false);
            CheckBoxPersone.setSelected(false);
            CheckBoxCompetence.setSelected(false);
            TextFieldNom.setVisible(false);
            TextFieldPrenom.setVisible(false);
            TextFieldTel.setVisible(false);
            TextFieldEmail.setVisible(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TableViewEntreprise.setVisible(true);
            TextFieldSelectEntreprise.setVisible(true);
            TextFieldSelectEvent.setVisible(true);
            TableViewAfficherEvent.setVisible(true);
            TableViewCompetence2.setVisible(false);
            ChoixCompetencePersonne.setVisible(false);
            clearTextfield();


        } else {
            BtnAjouter.setDisable(true);
            TextFieldNom.setVisible(false);
            TextFieldPrenom.setVisible(false);
            TextFieldTel.setVisible(false);
            TextFieldEmail.setVisible(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TextFieldSelectEvent.setVisible(false);
            TableViewAfficherEvent.setVisible(false);
            TableViewEntreprise.setVisible(false);
            TextFieldSelectEntreprise.setVisible(false);
            TableViewCompetence2.setVisible(false);
            ChoixCompetencePersonne.setVisible(false);
            clearTextfield();

        }

    }

    public void CheckEventCompetence(javafx.event.ActionEvent actionEvent) {
        if (CheckBoxCompetence.isSelected()) {
            BtnAjouter.setDisable(false);
            TextFieldCompetence.setVisible(true);
            TextFieldCompetence.setDisable(false);
            CheckBoxkEvententreprise.setSelected(false);
            CheckBoxPersone.setSelected(false);
            TextFieldNom.setVisible(false);
            TextFieldPrenom.setVisible(false);
            TextFieldTel.setVisible(false);
            TextFieldEmail.setVisible(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TableViewEntreprise.setVisible(false);
            TextFieldSelectEntreprise.setVisible(false);
            TextFieldSelectEvent.setVisible(false);
            TableViewAfficherEvent.setVisible(false);
            TableViewCompetence2.setVisible(false);
            ChoixCompetencePersonne.setVisible(false);
            clearTextfield();


        } else {
            BtnAjouter.setDisable(true);
            TextFieldNom.setVisible(false);
            TextFieldPrenom.setVisible(false);
            TextFieldTel.setVisible(false);
            TextFieldEmail.setVisible(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TextFieldSelectEvent.setVisible(false);
            TableViewAfficherEvent.setVisible(false);
            TableViewCompetence2.setVisible(false);
            ChoixCompetencePersonne.setVisible(false);
            clearTextfield();

        }


    }

    public void CheckEventPersonne(javafx.event.ActionEvent actionEvent) {
        if (CheckBoxPersone.isSelected()) {
            BtnAjouter.setDisable(false);
            TextFieldNom.setVisible(true);
            TextFieldPrenom.setVisible(true);
            TextFieldTel.setVisible(true);
            TextFieldEmail.setVisible(true);
            CheckBoxCompetence.setSelected(false);
            TextFieldCompetence.setVisible(false);
            CheckBoxkEvententreprise.setSelected(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TableViewEntreprise.setVisible(true);
            TextFieldSelectEntreprise.setVisible(true);
            TextFieldSelectEvent.setVisible(false);
            TableViewAfficherEvent.setVisible(false);
            TableViewCompetence2.setVisible(true);
            ChoixCompetencePersonne.setVisible(true);
            clearTextfield();

        } else {
            BtnAjouter.setDisable(true);
            TextFieldNom.setVisible(false);
            TextFieldPrenom.setVisible(false);
            TextFieldTel.setVisible(false);
            TextFieldEmail.setVisible(false);
            BtnModifierCompetence.setDisable(true);
            BtnSupprimerPersonne.setDisable(true);
            BtnSupprimerCompetence.setDisable(true);
            BtnModifierPersonneEvenementEntreprise.setDisable(true);
            BtnSupprimerEvenementEntreprise.setDisable(true);
            BtnModifierPersonne.setDisable(true);
            TextFieldSelectEvent.setVisible(false);
            TableViewAfficherEvent.setVisible(false);
            TableViewCompetence2.setVisible(false);
            ChoixCompetencePersonne.setVisible(false);

            clearTextfield();

        }

    }

    int index = -1;

    @FXML
    public void getSelecectpersone(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableViewPersone.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TextFieldNom.setText(ColumnNom.getCellData(index).toString());
        TextFieldPrenom.setText(ColumnPrenom.getCellData(index).toString());
        TextFieldTel.setText(ColumnTel.getCellData(index).toString());
        TextFieldEmail.setText(ColumnEmail.getCellData(index).toString());
        TextFieldSelectEntreprise.setText(ColumnEntreprise.getCellData(index).toString());
        ChoixCompetencePersonne.setText(ColumnCompetenceTPersone.getCellData(index).toString());

        LabelNom.setText(ColumnNom.getCellData(index).toString());
        LabelPrenom.setText(ColumnPrenom.getCellData(index).toString());
        LabelTel.setText(ColumnTel.getCellData(index).toString());
        LabelEmail.setText(ColumnEmail.getCellData(index).toString());


        TextFieldSelectEntreprise.setVisible(true);
        TextFieldNom.setVisible(true);
        TextFieldPrenom.setVisible(true);
        TextFieldTel.setVisible(true);
        TextFieldEmail.setVisible(true);
        TextFieldCompetence.setVisible(false);
        CheckBoxPersone.setSelected(false);
        CheckBoxCompetence.setSelected(false);
        BtnAjouter.setDisable(true);
        BtnModifierCompetence.setDisable(true);
        BtnSupprimerPersonne.setDisable(false);
        BtnSupprimerPersonne.setVisible(true);
        BtnSupprimerCompetence.setDisable(true);
        BtnSupprimerCompetence.setVisible(false);
        BtnModifierPersonneEvenementEntreprise.setDisable(true);
        BtnSupprimerEvenementEntreprise.setDisable(true);
        BtnModifierPersonneEvenementEntreprise.setVisible(false);
        BtnSupprimerEvenementEntreprise.setVisible(false);;
        BtnModifierPersonne.setDisable(false);
        BtnModifierCompetence.setVisible(false);
        BtnModifierPersonne.setVisible(true);
        TableViewEntreprise.setVisible(true);
        TextFieldSelectEvent.setVisible(false);
        TableViewAfficherEvent.setVisible(false);
        CheckBoxkEvententreprise.setSelected(false);
        ChoixCompetencePersonne.setVisible(true);
        TableViewCompetence2.setVisible(true);

    }

    int indexGetC = -1;

    @FXML
    public void getSelectCompetence(javafx.scene.input.MouseEvent mouseEvent) {
        indexGetC = TableViewCompetence.getSelectionModel().getSelectedIndex();
        if (indexGetC <= -1) {
            return;
        }
        TextFieldCompetence.setText(ColumnCompetence.getCellData(indexGetC).toString());
        LabelComp.setText(ColumnCompetence.getCellData(indexGetC).toString());
        TextFieldCompetence.setVisible(true);
        TextFieldNom.setVisible(false);
        TextFieldPrenom.setVisible(false);
        TextFieldTel.setVisible(false);
        TextFieldEmail.setVisible(false);
        CheckBoxPersone.setSelected(false);
        CheckBoxCompetence.setSelected(false);
        BtnAjouter.setDisable(true);
        BtnModifierCompetence.setDisable(false);
        BtnSupprimerPersonne.setVisible(false);
        BtnSupprimerPersonne.setDisable(true);
        BtnSupprimerCompetence.setDisable(false);
        BtnSupprimerCompetence.setVisible(true);
        BtnModifierPersonneEvenementEntreprise.setDisable(true);
        BtnSupprimerEvenementEntreprise.setDisable(true);
        BtnModifierPersonneEvenementEntreprise.setVisible(false);
        BtnSupprimerEvenementEntreprise.setVisible(false);;
        BtnModifierPersonne.setDisable(true);
        TextFieldSelectEntreprise.setVisible(false);
        TableViewEntreprise.setVisible(false);
        BtnModifierCompetence.setVisible(true);
        BtnModifierPersonne.setVisible(false);
        TextFieldSelectEvent.setVisible(false);
        TableViewAfficherEvent.setVisible(false);
        CheckBoxkEvententreprise.setSelected(false);
        ChoixCompetencePersonne.setVisible(false);
        TableViewCompetence2.setVisible(false);

    }

    @FXML
    int indexEntrepriseEvent = -1;
    public void getSelectEntrepriseEvenemnt(javafx.scene.input.MouseEvent mouseEvent) {
        indexEntrepriseEvent = TableViewEvenement.getSelectionModel().getSelectedIndex();
        if (indexEntrepriseEvent <= -1) {
            return;
        }
        TextFieldSelectEntreprise.setText(ColumnEntrepriseTEvenement.getCellData(indexEntrepriseEvent).toString());
        TextFieldSelectEvent.setText(ColumnEvenementTEvenement.getCellData(indexEntrepriseEvent).toString());
        TextFieldCompetence.setVisible(false);
        TextFieldNom.setVisible(false);
        TextFieldPrenom.setVisible(false);
        TextFieldTel.setVisible(false);
        TextFieldEmail.setVisible(false);
        CheckBoxPersone.setSelected(false);
        CheckBoxCompetence.setSelected(false);
        BtnAjouter.setDisable(true);
        BtnModifierCompetence.setDisable(true);
        BtnSupprimerPersonne.setVisible(false);
        BtnSupprimerPersonne.setDisable(true);
        BtnSupprimerCompetence.setDisable(true);
        BtnSupprimerCompetence.setVisible(false);
        BtnModifierPersonne.setDisable(true);
        TextFieldSelectEntreprise.setVisible(true);
        TableViewEntreprise.setVisible(true);
        BtnModifierCompetence.setVisible(true);
        BtnModifierPersonne.setVisible(false);
        BtnModifierPersonneEvenementEntreprise.setDisable(true);
        BtnSupprimerEvenementEntreprise.setDisable(false);
        BtnModifierPersonneEvenementEntreprise.setVisible(false);
        BtnSupprimerEvenementEntreprise.setVisible(true);
        TextFieldSelectEvent.setVisible(true);
        TableViewAfficherEvent.setVisible(true);
        CheckBoxkEvententreprise.setSelected(false);
        ChoixCompetencePersonne.setVisible(false);
        TableViewCompetence2.setVisible(false);

    }

    int indexGetEntreprise = -1;

    @FXML
    public void getSelectEntrepriseNom(javafx.scene.input.MouseEvent mouseEvent) {
        indexGetEntreprise = TableViewEntreprise.getSelectionModel().getSelectedIndex();
        if (indexGetEntreprise <= -1) {
            return;
        }
        TextFieldSelectEntreprise.setText(ColumnEntrepriseTentreprise.getCellData(indexGetEntreprise).toString());
    }

    int indexGetCompetenceP = -1;

    @FXML
    public void getSelectCompetenceP(javafx.scene.input.MouseEvent mouseEvent) {
        indexGetCompetenceP = TableViewCompetence2.getSelectionModel().getSelectedIndex();
        if (indexGetCompetenceP <= -1) {
            return;
        }
        ChoixCompetencePersonne.setText(ColumnCompetence2.getCellData(indexGetCompetenceP).toString());
    }

    int indexGetEventEntreprise = -1;

    @FXML
    public void getSelectEventEntreprise(javafx.scene.input.MouseEvent mouseEvent) {
        indexGetEventEntreprise = TableViewAfficherEvent.getSelectionModel().getSelectedIndex();
        if (indexGetEventEntreprise <= -1) {
            return;
        }
        TextFieldSelectEvent.setText(ColumnEventTEvent.getCellData(indexGetEventEntreprise).toString());
    }

    public static boolean TextMajIsValide(String TextMaj) {
        String mdpRegex = "[A-Z0-9]*";

        Pattern pat = Pattern.compile(mdpRegex);
        if (TextMaj == null)
            return false;
        return pat.matcher(TextMaj).matches();
    }

    public static boolean NomPIsValide(String NomP) {
        String mdpRegex = "[A-Z]{1,30}[A-Z ]*";

        Pattern pat = Pattern.compile(mdpRegex);
        if (NomP == null)
            return false;
        return pat.matcher(NomP).matches();
    }

    public static boolean TelIsValide(String Tel) {
        String mdpRegex = "([0][0-9]{9})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Tel == null)
            return false;
        return pat.matcher(Tel).matches();
    }

    public static boolean mailisValid(String mail) {
        String emailRegex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        Pattern pat = Pattern.compile(emailRegex);
        if (mail == null)
            return false;
        return pat.matcher(mail).matches();

    }

    public void Ajouter() {
        if (CheckBoxCompetence.isSelected()) {
            String Competence = TextFieldCompetence.getText();
            if (Competence.length() > 0) {
                if (TextMajIsValide(Competence)) {
                    String VerifCompSQL = "SELECT Competence FROM Competence WHERE Competence = ?";
                    try {
                        preparedStatement = connection.prepareStatement(VerifCompSQL);
                        preparedStatement.setString(1, Competence);
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                            String AjoutCompSQL = "INSERT INTO Competence (Competence) VALUE (?)";
                            try {
                                preparedStatement = connection.prepareStatement(AjoutCompSQL);
                                preparedStatement.setString(1, Competence);
                                preparedStatement.execute();
                                JOptionPane.showMessageDialog(null, "Competence Ajouter avec Succée");
                                UpdateTable();
                                clearTextfield();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Cette competence est déja dans la liste");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Veuille rentre votre competence en MAJ, elle doit contenir uniquement des MAJ, chiffre ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Si vous voulez ajouter une competence veuillez ajouter du contenu dans competance svp");
            }
        }
        if (CheckBoxPersone.isSelected()) {
            String NomP = TextFieldNom.getText();
            String PrenomP = TextFieldPrenom.getText();
            String TelP = TextFieldTel.getText();
            String EmailP = TextFieldEmail.getText();
            String Entreprise = TextFieldSelectEntreprise.getText();
            String Competence = ChoixCompetencePersonne.getText();
            if (NomP.length() > 0) {
                if (NomPIsValide(NomP)) {
                    if (PrenomP.length() > 0) {
                        if (NomPIsValide(PrenomP)) {
                            if (TelP.length() > 0) {
                                if (TelIsValide(TelP)) {
                                    if (EmailP.length() > 0) {
                                        if (mailisValid(EmailP)) {
                                            if (Entreprise.length() > 0) {
                                                if(Competence.length()>0){

                                                    String EntrepriseSelectSQL = "SELECT * FROM  entreprise WHERE Raison_Sociale = ?";
                                                    try {
                                                        preparedStatement = connection.prepareStatement(EntrepriseSelectSQL);
                                                        preparedStatement.setString(1, Entreprise);
                                                        resultSet = preparedStatement.executeQuery();
                                                        if (resultSet.next()) {
                                                            String idEntreprise = resultSet.getString("idEntreprise");
                                                            String SelectCompetenceZSQL = "SELECT idcompetence FROM competence Where competence = ?";
                                                            try {
                                                                preparedStatement = connection.prepareStatement(SelectCompetenceZSQL);
                                                                preparedStatement.setString(1, Competence);
                                                                resultSet = preparedStatement.executeQuery();
                                                                if(resultSet.next()){
                                                                    String idCompetence = resultSet.getString("idCompetence");
                                                                    String VerifPersonneSQL = "SELECT NomPersone,PrenomPersone,TelPersone,EmailPersonne FROM  persone WHERE NomPersone = ? AND PrenomPersone = ? AND TelPersone = ? AND EmailPersonne = ?";
                                                                    try {
                                                                        preparedStatement = connection.prepareStatement(VerifPersonneSQL);
                                                                        preparedStatement.setString(1, NomP);
                                                                        preparedStatement.setString(2, PrenomP);
                                                                        preparedStatement.setString(3, TelP);
                                                                        preparedStatement.setString(4, EmailP);
                                                                        resultSet = preparedStatement.executeQuery();
                                                                        if (!resultSet.next()) {
                                                                            String AjouterPErsoneSQL = "INSERT INTO persone (NomPersone,PrenomPersone,TelPersone,EmailPersonne, idEntreprise, idCompetence) VALUE (?,?,?,?,?,?)";
                                                                            try {
                                                                                preparedStatement = connection.prepareStatement(AjouterPErsoneSQL);
                                                                                preparedStatement.setString(1, NomP);
                                                                                preparedStatement.setString(2, PrenomP);
                                                                                preparedStatement.setString(3, TelP);
                                                                                preparedStatement.setString(4, EmailP);
                                                                                preparedStatement.setString(5, idEntreprise);
                                                                                preparedStatement.setString(6, idCompetence);
                                                                                preparedStatement.execute();
                                                                                JOptionPane.showMessageDialog(null, "Personne Ajouter avec Succée");
                                                                                UpdateTable();
                                                                                clearTextfield();
                                                                            } catch (SQLException throwables) {
                                                                                throwables.printStackTrace();
                                                                            } catch (HeadlessException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "La personne existe déja");
                                                                            clearTextfield();
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
                                                }else{
                                                    JOptionPane.showMessageDialog(null,"competence Obligatoire");
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(null, "VEuillez selection une entreprise");
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Veuillez rentrez une email valide");
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veuillez saisir l'Email");
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Verifier le numero de telephone");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Veuillez rentrez un numero  de Telephone");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Rentrez le prenom en MAJ espace autoriser ");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Rentrez le prenom de l'interveant");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Rentrez le nom en MAJ espace autoriser ");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Rentrez le nom de l'interveant");
            }

        }
        if(CheckBoxkEvententreprise.isSelected()){
            String SelectEvent =  TextFieldSelectEvent.getText();
            String NomEnptreprise =  TextFieldSelectEntreprise.getText();
            if(SelectEvent.length()>0 && NomEnptreprise.length()>0){
                String getidEventSQL = "SELECT idevenement FROM  evenement WHERE nomEvenement = ?";
                try {
                    preparedStatement = connection.prepareStatement(getidEventSQL);
                    preparedStatement.setString(1, SelectEvent);
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) {
                        String idevenement = resultSet.getString("idevenement");
                        String getidEntrepriseSQL = "SELECT idEntreprise FROM  entreprise WHERE Raison_Sociale = ?";
                        try {
                            preparedStatement = connection.prepareStatement(getidEntrepriseSQL);
                            preparedStatement.setString(1, NomEnptreprise);
                            resultSet = preparedStatement.executeQuery();
                            if(resultSet.next()){
                                String idEntreprise = resultSet.getString("idEntreprise");
                                String verifSQL  ="SELECT idEvenementEntreprise FROM evenemntentreprise WHERE idEntreprise = '"+idEntreprise+"' AND idevenement= '"+idevenement+"' ";
                                try{
                                    preparedStatement = connection.prepareStatement(verifSQL);
                                    resultSet = preparedStatement.executeQuery();;
                                    if(!resultSet.next()){
                                        String AjoutevenententrepriseSQL ="INSERT INTO evenemntentreprise (idEntreprise,idEvenement) VALUE (?,?)";
                                        try{
                                            preparedStatement = connection.prepareStatement(AjoutevenententrepriseSQL);
                                            preparedStatement.setString(1, idEntreprise);
                                            preparedStatement.setString(2, idevenement);
                                            preparedStatement.execute();

                                            JOptionPane.showMessageDialog(null, "Ajout Reussit");
                                            UpdateTable();


                                            clearTextfield();
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        } catch (HeadlessException e) {
                                            e.printStackTrace();
                                        }

                                    }else{
                                        JOptionPane.showMessageDialog(null, "Cette entreprise est déja associer a cette evenement");
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

            }else{
                JOptionPane.showMessageDialog(null,"Veuillez Selectionne un Evenment et Une entrprise");
            }
        }
    }

    public void modifierPersone() {
        String NomP = TextFieldNom.getText();
        String PrenomP = TextFieldPrenom.getText();
        String TelP = TextFieldTel.getText();
        String EmailP = TextFieldEmail.getText();
        String Entreprise = TextFieldSelectEntreprise.getText();
        String LabelNomP = LabelNom.getText();
        String LabelPrenomP = LabelPrenom.getText();
        String LabelTelP = LabelTel.getText();
        String LabelEmailP = LabelEmail.getText();
        String Competence =ChoixCompetencePersonne.getText();
        if (NomP.length() > 0) {
            if (NomPIsValide(NomP)) {
                if (PrenomP.length() > 0) {
                    if (NomPIsValide(PrenomP)) {
                        if (TelP.length() > 0) {
                            if (TelIsValide(TelP)) {
                                if (EmailP.length() > 0) {
                                    if (mailisValid(EmailP)) {
                                        if (Entreprise.length() > 0) {
                                          if(Competence.length()>0){
                                              String SelectCompetenceZSQL = "SELECT idcompetence FROM competence Where competence = ?";
                                              try {
                                                  preparedStatement = connection.prepareStatement(SelectCompetenceZSQL);
                                                  preparedStatement.setString(1, Competence);
                                                  resultSet = preparedStatement.executeQuery();
                                                  if(resultSet.next()) {
                                                      String idCompetence = resultSet.getString("idCompetence");
                                                      String EntrepriseSelectSQL = "SELECT * FROM  entreprise WHERE Raison_Sociale = ?";
                                                      try {
                                                          preparedStatement = connection.prepareStatement(EntrepriseSelectSQL);
                                                          preparedStatement.setString(1, Entreprise);
                                                          resultSet = preparedStatement.executeQuery();
                                                          if (resultSet.next()) {

                                                              String idEntreprise = resultSet.getString("idEntreprise");
                                                              String IdPErsonEsql = "SELECT * FROM  persone WHERE NomPersone = ? AND PrenomPersone = ? AND TelPersone = ? AND EmailPersonne = ?";
                                                              try {
                                                                  preparedStatement = connection.prepareStatement(IdPErsonEsql);
                                                                  preparedStatement.setString(1, LabelNomP);
                                                                  preparedStatement.setString(2, LabelPrenomP);
                                                                  preparedStatement.setString(3, LabelTelP);
                                                                  preparedStatement.setString(4, LabelEmailP);
                                                                  resultSet = preparedStatement.executeQuery();
                                                                  if (resultSet.next()) {
                                                                      String idPersone = resultSet.getString("idPersone");
                                                                      System.out.println(idPersone);
                                                                      String VerifPersonneSQL = "UPDATE persone SET NomPersone = '" + NomP + "' , " +
                                                                              "PrenomPersone = '" + PrenomP + "' , " +
                                                                              "TelPersone = '" + TelP + "' , " +
                                                                              "EmailPersonne = '" + EmailP + "' ,"+
                                                                              "IdEntreprise = '"+idEntreprise+"' ,"+
                                                                              " idCompetence = '"+idCompetence+"' WHERE idPersone = '"+ idPersone + "' ";
                                                                      preparedStatement = connection.prepareStatement(VerifPersonneSQL);
                                                                      preparedStatement.execute();

                                                                      JOptionPane.showMessageDialog(null, "La modification de " + PrenomP + " a etait prise en compte");
                                                                      UpdateTable();
                                                                      clearTextfield();
                                                                  }else {

                                                                  }
                                                              } catch (SQLException throwables) {
                                                                  throwables.printStackTrace();
                                                              }
                                                          }else{

                                                          }

                                                      } catch (SQLException throwables) {
                                                          throwables.printStackTrace();
                                                      }

                                                  }
                                              } catch (SQLException throwables) {
                                                  throwables.printStackTrace();
                                              }

                                          }else{
                                              JOptionPane.showMessageDialog(null, "Veuillez saisir une competence");
                                          }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "VEuillez selection une entreprise");
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veuillez rentrez une email valide");
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Veuillez saisir l'Email");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Verifier le numero de telephone");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Veuillez rentrez un numero  de Telephone");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Rentrez le prenom en MAJ espace autoriser ");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Rentrez le prenom de l'interveant");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Rentrez le nom en MAJ espace autoriser ");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Rentrez le nom de l'interveant");
        }

    }
    public void ModiferCompetence(){
        String Competence = TextFieldCompetence.getText();
        String LabelCompe = LabelComp.getText();
        if (Competence.length() > 0) {
            if (TextMajIsValide(Competence)) {
                String EntrepriseSelectSQL = "SELECT idCompetence FROM  competence WHERE competence = ?";
                try {
                    preparedStatement = connection.prepareStatement(EntrepriseSelectSQL);
                    preparedStatement.setString(1, LabelCompe);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String IdCompetence = resultSet.getString("idcompetence");
                        String VErifUnsiqueSQL = "SELECT idCompetence FROM  competence WHERE competence = ?";

                        preparedStatement = connection.prepareStatement(VErifUnsiqueSQL);
                        preparedStatement.setString(1, Competence);
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {

                            String AjoutCompSQL = "UPDATE competence SET Competence = '"+Competence+"' WHERE idCompetence = '"+IdCompetence+"'";
                            try {
                                preparedStatement = connection.prepareStatement(AjoutCompSQL);
                                preparedStatement.execute();
                                JOptionPane.showMessageDialog(null, "Competence modifer avec Succée");
                                UpdateTable();
                                clearTextfield();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Cette Competenceexiste déja");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Cette competence est déja dans la liste");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Veuille rentre votre competence en MAJ, elle doit contenir uniquement des MAJ, chiffre ");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Si vous voulez ajouter une competence veuillez ajouter du contenu dans competance svp");
        }
    }
    public void SupprimerPersonne(){
        String SupprimerPersonneSql = "DELETE FROM  persone WHERE NomPersone = ? AND PrenomPersone = ? AND TelPersone = ? AND EmailPersonne = ?";
        try{
            preparedStatement = connection.prepareStatement(SupprimerPersonneSql);
            preparedStatement.setString(1, TextFieldNom.getText());
            preparedStatement.setString(2, TextFieldPrenom.getText());
            preparedStatement.setString(3, TextFieldTel.getText());
            preparedStatement.setString(4, TextFieldEmail.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "la persone " + TextFieldNom.getText() + " a été suprimer");
            UpdateTable();
            clearTextfield();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void SupprimerCompetence(){
        String SupprimerComeptenceSql = "DELETE FROM  competence WHERE competence = ? ";
        try{
            preparedStatement = connection.prepareStatement(SupprimerComeptenceSql);
            preparedStatement.setString(1, TextFieldCompetence.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "la competence " + TextFieldCompetence.getText() + " a été suprimer");
            UpdateTable();
            clearTextfield();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private void ButtonRechercheOnAction(ActionEvent event) {
        labelCatégorierecherche.setText(ChoiceBoxRecherche.getValue());
        TextFieldRecherche.setDisable(false);
        PersonneRechercher();

    }
    @FXML
    ObservableList<PersonneNomPrenom> ListRechecher;
    void PersonneRechercher(){
        ListRechecher = getDataPersone();
        TableViewPersone.setItems(ListRechecher);
        FilteredList<PersonneNomPrenom> filteredData = new FilteredList<>(ListRechecher, b -> true);
        TextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if(labelCatégorierecherche.getText().equals("Nom")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getNomPersone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            } else if(labelCatégorierecherche.getText().equals("Prenom")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getPrenomPersone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }else if(labelCatégorierecherche.getText().equals("Numero")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getTelPersone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }
            else if(labelCatégorierecherche.getText().equals("Entreprise")) {
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
            }else if(labelCatégorierecherche.getText().equals("Competence")) {
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
            }
        });
        SortedList<PersonneNomPrenom> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableViewPersone.comparatorProperty());
        TableViewPersone.setItems(sortedData);
    }

    @FXML
    void SupprimerEvenementEntreprise(){
        String SelectEvent =   TextFieldSelectEvent.getText();
        String NomEnptreprise = TextFieldSelectEntreprise.getText();
        String getidEventSQL = "SELECT idevenement FROM  evenement WHERE nomEvenement = '"+SelectEvent+"'";
        try {
            preparedStatement = connection.prepareStatement(getidEventSQL);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String idevenement = resultSet.getString("idevenement");
                String getidEntrepriseSQL = "SELECT idEntreprise FROM  entreprise WHERE Raison_Sociale = '"+NomEnptreprise+"'";
                try {
                    preparedStatement = connection.prepareStatement(getidEntrepriseSQL);
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        String idEntreprise = resultSet.getString("idEntreprise");
                        String verifSQL  ="SELECT idEvenementEntreprise FROM evenemntentreprise WHERE idEntreprise = '"+idEntreprise+"' AND idevenement= '"+idevenement+"' ";
                        try{
                            preparedStatement = connection.prepareStatement(verifSQL);
                            resultSet = preparedStatement.executeQuery();;

                            if(resultSet.next()){
                                String idEvenementEntreprise = resultSet.getString("idEvenementEntreprise");
                                String SupprimervenententrepriseSQL ="DELETE FROM evenemntentreprise WHERE idEvenementEntreprise = '"+idEvenementEntreprise+"'";
                                try{
                                    preparedStatement = connection.prepareStatement(SupprimervenententrepriseSQL);
                                    preparedStatement.execute();

                                    JOptionPane.showMessageDialog(null, "Suppression Reussit");
                                    UpdateTable();
                                    clearTextfield();
                                    TableViewAfficherEvent.setVisible(false);
                                    TableViewEntreprise.setVisible(false);
                                    TextFieldSelectEvent.setVisible(false);
                                    TextFieldSelectEntreprise.setVisible(false);

                                    clearTextfield();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                } catch (HeadlessException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                JOptionPane.showMessageDialog(null, "ERREUR");
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        AnchorPaneAddPersonne.setDisable(true);
        btnMenuAdmin.setVisible(false);
    }

    public void cachermenue(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(false);
        AnchorPaneAddPersonne.setDisable(false);
        btnMenuAdmin.setVisible(true);
    }


}