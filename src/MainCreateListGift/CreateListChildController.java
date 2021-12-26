package MainCreateListGift;

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
    
    private Stage stage;
	private Scene scene;
	
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
	
	public void changeToGiftChild(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainGift/UI_Gift.fxml"));
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
			e.printStackTrace();
		}	
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
       
    listPerson = new ArrayList<Person>();
    try {
		ResultSet rs = GetDataChild.getStudentList();
		while(rs.next()) {
			Person p = new Person(rs.getString("FullName"),String.valueOf(ConnectToDB.VNDF.format(rs.getDate("BirthDate"))), rs.getString("Gender"), rs.getInt("Age"), rs.getString("BookID"));
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
