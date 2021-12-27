package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ConnectToDB {

	public static SimpleDateFormat VNDF = new SimpleDateFormat("dd-MM-yyyy");

	public static Connection openConnection() throws SQLException {
		Connection connection = null;
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// connection =
			// DriverManager.getConnection("jdbc:sqlserver://DAT\\SQLEXPRESS;databaseName=PopulationManagement",
			// "kdat194011", "datbk21094011");
			// System.out.println("Ket noi thanh cong!");

			String dbURL = "jdbc:sqlserver://TANMINH;databaseName=PopulationManagement;user=sa;password=12345";
			connection = DriverManager.getConnection(dbURL);
			// statement = connection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "\n" + e.getClass() + "\n" + e.getCause());
		}

		return connection;
	}

	public static void main(String[] args) throws SQLException {
		openConnection();
	}

}
