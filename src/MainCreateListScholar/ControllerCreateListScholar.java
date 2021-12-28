/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainCreateListScholar;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ConnectDB.ConnectToDB;
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
    public static ArrayList<Person> listPerson;
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
    		"Học sinh giỏi",
    		"Thành tích đặc biệt",
    		"Học sinh tiên tiến",
    		"Không"
    		);
    listPerson = new ArrayList<Person>();
    try {
		ResultSet rs = GetData.getStudentList();
		while(rs.next()) {
			Person p = new Person(rs.getString("FullName"), String.valueOf(ConnectToDB.VNDF.format(rs.getDate("BirthDate"))), rs.getString("Place"), rs.getString("Class"), list, rs.getString("BookID"), rs.getInt("personID"));
			p.getAchievement().getSelectionModel().selectFirst();
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
    		
    		try {
    			String sql = "delete from Gift.Giving \n"
        				+ "where givingID in (\n"
        				+ "	select gi.GivingID\n"
        				+ "	from Gift.Giving gi\n"
        				+ "	join Gift.Gift g on g.GiftID = gi.GiftID \n"
        				+ "	where g.Event = N'Trao thưởng cuối kì')";
    			Connection connection = ConnectToDB.openConnection();
    			PreparedStatement stmt = connection.prepareStatement(sql);
    			stmt.executeUpdate();
				
				for (Person p:listPerson) {
					sql = "insert into Gift.Giving\n"
							+ "	(PersonID, GiftID, Year)\n"
							+ "values (? ,?, 2021)";
					stmt = connection.prepareStatement(sql);
					stmt.setInt(1, p.getPersonID());
					stmt.setString(2, getGift(p));
					stmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	} else {
    		lbNoti.setText("Vui lòng chọn thành tích đầy đủ");
    	}
    	
    }
    
    private String getGift (Person p) {
    	int clas = Integer.valueOf(p.getClassName());
    	String giftID = new String();
    	
    	if (clas < 6) {
    		switch(p.getAchievement().getSelectionModel().getSelectedItem()) {
    			case "Thành tích đặc biệt": 
    			case "Học sinh giỏi": giftID = "4C"; break;
    			case "Học sinh tiên tiến": giftID =  "4B"; break;
    			case "Không": giftID = "4A"; break;
    			default: System.out.println("hmm");
    		}
    	}
    	
    	else {
    		switch(p.getAchievement().getSelectionModel().getSelectedItem()) {
				case "Thành tích đặc biệt": 
				case "Học sinh giỏi": giftID = "4F"; break;
				case "Học sinh tiên tiến": giftID =  "4D"; break;
				case "Không": giftID = "4E"; break;
				default: System.out.println("hmm");
	   		}
    	}
   		
   		return giftID;

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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/gift/thongkescholar/UI_TKScholar.fxml"));
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
