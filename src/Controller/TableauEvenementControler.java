package Controller;

import AutreClasse.Competence;
import AutreClasse.ConexionBDD;
import AutreClasse.Evenement;

import AutreClasse.TypeEvenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.Button;

public class TableauEvenementControler implements Initializable {

    @FXML
    private Button BouttonPageEntreprise;
    @FXML
    private TableView<Evenement> TableauEvenement;
    @FXML
    private TableColumn<Evenement, String> ColumnNom;
    @FXML
    private TableColumn<Evenement, String> ColumnCodePostal;
    @FXML
    private TableColumn<Evenement, String> ColumnVille;
    @FXML
    private TableColumn<Evenement, String> ColumnAdresse;
    @FXML
    private TableColumn<Evenement, String> ColumnDate;
    @FXML
    private TableColumn<Evenement, String> ColumnHeure;
    @FXML
    private TableColumn<Evenement, String> ColumnSalle;
    @FXML
    private TableColumn<Evenement, String> ColumnBatiment;
    @FXML
    private TableColumn<Evenement, String> ColumnDureeCours;
    @FXML
    private TableColumn<Evenement, String> ColumnProf;
    @FXML
    private TableColumn<Evenement, String> ColumnDureeConference;
    @FXML
    private TableColumn<Evenement, String> ColumnDescriptionAutrevenement;
    @FXML
    private TableColumn<Evenement, String> ColumnTypeEvenement;
    @FXML
    private TableColumn<Evenement, String> ColumnCompetence;
    @FXML
    private AnchorPane AnchorPane2;
    @FXML
    private AnchorPane AnchorPane1;
    @FXML
    private Button ButtonMenu;
    @FXML
    private Label LabelChoixVueTableau;
    @FXML
    private TextField TextFieldRecherche;
    @FXML
    private  ChoiceBox<String> ChoiceBoxRecherche;
    @FXML
    private TableColumn<Competence, String> ColumnCompétenceTComp;
    @FXML
    private TableView<Competence> TableauCompetence;
    @FXML
    private TableColumn<TypeEvenement, String> ColumnTypeEvenementTTypeEvent;
    @FXML
    private TableView<TypeEvenement> TableauTypeEvenement;
    @FXML
    private Label LabelCompetence;
    @FXML
    private Label LabelTypeEvenement;
    @FXML
    private TextField TextFieldNomEvenement;
    @FXML
    private TextField TextFieldVilleEvenement;
    @FXML
    private TextField TextFieldDateEvenement;
    @FXML
    private TextField TextFieldIntervenant;
    @FXML
    private TextField TextFieldBatimentCours;
    @FXML
    private TextField TextFieldDureeConf;
    @FXML
    private TextField TextFieldAutreEvenement;
    @FXML
    private TextField TextFieldAdresseEvenement;
    @FXML
    private TextField TextFieldHeureEvenement;
    @FXML
    private TextField TextFieldSalleCours;
    @FXML
    private TextField TextFieldDureeCours;
    @FXML
    private TextField TextFieldCodePostalEvenement;
    @FXML
    private ChoiceBox<String>ChoiceBoxActiion;
    /*
 Connexion classe BDD
  */
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public TableauEvenementControler() {
        connection = ConexionBDD.connectdb();
    }
    ObservableList<Evenement> listEvenement;

    public static  ObservableList<Evenement>  getDataEvenement(){
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Evenement> listE = FXCollections.observableArrayList();


        try {
            String EvenementSql = "SELECT * FROM evenement E LEFT OUTER JOIN cours C ON C.idCours=E.idEvenement LEFT OUTER JOIN conference Conf ON Conf.idEvenement=E.idEvenement LEFT OUTER JOIN typeevenement T ON T.idtypeEvenement=E.idtypeEvenement LEFT OUTER JOIN autreevenement A ON A.idEvenement = E.idEvenement LEFT OUTER JOIN competence Comp ON Comp.idCompetence=E.idCompetence";
            PreparedStatement ps = connection.prepareStatement(EvenementSql);
            ResultSet rs = ps.executeQuery();

            String PersonneEvenementSQL = "SELECT P.NomPersone  FROM  persone P INNER JOIN evenement E ON P.idPersone=E.idPersone";
            PreparedStatement psPersonneEvenement = connection.prepareStatement(PersonneEvenementSQL);
            ResultSet rsPersonneEvenement = psPersonneEvenement.executeQuery();


            while (rs.next() && rsPersonneEvenement.next()) {
                listE.add(new Evenement(
                        rs.getString("NomEvenement"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"),
                        rs.getString("Adresse"),
                        rs.getString("Date"),
                        rs.getString("Heure"),
                        rsPersonneEvenement.getString("NomPersone"),
                        rs.getString("salle"),
                        rs.getString("Batiment"),
                        rs.getString("dureeCours"),
                        rs.getString("DureeConference"),
                        rs.getString("DescriptionAutreEvenement"),
                        rs.getString("TypeEvenement"),
                        rs.getString("Competence")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listE;

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

    ObservableList<TypeEvenement> listTypeEvenement;

    public static ObservableList<TypeEvenement> getDataTypeEvenement() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<TypeEvenement> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT typeEvenement FROM TypeEvenement";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TypeEvenement(rs.getString("TypeEvenement")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        ColumnNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvenement"));
        ColumnCodePostal.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CodePostal"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Ville"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Adresse"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Date"));
        ColumnProf.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomPersone"));
        ColumnHeure.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Heure"));
        ColumnSalle.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Salle"));
        ColumnBatiment.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Batiment"));
        ColumnDureeCours.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dureeCours"));
        ColumnDureeConference.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DureeConference"));
        ColumnDescriptionAutrevenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DescriptionAutreEvenement"));
        ColumnTypeEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("TypeEvenement"));
        ColumnCompetence.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Competence"));

        listEvenement = getDataEvenement();
        TableauEvenement.setItems(listEvenement);

        ColumnCompétenceTComp.setCellValueFactory((new PropertyValueFactory<Competence, String>("Competence")));
        listCompetence = getDataCompetence();
        TableauCompetence.setItems(listCompetence);

        ColumnTypeEvenementTTypeEvent.setCellValueFactory((new PropertyValueFactory<TypeEvenement, String>("TypeEvenement")));
        listTypeEvenement = getDataTypeEvenement();
        TableauTypeEvenement.setItems(listTypeEvenement);

        loadData();
        loadDataChoiceBoxActiion();


    }
    public void UpdateTable(){
        ColumnNom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvenement"));
        ColumnCodePostal.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CodePostal"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Ville"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Adresse"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Date"));
        ColumnProf.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomPersone"));
        ColumnHeure.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Heure"));
        ColumnSalle.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Salle"));
        ColumnBatiment.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Batiment"));
        ColumnDureeCours.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dureeCours"));
        ColumnDureeConference.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DureeConference"));
        ColumnDescriptionAutrevenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DescriptionAutreEvenement"));
        ColumnTypeEvenement.setCellValueFactory(new PropertyValueFactory<Evenement, String>("TypeEvenement"));
        ColumnCompetence.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Competence"));
        listEvenement = getDataEvenement();
        TableauEvenement.setItems(listEvenement);recherche_Evenement();


    }

    public void AjouterEvenement(ActionEvent actionEvent) {
    }

    public void EditEvenement(ActionEvent actionEvent) {
    }

    public void SupprimerEvenement(ActionEvent actionEvent) {
    }

    public void cachermenue(ActionEvent actionEvent) {
    }

    public void BtnRechercheAtion(ActionEvent actionEvent) {

    }
    int index = -1;
    @FXML
    public void getSelectCompetence(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauCompetence.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        LabelCompetence.setText(ColumnCompétenceTComp.getCellData(index).toString());
    }

    @FXML
    public void getSelectTypeEvenement(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauTypeEvenement.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        LabelTypeEvenement.setText(ColumnTypeEvenementTTypeEvent.getCellData(index).toString());
    }

    ObservableList listeActionTableau = FXCollections.observableArrayList();
    public void loadDataChoiceBoxActiion(){
        listeActionTableau.removeAll(listeActionTableau);
        String a = "Cours";
        String b = "Conference";
        String c = "Evenement";

        listeActionTableau.addAll(a,b, c);
        ChoiceBoxActiion.getItems().addAll(listeActionTableau);
    }
    @FXML
    private Label labelChoiceBoxActiion ;
    @FXML
    private void ChoiceBoxActionOnAction(ActionEvent event) {
        labelChoiceBoxActiion.setText(ChoiceBoxActiion.getValue());

    }
    private void TextFieldVisivbiliti(){
        TextFieldNomEvenement.setVisible(true);
        TextFieldVilleEvenement.setVisible(true);
        TextFieldDateEvenement.setVisible(true);
        TextFieldCodePostalEvenement.setVisible(true);
        TextFieldIntervenant.setVisible(true);
        LabelTypeEvenement.setVisible(true);
        LabelCompetence.setVisible(true);

    }
    private void TextFieldClear(){
        TextFieldNomEvenement.clear();
        TextFieldVilleEvenement.clear();
        TextFieldDateEvenement.clear();
        TextFieldCodePostalEvenement.clear();
        TextFieldIntervenant.clear();
        LabelTypeEvenement.setText("");
        LabelCompetence.setText("");
        TextFieldSalleCours.clear();
        TextFieldDureeCours.clear();
        TextFieldBatimentCours.clear();
        TextFieldAutreEvenement.clear();
        TextFieldDureeConf.clear();


    }
    @FXML
    public void getSelectEvenment(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauEvenement .getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TextFieldClear();
        labelChoiceBoxActiion.setText(ColumnTypeEvenement.getCellData(index).toString());

        if(labelChoiceBoxActiion.getText().equals("Cours")){

            TextFieldVisivbiliti();
            TextFieldSalleCours.setVisible(true);
            TextFieldDureeCours.setVisible(true);
            TextFieldBatimentCours.setVisible(true);
            TextFieldDureeConf.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);

            TextFieldNomEvenement.setText(ColumnNom.getCellData(index).toString());
            TextFieldVilleEvenement.setText(ColumnVille.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            TextFieldIntervenant.setText(ColumnProf.getCellData(index).toString());
            LabelTypeEvenement.setText(ColumnTypeEvenement.getCellData(index).toString());
            LabelCompetence.setText(ColumnCompetence.getCellData(index).toString());

            TextFieldSalleCours.setText(ColumnSalle.getCellData(index).toString());
            TextFieldDureeCours.setText(ColumnDureeCours.getCellData(index).toString());
            TextFieldBatimentCours.setText(ColumnBatiment.getCellData(index).toString());


        }
        else if(labelChoiceBoxActiion.getText().equals("Conference")){

            TextFieldVisivbiliti();
            TextFieldDureeConf.setVisible(true);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);

            TextFieldNomEvenement.setText(ColumnNom.getCellData(index).toString());
            TextFieldVilleEvenement.setText(ColumnVille.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            TextFieldIntervenant.setText(ColumnProf.getCellData(index).toString());
            LabelTypeEvenement.setText(ColumnTypeEvenement.getCellData(index).toString());
            LabelCompetence.setText(ColumnCompetence.getCellData(index).toString());

            TextFieldDureeConf.setText(ColumnDureeConference.getCellData(index).toString());

        }
        else if(labelChoiceBoxActiion.getText().equals("AutreEvenement")){


            TextFieldVisivbiliti();
            TextFieldAutreEvenement.setVisible(true);
            TextFieldDureeConf.setVisible(false);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);

            TextFieldNomEvenement.setText(ColumnNom.getCellData(index).toString());
            TextFieldVilleEvenement.setText(ColumnVille.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            TextFieldIntervenant.setText(ColumnProf.getCellData(index).toString());
            LabelTypeEvenement.setText(ColumnTypeEvenement.getCellData(index).toString());
            LabelCompetence.setText(ColumnCompetence.getCellData(index).toString());

            TextFieldAutreEvenement.setText(ColumnDescriptionAutrevenement.getCellData(index).toString());

        }else{
            System.out.println("selectioner un truc");
        }

    }


    public void BouttonPageEntrepriseOnActionOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)  BouttonPageEntreprise.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/TableauEntreprise.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Entreprise");
    }

    public void BtnToutOnAction(ActionEvent actionEvent) {
        afficherColumnPrincipale();
        ColumnSalle.setVisible(true);
        ColumnBatiment.setVisible(true);
        ColumnDureeCours.setVisible(true);
        ColumnProf.setVisible(true);
        ColumnDureeConference.setVisible(true);

        ColumnDescriptionAutrevenement.setVisible(true);

        ColumnTypeEvenement.setVisible(true);
        ColumnCompetence.setVisible(true);
        UpdateTable();

    }

    public void BtnConfOnAction(ActionEvent actionEvent) {
        afficherColumnPrincipale();
        ColumnSalle.setVisible(false);
        ColumnBatiment.setVisible(false);
        ColumnDureeCours.setVisible(false);
        ColumnProf.setVisible(false);
        ColumnDureeConference.setVisible(true);

        ColumnDescriptionAutrevenement.setVisible(false);

        LabelChoixVueTableau.setText("conference");
        recherche_Type();

    }

    public void BtnAutreOnAction(ActionEvent actionEvent) {
        afficherColumnPrincipale();
        ColumnSalle.setVisible(false);
        ColumnBatiment.setVisible(false);
        ColumnDureeCours.setVisible(false);
        ColumnProf.setVisible(false);
        ColumnDureeConference.setVisible(false);
        ColumnDescriptionAutrevenement.setVisible(true);

        LabelChoixVueTableau.setText("autreevenement");
        recherche_Type();

    }

    public void BtnCoursOnAction(ActionEvent actionEvent) {
        afficherColumnPrincipale();
        ColumnSalle.setVisible(true);
        ColumnBatiment.setVisible(true);
        ColumnDureeCours.setVisible(true);
        ColumnProf.setVisible(true);
        ColumnDureeConference.setVisible(false);
        LabelChoixVueTableau.setText("cours");

        ColumnDescriptionAutrevenement.setVisible(false);

        recherche_Type();


    }

    private void afficherColumnPrincipale(){
        ColumnNom.setVisible(true);
        ColumnCodePostal.setVisible(true);
        ColumnVille.setVisible(true);
        ColumnAdresse.setVisible(true);
        ColumnDate.setVisible(true);
        ColumnHeure.setVisible(true);
        ColumnTypeEvenement.setVisible(true);
        ColumnCompetence.setVisible(true);
    }

    public void affichermenue(ActionEvent actionEvent) {
        AnchorPane1.setVisible(true);
        AnchorPane2.setVisible(true);
        ButtonMenu.setVisible(false);
    }

    ObservableList listFiltreRecherche = FXCollections.observableArrayList();
    public void loadData(){
        listFiltreRecherche.removeAll(listFiltreRecherche);
        String a = "Nom Evenement";
        String e = "Ville";
        String b = "Date";
        String c = "Competence";
        String d = "Tout";
        listFiltreRecherche.addAll(a, e, b, c, d);
        ChoiceBoxRecherche.getItems().addAll(listFiltreRecherche);
    }


    ObservableList<Evenement> ListEvenementRecherche ;
    @FXML
    void recherche_Type() {

        ListEvenementRecherche = getDataEvenement();
        TableauEvenement.setItems(ListEvenementRecherche);

        String label = LabelChoixVueTableau.getText();
        FilteredList<Evenement> filteredData = new FilteredList<>(ListEvenementRecherche, b -> true);
        if (LabelChoixVueTableau.getText().equals(label)) {
            filteredData.setPredicate(person -> {

                if (person.getTypeEvenement().toLowerCase().indexOf(label) != -1) {
                    return true;
                } else
                    return false;

            });
        }
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableauEvenement.comparatorProperty());
        TableauEvenement.setItems(sortedData);


    }

    ObservableList<Evenement> ListRecherche ;
    @FXML
    private  Label labelCatégorierecherche;
    @FXML
    private void ButtonRechercheOnAction(ActionEvent event) {
        labelCatégorierecherche.setText(ChoiceBoxRecherche.getValue());
        recherche_Evenement();
    }
    @FXML
    void recherche_Evenement() {
        ListRecherche = getDataEvenement();
        TableauEvenement.setItems(ListRecherche);

        FilteredList<Evenement> filteredData = new FilteredList<>(ListRecherche, b -> true);
        TextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if(labelCatégorierecherche.getText().equals("") ||labelCatégorierecherche.getText().equals("Tout")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getNomEvenement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getCodePostal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getHeure().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getNomPersone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    if (person.getTypeEvenement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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

                    if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
            }else if(labelCatégorierecherche.getText().equals("Nom Evenement")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getNomEvenement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableauEvenement.comparatorProperty());
        TableauEvenement.setItems(sortedData);
    }


    public void BtnRechercheOnAction(ActionEvent actionEvent) {
        ChoiceBoxRecherche.setVisible(true);
        TextFieldRecherche.setVisible(true);
        recherche_Evenement();
    }
}

