package searchPerson;

//import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.*;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import model.person.InforPerson;

public class SearchPersonController implements Initializable{
	private Stage stage;
	private Scene scene;
	private static ResultSet rs;
	private static ArrayList<String> names = new ArrayList<String>();
	
	@FXML
	private ScrollPane mainPane;
	@FXML
	private AnchorPane searchPersonPane;
	@FXML
	private TextField input;
	@FXML
	private Label error;
	@FXML
	private ImageView photo;
	@FXML
	private Text personID, fullName, nickName, birthDate, gender, birthPlace, originPlace, ethnic, religion, nationality, cardNumber, registerDate, registerPlace;
	
//	@FXML
//	private Button searchButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		if (rs == null) {
			try {
				//Get names of people
				String query = "select * from Person.Person order by PersonID asc;";
				rs = SQLConnection.statement.executeQuery(query);
				while (rs.next()) {
					names.add(rs.getString("PersonID") + " - " + rs.getString("FullName"));						
				}
				
				try {
					//Create store procedure getPersonInfo
					String createProcedure = Files.readString(Paths.get("src/database/getPersonInfo.sql"));
					SQLConnection.statement.executeQuery(createProcedure);	
				} catch (Exception e) {
					//System.out.println(e);
				}
			} catch (Exception e) {System.out.println(e);;}
		}
		TextFields.bindAutoCompletion(input, names);
		reset();
	}
	
	public void reset() {
		mainPane.setVisible(false);
		mainPane.setVvalue(0);
		
		error.setVisible(false);
		error.setText("");
	}
	
	public void showPerson(ActionEvent event) throws Exception {
		reset();
		
		try {		
			String inputString = input.getText();
			String PersonID;
			if (inputString.matches("[0-9]+")) {
				PersonID = inputString;
			}
			else {
				PersonID = inputString.substring(0, inputString.indexOf(" -"));
			}
			
			String queryData = "execute getPersonInfo " + PersonID;
			ResultSet result = SQLConnection.statement.executeQuery(queryData);
			
			result.next();
			
			if (result.getString("PersonID") == null) throw new IOException();
			
			personID.setText(PersonID);
			fullName.setText(result.getString("FullName"));
			nickName.setText(result.getString("Nickname"));
			birthDate.setText(result.getString("BirthDate"));
			gender.setText(result.getString("Gender"));
			birthPlace.setText(result.getString("BirthPlace"));
			originPlace.setText(result.getString("OriginPlace"));
			ethnic.setText(result.getString("Ethnic"));
			//religion.setText(result.getString("Religion"));
			nationality.setText(result.getString("Nationality"));
			cardNumber.setText(result.getString("CardNumber"));
			registerDate.setText(result.getString("RegisterDate"));
			registerPlace.setText(result.getString("RegisterPlace"));
			
			try {
				String imageURL = System.getProperty("user.dir") + result.getString("PhotoURL");
	//			System.out.println(imageURL);
				photo.setImage(new Image(getClass().getResourceAsStream(imageURL)));
			} catch (Exception e) {
			//	System.out.println(e);
			}
			
			mainPane.setVisible(true);

		} catch (Exception e) {
			error.setVisible(true);
			error.setText("Không tìm thấy!");
		}

	}
	
	public void changeToSearchPerson(ActionEvent event) {
		try {
			
//			System.out.println("Run changeToSearchPerson in SearchBookController");
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/searchPerson/SearchPerson.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToSearchBook(ActionEvent event) {
		try {
			
//			System.out.println("Run changeToSearchBook in SearchBookController");

			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/searchBook/SearchBook.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
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
}
