package ChangeInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ChangeInfo.UpdateInfo;
import ChangeInfo.UpdatePhone;
import ChangeInfo.UpdateEmail;
import ChangeInfo.GetInfo;

public class MainController implements Initializable{
	
	@FXML
	private TextField txtName, txtPhone, txtEmail;
	
	@FXML
	private Label lbNotice;
	
	@FXML
	private Button SCREEN1_buttonSubmit;
	
	private String fullName, email, phone, roleName, positionName;
	
	@FXML
	private ComboBox cbPosition, cbRole;
	
	private int userID = 3;
	
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
