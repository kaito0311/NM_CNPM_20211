package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.SQLConnection;
import dotuoi.*;
import gioitinh.*;
import tamvang_tamtru.*;
import thoigian.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("ThongKe.fxml"));
			root.getSelectionModel().select(3);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("thong-ke.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SQLConnection sqlConnection = new SQLConnection("jdbc:sqlserver://DAT\\SQLEXPRESS;databaseName=PopulationManagement", "kdat194011", "datbk21094011");
		
//		sqlConnection.queryPerson_Person();
		
		launch(args);
	}
}
