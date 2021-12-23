package ChangeKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class UpdatePassword {
	
	public static void updateNewPassword(int userID, String passwordHash) throws SQLException {
		Connection connection = ConnectToDB.openConnection();
		
		// Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
		String sql = "update [User].Password set PasswordHash = ?, ModifiedDate = ? where UserID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		long millis = System.currentTimeMillis();
		Date modifiedDate = new Date(millis);
		stmt.setInt(3, userID);
		stmt.setString(1, passwordHash);
		stmt.setDate(2, modifiedDate);
		// Thực hiện lệnh SQL
		stmt.executeUpdate();
		
		// Đóng kết nối
		connection.close();
	}
	
//	public static void main(String[] args) throws SQLException {
//		String strSalt = GetPassword.getPasswordSalt(1);
//		byte[] salt = Base64.getDecoder().decode(strSalt);
//		String passHash = CreatePass.get_SHA_512_SecurePassword("thangquoc", salt);
//		updateNewPassword(1, passHash);
//	}

}
