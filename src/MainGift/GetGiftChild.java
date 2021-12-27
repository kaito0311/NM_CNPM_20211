package MainGift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectToDB;

public class GetGiftChild {
	
	public static ArrayList<String> getGiftID(String event) {
		ArrayList<String> giftID = new ArrayList<String>();
		String sql = "select GiftID from Gift.Gift where Event = N'" + event + "'";
		Connection connection;
		try {
			connection = ConnectToDB.openConnection();
		
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println(event + " quà:");
			while(rs.next()) {
				System.out.println(rs.getString("GiftID"));
				giftID.add(rs.getString("GiftID"));
			}
		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return giftID;
	}
	
	public static String getGift (String giftID) {
		String gift = "";
		String sql = "select * from Gift.Detail d join Gift.Product p on p.ProductID = d.ProductID where d.GiftID = '" + giftID + "'";
		
		Connection connection;
		try {
			connection = ConnectToDB.openConnection();
		
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		 
			while(rs.next()) {
				gift += rs.getInt("Quantity") + " " + rs.getString("Name") + ", ";
			}
		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gift.substring(0, gift.length() - 2);
	}
	
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
