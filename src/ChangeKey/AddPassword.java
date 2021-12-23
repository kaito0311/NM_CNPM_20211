package ChangeKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class AddPassword {
	
	public static void addNewPassword(int userID, String passwordHash, String passwordSalt) throws SQLException {
		Connection connection = ConnectToDB.openConnection();
		
		// Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
		String sql = "INSERT INTO [User].Password (UserID,PasswordHash,PasswordSalt,ModifiedDate) VALUES(?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		long millis = System.currentTimeMillis();
		Date modifiedDate = new Date(millis);
		stmt.setInt(1, userID);
		stmt.setString(2, passwordHash);
		stmt.setString(3, passwordSalt);
		stmt.setDate(4, modifiedDate);
		// Thực hiện lệnh SQL
		stmt.executeUpdate();
		
		// Đóng kết nối
		connection.close();
	}

}
