package MainScholar;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class GiftController implements Initializable{
	
	@FXML
	TableView<Gift> tbHSG, tbHSTT, tbOther;
	
	@FXML
	TableColumn<Gift, String> giftNameHSG, priceHSG, quantityHSG, giftNameHSTT, priceHSTT, quantityHSTT, giftNameOther, priceOther, quantityOther;
	
	ObservableList<Gift> dataHSG, dataHSTT, dataOther;
    ArrayList<Gift> listGiftHSG, listGiftHSTT, listGiftOther;
    
    private Stage stage;
	private Scene scene;
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
	
	public void changeToGiftChild(ActionEvent event) {
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
