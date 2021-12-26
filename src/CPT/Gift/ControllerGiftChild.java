package CPT.Gift;

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
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerGiftChild implements Initializable{
	
	@FXML
	TableView<GiftChild> listGiftChild;
	
	@FXML
	TableColumn<GiftChild, String> giftName, price, quantity;
	
	@FXML
	ComboBox<String> chooseEvent;
	
	ObservableList<GiftChild> data;
	ObservableList<String> listEvent;
    ArrayList<GiftChild> listGift;
    
    private Stage stage;
	private Scene scene;
	
	public void changeToHomePage(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Login/UI_HomePage.fxml"));
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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/QLTK/ChangeInfo/UI_ChangeInfo.fxml"));
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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/CPT/CreateListGift/UI_CreateListGift.fxml"));
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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/CPT/CreateListScholar/UI_CreateListScholar.fxml"));
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

    
    public void selectEvent(ActionEvent event) {
    	String strEvent = chooseEvent.getSelectionModel().getSelectedItem().toString();
    	listGift = new ArrayList<GiftChild>();
    	if (strEvent.equals("Tết Trung Thu")) {
    		try {
    			ResultSet rs = GetGiftChild.getMidAutumnGift();
    			while (rs.next()) {
    				GiftChild g = new GiftChild(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
    				listGift.add(g);
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else if (strEvent.equals("Tết Thiếu Nhi")) {
    		try {
    			ResultSet rs = GetGiftChild.getChildrenDayGift();
    			while (rs.next()) {
    				GiftChild g = new GiftChild(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
    				listGift.add(g);
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else if (strEvent.equals("Tết Nguyên Đán")) {
    		try {
    			ResultSet rs = GetGiftChild.getNewYearGift();
    			while (rs.next()) {
    				GiftChild g = new GiftChild(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
    				listGift.add(g);
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	data = FXCollections.observableArrayList(
			    listGift
			    );
			    
	    giftName.setCellValueFactory(
	    new PropertyValueFactory<GiftChild,String>("giftName")
	    );
	    price.setCellValueFactory(
	    		new PropertyValueFactory<GiftChild,String>("price")
	    );
	    quantity.setCellValueFactory(
	    new PropertyValueFactory<GiftChild,String>("quantity")
	    );
	    
	    listGiftChild.setItems(data);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		listEvent = FXCollections.observableArrayList(
				"Tết Nguyên Đán",
				"Tết Thiếu Nhi",
				"Tết Trung Thu"
				);
		chooseEvent.setItems(listEvent);
		
	}

}
