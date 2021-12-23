package GiftChild;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class GiftController implements Initializable{
	
	@FXML
	TableView<GiftChild> listGiftChild;
	
	@FXML
	TableColumn<GiftChild, String> giftName, price, quantity;
	
	@FXML
	ComboBox<String> chooseEvent;
	
	ObservableList<GiftChild> data;
	ObservableList<String> listEvent;
    ArrayList<GiftChild> listGift;
    
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
