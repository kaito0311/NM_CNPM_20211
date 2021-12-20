package ChangeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import ConnectDB.ConnectToDB;

public class GetInfo {
	
	public static String getName(int userID) throws SQLException {
		 String sql = "select FullName from [User].[User] where UserID = ?";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String fullName = rs.getString("FullName");
		 return fullName;
	}
	
	public static String getPhone(int userID) throws SQLException {
		String sql = "select PhoneNumber from [User].PhoneNumber where UserID = ?";
		Connection connection = ConnectToDB.openConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String phone = rs.getString("PhoneNumber");
		 return phone;
		
	}
	
	public static String getEmail(int userID) throws SQLException {
		String sql = "select EmailAddress from [User].EmailAddress where UserID = ?";
		Connection connection = ConnectToDB.openConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String email = rs.getString("EmailAddress");
		 return email;
		
	}
	
	public static String getPosition(int userID) throws SQLException {
		 String sql = "select p.Name from [User].[User] u, [User].Position p where u.PositionID = p.PositionID and u.UserID = ?";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String positionName = rs.getString("Name");
		 return positionName;
	}
	
	public static String getRole(int userID) throws SQLException {
		 String sql = "select r.Name from [User].[User] u, [User].Role r where u.RoleID = r.RoleID and u.UserID = ?";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 stmt.setInt(1, userID);
		 ResultSet rs = stmt.executeQuery();
		 rs.next();
		 String roleName = rs.getString("Name");
		 return roleName;
	}
	
	
//	
//	public static void main(String[] args) throws SQLException {
//		System.out.println(getName(3));;
//		System.out.println(getPhone(3));
//		System.out.println(getPosition(3));
//		System.out.println(getRole(3));
//	}

}
