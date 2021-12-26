package thongke.thongkehome;
	
import java.sql.ResultSet;
import java.sql.SQLException;

import database.SQLConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.person.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/tamvang_tamtru/TamVang_TamTru.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("thong-ke.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SQLConnection.ConnectData();
		SQLConnection.setList();
		for (InforPerson s : SQLConnection.danhSachNhanKhau)
			System.out.println(s.getPerson().getFullName() + SQLConnection.toPlace(s.getBirthPlace().getNationID(), s.getBirthPlace().getProvinceID(), s.getBirthPlace().getDistrictID(), s.getBirthPlace().getCommuneID()));
			
		try {

			SQLConnection.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
}
