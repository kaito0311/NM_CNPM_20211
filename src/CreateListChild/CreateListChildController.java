package CreateListChild;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Cool IT Help
 */
public class CreateListChildController implements Initializable {
    
    @FXML 
     TableView<Person> createListChild;  
    
    @FXML
    private TableColumn<Person, String> fullName, birthDate, gender, age, houseID;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
       
    listPerson = new ArrayList<Person>();
    try {
		ResultSet rs = GetDataChild.getStudentList();
		while(rs.next()) {
			Person p = new Person(rs.getString("FullName"), rs.getString("BirthDate"), rs.getString("Gender"), rs.getString("Age"), rs.getString("BookID"));
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
    gender.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("gender")
    );     
    age.setCellValueFactory(     
            new PropertyValueFactory<Person,String>("age")
   );
    
    houseID.setCellValueFactory(     
            new PropertyValueFactory<Person,String>("houseID")
   );
           
     
    createListChild.setItems(data);                         
    }  
      
    public void saveList(ActionEvent event) throws IOException {
    	lbNoti.setText("Lập danh sách thành công");
		IOFile.writeExcel(listPerson);
    }
    
    
}
