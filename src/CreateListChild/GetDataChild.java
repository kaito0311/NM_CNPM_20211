package CreateListChild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class GetDataChild {
	
	public static ResultSet getStudentList() throws SQLException {
		 String sql = "select p.FullName, p.BirthDate, p.Gender, DATEDIFF(year, p.BirthDate, getdate()) as Age, r.BookID\r\n"
		 		+ "from Person.Person p, Person.Residence r\r\n"
		 		+ "where p.PersonID = r.PersonID\r\n"
		 		+ "	and DATEDIFF(year, p.BirthDate, getdate()) < 18";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getStudentList());
	}

}
