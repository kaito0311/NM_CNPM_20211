package gift.thongkegift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectDB.ConnectToDB;
import MainGift.GetGiftChild;
import database.SQLConnection;

public class GetDataGiving {
	
	public static ArrayList<Person> danhSachNhanQua = new ArrayList<Person>();
	
	public static void setGivingList() {
		danhSachNhanQua.clear();
		try {
		Connection connection = ConnectToDB.openConnection();
		String sql = "select p.FullName, p.BirthDate, p.Gender, DATEDIFF(year, p.BirthDate, getdate()) as Age, gv.GiftID, re.BookID\r\n"
				+ "from Person.Person p\r\n"
				+ "	join Gift.Giving gv on p.PersonID = gv.PersonID\r\n"
				+ "	join Gift.Gift gi on gi.GiftID = gv.GiftID\r\n"
				+ "	join Gift.Detail d on d.GiftID = gv.GiftID\r\n"
				+ "	join Gift.Product pr on pr.ProductID = d.ProductID\r\n"
				+ "	join Person.Residence re on re.PersonID = p.PersonID";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			danhSachNhanQua.add(new Person(rs.getString("FullName"), String.valueOf(ConnectToDB.VNDF.format(rs.getDate("BirthDate"))), rs.getString("Gender"), rs.getInt("Age"), rs.getString("BookID"), GetGiftChild.getGift(rs.getString("GiftID")), getValue(rs.getString("GiftID"))));
		}
		
		connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static int getValue (String giftID) {
		int value = 0;
		String sql = "select * from Gift.Detail d join Gift.Product p on p.ProductID = d.ProductID where d.GiftID = ?";
		
		Connection connection;
		try {
			connection = ConnectToDB.openConnection();
		
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, giftID);
			ResultSet rs = stmt.executeQuery();
		 
			while(rs.next()) {
				value += rs.getInt("Quantity") * rs.getInt("Price");
			}
		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
}
