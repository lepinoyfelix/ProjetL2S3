package DataBase;
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
            JOptionPane.showMessageDialog(null, e);
            return null;
        }



    }

}

