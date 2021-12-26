package MainChangeInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Main.LoginController;


public class ControllerChangeInfo implements Initializable{
	
	@FXML
	private TextField txtName, txtPhone, txtEmail;
	
	@FXML
	private Label lbNotice;
	
	@FXML
	private Button SCREEN2_buttonSubmit;
	
	private String fullName, email, phone, roleName, positionName;
	
	@FXML
	private ComboBox<String> cbPosition, cbRole;
	
	private int userID;
	
	private Stage stage;
	private Scene scene;
	
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
	
	public void logOut(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Main/UI_Login.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root,800,670);
//			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			stage.setScene(scene);
			stage.setX(600);
			stage.setY(100);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeToChangeKey(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainChangeKey/UI_ChangeKey.fxml"));
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
	
	public void getInfoFromText(ActionEvent event) throws SQLException {
		fullName = txtName.getText();
		phone = txtPhone.getText();
		email = txtEmail.getText();
//		roleName = txtRole.getText();
//		positionName = txtPosition.getText();
		if (fullName.equals("") || phone.equals("") || email.equals("")) {
			lbNotice.setText("Vui lòng nhập đủ thông tin!");
		} else {
			positionName = cbPosition.getSelectionModel().getSelectedItem().toString();
			roleName = cbRole.getSelectionModel().getSelectedItem().toString();
			UpdateInfo.addNewInfo(userID, fullName, roleName, positionName);
			UpdatePhone.addNewPhone(userID, phone);
			UpdateEmail.addNewEmail(userID, email);
			lbNotice.setText("Thay đổi thành công");
		}
		
		
	}
	
	public void selectPosition(ActionEvent event) {
		positionName = cbPosition.getSelectionModel().getSelectedItem().toString();
	}
	
	public void selectRole(ActionEvent event) {
		roleName = cbRole.getSelectionModel().getSelectedItem().toString();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userID = LoginController.getUserID();
		// TODO Auto-generated method stub
		ObservableList<String> pstList = 
			    FXCollections.observableArrayList(
			        "Tổ trưởng",
			        "Tổ phó",
			        "Thủ quỹ"
			    );
		cbPosition.setItems(pstList);
		ObservableList<String> rList = 
			    FXCollections.observableArrayList(
			        "Admin",
			        "Editor"
			    );
		cbRole.setItems(rList);
		
		try {
			txtName.setText(GetInfo.getName(userID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			txtEmail.setText(GetInfo.getEmail(userID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			txtPhone.setText(GetInfo.getPhone(userID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cbPosition.setValue(GetInfo.getPosition(userID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cbRole.setValue(GetInfo.getRole(userID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lbNotice.setText("");
		
	}
	

}
