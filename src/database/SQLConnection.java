package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.person.Person;

public class SQLConnection {
	private String url;
	private String username;
	private String password;
	
	public SQLConnection(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public void queryPerson_Person() throws SQLException{
		
		Connection connection = DriverManager.getConnection(url, username, password);
		String query = "select * from Person.Person";
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery(query);
		
		
		while (result.next())
			System.out.println(result.getString("FullName"));
		
		connection.close();
			
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
