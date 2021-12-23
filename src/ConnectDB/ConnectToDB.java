package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

	public static Connection openConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://DESKTOP-N17DMQT\\SQLEXPRESS:1433;databaseName=PM;integratedSecurity=true;";
			connection = DriverManager.getConnection(connectionURL, "sa", "quocthang");
			System.out.println("Ket noi thanh cong!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n" + e.getClass() + "\n" + e.getCause());
		}
		
		return connection;
	}
	
	public static void main(String[] args) throws SQLException {
		openConnection();
	}
	
}
