import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController2 {
	
	@FXML
	private Label loginNotice;
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	public void Login(ActionEvent event) {
		if (txtUserName.getText().equals("user") && txtPassword.getText().equals("pass")) {
			loginNotice.setText("Đăng nhập thành công!");
			
		}
		else {
			loginNotice.setText("Tai khoan hoac mat khau khong chinh xac");
		}
	}
}
