package MainGift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class GetGiftChild {
	
	public static ResultSet getMidAutumnGift() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Product p, Gift.Detail d\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and p.ProductID = d.ProductID\r\n"
		 		+ "	and g.Event = N'Tết Trung Thu'";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static ResultSet getNewYearGift() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Product p, Gift.Detail d\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and p.ProductID = d.ProductID\r\n"
		 		+ "	and g.Event = N'Tết Nguyên Đán'";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static ResultSet getChildrenDayGift() throws SQLException {
		 String sql = "select p.Name, p.Price, d.Quantity\r\n"
		 		+ "from Gift.Gift g, Gift.Product p, Gift.Detail d\r\n"
		 		+ "where g.GiftID = d.GiftID\r\n"
		 		+ "	and p.ProductID = d.ProductID\r\n"
		 		+ "	and g.Event = N'Tết Thiếu Nhi'";
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
