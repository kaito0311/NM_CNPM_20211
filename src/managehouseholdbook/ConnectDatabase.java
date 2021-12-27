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
      // connection = DriverManager.getConnection("jdbc:sqlserver://DAT\\SQLEXPRESS;databaseName=PopulationManagement", "kdat194011", "datbk21094011");
      // statement = connection.createStatement();
      String dbURL = "jdbc:sqlserver://TANMINH;databaseName=PopulationManagement;user=sa;password=12345";
      connection = DriverManager.getConnection(dbURL);
      statement = connection.createStatement();

    } catch (SQLException ex) {
      System.err.println("Cannot connect database, " + ex);

    }
  }
}