package gift.thongkegift;

import java.net.URL;
import java.util.ArrayList;
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

public class ThongKeGiftController implements Initializable{
    private Stage stage;
	private Scene scene;
	
//	@FXML
//	public TableView <Person> giftThongKeTable;
//	@FXML
//	public TableColumn<Person, String> fullName, birthDate, event, gift;
//	@FXML
//	public TableColumn<Person, Number> STT, year, value, bookID;
//	@FXML
//	public Label valueLabel;
	@FXML
	private TableView <Person> giftThongKeTable;
	@FXML
	private TableColumn<Person, String> fullName;
	@FXML
	private TableColumn<Person, String> birthDate;
	@FXML
	private TableColumn<Person, String> event;
	@FXML
	private TableColumn<Person, String> gift;
	@FXML
	private TableColumn<Person, Number> STT;
	@FXML
	private TableColumn<Person, Number> year;
	@FXML
	private TableColumn<Person, Number> value;
	@FXML
	private TableColumn<Person, Number> bookID;
	@FXML
	private Label valueLabel;
	
	private ObservableList<Person> giftThongKeList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		SQLConnection.ConnectData();
		GetDataGiving.setGivingList();
		SQLConnection.DisconnectData();
		giftThongKeList = FXCollections.observableArrayList(GetDataGiving.danhSachNhanQua);
		loadTable();
		loadLabel();
	}
	
	private void loadLabel() {
		int sumValue = 0;
		String text = "Tổng giá trị phần quà: ";
		
		for (Person p : giftThongKeList)
			sumValue += p.getValue();
		
		text += String.format("%,d", sumValue) + " đồng";
		
		valueLabel.setText(text);
	}

	private void loadTable() {
		STT.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + giftThongKeTable.getItems().indexOf(column.getValue())));
	    fullName.setCellValueFactory(new PropertyValueFactory<Person,String>("fullName"));
	    birthDate.setCellValueFactory(new PropertyValueFactory<Person,String>("birthDate") );
	    event.setCellValueFactory(new PropertyValueFactory<Person,String>("event"));     
	    year.setCellValueFactory(new PropertyValueFactory<Person,Number>("year"));
	    bookID.setCellValueFactory(new PropertyValueFactory<Person,Number>("houseID"));
	    gift.setCellValueFactory(new PropertyValueFactory<Person,String>("gift")); 
	    value.setCellValueFactory(new PropertyValueFactory<Person,Number>("value"));
		
		giftThongKeTable.setItems(giftThongKeList);
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
	
	public void changeToGiftChild(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainGift/UI_Gift.fxml"));
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
