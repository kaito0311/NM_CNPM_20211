package MainChangeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class UpdatePhone {
	
	public static void addNewPhone(int userID, String phoneNumber) throws SQLException {
		Connection connection = ConnectToDB.openConnection();
		
		// Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
//		String sql = "INSERT INTO [User].Password (UserID,PasswordHash,PasswordSalt,ModifiedDate) VALUES(?,?,?,?)";
//		String sql1 = "insert into [User].PhoneNumber (UserID, PhoneNumber, ModifiedDate)"
//				+ "values (?,?, '2021-12-12')";
		
		String sql = "update [User].PhoneNumber set PhoneNumber = ? where UserID = ?";
				
				
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setInt(2, userID);
		stmt.setString(1, phoneNumber);
		// Thực hiện lệnh SQL
		stmt.executeUpdate();
		
		// Đóng kết nối
		connection.close();
	}

}
