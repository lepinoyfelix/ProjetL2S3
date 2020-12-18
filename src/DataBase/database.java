package DataBase;
import javax.swing.*;
import java.sql.*;


public class database {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/projetl2info", "root", "");
            Statement statement = conn.createStatement();
            String mail = "jean.duppon@gmail.com";
            String sql = "SELECT idPersonnes FROM personne where mail ='"+mail+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(("idPersonnes")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}