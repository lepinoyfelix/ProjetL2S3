package Controller;

import AutreClasse.ConexionBDD;
import AutreClasse.Stagiaire;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StageController implements Initializable {
    @FXML
    private AnchorPane AnchorPaneMenu;
    @FXML
    private Button Bonjour;
    @FXML
    private Button BtnMenue;
    @FXML
    private Button btnCloseMenuAdmin;
    @FXML
    private Button btnAccueilEtudiant;
    @FXML
    private Button btnStagiaire;
    @FXML
    private ComboBox<String> ComboBoxRecherche;
    @FXML
    private Label labelCatégorierecherche;
    @FXML
    private TextField TextFieldRecherche;
    @FXML
    private TableView<Stagiaire> TableViewStage;
    @FXML
    private TableColumn<Stagiaire, String> ColumnNomEntreprise;
    @FXML
    private TableColumn<Stagiaire, String> ColumnNiveau;
    @FXML
    private TableColumn<Stagiaire, String> ColumnDescription;


    ObservableList listFiltreRecherche = FXCollections.observableArrayList();

    public void loadData(){
        listFiltreRecherche.removeAll(listFiltreRecherche);
        String a = "Entreprise";
        String e = "Niveau";
        listFiltreRecherche.addAll(a, e);
        ComboBoxRecherche.getItems().addAll(listFiltreRecherche);
    }
    @FXML
    private void ComboBoxOnAction(ActionEvent actionEvent){
        labelCatégorierecherche.setText(ComboBoxRecherche.getValue());
        Stage_search();
        TextFieldRecherche.setDisable(false);
    }

    /*
Connexion classe BDD
*/
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public StageController() {

        connection = ConexionBDD.connectdb();
    }


    ObservableList<Stagiaire> listStagiaire;
    public  ObservableList<Stagiaire> getDataStage(){
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Stagiaire> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT E.Raison_Sociale, S.NomPrenom, S.Niveau,S.NoteDeStage  FROM stage S INNER JOIN entreprise E ON E.idEntreprise=S.idEntreprise";
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ColumnNomEntreprise.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Raison_Sociale"));
        ColumnNiveau.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Niveau"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("noteDeStage"));
        listStagiaire = getDataStage();
        TableViewStage.setItems(listStagiaire);

        loadData();
    }

    ObservableList<Stagiaire> ListRechecher;
    @FXML
    void Stage_search() {
        ListRechecher = getDataStage();
        TableViewStage.setItems(ListRechecher);


        FilteredList<Stagiaire> filteredData = new FilteredList<>(ListRechecher, b -> true);
        TextFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if(labelCatégorierecherche.getText().equals("Entreprise")){
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
            }else if(labelCatégorierecherche.getText().equals("Niveau")) {
                filteredData.setPredicate(person -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getNiveau().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;

                });
            }
        });
        SortedList<Stagiaire> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableViewStage.comparatorProperty());
        TableViewStage.setItems(sortedData);
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
    public void BonjourOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Connexion.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Connexion");

    }
    public void Stage(ActionEvent event) throws IOException {
        Stage stage = (Stage) Bonjour.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/Stage.fxml")));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Stage");

    }
    public void BtnMenueOnAction(ActionEvent actionEvent){
        AnchorPaneMenu.setVisible(true);
        AnchorPaneMenu.setDisable(false);
        TableViewStage.setDisable(true);
        Bonjour.setDisable(true);
    }
    public void btnCloseMenuAdminOnAction(ActionEvent actionEvent){
        AnchorPaneMenu.setVisible(false);
        AnchorPaneMenu.setDisable(true);
        TableViewStage.setDisable(false);
        Bonjour.setDisable(false);
    }


}
