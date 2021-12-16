package dotuoi;

import java.net.URL;
import java.util.ResourceBundle;
import gioitinh.*;
import tamvang_tamtru.*;
import thoigian.*;
import application.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class DoTuoiController implements Initializable{
	private Stage stage;
	private Scene scene;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeToThongKeDoTuoi(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/dotuoi/DoTuoi.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToThongKeGioiTinh(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/gioitinh/GioiTinh.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void changeToThongKeTamVangTru(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/tamvang_tamtru/TamVang_TamTru.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToThongKeThoiGian(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/thoigian/ThoiGian.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
