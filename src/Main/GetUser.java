package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class GetUser {
	
//	public static ResultSet getUserPassHash(String email) throws SQLException {
//		 String sql = "select up.PasswordHash\r\n"
//		 		+ "from [User].EmailAddress ue, [User].Password up\r\n"
//		 		+ "where ue.UserID = up.UserID\r\n"
//		 		+ "	and ue.EmailAddress = ?";
//		 Connection connection = ConnectToDB.openConnection();
//		 PreparedStatement stmt = connection.prepareStatement(sql);
//		 
//		 stmt.setString(1, email);
//		 ResultSet rs = stmt.executeQuery();
//		 rs.next();
//		 return rs;
//	}
	
	public static ResultSet getUserID(String email) throws SQLException {
		 String sql = "select UserID\r\n"
		 		+ "from [User].EmailAddress \r\n"
		 		+ "where EmailAddress = ?";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setString(1, email);
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		ResultSet rs = getUserID("thang@gmail.com");
		rs.next();
		System.out.println(rs.getString("UserID"));
	}
	
}