package CPT.Scholar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connect.ConnectToDB;

public class GetScholar {
	
	public static ResultSet getHSG() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Detail d, Gift.Product p\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and d.ProductID = p.ProductID\r\n"
		 		+ "	and g.Event = N'Thành tích đặc biệt'";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static ResultSet getHSTT() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Detail d, Gift.Product p\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and d.ProductID = p.ProductID\r\n"
		 		+ "	and g.Event = N'Học sinh tiên tiến'";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static ResultSet getOther() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Detail d, Gift.Product p\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and d.ProductID = p.ProductID\r\n"
		 		+ "	and g.Event = N'Khác'";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
//	public static void main(String[] args) throws SQLException {
//		ResultSet rs = getStudentList();
//		while (rs.next()) {
//			System.out.println(rs.getString("Name") + " " + rs.getString("Quantity"));
//		}
//	}

}
