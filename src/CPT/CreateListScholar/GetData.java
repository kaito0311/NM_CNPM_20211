package CPT.CreateListScholar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connect.ConnectToDB;

public class GetData {
	
	public static ResultSet getStudentList() throws SQLException {
		 String sql = "select p.FullName, p.BirthDate, w.Place, e.Class, r.BookID\r\n"
		 		+ "from Person.Person p, Person.Work w, Person.Education e, Person.Residence r\r\n"
		 		+ "where p.PersonID = w.PersonID\r\n"
		 		+ "	and p.PersonID = e.PersonID\r\n"
		 		+ "	and p.PersonID = r.PersonID\r\n"
		 		+ "	and DATEDIFF(year, p.BirthDate, GETDATE()) < 18";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getStudentList());
	}

}
