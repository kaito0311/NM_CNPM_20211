package MainGift;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
	private TableColumn<GiftChild, Number> price;	
	
	@FXML
	private ComboBox<String> chooseEvent;
	
	private ObservableList<GiftChild> giftList;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
	}
	
	private void loadTable() {
		ObservableList<String> list = FXCollections.observableArrayList("a", "b");
		giftList = FXCollections.observableArrayList(
			new GiftChild("0-5", list, 0),
			new GiftChild("6-11", list, 0),
			new GiftChild("12-15", list, 0),
			new GiftChild("16-18", list, 0));
		
	    giftName.setCellValueFactory(new PropertyValueFactory<GiftChild,String>("giftName"));
	    price.setCellValueFactory(new PropertyValueFactory<GiftChild,Number>("price"));
	    age.setCellValueFactory(new PropertyValueFactory<GiftChild,String>("age"));
		
		listGiftChild.setItems(giftList);
	}

	public void selectEvent(ActionEvent event) {
		
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

}
