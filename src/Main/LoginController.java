package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import MainChangeKey.CreatePass;
import MainChangeKey.GetPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private Label loginNotice;
	
	@FXML
	private TextField enterUserName;
	
	@FXML
	private TextField enterPassword;
	
	private Stage stage;
	private Scene scene;
	
	private static int userID;
	
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
	
	public void Login(ActionEvent event) throws SQLException {
		String userName = enterUserName.getText().toString();
		String password = enterPassword.getText().toString();
		ResultSet rs = GetUser.getUserID(userName);
		if (rs.next()) {
			int userID = rs.getInt("UserID");
			this.userID = userID;
			String passHash = GetPassword.getPasswordHash(userID);
			String passSalt = GetPassword.getPasswordSalt(userID);
			byte[] salt = Base64.getDecoder().decode(passSalt);
			String getPassHashEnter = CreatePass.get_SHA_512_SecurePassword(password, salt);
			if (passHash.equals(getPassHashEnter)) {
				changeToHomePage(event);
			} else {
				loginNotice.setText("Mật khẩu không chính xác");
			}
		} else {
			loginNotice.setText("Tài khoản không tồn tại");
		}
	}

	public static int getUserID() {
		return userID;
	}

//	public void setUserID(int userID) {
//		this.userID = userID;
//	}
}
