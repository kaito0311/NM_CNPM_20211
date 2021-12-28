package MainCreateListScholar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectToDB;

public class GetData {
	
	public static ResultSet getStudentList() throws SQLException {
		 String sql = "select p.personID, p.FullName, p.BirthDate, w.Place, e.Class, r.BookID\n"
		 		+ "		 		from Person.Person p, Person.Work w, Person.Education e, Person.Residence r\n"
		 		+ "		 		where p.PersonID = w.PersonID\n"
		 		+ "		 		and p.PersonID = e.PersonID\n"
		 		+ "		 		and p.PersonID = r.PersonID\n"
		 		+ "		 		and DATEDIFF(year, p.BirthDate, GETDATE()) between 7 and 17";
		 Connection connection = ConnectToDB.openConnection();
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 
		 
		 ResultSet rs = stmt.executeQuery();
		 return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getStudentList());
	}

}
