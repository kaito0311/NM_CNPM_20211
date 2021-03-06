package MainChangeKey;

import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;

import Main.LoginController;
import database.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class ControllerChangeKey implements Initializable{
	
	@FXML
	private PasswordField passNow, passNew, passNewCf;
	
	@FXML
	private Label lbNoti;
	
	private String strPassNowEnter, strPassNew, strPassNewCf;
	private String strPassHashNowEnter, strPassHashNow, strPassSalt, strPassHashNew;
	private int userID;

	private Stage stage;
	private Scene scene;

	public void changeToTimKiem(ActionEvent event) {
		try {
			SQLConnection.ConnectData();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/Application.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}	
	public void changeToThongKeHome(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/thongkehome/Thong_Ke.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void changeToChangeInfo(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainChangeInfo/UI_ChangeInfo.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	public void changeToManageHousehold(ActionEvent event) {
		try {
		ConnectDatabase.ConnectData();
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/managehouseholdbook/HouseholdRegistrationBookManagement.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
	} catch(Exception e) {
		e.printStackTrace();
		}
	}
	
	public void changeToHomePage(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Main/UI_HomePage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void changeToCreateListScholar(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainCreateListScholar/UI_CreateListScholar.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void changePass(ActionEvent event) throws SQLException {
		strPassNowEnter = passNow.getText();
		strPassNew = passNew.getText();
		strPassNewCf = passNewCf.getText();
		
		strPassHashNow = GetPassword.getPasswordHash(userID);
		strPassSalt = GetPassword.getPasswordSalt(userID);
		
		byte[] salt = Base64.getDecoder().decode(strPassSalt);
		
		strPassHashNowEnter = CreatePass.get_SHA_512_SecurePassword(strPassNowEnter, salt);
		
		if (strPassNowEnter.isEmpty() || strPassNew.isEmpty() || strPassNewCf.isEmpty()) {
			lbNoti.setText("Vui l??ng nh???p ????? th??ng tin!");
		} else {
			if (strPassHashNow.equals(strPassHashNowEnter)) {
				if (strPassNew.equals(strPassNewCf)) {
					strPassHashNew = CreatePass.get_SHA_512_SecurePassword(strPassNew, salt);
					UpdatePassword.updateNewPassword(userID, strPassHashNew);
					lbNoti.setText("Thay ?????i m???t kh???u th??nh c??ng");
				} else {
					lbNoti.setText("C???n x??c nh???n m???t kh???u ????ng v???i m???t kh???u ???? nh???p!");
				}
				
			} else {
				lbNoti.setText("M???t kh???u ch??a ch??nh x??c!");
			}
		}
		
		
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lbNoti.setText("");
		userID = LoginController.getUserID();
		
	}
	
//	public static void main(String[] args) {
//		System.out.println(strPassNow);
//		System.out.println(strPassNew);
//		System.out.println(strPassNewCf);
//		
//	}

}
