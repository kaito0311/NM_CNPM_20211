package managehouseholdbook;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class ConnectDatabase {
  public static Connection connection;
  public static Statement statement; 

  public static void ConnectData() {
    try {
      String dbURL = "jdbc:sqlserver://TANMINH;databaseName=PopulationManagement;user=sa;password=12345";
      connection = DriverManager.getConnection(dbURL);
      statement = connection.createStatement();
      

    } catch (SQLException ex) {
      System.err.println("Cannot connect database, " + ex);

    }
  }
}