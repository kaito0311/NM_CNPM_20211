package gift.thongkescholar;

import java.net.URL;
import java.util.ResourceBundle;

import database.SQLConnection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class TKScholarController implements Initializable{
    private Stage stage;
	private Scene scene;
	
	@FXML
	private TableView <Person> giftThongKeTable;
	@FXML
	private TableColumn<Person, String> fullName, birthDate, gender, gift;
	@FXML
	private TableColumn<Person, Number> STT, age, value, bookID;
	@FXML
	private Label valueLabel;
	
	private ObservableList<Person> TKScholarList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		
		TKScholarList = FXCollections.observableArrayList(GetDataScholar.danhSachNhanQua);
		loadTable();
		loadLabel();
	}
	
	private void loadLabel() {
		int sumValue = 0;
		String text = "Tổng giá trị phần quà: ";
		
		for (Person p : TKScholarList)
			sumValue += p.getValue();
		
		text += String.format("%,d", sumValue) + " đồng";
		
		valueLabel.setText(text);
	}

	private void loadTable() {
		STT.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + giftThongKeTable.getItems().indexOf(column.getValue())));
	    fullName.setCellValueFactory(new PropertyValueFactory<Person,String>("fullName"));
	    birthDate.setCellValueFactory(new PropertyValueFactory<Person,String>("birthDate") );
	    gender.setCellValueFactory(new PropertyValueFactory<Person,String>("gender"));     
	    age.setCellValueFactory(new PropertyValueFactory<Person,Number>("age"));
	    bookID.setCellValueFactory(new PropertyValueFactory<Person,Number>("houseID"));
	    gift.setCellValueFactory(new PropertyValueFactory<Person,String>("gift")); 
	    value.setCellValueFactory(new PropertyValueFactory<Person,Number>("value"));
		
		giftThongKeTable.setItems(TKScholarList);
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
	
	public void changeToScholarship(ActionEvent event) {
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
	
	public void changeToCreateListChild(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainCreateListGift/UI_CreateListGift.fxml"));
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

}
