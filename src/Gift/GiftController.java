package Gift;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class GiftController implements Initializable{
	
	@FXML
	TableView<Gift> tbHSG, tbHSTT, tbOther;
	
	@FXML
	TableColumn<Gift, String> giftNameHSG, priceHSG, quantityHSG, giftNameHSTT, priceHSTT, quantityHSTT, giftNameOther, priceOther, quantityOther;
	
	ObservableList<Gift> dataHSG, dataHSTT, dataOther;
    ArrayList<Gift> listGiftHSG, listGiftHSTT, listGiftOther;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		listGiftHSG = new ArrayList<Gift>();
		listGiftHSTT = new ArrayList<Gift>();
		listGiftOther = new ArrayList<Gift>();
		try {
			ResultSet rs = GetGift.getHSG();
			while (rs.next()) {
				Gift g = new Gift(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
				listGiftHSG.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = GetGift.getHSTT();
			while (rs.next()) {
				Gift g = new Gift(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
				listGiftHSTT.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = GetGift.getOther();
			while (rs.next()) {
				Gift g = new Gift(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
				listGiftOther.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    dataHSG = FXCollections.observableArrayList(
			    listGiftHSG
			    );
			    
	    giftNameHSG.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("giftName")
	    );
	    giftNameHSG.setCellFactory(TextFieldTableCell.forTableColumn());
	    priceHSG.setCellValueFactory(
	    		new PropertyValueFactory<Gift,String>("price")
	    );
	    quantityHSG.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("quantity")
	    );
	    
	    tbHSG.setItems(dataHSG);
	    
	    
	    dataHSTT = FXCollections.observableArrayList(
			    listGiftHSTT
			    );
			    
	    giftNameHSTT.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("giftName")
	    );
	    priceHSTT.setCellValueFactory(
	    		new PropertyValueFactory<Gift,String>("price")
	    );
	    quantityHSTT.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("quantity")
	    );
	    
	    tbHSTT.setItems(dataHSTT);
	    
	    
	    dataOther = FXCollections.observableArrayList(
			    listGiftOther
			    );
			    
	    giftNameOther.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("giftName")
	    );
	    priceOther.setCellValueFactory(
	    		new PropertyValueFactory<Gift,String>("price")
	    );
	    quantityOther.setCellValueFactory(
	    new PropertyValueFactory<Gift,String>("quantity")
	    );
	    
	    tbOther.setItems(dataOther);
	}

}
