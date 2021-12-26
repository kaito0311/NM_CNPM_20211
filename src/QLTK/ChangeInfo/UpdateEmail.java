package QLTK.ChangeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connect.ConnectToDB;

public class UpdateEmail {
	
	public static void addNewEmail(int userID, String emailAddress) throws SQLException {
		Connection connection = ConnectToDB.openConnection();
		
		// Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
//		String sql = "INSERT INTO [User].Password (UserID,PasswordHash,PasswordSalt,ModifiedDate) VALUES(?,?,?,?)";
//		String sql1 = "insert into [User].EmailAddress (UserID, EmailAddress, ModifiedDate)"
//				+ "values (?,?, '2021-12-12')";
		
		String sql = "update [User].EmailAddress set EmailAddress = ? where UserID = ?";
				
				
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setInt(2, userID);
		stmt.setString(1, emailAddress);
		// Thực hiện lệnh SQL
		stmt.executeUpdate();
		
		// �?óng kết nối
		connection.close();
	}

}
