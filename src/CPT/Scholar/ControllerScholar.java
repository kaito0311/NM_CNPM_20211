package CPT.Scholar;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerScholar implements Initializable{
	
	@FXML
	TableView<Scholar> tbHSG, tbHSTT, tbOther;
	
	@FXML
	TableColumn<Scholar, String> giftNameHSG, priceHSG, quantityHSG, giftNameHSTT, priceHSTT, quantityHSTT, giftNameOther, priceOther, quantityOther;
	
	ObservableList<Scholar> dataHSG, dataHSTT, dataOther;
    ArrayList<Scholar> listGiftHSG, listGiftHSTT, listGiftOther;
    
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
	
	public void changeToCreateListScholar(ActionEvent event) {
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
	
	public void changeToGiftChild(ActionEvent event) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		listGiftHSG = new ArrayList<Scholar>();
		listGiftHSTT = new ArrayList<Scholar>();
		listGiftOther = new ArrayList<Scholar>();
		try {
			ResultSet rs = GetScholar.getHSG();
			while (rs.next()) {
				Scholar g = new Scholar(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
				listGiftHSG.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = GetScholar.getHSTT();
			while (rs.next()) {
				Scholar g = new Scholar(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
				listGiftHSTT.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = GetScholar.getOther();
			while (rs.next()) {
				Scholar g = new Scholar(rs.getString("Name"), rs.getString("Price"), rs.getString("Quantity"));
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
	    new PropertyValueFactory<Scholar,String>("giftName")
	    );
	    giftNameHSG.setCellFactory(TextFieldTableCell.forTableColumn());
	    priceHSG.setCellValueFactory(
	    		new PropertyValueFactory<Scholar,String>("price")
	    );
	    quantityHSG.setCellValueFactory(
	    new PropertyValueFactory<Scholar,String>("quantity")
	    );
	    
	    tbHSG.setItems(dataHSG);
	    
	    
	    dataHSTT = FXCollections.observableArrayList(
			    listGiftHSTT
			    );
			    
	    giftNameHSTT.setCellValueFactory(
	    new PropertyValueFactory<Scholar,String>("giftName")
	    );
	    priceHSTT.setCellValueFactory(
	    		new PropertyValueFactory<Scholar,String>("price")
	    );
	    quantityHSTT.setCellValueFactory(
	    new PropertyValueFactory<Scholar,String>("quantity")
	    );
	    
	    tbHSTT.setItems(dataHSTT);
	    
	    
	    dataOther = FXCollections.observableArrayList(
			    listGiftOther
			    );
			    
	    giftNameOther.setCellValueFactory(
	    new PropertyValueFactory<Scholar,String>("giftName")
	    );
	    priceOther.setCellValueFactory(
	    		new PropertyValueFactory<Scholar,String>("price")
	    );
	    quantityOther.setCellValueFactory(
	    new PropertyValueFactory<Scholar,String>("quantity")
	    );
	    
	    tbOther.setItems(dataOther);
	}

}
