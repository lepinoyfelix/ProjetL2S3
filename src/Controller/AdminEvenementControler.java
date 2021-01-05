package Controller;

import AutreClasse.*;

import com.sun.org.apache.bcel.internal.classfile.Code;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javafx.scene.control.Button;

import javax.swing.*;

public class AdminEvenementControler implements Initializable {

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
    private AnchorPane AnchorPaneMenu;
    @FXML
    private AnchorPane AnchorPaneEvenement;
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
    private Label LabelIntervenant;
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
    private CheckBox CheckBoxAjouterEvent;
    @FXML
    private  Button ButtonAjouter;
    @FXML
    private TableView<Intervenant> TableViewIntervenant;
    @FXML
    private TableColumn<Intervenant, String> ColumnInterveant;
    @FXML
    private Button ButtonSupprimer;
    @FXML
    private Button ButtonModifier;
    @FXML
    private Button btnMenuAdmin;

    /*
 Connexion classe BDD
  */
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public AdminEvenementControler() {
        connection = ConexionBDD.connectdb();
    }
    ObservableList<Evenement> listEvenement;

    public static  ObservableList<Evenement>  getDataEvenement(){
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Evenement> listE = FXCollections.observableArrayList();


        try {
            String EvenementSql = "SELECT * FROM evenement E LEFT OUTER JOIN cours C ON C.idEvenement=E.idEvenement LEFT OUTER JOIN conference Conf ON Conf.idEvenement=E.idEvenement LEFT OUTER JOIN typeevenement T ON T.idtypeEvenement=E.idtypeEvenement LEFT OUTER JOIN autreevenement A ON A.idEvenement = E.idEvenement LEFT OUTER JOIN competence Comp ON Comp.idCompetence=E.idCompetence LEFT OUTER JOIN persone Per ON E.idPersone=Per.idPersone";
            PreparedStatement ps = connection.prepareStatement(EvenementSql);
            ResultSet rs = ps.executeQuery();



            while (rs.next()) {
                listE.add(new Evenement(
                        rs.getString("NomEvenement"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"),
                        rs.getString("Adresse"),
                        rs.getString("Date"),
                        rs.getString("Heure"),
                        rs.getString("NomPersone"),
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

    ObservableList<Intervenant> listIntervenant;

    public static ObservableList<Intervenant> getDataIntervenant() {
        Connection connection = ConexionBDD.connectdb();
        ObservableList<Intervenant> list = FXCollections.observableArrayList();

        try {
            String CompetenceSQL = "SELECT NomPersone FROM persone";
            PreparedStatement ps = connection.prepareStatement(CompetenceSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Intervenant(rs.getString("NomPersone")));
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

        ColumnInterveant.setCellValueFactory((new  PropertyValueFactory<Intervenant, String>("NomPersone")));
        listIntervenant = getDataIntervenant();
        TableViewIntervenant.setItems(listIntervenant);

        TableauEvenement.setRowFactory(tv -> new TableRow<Evenement>() {

            @Override
            public void updateItem(Evenement item, boolean empty) {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String  Date = format.format(date);
                String Separateur = "/";
                String MaChaine = Date;
                StringTokenizer ST = new StringTokenizer(MaChaine, Separateur);
                int A= Integer.parseInt(ST.nextToken());
                int B = Integer.parseInt(ST.nextToken());
                int C = Integer.parseInt(ST.nextToken());
                super.updateItem(item, empty) ;
                if(item == null){
                    setStyle("");
                }else{
                    String DateSaise = item.getDate();

                    String Separateur2 = "/";
                    StringTokenizer ST1 = new StringTokenizer(DateSaise, Separateur2);
                    int D = Integer.parseInt(ST1.nextToken());
                    int E = Integer.parseInt(ST1.nextToken());
                    int F = Integer.parseInt(ST1.nextToken());

                    if(F>C){
                        setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");

                    }else if(F<C){
                        setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                    }else if (F == C){
                        if(E>B){
                            setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                        }else if(E<B) {
                            setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                        } else if (E == B) {
                            if(D>A){
                                setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                            }else if(D<A) {
                                setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                            } else if (D == A) {
                                setStyle("-fx-background-color: #b2ff95;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");

                            }
                        }
                    }
                }
            }
        });
        loadData();

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
        TableauEvenement.setItems(listEvenement);


        ColumnInterveant.setCellValueFactory((new  PropertyValueFactory<Intervenant, String>("NomPersone")));
        listIntervenant = getDataIntervenant();
        TableViewIntervenant.setItems(listIntervenant);
        recherche_Evenement();

        TableauEvenement.setRowFactory(tv -> new TableRow<Evenement>() {

            @Override
            public void updateItem(Evenement item, boolean empty) {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String  Date = format.format(date);
                String Separateur = "/";
                String MaChaine = Date;
                StringTokenizer ST = new StringTokenizer(MaChaine, Separateur);
                int A= Integer.parseInt(ST.nextToken());
                int B = Integer.parseInt(ST.nextToken());
                int C = Integer.parseInt(ST.nextToken());
                super.updateItem(item, empty) ;
                if(item == null){
                    setStyle("");
                }else{
                    String DateSaise = item.getDate();

                    String Separateur2 = "/";
                    StringTokenizer ST1 = new StringTokenizer(DateSaise, Separateur2);
                    int D = Integer.parseInt(ST1.nextToken());
                    int E = Integer.parseInt(ST1.nextToken());
                    int F = Integer.parseInt(ST1.nextToken());

                    if(F>C){
                        setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");

                    }else if(F<C){
                        setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                    }else if (F == C){
                        if(E>B){
                            setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                        }else if(E<B) {
                            setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                        } else if (E == B) {
                            if(D>A){
                                setStyle("-fx-background-color:#a8f2ff;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0;");
                            }else if(D<A) {
                                setStyle("-fx-background-color: #ffc2c2;-fx-selection-bar: #c0c0c0;-fx-selection-text: #1c1c1c;");
                            } else if (D == A) {
                                setStyle("-fx-background-color: #b2ff95;-fx-selection-bar: #c0c0c0;-fx-selection-text: #c0c0c0");

                            }
                        }
                    }
                }
            }
        });
        loadData();



    }





    int index3 = -1;
    @FXML
    public void getSelectCompetence(javafx.scene.input.MouseEvent mouseEvent) {
        index3 = TableauCompetence.getSelectionModel().getSelectedIndex();
        if (index3 <= -1) {
            return;
        }
        LabelCompetence.setText(ColumnCompétenceTComp.getCellData(index3).toString());

    }

    @FXML
    int index1= -1;
    public void getSelectIntervenant(javafx.scene.input.MouseEvent mouseEvent) {
        index1 = TableViewIntervenant.getSelectionModel().getSelectedIndex();
        if (index1 <= -1) {
            return;
        }
        LabelIntervenant.setText(ColumnInterveant.getCellData(index1).toString());

    }


    @FXML
    int index2= -1;
    public void getSelectTypeEvenement(javafx.scene.input.MouseEvent mouseEvent) {
        index2 = TableauTypeEvenement.getSelectionModel().getSelectedIndex();
        if (index2 <= -1) {
            return;
        }

        LabelTypeEvenement.setText(ColumnTypeEvenementTTypeEvent.getCellData(index2).toString());
        ButtonAjouter.setDisable(false);
        if(LabelTypeEvenement.getText().equals("Cours")){
            TextFieldVisivbiliti();
            TextFieldSalleCours.setVisible(true);
            TextFieldDureeCours.setVisible(true);
            TextFieldBatimentCours.setVisible(true);
            TextFieldDureeConf.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            TextFieldClear();

        }else if(LabelTypeEvenement.getText().equals("Conference")){

            TextFieldVisivbiliti();
            TextFieldDureeConf.setVisible(true);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            TextFieldClear();


        }else if(LabelTypeEvenement.getText().equals("AutreEvenement")){
            TextFieldVisivbiliti();
            TextFieldAutreEvenement.setVisible(true);
            TextFieldDureeConf.setVisible(false);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            TextFieldClear();

        }else{
            JOptionPane.showMessageDialog(null, "Une erreur est survenue veuillez ressayer");
            TextFieldVisivbiliti();
            TextFieldAutreEvenement.setVisible(false);
            TextFieldDureeConf.setVisible(false);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            TextFieldNomEvenement.setVisible(false);
            TextFieldVilleEvenement.setVisible(false);
            TextFieldDateEvenement.setVisible(false);
            TextFieldCodePostalEvenement.setVisible(false);
            LabelIntervenant.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            LabelCompetence.setVisible(false);
        }
    }


    @FXML
    private Label labelChoiceBoxActiion ;

    private void TextFieldVisivbiliti(){
        TextFieldNomEvenement.setVisible(true);
        TextFieldVilleEvenement.setVisible(true);
        TextFieldDateEvenement.setVisible(true);
        TextFieldAdresseEvenement.setVisible(true);
        TextFieldCodePostalEvenement.setVisible(true);
        TextFieldHeureEvenement.setVisible(true);
        LabelIntervenant.setVisible(true);
        LabelCompetence.setVisible(true);
        TableauCompetence.setVisible(true);
        TableViewIntervenant.setVisible(true);

    }
    private void TextFieldClear(){
        TextFieldNomEvenement.clear();
        TextFieldVilleEvenement.clear();
        TextFieldAdresseEvenement.clear();
        TextFieldHeureEvenement.clear();
        TextFieldDateEvenement.clear();
        TextFieldCodePostalEvenement.clear();
        LabelIntervenant.setText("");
        LabelTypeEvenement.setText("");
        LabelCompetence.setText("");
        TextFieldSalleCours.clear();
        TextFieldDureeCours.clear();
        TextFieldBatimentCours.clear();
        TextFieldAutreEvenement.clear();
        TextFieldDureeConf.clear();


    }
    int index =  -1;
    @FXML
    public void getSelectEvenment(javafx.scene.input.MouseEvent mouseEvent) {
        index = TableauEvenement .getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        TextFieldClear();
        TextFieldNomEvenement.setDisable(true);
        CheckBoxAjouterEvent.setSelected(false);
        ButtonModifier.setDisable(false);
        ButtonSupprimer.setDisable(false);
        CheckBoxAjouterEventOnAction();

        labelChoiceBoxActiion.setText(ColumnTypeEvenement.getCellData(index).toString());

        if(labelChoiceBoxActiion.getText().equals("Cours")){

            TextFieldVisivbiliti();
            TextFieldSalleCours.setVisible(true);
            TextFieldDureeCours.setVisible(true);
            TextFieldBatimentCours.setVisible(true);
            TextFieldDureeConf.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);
            TableauCompetence.setVisible(true);

            TextFieldNomEvenement.setText(ColumnNom.getCellData(index).toString());
            TextFieldVilleEvenement.setText(ColumnVille.getCellData(index).toString());
            TextFieldAdresseEvenement.setText(ColumnAdresse.getCellData(index).toString());
            TextFieldHeureEvenement.setText(ColumnHeure.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            LabelIntervenant.setText(ColumnProf.getCellData(index).toString());
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
            TextFieldAdresseEvenement.setText(ColumnAdresse.getCellData(index).toString());
            TextFieldHeureEvenement.setText(ColumnHeure.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            LabelIntervenant.setText(ColumnProf.getCellData(index).toString());
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
            TextFieldAdresseEvenement.setText(ColumnAdresse.getCellData(index).toString());
            TextFieldHeureEvenement.setText(ColumnHeure.getCellData(index).toString());
            TextFieldDateEvenement.setText(ColumnDate.getCellData(index).toString());
            TextFieldCodePostalEvenement.setText(ColumnCodePostal.getCellData(index).toString());
            LabelIntervenant.setText(ColumnProf.getCellData(index).toString());
            LabelTypeEvenement.setText(ColumnTypeEvenement.getCellData(index).toString());
            LabelCompetence.setText(ColumnCompetence.getCellData(index).toString());

            TextFieldAutreEvenement.setText(ColumnDescriptionAutrevenement.getCellData(index).toString());

        }else{
            System.out.println("selectioner un truc");
        }

    }


    public void BouttonPageEntrepriseOnActionOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)  BouttonPageEntreprise.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Fxml/AdminEntreprise.fxml")));
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
        TextFieldRecherche.setDisable(false);
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
    public void CheckBoxAjouterEventOnAction() {
        if (CheckBoxAjouterEvent.isSelected()) {
            TableauTypeEvenement.setVisible(true);
            TextFieldNomEvenement.setVisible(false);
            TextFieldVilleEvenement.setVisible(false);
            TextFieldAdresseEvenement.setVisible(false);
            TextFieldHeureEvenement.setVisible(false);
            TextFieldDateEvenement.setVisible(false);
            TextFieldCodePostalEvenement.setVisible(false);
            LabelCompetence.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            LabelIntervenant.setVisible(false);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);
            TextFieldDureeConf.setVisible(false);
            TableauCompetence.setVisible(false);
            TableViewIntervenant.setVisible(false);
            ButtonSupprimer.setDisable(true);
            ButtonModifier.setDisable(true);
            TextFieldNomEvenement.setDisable(false);

        } else {
            TableauTypeEvenement.setVisible(false);
            ButtonAjouter.setDisable(true);
            TextFieldNomEvenement.setVisible(false);
            TextFieldVilleEvenement.setVisible(false);
            TextFieldAdresseEvenement.setVisible(false);
            TextFieldHeureEvenement.setVisible(false);
            TextFieldDateEvenement.setVisible(false);
            TextFieldCodePostalEvenement.setVisible(false);
            LabelCompetence.setVisible(false);
            LabelTypeEvenement.setVisible(false);
            LabelIntervenant.setVisible(false);
            TextFieldSalleCours.setVisible(false);
            TextFieldDureeCours.setVisible(false);
            TextFieldBatimentCours.setVisible(false);
            TextFieldAutreEvenement.setVisible(false);
            TextFieldDureeConf.setVisible(false);
            TableauCompetence.setVisible(false);
            TableViewIntervenant.setVisible(false);
            TextFieldNomEvenement.setDisable(true);

        }
    }
    public static boolean TextMajIsValide(String TextMaj) {
        String mdpRegex = "[A-Z0-9 ]*";

        Pattern pat = Pattern.compile(mdpRegex);
        if (TextMaj == null)
            return false;
        return pat.matcher(TextMaj).matches();
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
    public static boolean DateIsValide(String Date) {
        String mdpRegex = "([0-9]{2}[/][0-9]{2}[/][0-9]{4})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Date == null)
            return false;
        return pat.matcher(Date).matches();
    }
    public static boolean HeureIsValide(String Heure) {
        String mdpRegex = "([0-9]{1,2}[:][0-9]{2})";
        Pattern pat = Pattern.compile(mdpRegex);
        if (Heure == null)
            return false;
        return pat.matcher(Heure).matches();
    }
    public static boolean SalleIsValide(String Salle) {
        String mdpRegex = "([0-9]{1,6})";

        Pattern pat = Pattern.compile(mdpRegex);
        if (Salle == null)
            return false;
        return pat.matcher(Salle).matches();
    }

    public void AjouterEvenement() {
        LabelTypeEvenement.setText(ColumnTypeEvenementTTypeEvent.getCellData(index2));
        String NomEvenement = TextFieldNomEvenement.getText();
        String VilleEvenemen = TextFieldVilleEvenement.getText();
        String AdresseEvenement = TextFieldAdresseEvenement.getText();
        String HeureEvenement = TextFieldHeureEvenement.getText();
        String DateEvenement = TextFieldDateEvenement.getText();
        String CodePostalEvenemen = TextFieldCodePostalEvenement.getText();
        String Intervenant = LabelIntervenant.getText();
        String SalleCours = TextFieldSalleCours.getText();
        String DureeCours = TextFieldDureeCours.getText();
        String BatimentCours = TextFieldBatimentCours.getText();
        String AutreEvenement = TextFieldAutreEvenement.getText();
        String DureeConf = TextFieldDureeConf.getText();
        String labelCompetence = LabelCompetence.getText();
        String labelTypeEvenement = LabelTypeEvenement.getText();



        if(labelTypeEvenement.length() > 0) {

            if (labelTypeEvenement.equals("Cours")) {

                if(NomEvenement.length()>0){
                    if(TextMajIsValide(NomEvenement)){
                        if (CodePostalEvenemen.length() > 0) {
                            if (CodePostaleIsValide(CodePostalEvenemen)) {
                                if (VilleEvenemen.length() > 0) {
                                    if (TextMajIsValide(VilleEvenemen)) {
                                        if (AdresseEvenement.length() > 0) {
                                            if (AdresseIsValide(AdresseEvenement)) {
                                                if (DateEvenement.length() > 0) {
                                                    if (DateIsValide(DateEvenement)) {
                                                        if(HeureEvenement.length() > 0){
                                                            if(HeureIsValide(HeureEvenement)){
                                                                if (labelCompetence.length() > 0) {
                                                                    String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                    try {
                                                                        preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                        preparedStatement.setString(1, labelCompetence);
                                                                        resultSet = preparedStatement.executeQuery();
                                                                        if (resultSet.next()) {

                                                                            String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                            try{
                                                                                preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                                preparedStatement.setString(1, labelCompetence);
                                                                                resultSet = preparedStatement.executeQuery();
                                                                                if (resultSet.next()) {
                                                                                    String idCompetence = resultSet.getString("idCompetence");
                                                                                    if (Intervenant.length() > 0) {
                                                                                        String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                        try {
                                                                                            preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                            preparedStatement.setString(1, Intervenant);
                                                                                            resultSet = preparedStatement.executeQuery();
                                                                                            if (resultSet.next()) {
                                                                                                String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                                try{
                                                                                                    preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                    preparedStatement.setString(1, Intervenant);
                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                    if (resultSet.next()) {
                                                                                                        String idPersone = resultSet.getString("idPersone");
                                                                                                        if(SalleCours.length()>0){
                                                                                                            if(SalleIsValide(SalleCours)){
                                                                                                                if(BatimentCours.length()>0){
                                                                                                                    if(TextMajIsValide(BatimentCours)){
                                                                                                                        if(DureeCours.length()>0){
                                                                                                                            if(HeureIsValide(DureeCours)){
                                                                                                                                String TypeEvenmentSQL="SELECT idtypeEvenement FROM typeevenement WHERE  typeEvenement = ?";
                                                                                                                                try {
                                                                                                                                    preparedStatement = connection.prepareStatement(TypeEvenmentSQL);
                                                                                                                                    preparedStatement.setString(1, labelTypeEvenement);
                                                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                                                    if (resultSet.next()) {
                                                                                                                                        String idtypeEvenement= resultSet.getString("idtypeEvenement");
                                                                                                                                        String AjoutEvenemntSQL ="INSERT INTO evenement ( NomEvenement, CodePostal, Ville, Adresse, Date,Heure,IdCompetence, idPersone,idtypeEvenement ) VALUE( ?,?,?,?,?,?,?,?,?)";
                                                                                                                                        try {
                                                                                                                                            preparedStatement = connection.prepareStatement(AjoutEvenemntSQL);
                                                                                                                                            preparedStatement.setString(1, NomEvenement);
                                                                                                                                            preparedStatement.setString(2, CodePostalEvenemen);
                                                                                                                                            preparedStatement.setString(3, VilleEvenemen);
                                                                                                                                            preparedStatement.setString(4, AdresseEvenement);
                                                                                                                                            preparedStatement.setString(5, DateEvenement);
                                                                                                                                            preparedStatement.setString(6, HeureEvenement);
                                                                                                                                            preparedStatement.setString(7, idCompetence);
                                                                                                                                            preparedStatement.setString(8, idPersone);
                                                                                                                                            preparedStatement.setString(9, idtypeEvenement);
                                                                                                                                            preparedStatement.execute();

                                                                                                                                            String idEvenementSQL="SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' AND IdCompetence = '"+idCompetence+"' AND idPersone = '"+idPersone+"' AND idtypeEvenement = '"+idtypeEvenement+"' ";
                                                                                                                                            try {
                                                                                                                                                preparedStatement = connection.prepareStatement(idEvenementSQL);
                                                                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                                                                if (resultSet.next()) {
                                                                                                                                                    String idEvenement = resultSet.getString("idEvenement");
                                                                                                                                                    String AjoutCoursSQL ="INSERT INTO cours ( idEvenement,Salle, Batiment, DureeCours) VALUE(?, ?,?,?)";
                                                                                                                                                    try {
                                                                                                                                                        preparedStatement = connection.prepareStatement(AjoutCoursSQL);
                                                                                                                                                        preparedStatement.setString(1, idEvenement);
                                                                                                                                                        preparedStatement.setString(2, SalleCours);
                                                                                                                                                        preparedStatement.setString(3, BatimentCours);
                                                                                                                                                        preparedStatement.setString(4, DureeCours);
                                                                                                                                                        preparedStatement.execute();
                                                                                                                                                        JOptionPane.showMessageDialog(null, "Cours ajouter avec suces");
                                                                                                                                                        UpdateTable();
                                                                                                                                                        TextFieldClear();


                                                                                                                                                    }catch (SQLException throwables){
                                                                                                                                                        throwables.printStackTrace();
                                                                                                                                                    }

                                                                                                                                                }else{
                                                                                                                                                    System.out.println("pb1");
                                                                                                                                                }
                                                                                                                                            }catch (SQLException throwables){
                                                                                                                                                throwables.printStackTrace();
                                                                                                                                            }

                                                                                                                                        }catch (SQLException throwables){
                                                                                                                                            throwables.printStackTrace();
                                                                                                                                        }
                                                                                                                                    }else {
                                                                                                                                    }
                                                                                                                                }catch (SQLException throwables){
                                                                                                                                    throwables.printStackTrace();
                                                                                                                                }
                                                                                                                            }else{
                                                                                                                                JOptionPane.showMessageDialog(null, "Duree de cours non valide veuillez saisir sous le format suivant 00:00");
                                                                                                                            }
                                                                                                                        }else{
                                                                                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir une Duree de cours");
                                                                                                                        }
                                                                                                                    }else{
                                                                                                                        JOptionPane.showMessageDialog(null, "Nom de bat invalide Veuillez saisir en Maj et/ou avec des Chifre et  espace");
                                                                                                                    }
                                                                                                                }else{
                                                                                                                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nom de batiment");
                                                                                                                }
                                                                                                            }else{
                                                                                                                JOptionPane.showMessageDialog(null, "Nuero de salle invalide saisire seulement des chifre");
                                                                                                            }
                                                                                                        }else{
                                                                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir un numero de salle");
                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "persone");
                                                                                                    }
                                                                                                }catch (SQLException throwables){
                                                                                                    throwables.printStackTrace();
                                                                                                }
                                                                                            }else{
                                                                                                JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                            }
                                                                                        } catch (SQLException throwables) {
                                                                                            throwables.printStackTrace();
                                                                                        }
                                                                                    }else {
                                                                                        JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                    }
                                                                                }else{
                                                                                }
                                                                            }catch (SQLException throwables){
                                                                                throwables.printStackTrace();
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                        }
                                                                    } catch (SQLException throwables) {
                                                                        throwables.printStackTrace();
                                                                    }
                                                                }else {
                                                                    JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                                }

                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                            }
                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
                }

            }else if (labelTypeEvenement.equals("Conference")){

                if(NomEvenement.length()>0){
                    if(TextMajIsValide(NomEvenement)){
                        if (CodePostalEvenemen.length() > 0) {
                            if (CodePostaleIsValide(CodePostalEvenemen)) {
                                if (VilleEvenemen.length() > 0) {
                                    if (TextMajIsValide(VilleEvenemen)) {
                                        if (AdresseEvenement.length() > 0) {
                                            if (AdresseIsValide(AdresseEvenement)) {
                                                if (DateEvenement.length() > 0) {
                                                    if (DateIsValide(DateEvenement)) {
                                                        if(HeureEvenement.length() > 0){
                                                            if(HeureIsValide(HeureEvenement)){
                                                                if (labelCompetence.length() > 0) {
                                                                    String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                    try {
                                                                        preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                        preparedStatement.setString(1, labelCompetence);
                                                                        resultSet = preparedStatement.executeQuery();
                                                                        if (resultSet.next()) {

                                                                            String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                            try{
                                                                                preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                                preparedStatement.setString(1, labelCompetence);
                                                                                resultSet = preparedStatement.executeQuery();
                                                                                if (resultSet.next()) {
                                                                                    String idCompetence = resultSet.getString("idCompetence");
                                                                                    if (Intervenant.length() > 0) {
                                                                                        String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                        try {
                                                                                            preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                            preparedStatement.setString(1, Intervenant);
                                                                                            resultSet = preparedStatement.executeQuery();
                                                                                            if (resultSet.next()) {
                                                                                                String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                                try{
                                                                                                    preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                    preparedStatement.setString(1, Intervenant);
                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                    if (resultSet.next()) {
                                                                                                        String idPersone = resultSet.getString("idPersone");
                                                                                                        if(DureeConf.length()>0){
                                                                                                            if(HeureIsValide(DureeConf)){
                                                                                                                String TypeEvenmentSQL="SELECT idtypeEvenement FROM typeevenement WHERE  typeEvenement = ?";
                                                                                                                try {
                                                                                                                    preparedStatement = connection.prepareStatement(TypeEvenmentSQL);
                                                                                                                    preparedStatement.setString(1, labelTypeEvenement);
                                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                                    if (resultSet.next()) {

                                                                                                                        String idtypeEvenement= resultSet.getString("idtypeEvenement");
                                                                                                                        String AjoutEvenemntSQL ="INSERT INTO evenement ( NomEvenement, CodePostal, Ville, Adresse, Date,Heure,IdCompetence, idPersone,idtypeEvenement ) VALUE( ?,?,?,?,?,?,?,?,?)";
                                                                                                                        try {
                                                                                                                            preparedStatement = connection.prepareStatement(AjoutEvenemntSQL);
                                                                                                                            preparedStatement.setString(1, NomEvenement);
                                                                                                                            preparedStatement.setString(2, CodePostalEvenemen);
                                                                                                                            preparedStatement.setString(3, VilleEvenemen);
                                                                                                                            preparedStatement.setString(4, AdresseEvenement);
                                                                                                                            preparedStatement.setString(5, DateEvenement);
                                                                                                                            preparedStatement.setString(6, HeureEvenement);
                                                                                                                            preparedStatement.setString(7, idCompetence);
                                                                                                                            preparedStatement.setString(8, idPersone);
                                                                                                                            preparedStatement.setString(9, idtypeEvenement);
                                                                                                                            preparedStatement.execute();

                                                                                                                            String idEvenementSQL="SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' AND IdCompetence = '"+idCompetence+"' AND idPersone = '"+idPersone+"' AND idtypeEvenement = '"+idtypeEvenement+"' ";
                                                                                                                            try {
                                                                                                                                preparedStatement = connection.prepareStatement(idEvenementSQL);
                                                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                                                if (resultSet.next()) {
                                                                                                                                    String idEvenement = resultSet.getString("idEvenement");
                                                                                                                                    String AjoutCoursSQL ="INSERT INTO conference ( idEvenement,DureeConference) VALUE(?,?)";
                                                                                                                                    try {
                                                                                                                                        preparedStatement = connection.prepareStatement(AjoutCoursSQL);
                                                                                                                                        preparedStatement.setString(1, idEvenement);
                                                                                                                                        preparedStatement.setString(2, DureeConf);

                                                                                                                                        preparedStatement.execute();
                                                                                                                                        JOptionPane.showMessageDialog(null, "Conference ajouter avec suces");
                                                                                                                                        UpdateTable();
                                                                                                                                        TextFieldClear();


                                                                                                                                    }catch (SQLException throwables){
                                                                                                                                        throwables.printStackTrace();
                                                                                                                                    }

                                                                                                                                }else{
                                                                                                                                    System.out.println("pb1");
                                                                                                                                }
                                                                                                                            }catch (SQLException throwables){
                                                                                                                                throwables.printStackTrace();
                                                                                                                            }

                                                                                                                        }catch (SQLException throwables){
                                                                                                                            throwables.printStackTrace();
                                                                                                                        }

                                                                                                                    }else {
                                                                                                                        JOptionPane.showMessageDialog(null,"Type d'evenement non existant");
                                                                                                                    }
                                                                                                                }catch (SQLException throwables){
                                                                                                                    throwables.printStackTrace();
                                                                                                                }
                                                                                                            }else{
                                                                                                                JOptionPane.showMessageDialog(null, "Duree de conference non valide veuillez saisir sous le format suivant 00:00");
                                                                                                            }
                                                                                                        }else{
                                                                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir une Duree de conference");

                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "persone");
                                                                                                    }
                                                                                                }catch (SQLException throwables){
                                                                                                    throwables.printStackTrace();
                                                                                                }
                                                                                            }else{
                                                                                                JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                            }
                                                                                        } catch (SQLException throwables) {
                                                                                            throwables.printStackTrace();
                                                                                        }
                                                                                    }else {
                                                                                        JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                    }
                                                                                }else{
                                                                                }
                                                                            }catch (SQLException throwables){
                                                                                throwables.printStackTrace();
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                        }
                                                                    } catch (SQLException throwables) {
                                                                        throwables.printStackTrace();
                                                                    }
                                                                }else {
                                                                    JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                                }

                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                            }
                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
                }

            }else if(labelTypeEvenement.equals("AutreEvenement")){

                if(NomEvenement.length()>0){
                    if(TextMajIsValide(NomEvenement)){
                        if (CodePostalEvenemen.length() > 0) {
                            if (CodePostaleIsValide(CodePostalEvenemen)) {
                                if (VilleEvenemen.length() > 0) {
                                    if (TextMajIsValide(VilleEvenemen)) {
                                        if (AdresseEvenement.length() > 0) {
                                            if (AdresseIsValide(AdresseEvenement)) {
                                                if (DateEvenement.length() > 0) {
                                                    if (DateIsValide(DateEvenement)) {
                                                        if(HeureEvenement.length() > 0){
                                                            if(HeureIsValide(HeureEvenement)){
                                                                if (labelCompetence.length() > 0) {
                                                                    String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                    try {
                                                                        preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                        preparedStatement.setString(1, labelCompetence);
                                                                        resultSet = preparedStatement.executeQuery();
                                                                        if (resultSet.next()) {

                                                                            String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                            try{
                                                                                preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                                preparedStatement.setString(1, labelCompetence);
                                                                                resultSet = preparedStatement.executeQuery();
                                                                                if (resultSet.next()) {
                                                                                    String idCompetence = resultSet.getString("idCompetence");
                                                                                    if (Intervenant.length() > 0) {
                                                                                        String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                        try {
                                                                                            preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                            preparedStatement.setString(1, Intervenant);
                                                                                            resultSet = preparedStatement.executeQuery();
                                                                                            if (resultSet.next()) {
                                                                                                String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                                try{
                                                                                                    preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                    preparedStatement.setString(1, Intervenant);
                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                    if (resultSet.next()) {
                                                                                                        String idPersone = resultSet.getString("idPersone");
                                                                                                        if(AutreEvenement.length()>0){
                                                                                                            String TypeEvenmentSQL="SELECT idtypeEvenement FROM typeevenement WHERE  typeEvenement = ?";
                                                                                                            try {
                                                                                                                preparedStatement = connection.prepareStatement(TypeEvenmentSQL);
                                                                                                                preparedStatement.setString(1, labelTypeEvenement);
                                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                                if (resultSet.next()) {

                                                                                                                    String idtypeEvenement= resultSet.getString("idtypeEvenement");
                                                                                                                    String AjoutEvenemntSQL ="INSERT INTO evenement ( NomEvenement, CodePostal, Ville, Adresse, Date,Heure,IdCompetence, idPersone,idtypeEvenement ) VALUE( ?,?,?,?,?,?,?,?,?)";
                                                                                                                    try {
                                                                                                                        preparedStatement = connection.prepareStatement(AjoutEvenemntSQL);
                                                                                                                        preparedStatement.setString(1, NomEvenement);
                                                                                                                        preparedStatement.setString(2, CodePostalEvenemen);
                                                                                                                        preparedStatement.setString(3, VilleEvenemen);
                                                                                                                        preparedStatement.setString(4, AdresseEvenement);
                                                                                                                        preparedStatement.setString(5, DateEvenement);
                                                                                                                        preparedStatement.setString(6, HeureEvenement);
                                                                                                                        preparedStatement.setString(7, idCompetence);
                                                                                                                        preparedStatement.setString(8, idPersone);
                                                                                                                        preparedStatement.setString(9, idtypeEvenement);
                                                                                                                        preparedStatement.execute();

                                                                                                                        String idEvenementSQL="SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' AND IdCompetence = '"+idCompetence+"' AND idPersone = '"+idPersone+"' AND idtypeEvenement = '"+idtypeEvenement+"' ";
                                                                                                                        try {
                                                                                                                            preparedStatement = connection.prepareStatement(idEvenementSQL);
                                                                                                                            resultSet = preparedStatement.executeQuery();
                                                                                                                            if (resultSet.next()) {
                                                                                                                                String idEvenement = resultSet.getString("idEvenement");
                                                                                                                                String AjoutCoursSQL ="INSERT INTO autreevenement ( idEvenement,DescriptionAutreEvenement) VALUE(?,?)";
                                                                                                                                try {
                                                                                                                                    preparedStatement = connection.prepareStatement(AjoutCoursSQL);
                                                                                                                                    preparedStatement.setString(1, idEvenement);
                                                                                                                                    preparedStatement.setString(2, AutreEvenement);

                                                                                                                                    preparedStatement.execute();
                                                                                                                                    JOptionPane.showMessageDialog(null, "Autre Evenement ajouter avec suces");
                                                                                                                                    UpdateTable();
                                                                                                                                    TextFieldClear();


                                                                                                                                }catch (SQLException throwables){
                                                                                                                                    throwables.printStackTrace();
                                                                                                                                }

                                                                                                                            }else{
                                                                                                                                System.out.println("pb1");
                                                                                                                            }
                                                                                                                        }catch (SQLException throwables){
                                                                                                                            throwables.printStackTrace();
                                                                                                                        }

                                                                                                                    }catch (SQLException throwables){
                                                                                                                        throwables.printStackTrace();
                                                                                                                    }

                                                                                                                }else {
                                                                                                                    JOptionPane.showMessageDialog(null,"Type d'evenement non existant");
                                                                                                                }
                                                                                                            }catch (SQLException throwables){
                                                                                                                throwables.printStackTrace();
                                                                                                            }
                                                                                                        }else{
                                                                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir une Description de l'evenement");

                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "persone");
                                                                                                    }
                                                                                                }catch (SQLException throwables){
                                                                                                    throwables.printStackTrace();
                                                                                                }
                                                                                            }else{
                                                                                                JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                            }
                                                                                        } catch (SQLException throwables) {
                                                                                            throwables.printStackTrace();
                                                                                        }
                                                                                    }else {
                                                                                        JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                    }
                                                                                }else{
                                                                                }
                                                                            }catch (SQLException throwables){
                                                                                throwables.printStackTrace();
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                        }
                                                                    } catch (SQLException throwables) {
                                                                        throwables.printStackTrace();
                                                                    }
                                                                }else {
                                                                    JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                                }

                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                            }
                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Veuillez redemarer l'application un problem est survenue");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Veuillez saisir un Type d'evenement");
        }
    }
    @FXML
    private Label labelpourSupprimer;

    public void SupprimerEvenement(ActionEvent actionEvent){
        labelpourSupprimer.setText(ColumnTypeEvenement.getCellData(index).toString());
        String NomEvenement = TextFieldNomEvenement.getText();
        String VilleEvenemen = TextFieldVilleEvenement.getText();
        String AdresseEvenement = TextFieldAdresseEvenement.getText();
        String HeureEvenement = TextFieldHeureEvenement.getText();
        String DateEvenement = TextFieldDateEvenement.getText();
        String CodePostalEvenemen = TextFieldCodePostalEvenement.getText();
        String Intervenant = LabelIntervenant.getText();
        String SalleCours = TextFieldSalleCours.getText();
        String DureeCours = TextFieldDureeCours.getText();
        String BatimentCours = TextFieldBatimentCours.getText();
        String AutreEvenement = TextFieldAutreEvenement.getText();
        String DureeConf = TextFieldDureeConf.getText();
        String labelCompetence = LabelCompetence.getText();
        String labelTypeEvenement = LabelTypeEvenement.getText();

        if(labelpourSupprimer.getText().equals("Cours")){
            String idEvenementCoursSQL = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' ";
            try {
                preparedStatement = connection.prepareStatement(idEvenementCoursSQL);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String idEvenementAsupp = resultSet.getString("idEvenement");
                    String CoursSuprimerSQL="DELETE  FROM cours WHERE idevenement = ?";
                    try {
                        preparedStatement = connection.prepareStatement(CoursSuprimerSQL);
                        preparedStatement.setString(1, idEvenementAsupp);
                        preparedStatement.execute();
                        String EvenementAsupprimerAvecIDSQL ="DELETE FROM evenement Where idevenement = ?";
                        try {
                            preparedStatement = connection.prepareStatement(EvenementAsupprimerAvecIDSQL);
                            preparedStatement.setString(1, idEvenementAsupp);
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(null, "l'evenement " + NomEvenement + " a été suprimer");
                            ButtonSupprimer.setDisable(true);
                            UpdateTable();
                            TextFieldClear();
                            recherche_Evenement();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(labelpourSupprimer.getText().equals("Conference")){
            String idEvenementCoursSQL = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' ";
            try {
                preparedStatement = connection.prepareStatement(idEvenementCoursSQL);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String idEvenementAsupp = resultSet.getString("idEvenement");
                    String ConferenceSuprimerSQL="DELETE  FROM conference WHERE idevenement = ?";
                    try {
                        preparedStatement = connection.prepareStatement(ConferenceSuprimerSQL);
                        preparedStatement.setString(1, idEvenementAsupp);
                        preparedStatement.execute();
                        String EvenementAsupprimerAvecIDSQL ="DELETE FROM evenement Where idevenement = ?";
                        try {
                            preparedStatement = connection.prepareStatement(EvenementAsupprimerAvecIDSQL);
                            preparedStatement.setString(1, idEvenementAsupp);
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(null, "l'evenement " + NomEvenement + " a été suprimer");
                            ButtonSupprimer.setDisable(true);
                            UpdateTable();
                            TextFieldClear();
                            recherche_Evenement();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else if(labelpourSupprimer.getText().equals("AutreEvenement")){
            String idEvenementCoursSQL = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' AND CodePostal = '"+CodePostalEvenemen+"' AND Ville = '"+VilleEvenemen+"' AND Adresse = '"+AdresseEvenement+"' AND Date = '"+DateEvenement+"' AND Heure = '"+HeureEvenement+"' ";
            try {
                preparedStatement = connection.prepareStatement(idEvenementCoursSQL);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String idEvenementAsupp = resultSet.getString("idEvenement");
                    String ConferenceSuprimerSQL="DELETE  FROM autreevenement WHERE idevenement = ?";
                    try {
                        preparedStatement = connection.prepareStatement(ConferenceSuprimerSQL);
                        preparedStatement.setString(1, idEvenementAsupp);
                        preparedStatement.execute();
                        String EvenementAsupprimerAvecIDSQL ="DELETE FROM evenement Where idevenement = ?";
                        try {
                            preparedStatement = connection.prepareStatement(EvenementAsupprimerAvecIDSQL);
                            preparedStatement.setString(1, idEvenementAsupp);
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(null, "l'evenement " + NomEvenement + " a été suprimer");
                            ButtonSupprimer.setDisable(true);
                            UpdateTable();
                            TextFieldClear();
                            recherche_Evenement();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }else{
            JOptionPane.showMessageDialog(null, "Une erreur est surenue veuillez contacter le support");
        }

    }
    public void EditerEvenement(ActionEvent actionEvent){
        labelpourSupprimer.setText(ColumnTypeEvenement.getCellData(index).toString());
        String NomEvenement = TextFieldNomEvenement.getText();
        String VilleEvenemen = TextFieldVilleEvenement.getText();
        String AdresseEvenement = TextFieldAdresseEvenement.getText();
        String HeureEvenement = TextFieldHeureEvenement.getText();
        String DateEvenement = TextFieldDateEvenement.getText();
        String CodePostalEvenemen = TextFieldCodePostalEvenement.getText();
        String Intervenant = LabelIntervenant.getText();
        String SalleCours = TextFieldSalleCours.getText();
        String DureeCours = TextFieldDureeCours.getText();
        String BatimentCours = TextFieldBatimentCours.getText();
        String AutreEvenement = TextFieldAutreEvenement.getText();
        String DureeConf = TextFieldDureeConf.getText();
        String labelCompetence = LabelCompetence.getText();
        String labelTypeEvenement = LabelTypeEvenement.getText();


        if(labelpourSupprimer.getText().equals("Cours")){
            if(NomEvenement.length()>0){
                if(TextMajIsValide(NomEvenement)){
                    if (CodePostalEvenemen.length() > 0) {
                        if (CodePostaleIsValide(CodePostalEvenemen)) {
                            if (VilleEvenemen.length() > 0) {
                                if (TextMajIsValide(VilleEvenemen)) {
                                    if (AdresseEvenement.length() > 0) {
                                        if (AdresseIsValide(AdresseEvenement)) {
                                            if (DateEvenement.length() > 0) {
                                                if (DateIsValide(DateEvenement)) {
                                                    if(HeureEvenement.length() > 0){
                                                        if(HeureIsValide(HeureEvenement)){
                                                            if (labelCompetence.length() > 0) {
                                                                String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                try {
                                                                    preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                    preparedStatement.setString(1, labelCompetence);
                                                                    resultSet = preparedStatement.executeQuery();
                                                                    if (resultSet.next()) {

                                                                        String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                        try{
                                                                            preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                            preparedStatement.setString(1, labelCompetence);
                                                                            resultSet = preparedStatement.executeQuery();
                                                                            if (resultSet.next()) {
                                                                                String idCompetence = resultSet.getString("idCompetence");
                                                                                if (Intervenant.length() > 0) {
                                                                                    String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                        preparedStatement.setString(1, Intervenant);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (resultSet.next()) {
                                                                                            String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                            try{
                                                                                                preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                preparedStatement.setString(1, Intervenant);
                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                if (resultSet.next()) {
                                                                                                    String idPersone = resultSet.getString("idPersone");
                                                                                                    if(SalleCours.length()>0){
                                                                                                        if(SalleIsValide(SalleCours)){
                                                                                                            if(BatimentCours.length()>0){
                                                                                                                if(TextMajIsValide(BatimentCours)){
                                                                                                                    if(DureeCours.length()>0){
                                                                                                                        if(HeureIsValide(DureeCours)){
                                                                                                                            String idEvenement = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' ";
                                                                                                                            try {
                                                                                                                                preparedStatement = connection.prepareStatement(idEvenement);
                                                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                                                if(resultSet.next()){
                                                                                                                                    String idEvenementCoursModif = resultSet.getString("idEvenement");
                                                                                                                                    String idCoursEvenemnt = "SELECT idCours FROM  cours WHERE idevenement = ?";
                                                                                                                                    try{
                                                                                                                                        preparedStatement = connection.prepareStatement(idCoursEvenemnt);
                                                                                                                                        preparedStatement.setString(1, idEvenementCoursModif);
                                                                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                                                                        if(resultSet.next()) {
                                                                                                                                            String idCoursEvenementModif = resultSet.getString("idCours");
                                                                                                                                            String UpdateEvenemntSQL = "UPDATE evenement SET NomEvenement = '" + NomEvenement + "' , " +
                                                                                                                                                    "CodePostal = '" + CodePostalEvenemen + "' , " +
                                                                                                                                                    "Ville = '" + VilleEvenemen + "' , " +
                                                                                                                                                    "Adresse = '" + AdresseEvenement + "' , " +
                                                                                                                                                    "Date = '" + DateEvenement + "' , " +
                                                                                                                                                    "IdCompetence = '" + idCompetence + "' , " +
                                                                                                                                                    "idPersone = '" + idPersone + "' , " +
                                                                                                                                                    "idCompetence  = '" + idCompetence + "' WHERE  idevenement = '" + idEvenementCoursModif + "' ";

                                                                                                                                            String UpdateCoursSQL = "UPDATE cours SET Salle = '" + SalleCours + "' , Batiment = '" + BatimentCours + "', DureeCours = '" + DureeCours + "'  WHERE idCours = '" + idCoursEvenementModif + "'";

                                                                                                                                            preparedStatement = connection.prepareStatement(UpdateEvenemntSQL);
                                                                                                                                            preparedStatement.execute();
                                                                                                                                            preparedStatement = connection.prepareStatement(UpdateCoursSQL);
                                                                                                                                            preparedStatement.execute();
                                                                                                                                            JOptionPane.showMessageDialog(null, "La modification de " + NomEvenement + " a etait prise en compte" );
                                                                                                                                            UpdateTable();
                                                                                                                                            TextFieldClear();
                                                                                                                                            TableauTypeEvenement.setVisible(false);
                                                                                                                                            TextFieldNomEvenement.setVisible(false);
                                                                                                                                            TextFieldVilleEvenement.setVisible(false);
                                                                                                                                            TextFieldAdresseEvenement.setVisible(false);
                                                                                                                                            TextFieldHeureEvenement.setVisible(false);
                                                                                                                                            TextFieldDateEvenement.setVisible(false);
                                                                                                                                            TextFieldCodePostalEvenement.setVisible(false);
                                                                                                                                            LabelCompetence.setVisible(false);
                                                                                                                                            LabelTypeEvenement.setVisible(false);
                                                                                                                                            LabelIntervenant.setVisible(false);
                                                                                                                                            TextFieldSalleCours.setVisible(false);
                                                                                                                                            TextFieldDureeCours.setVisible(false);
                                                                                                                                            TextFieldBatimentCours.setVisible(false);
                                                                                                                                            TextFieldAutreEvenement.setVisible(false);
                                                                                                                                            TextFieldDureeConf.setVisible(false);
                                                                                                                                            TableauCompetence.setVisible(false);
                                                                                                                                            TableViewIntervenant.setVisible(false);
                                                                                                                                        }

                                                                                                                                    } catch (SQLException throwables) {
                                                                                                                                        throwables.printStackTrace();
                                                                                                                                    }

                                                                                                                                }else{

                                                                                                                                }
                                                                                                                            } catch (SQLException throwables) {
                                                                                                                                throwables.printStackTrace();
                                                                                                                            }
                                                                                                                        }else{
                                                                                                                            JOptionPane.showMessageDialog(null, "Duree de cours non valide veuillez saisir sous le format suivant 00:00");
                                                                                                                        }
                                                                                                                    }else{
                                                                                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir une Duree de cours");
                                                                                                                    }
                                                                                                                }else{
                                                                                                                    JOptionPane.showMessageDialog(null, "Nom de bat invalide Veuillez saisir en Maj et/ou avec des Chifre et  espace");
                                                                                                                }
                                                                                                            }else{
                                                                                                                JOptionPane.showMessageDialog(null, "Veuillez saisir un nom de batiment");
                                                                                                            }
                                                                                                        }else{
                                                                                                            JOptionPane.showMessageDialog(null, "Nuero de salle invalide saisire seulement des chifre");
                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir un numero de salle");
                                                                                                    }
                                                                                                }else{
                                                                                                    JOptionPane.showMessageDialog(null, "persone");
                                                                                                }
                                                                                            }catch (SQLException throwables){
                                                                                                throwables.printStackTrace();
                                                                                            }
                                                                                        }else{
                                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                        }
                                                                                    } catch (SQLException throwables) {
                                                                                        throwables.printStackTrace();
                                                                                    }
                                                                                }else {
                                                                                    JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                }
                                                                            }else{
                                                                            }
                                                                        }catch (SQLException throwables){
                                                                            throwables.printStackTrace();
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                    }
                                                                } catch (SQLException throwables) {
                                                                    throwables.printStackTrace();
                                                                }
                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                            }

                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                        }
                                                    }else {
                                                        JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
            }

        }else if(labelpourSupprimer.getText().equals("Conference")){
            if(NomEvenement.length()>0){
                if(TextMajIsValide(NomEvenement)){
                    if (CodePostalEvenemen.length() > 0) {
                        if (CodePostaleIsValide(CodePostalEvenemen)) {
                            if (VilleEvenemen.length() > 0) {
                                if (TextMajIsValide(VilleEvenemen)) {
                                    if (AdresseEvenement.length() > 0) {
                                        if (AdresseIsValide(AdresseEvenement)) {
                                            if (DateEvenement.length() > 0) {
                                                if (DateIsValide(DateEvenement)) {
                                                    if(HeureEvenement.length() > 0){
                                                        if(HeureIsValide(HeureEvenement)){
                                                            if (labelCompetence.length() > 0) {
                                                                String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                try {
                                                                    preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                    preparedStatement.setString(1, labelCompetence);
                                                                    resultSet = preparedStatement.executeQuery();
                                                                    if (resultSet.next()) {

                                                                        String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                        try{
                                                                            preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                            preparedStatement.setString(1, labelCompetence);
                                                                            resultSet = preparedStatement.executeQuery();
                                                                            if (resultSet.next()) {
                                                                                String idCompetence = resultSet.getString("idCompetence");
                                                                                if (Intervenant.length() > 0) {
                                                                                    String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                        preparedStatement.setString(1, Intervenant);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (resultSet.next()) {
                                                                                            String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                            try{
                                                                                                preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                preparedStatement.setString(1, Intervenant);
                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                if (resultSet.next()) {
                                                                                                    String idPersone = resultSet.getString("idPersone");
                                                                                                    if(DureeConf.length()>0){
                                                                                                        if(HeureIsValide(DureeConf)){
                                                                                                            String idEvenement = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' ";
                                                                                                            try {
                                                                                                                preparedStatement = connection.prepareStatement(idEvenement);
                                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                                if(resultSet.next()){
                                                                                                                    String idEvenementCoursModif = resultSet.getString("idEvenement");
                                                                                                                    String idConfEvenemnt = "SELECT idConference FROM  conference WHERE idevenement = ?";
                                                                                                                    try{
                                                                                                                        preparedStatement = connection.prepareStatement(idConfEvenemnt);
                                                                                                                        preparedStatement.setString(1, idEvenementCoursModif);
                                                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                                                        if(resultSet.next()) {
                                                                                                                            String idConfEvenementModif = resultSet.getString("idConference");
                                                                                                                            String UpdateEvenemntSQL = "UPDATE evenement SET NomEvenement = '" + NomEvenement + "' , " +
                                                                                                                                    "CodePostal = '" + CodePostalEvenemen + "' , " +
                                                                                                                                    "Ville = '" + VilleEvenemen + "' , " +
                                                                                                                                    "Adresse = '" + AdresseEvenement + "' , " +
                                                                                                                                    "Date = '" + DateEvenement + "' , " +
                                                                                                                                    "IdCompetence = '" + idCompetence + "' , " +
                                                                                                                                    "idPersone = '" + idPersone + "' , " +
                                                                                                                                    "idCompetence  = '" + idCompetence + "' WHERE  idevenement = '" + idEvenementCoursModif + "' ";

                                                                                                                            String UpdateConfSQL = "UPDATE conference SET DureeConference = '"+ DureeConf+"' WHERE idconference = '"+ idConfEvenementModif +"' ";

                                                                                                                            preparedStatement = connection.prepareStatement(UpdateEvenemntSQL);
                                                                                                                            preparedStatement.execute();
                                                                                                                            preparedStatement = connection.prepareStatement(UpdateConfSQL);
                                                                                                                            preparedStatement.execute();
                                                                                                                            JOptionPane.showMessageDialog(null, "La modification de " + NomEvenement + " a etait prise en compte");
                                                                                                                            UpdateTable();
                                                                                                                            TextFieldClear();
                                                                                                                            TableauTypeEvenement.setVisible(false);
                                                                                                                            TextFieldNomEvenement.setVisible(false);
                                                                                                                            TextFieldVilleEvenement.setVisible(false);
                                                                                                                            TextFieldAdresseEvenement.setVisible(false);
                                                                                                                            TextFieldHeureEvenement.setVisible(false);
                                                                                                                            TextFieldDateEvenement.setVisible(false);
                                                                                                                            TextFieldCodePostalEvenement.setVisible(false);
                                                                                                                            LabelCompetence.setVisible(false);
                                                                                                                            LabelTypeEvenement.setVisible(false);
                                                                                                                            LabelIntervenant.setVisible(false);
                                                                                                                            TextFieldSalleCours.setVisible(false);
                                                                                                                            TextFieldDureeCours.setVisible(false);
                                                                                                                            TextFieldBatimentCours.setVisible(false);
                                                                                                                            TextFieldAutreEvenement.setVisible(false);
                                                                                                                            TextFieldDureeConf.setVisible(false);
                                                                                                                            TableauCompetence.setVisible(false);
                                                                                                                            TableViewIntervenant.setVisible(false);

                                                                                                                        }

                                                                                                                    } catch (SQLException throwables) {
                                                                                                                        throwables.printStackTrace();
                                                                                                                    }

                                                                                                                }else{

                                                                                                                }
                                                                                                            } catch (SQLException throwables) {
                                                                                                                throwables.printStackTrace();
                                                                                                            }
                                                                                                        }else{
                                                                                                            JOptionPane.showMessageDialog(null, "Duree de cours non valide veuillez saisir sous le format suivant 00:00");
                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir une Duree de cours");
                                                                                                    }
                                                                                                }else{
                                                                                                    JOptionPane.showMessageDialog(null, "persone");
                                                                                                }
                                                                                            }catch (SQLException throwables){
                                                                                                throwables.printStackTrace();
                                                                                            }
                                                                                        }else{
                                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                        }
                                                                                    } catch (SQLException throwables) {
                                                                                        throwables.printStackTrace();
                                                                                    }
                                                                                }else {
                                                                                    JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                }
                                                                            }else{
                                                                            }
                                                                        }catch (SQLException throwables){
                                                                            throwables.printStackTrace();
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                    }
                                                                } catch (SQLException throwables) {
                                                                    throwables.printStackTrace();
                                                                }
                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                            }

                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                        }
                                                    }else {
                                                        JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
            }

        }else if(labelpourSupprimer.getText().equals("AutreEvenement")){
            if(NomEvenement.length()>0){
                if(TextMajIsValide(NomEvenement)){
                    if (CodePostalEvenemen.length() > 0) {
                        if (CodePostaleIsValide(CodePostalEvenemen)) {
                            if (VilleEvenemen.length() > 0) {
                                if (TextMajIsValide(VilleEvenemen)) {
                                    if (AdresseEvenement.length() > 0) {
                                        if (AdresseIsValide(AdresseEvenement)) {
                                            if (DateEvenement.length() > 0) {
                                                if (DateIsValide(DateEvenement)) {
                                                    if(HeureEvenement.length() > 0){
                                                        if(HeureIsValide(HeureEvenement)){
                                                            if (labelCompetence.length() > 0) {
                                                                String CompetenceSQL = "SELECT * FROM Competence Where Competence = ?";
                                                                try {
                                                                    preparedStatement = connection.prepareStatement(CompetenceSQL);
                                                                    preparedStatement.setString(1, labelCompetence);
                                                                    resultSet = preparedStatement.executeQuery();
                                                                    if (resultSet.next()) {

                                                                        String IdCompetenceSQL = "SELECT idCompetence FROM Competence Where Competence = ?";
                                                                        try{
                                                                            preparedStatement = connection.prepareStatement(IdCompetenceSQL);
                                                                            preparedStatement.setString(1, labelCompetence);
                                                                            resultSet = preparedStatement.executeQuery();
                                                                            if (resultSet.next()) {
                                                                                String idCompetence = resultSet.getString("idCompetence");
                                                                                if (Intervenant.length() > 0) {
                                                                                    String IntervenantSQL = "SELECT * FROM persone Where NomPersone = ?";
                                                                                    try {
                                                                                        preparedStatement = connection.prepareStatement(IntervenantSQL);
                                                                                        preparedStatement.setString(1, Intervenant);
                                                                                        resultSet = preparedStatement.executeQuery();
                                                                                        if (resultSet.next()) {
                                                                                            String idIntervenantSQL = "SELECT idPersone FROM persone Where NomPersone = ?";
                                                                                            try{
                                                                                                preparedStatement = connection.prepareStatement(idIntervenantSQL);
                                                                                                preparedStatement.setString(1, Intervenant);
                                                                                                resultSet = preparedStatement.executeQuery();
                                                                                                if (resultSet.next()) {
                                                                                                    String idPersone = resultSet.getString("idPersone");
                                                                                                    if(AutreEvenement.length()>0){
                                                                                                        String idEvenement = "SELECT idEvenement FROM evenement WHERE  NomEvenement = '"+NomEvenement+"' ";
                                                                                                        try {
                                                                                                            preparedStatement = connection.prepareStatement(idEvenement);
                                                                                                            resultSet = preparedStatement.executeQuery();
                                                                                                            if(resultSet.next()){
                                                                                                                String idEvenementCoursModif = resultSet.getString("idEvenement");
                                                                                                                String idAutreEventModif = "SELECT idAutreEvenement FROM  autreevenement WHERE idevenement = ?";
                                                                                                                try{
                                                                                                                    preparedStatement = connection.prepareStatement(idAutreEventModif);
                                                                                                                    preparedStatement.setString(1, idEvenementCoursModif);
                                                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                                                    if(resultSet.next()) {
                                                                                                                        String idAutreEventEvenementModif = resultSet.getString("idAutreEvenement");
                                                                                                                        String UpdateEvenemntSQL = "UPDATE evenement SET NomEvenement = '" + NomEvenement + "' , " +
                                                                                                                                "CodePostal = '" + CodePostalEvenemen + "' , " +
                                                                                                                                "Ville = '" + VilleEvenemen + "' , " +
                                                                                                                                "Adresse = '" + AdresseEvenement + "' , " +
                                                                                                                                "Date = '" + DateEvenement + "' , " +
                                                                                                                                "IdCompetence = '" + idCompetence + "' , " +
                                                                                                                                "idPersone = '" + idPersone + "' , " +
                                                                                                                                "idCompetence  = '" + idCompetence + "' WHERE  idevenement = '" + idEvenementCoursModif + "' ";

                                                                                                                        String UpdateConfSQL = "UPDATE autreevenement SET DescriptionAutreEvenement = '"+ AutreEvenement+"' WHERE idAutreEvenement = '"+ idAutreEventEvenementModif +"' ";

                                                                                                                        preparedStatement = connection.prepareStatement(UpdateEvenemntSQL);
                                                                                                                        preparedStatement.execute();
                                                                                                                        preparedStatement = connection.prepareStatement(UpdateConfSQL);
                                                                                                                        preparedStatement.execute();
                                                                                                                        JOptionPane.showMessageDialog(null, "La modification de " + NomEvenement + " a etait prise en compte");
                                                                                                                        UpdateTable();
                                                                                                                        TextFieldClear();
                                                                                                                        TableauTypeEvenement.setVisible(false);
                                                                                                                        TextFieldNomEvenement.setVisible(false);
                                                                                                                        TextFieldVilleEvenement.setVisible(false);
                                                                                                                        TextFieldAdresseEvenement.setVisible(false);
                                                                                                                        TextFieldHeureEvenement.setVisible(false);
                                                                                                                        TextFieldDateEvenement.setVisible(false);
                                                                                                                        TextFieldCodePostalEvenement.setVisible(false);
                                                                                                                        LabelCompetence.setVisible(false);
                                                                                                                        LabelTypeEvenement.setVisible(false);
                                                                                                                        LabelIntervenant.setVisible(false);
                                                                                                                        TextFieldSalleCours.setVisible(false);
                                                                                                                        TextFieldDureeCours.setVisible(false);
                                                                                                                        TextFieldBatimentCours.setVisible(false);
                                                                                                                        TextFieldAutreEvenement.setVisible(false);
                                                                                                                        TextFieldDureeConf.setVisible(false);
                                                                                                                        TableauCompetence.setVisible(false);
                                                                                                                        TableViewIntervenant.setVisible(false);

                                                                                                                    }

                                                                                                                } catch (SQLException throwables) {
                                                                                                                    throwables.printStackTrace();
                                                                                                                }

                                                                                                            }else{

                                                                                                            }
                                                                                                        } catch (SQLException throwables) {
                                                                                                            throwables.printStackTrace();
                                                                                                        }
                                                                                                    }else{
                                                                                                        JOptionPane.showMessageDialog(null, "Veuillez saisir une description de autre evenement");
                                                                                                    }
                                                                                                }else{
                                                                                                    JOptionPane.showMessageDialog(null, "persone");
                                                                                                }
                                                                                            }catch (SQLException throwables){
                                                                                                throwables.printStackTrace();
                                                                                            }
                                                                                        }else{
                                                                                            JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table du dessus");
                                                                                        }
                                                                                    } catch (SQLException throwables) {
                                                                                        throwables.printStackTrace();
                                                                                    }
                                                                                }else {
                                                                                    JOptionPane.showMessageDialog(null, "Un Intervenant est obligatoire");
                                                                                }
                                                                            }else{
                                                                            }
                                                                        }catch (SQLException throwables){
                                                                            throwables.printStackTrace();
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Veuille saisir une donnée présent dans la table de gauche");
                                                                    }
                                                                } catch (SQLException throwables) {
                                                                    throwables.printStackTrace();
                                                                }
                                                            }else {
                                                                JOptionPane.showMessageDialog(null, "Une competence est obligatoire");
                                                            }

                                                        }else {
                                                            JOptionPane.showMessageDialog(null, "Veuillez saisir l'heure sous le format 00:00");
                                                        }
                                                    }else {
                                                        JOptionPane.showMessageDialog(null, "Saisire une Heure Svp");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Veuillez saisir la date sous le format mm/dd/year");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Date Obligaoiree");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Verifier que l'adresse est corect et qu'elle est en Maj ");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "L'Adresse'est obligatoire");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Veuillez rentre le nom de la ville en  Maj");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La ville est obligatoire");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Une erreur a était détecter dans le Code Postal ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Le Code Postal est obligatoire");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Veuillez Verifier que le nom de l'evenment contien que des MAJ et ou des Chifre");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'evenement");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Une erreur est surenue veuillez contacter le support");
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
        stage.centerOnScreen();
        stage.show();
        stage.setTitle("Connexion");
        stage.setScene(scene);
    }

    public void affichermenue(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(true);
        AnchorPaneEvenement.setDisable(true);
        btnMenuAdmin.setVisible(false);
    }

    public void cachermenue(ActionEvent actionEvent) {
        AnchorPaneMenu.setVisible(false);
        AnchorPaneEvenement.setDisable(false);
        btnMenuAdmin.setVisible(true);
    }
}

