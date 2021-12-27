package MainChangeKey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Base64;

import ConnectDB.ConnectToDB;

public class GetPassword {
	
	public static String getPasswordHash(int userID) throws SQLException {
		 String sql = "select PasswordHash from [User].Password where UserID = ?";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String passwordHash = rs.getString("PasswordHash");
		 return passwordHash;
	}
	
	public static String getPasswordSalt(int userID) throws SQLException {
		String sql = "select PasswordSalt from [User].Password where UserID = ?";
		Connection connection = ConnectToDB.openConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String passwordSalt = rs.getString("PasswordSalt");
		 return passwordSalt;
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getPasswordHash(3));;
		System.out.println(getPasswordSalt(3));
	}

}
