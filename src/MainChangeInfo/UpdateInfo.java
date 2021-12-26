package MainChangeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class UpdateInfo {
	
	public static void addNewInfo(int userID, String fullName, String roleID, String positionID) throws SQLException {
		Connection connection = ConnectToDB.openConnection();
		
		// Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
//		String sql = "INSERT INTO [User].Password (UserID,PasswordHash,PasswordSalt,ModifiedDate) VALUES(?,?,?,?)";
//		String sql1 = "insert into [User].[User] (FullName, PositionID, RoleID, CreatedDate)"
//				+ "values (?,(select PositionID from [User].Position where Name = ?),(select RoleID from [User].Role where Name = ?), '2021-12-19')";
		String sql1 = "update [User].[User] set FullName = ?,"
				+ "PositionID = (select PositionID from [User].Position where Name = ?),"
				+ "RoleID =  (select RoleID from [User].Role where Name = ?)"
				+ "where UserID = ?";		
				
		PreparedStatement stmt = connection.prepareStatement(sql1);
		
		stmt.setString(1, fullName);
		stmt.setString(2, positionID);
		stmt.setString(3, roleID);
		stmt.setInt(4, userID);
		// Thực hiện lệnh SQL
		stmt.executeUpdate();
		
		// Đóng kết nối
		connection.close();
	}

}
