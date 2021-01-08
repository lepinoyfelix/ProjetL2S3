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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import java.util.StringTokenizer;

import static sun.util.logging.LoggingSupport.log;


public class AccueilEtudiantController implements Initializable{

    @FXML
    private Label LabelDeBienVenu;
    @FXML
    private Button Bonjour;
    @FXML
    private  WebView viewWeb;
    @FXML
    private TextField TextFieldRecherche;
    @FXML
    private ComboBox<String> ChoiceBoxRecherche;
    @FXML
    private TableView<Conference> TableViewConference;
    @FXML
    private TableColumn<Conference, String> ColumnConference;
    @FXML
    private TableColumn<Conference, String> ColumnDate;
    @FXML
    private TableColumn<Conference, String> ColumnHeure;
    @FXML
    private TableColumn<Conference, String> ColumnVille;
    @FXML
    private TableColumn<Conference, String> ColumnAdresse;
    @FXML
    private Label labelCatégorierecherche;
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


    /*
 Connexion classe BDD
  */
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public AccueilEtudiantController() {

        connection = ConexionBDD.connectdb();
    }

    ObservableList<Conference> listConference;

    public static  ObservableList<Conference>  getDataConference(){
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Conference> listE = FXCollections.observableArrayList();
        try{
            String ConferenceSql ="SELECT * FROM evenement E INNER JOIN typeevenement Typ ON E.idtypeEvenement=Typ.idtypeEvenement WHERE Typ.typeEvenement LIKE 'conference'";
            PreparedStatement ps = connection.prepareStatement(ConferenceSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listE.add(new Conference(
                        rs.getString("NomEvenement"),
                        rs.getString("Ville"),
                        rs.getString("Adresse"),
                        rs.getString("Date"),
                        rs.getString("Heure")
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listE;
    }

    public void BonjourOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Connexion");

    }
    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public void NomPrenomConnexion(String text){
        String Separateur = "@";
        String Separateur2 = ".";
        StringTokenizer ST = new StringTokenizer(text, Separateur);
        String A = ST.nextToken();
        StringTokenizer STNomPrenom = new StringTokenizer(A, Separateur2);
        String Prenom = upperCaseFirst(STNomPrenom.nextToken());
        String Nom = upperCaseFirst(STNomPrenom.nextToken());
        LabelDeBienVenu.setText("Bonjour "+Prenom+" "+Nom+" voici les Dernier actualité");
    }

    ObservableList listFiltreRecherche = FXCollections.observableArrayList();

    public void loadData(){
        listFiltreRecherche.removeAll(listFiltreRecherche);
        String a = "Conference";
        String e = "Ville";
        String b = "Date";
        String c = "Heure";
        listFiltreRecherche.addAll(a, e, b, c);
        ChoiceBoxRecherche.getItems().addAll(listFiltreRecherche);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ColumnConference.setCellValueFactory(new PropertyValueFactory<Conference, String>("NomEvenement"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<Conference, String>("Date"));
        ColumnHeure.setCellValueFactory(new PropertyValueFactory<Conference, String>("Heure"));
        ColumnVille.setCellValueFactory(new PropertyValueFactory<Conference, String>("Ville"));
        ColumnAdresse.setCellValueFactory(new PropertyValueFactory<Conference, String>("Adresse"));



        listConference = getDataConference();
        TableViewConference.setItems(listConference);

        final WebEngine web = viewWeb.getEngine();
        String urlWeb="https://www.univ-tours.fr/campus/actualite-etudiante-1";
        web.load(urlWeb);
        NomPrenomConnexion(User.Mail);

        loadData();
    }
    @FXML
    private void ComboBoxOnAction(ActionEvent actionEvent){
        labelCatégorierecherche.setText(ChoiceBoxRecherche.getValue());
        Conference_search();
        TextFieldRecherche.setDisable(false);
    }
    ObservableList<Conference> ListRechecher;

    @FXML
    void Conference_search() {
        ListRechecher = getDataConference();
        TableViewConference.setItems(ListRechecher);


        FilteredList<Conference> filteredData = new FilteredList<>(ListRechecher, b -> true);
        TextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if(labelCatégorierecherche.getText().equals("Conference")){
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
            }else if(labelCatégorierecherche.getText().equals("Ville")) {
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
            } else if(labelCatégorierecherche.getText().equals("Date")) {
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
            }else if(labelCatégorierecherche.getText().equals("Heure")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getHeure().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }
        });
        SortedList<Conference> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableViewConference.comparatorProperty());
        TableViewConference.setItems(sortedData);
    }

    public void BtnMenueOnAction(ActionEvent actionEvent){
        AnchorPaneMenu.setVisible(true);
        Disabletrue();
    }
    public void btnCloseMenuAdminOnAction(ActionEvent actionEvent){
        AnchorPaneMenu.setVisible(false);
        DisableFalse();
    }

    public void Disabletrue(){
        Bonjour.setDisable(true);
        TableViewConference.setDisable(true);
        ChoiceBoxRecherche.setDisable(true);
        viewWeb.setDisable(true);
        BtnMenue.setDisable(true);
        BtnMenue.setVisible(false);
        TextFieldRecherche.setDisable(true);

    }
    public void DisableFalse(){
        Bonjour.setDisable(false);
        TableViewConference.setDisable(false);
        ChoiceBoxRecherche.setDisable(false);
        viewWeb.setDisable(false);
        BtnMenue.setDisable(false);
        BtnMenue.setVisible(true);
        TextFieldRecherche.setDisable(false);

    }
    public void AccueilEtudiant() throws Exception {
        Stage stage = (Stage) btnAccueilEtudiant.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AccueilEtudiant.fxml")));
        stage.show();
        stage.setTitle("Accueil  Etudiant");
        stage.setScene(scene);
    }

    public void Stagiaire() throws  Exception{
        Stage stage = (Stage) btnStagiaire.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Stagiaire.fxml")));
        stage.show();
        stage.setTitle("Stagiaire");
        stage.setScene(scene);
    }
    public void Stage(ActionEvent event) throws Exception {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Stage.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Stage");

    }

}