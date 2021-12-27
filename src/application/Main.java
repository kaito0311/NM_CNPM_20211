package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.SQLConnection;


public class Main extends Application {
	// Thay đổi theo kích thước màn hình người dùng
	public static double Width = 1440;
	public static double Height = 1024;
//	static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//	public static double ScreenRatio = Math.min(1, Math.min(size.width/Width, (size.height - 100)/Height));
	public static double ScreenRatio = 1;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("Application.fxml"));
			root.getSelectionModel().select(4);
			Scene scene = new Scene(root, ScreenRatio*Width, ScreenRatio*Height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Phần mềm quản lý dân cư");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String serverName = "localhost";
//		String dbName = "PopulationManagement";
//		String username = "sa";
//		String password = "admin";
//		String dbURL = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName;
//		
//		SQLConnection sqlConnection = new SQLConnection(dbURL, username, password);			
			
		SQLConnection.ConnectData();
//		new SQLConnection().setList();
		
//			// Kiểm tra kết nối đến SQL Server
//			if (sqlConnection != null) {
//				 System.out.println("Connected");
//				 DatabaseMetaData dm = (DatabaseMetaData) sqlConnection.getMetaData();
//			     System.out.println("Driver name: " + dm.getDriverName());
//			     System.out.println("Driver version: " + dm.getDriverVersion());
//			     System.out.println("Product name: " + dm.getDatabaseProductName());
//			     System.out.println("Product version: " + dm.getDatabaseProductVersion());
//			}

		launch(args);
	}
}
