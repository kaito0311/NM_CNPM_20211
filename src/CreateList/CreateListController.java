/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateList;

import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Cool IT Help
 */
public class CreateListController implements Initializable {
    
    @FXML 
     TableView lapDanhSach;  
    
    @FXML
    private TableColumn fullName, birthDate, school, className, achievement, houseID;
    
    ObservableList<Person> data;
    ObservableList<String> list;
    ArrayList<Person> listPerson;
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
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
			Person p = new Person(rs.getString("FullName"), rs.getString("BirthDate"), rs.getString("Place"), rs.getString("Class"), list, rs.getString("BookID"));
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
     
    lapDanhSach.setItems(data);                         
    }  
      
    public void saveList(ActionEvent event) throws IOException {
    	IOFile.writeExcel(listPerson);
    }
    
    
}
