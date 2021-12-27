/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainCreateListScholar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ConnectDB.ConnectToDB;
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

/**
 *
 * @author Cool IT Help
 */
public class ControllerCreateListScholar implements Initializable {
    
    @FXML 
     TableView<Person> tableCreateList;  
    
    @FXML
    private TableColumn<Person, String> fullName, birthDate, school, className, achievement, houseID;
    
    @FXML
    Label lbNoti;
    
    ObservableList<Person> data;
    ObservableList<String> list;
    ArrayList<Person> listPerson;
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    private Stage stage;
	private Scene scene;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
       
//    tableview.getColumns().addAll(firstNameCol, lastNameCol, emailCol, actionCol );
    list = FXCollections.observableArrayList(
    		"Thành tích đặc biệt",
    		"Học sinh giỏi",
    		"Học sinh tiên tiến",
    		"Không"
    		);
    listPerson = new ArrayList<Person>();
    try {
		ResultSet rs = GetData.getStudentList();
		while(rs.next()) {
			Person p = new Person(rs.getString("FullName"), String.valueOf(ConnectToDB.VNDF.format(rs.getDate("BirthDate"))), rs.getString("Place"), rs.getString("Class"), list, rs.getString("BookID"));
			listPerson.add(p);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
    data = FXCollections.observableArrayList(
    listPerson
    );
    
    fullName.setCellValueFactory(
    new PropertyValueFactory<Person,String>("fullName")
    );
    birthDate.setCellValueFactory(
    new PropertyValueFactory<Person,String>("birthDate")
    );
    school.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("school")
    );     
    className.setCellValueFactory(     
            new PropertyValueFactory<Person,String>("className")
   );
    
    houseID.setCellValueFactory(     
            new PropertyValueFactory<Person,String>("houseID")
   );
    
    achievement.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("achievement")
    );         
     
    tableCreateList.setItems(data);                         
    }  
      
    public void saveList(ActionEvent event) throws IOException {
    	int check = 1;
    	for (Person p:listPerson) {
    		if (p.getAchievement().getSelectionModel().isEmpty()) {
    			check = 0;
    			break;
    		}
    	}
    	if (check == 1) {
    		lbNoti.setText("Lập danh sách thành công");
    		IOFile.writeExcel(listPerson);
    	} else {
    		lbNoti.setText("Vui lòng chọn thành tích đầy đủ");
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
	
	public void changeToScholar(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainScholar/UI_Scholar.fxml"));
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
    
}
