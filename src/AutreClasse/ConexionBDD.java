package AutreClasse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class ConexionBDD {
    Connection conn = null;
    public static Connection connectdb()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetl2info", "root", "");
            return conn;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Veuillez lancer xampp/wanpp ou tout autre serveur web local");
            return null;
        }
    }

}

