package MainGift;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ConnectDB.ConnectToDB;
import MainCreateListGift.CreateListChildController;
import MainCreateListGift.Person;
import database.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class GiftController implements Initializable{
    
    private Stage stage;
	private Scene scene;
	
	@FXML
	private TableView<GiftChild> listGiftChild;
	@FXML
	private TableColumn<GiftChild, String> giftName, age;	
	@FXML
	private ComboBox<String> chooseEvent;
	@FXML
	private ComboBox<Number> yearBox;
	@FXML
	private Label errorLabel;

	private ObservableList<String> giftIDList;
	private ObservableList<String> list = FXCollections.observableArrayList();
	private ObservableList<GiftChild> giftList = FXCollections.observableArrayList(
			new GiftChild("0-5 tuổi", list),
			new GiftChild("6-11 tuổi", list),
			new GiftChild("12-15 tuổi", list),
			new GiftChild("16-18 tuổi", list));


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
		loadButton();
	}
	
	private void loadButton() {
		ObservableList<String> listEvent = FXCollections.observableArrayList(
				"Tết Trung Thu", "Tết Nguyên Đán", "Ngày Quốc tế Thiếu nhi"
		);
		ObservableList<Number> yearList = FXCollections.observableArrayList();
		
		chooseEvent.setItems(listEvent);
		
		int year = LocalDate.now().getYear();
		
		for (int i = -2; i<3; i++) {
			yearList.add(year + i);
		}
		
		yearBox.setItems(yearList);
	}
	
	private void loadTable() {	
	    giftName.setCellValueFactory(new PropertyValueFactory<GiftChild,String>("giftName"));
	    age.setCellValueFactory(new PropertyValueFactory<GiftChild,String>("age"));
		
		listGiftChild.setItems(giftList);
	}

	public void selectEvent(ActionEvent event) {
		giftList.clear();
		list.clear();
		
		if(yearBox.getSelectionModel().getSelectedItem() == null) {
			errorLabel.setText("Vui lòng chọn năm trước!");
			return;
		}
		
		errorLabel.setText("");
    	String strEvent = chooseEvent.getSelectionModel().getSelectedItem().toString();
    	giftIDList = FXCollections.observableArrayList(GetGiftChild.getGiftID(strEvent));
    	for (String s : giftIDList) {
    		s = GetGiftChild.getGift(s);
    		list.add(s);
    	}
    	
    	giftList = FXCollections.observableArrayList(
    			new GiftChild("0-5 tuổi", list),
    			new GiftChild("6-11 tuổi", list),
    			new GiftChild("12-15 tuổi", list),
    			new GiftChild("16-18 tuổi", list));
    	

    	listGiftChild.setItems(giftList);
	}
	
	public void handleYearBox(ActionEvent event) {
		giftList.clear();
		list.clear();
		chooseEvent.getItems().clear();
		chooseEvent.setItems(FXCollections.observableArrayList("Tết Trung Thu", "Tết Nguyên Đán", "Ngày Quốc tế Thiếu nhi"));
	}
	
	public void handleSaveList(ActionEvent event) {
		for (int i=0; i<4; i++) {
			if (giftList.get(i).getGiftName().getSelectionModel().getSelectedItem() == null) {
				errorLabel.setText("Vui lòng chọn đủ phần quà!");
				return;
			}
			System.out.println(giftList.get(i).getGiftName().getSelectionModel().getSelectedItem());
		}
			
		errorLabel.setText("");
		
    	String strEvent = chooseEvent.getSelectionModel().getSelectedItem();	
		int year = (int) yearBox.getSelectionModel().getSelectedItem();
		System.out.println(strEvent + " " + String.valueOf(year));
		
		try {
			String sql;
			Connection connection = ConnectToDB.openConnection();
			PreparedStatement stmt;
			sql = "delete from Gift.Giving \n"
					+ "where givingID = (\n"
					+ "	select gi.GivingID\n"
					+ "	from Gift.Giving gi\n"
					+ "	join Gift.Gift g on g.GiftID = gi.GiftID \n"
					+ "	where g.Event = ?\n"
					+ "	and gi.Year = ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, strEvent);
			System.out.println(sql);
			stmt.setInt(2, year);
			System.out.println(sql);
			stmt.executeUpdate();

			for (Person child : CreateListChildController.listPerson) {			
				sql = "insert into Gift.Giving\n"
						+ "	(PersonID, GiftID, Year)\n"
						+ "values (? ,?, ?)";
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, child.getPersonID());
				stmt.setString(2, receiveGift(child));
				stmt.setInt(3, year);
				stmt.executeUpdate();
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String receiveGift (Person child) {
		String event = String.valueOf(chooseEvent.getSelectionModel().getSelectedIndex() + 1);
		int age = child.getAge();
		if (age < 6) {
			System.out.println(event + convertToABC(giftList.get(0).getGiftName().getSelectionModel().getSelectedIndex()));
			return event + convertToABC(giftList.get(0).getGiftName().getSelectionModel().getSelectedIndex());
		}
		if (age < 12) {
			System.out.println(event + convertToABC(giftList.get(1).getGiftName().getSelectionModel().getSelectedIndex()));
			return event + convertToABC(giftList.get(1).getGiftName().getSelectionModel().getSelectedIndex());
		}
		if (age < 16) {
			System.out.println(event + convertToABC(giftList.get(2).getGiftName().getSelectionModel().getSelectedIndex()));
			return event + convertToABC(giftList.get(2).getGiftName().getSelectionModel().getSelectedIndex());
		}
		
		System.out.println(event + convertToABC(giftList.get(3).getGiftName().getSelectionModel().getSelectedIndex()));
		return event + convertToABC(giftList.get(3).getGiftName().getSelectionModel().getSelectedIndex());
	}
	
	private String convertToABC (int i) {
		if (i == 0)
			return "A";
		if (i == 1)
			return "B";
		if (i == 2)
			return "C";
		return "???";
	}
  
//    public void selectEvent(ActionEvent event) {
//    	String strEvent = chooseEvent.getSelectionModel().getSelectedItem().toString();
//    	listGift = new ArrayList<GiftChild>();
//    	
//    	if (strEvent.equals("Tết Trung Thu")) {
//    		try {
//    			
//    			ResultSet rs = GetGiftChild.getMidAutumnGift();
//    			while (rs.next()) {
//    				GiftChild g = new GiftChild(rs.getString("Name"), GetGiftChild.getGiftData("Tết Trung Thu"), rs.getString("Quantity"));
//    				listGift.add(g);
//    			}
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    	} else if (strEvent.equals("Tết Thiếu Nhi")) {
//    		try {
//    			ResultSet rs = GetGiftChild.getChildrenDayGift();
//    			while (rs.next()) {
//    				GiftChild g = new GiftChild(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
//    				listGift.add(g);
//    			}
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    	} else if (strEvent.equals("Tết Nguyên Đán")) {
//    		try {
//    			ResultSet rs = GetGiftChild.getNewYearGift();
//    			while (rs.next()) {
//    				GiftChild g = new GiftChild(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
//    				listGift.add(g);
//    			}
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    	}
//    	data = FXCollections.observableArrayList(
//			    listGift
//			    );
//			    
//	    giftName.setCellValueFactory(
//	    new PropertyValueFactory<GiftChild,String>("giftName")
//	    );
//	    price.setCellValueFactory(
//	    		new PropertyValueFactory<GiftChild,String>("price")
//	    );
//	    quantity.setCellValueFactory(
//	    new PropertyValueFactory<GiftChild,String>("quantity")
//	    );
//	    
//	    listGiftChild.setItems(data);
//    }
//
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//		listEvent = FXCollections.observableArrayList(
//				"Tết Nguyên Đán",
//				"Tết Thiếu Nhi",
//				"Tết Trung Thu"
//				);
//		chooseEvent.setItems(listEvent);
//		
//	}
	
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
	public void changeToThongKeGift(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/gift/thongkegift/ThongKeGift.fxml"));
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
}
