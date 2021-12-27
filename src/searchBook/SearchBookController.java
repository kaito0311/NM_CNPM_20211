package searchBook;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dotuoi.*;
import tamvang_tamtru.*;
import thoigian.*;
import application.*;
import database.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchBookController implements Initializable{
	private Stage stage;
	private Scene scene;
	private static ResultSet rs;
	private static ArrayList<String> names = new ArrayList<String>();
	
	@FXML
	private ScrollPane mainPane;
	@FXML
	private AnchorPane searchBookPane;
	@FXML
	private TextField input;
	@FXML
	private Label error;
	
	@FXML
	private Text bookID, residencePlace;
	
	@FXML
	private Pane person1Pane, person2Pane, person3Pane, person4Pane, person5Pane, person6Pane, person7Pane, person8Pane, person9Pane, person10Pane, person11Pane, person12Pane; 
	
	@FXML
	private Text memberName1, personID1, birthDate1, gender1, prePermanentAddress1;
	@FXML
	private Text memberName2, personID2, relationshipWithHead2, birthDate2, gender2, prePermanentAddress2;
	@FXML
	private Text memberName3, personID3, relationshipWithHead3, birthDate3, gender3, prePermanentAddress3;
	@FXML
	private Text memberName4, personID4, relationshipWithHead4, birthDate4, gender4, prePermanentAddress4;
	@FXML
	private Text memberName5, personID5, relationshipWithHead5, birthDate5, gender5, prePermanentAddress5;
	@FXML
	private Text memberName6, personID6, relationshipWithHead6, birthDate6, gender6, prePermanentAddress6;
	@FXML
	private Text memberName7, personID7, relationshipWithHead7, birthDate7, gender7, prePermanentAddress7;
	@FXML
	private Text memberName8, personID8, relationshipWithHead8, birthDate8, gender8, prePermanentAddress8;
	@FXML
	private Text memberName9, personID9, relationshipWithHead9, birthDate9, gender9, prePermanentAddress9;
	@FXML
	private Text memberName10, personID10, relationshipWithHead10, birthDate10, gender10, prePermanentAddress10;
	@FXML
	private Text memberName11, personID11, relationshipWithHead11, birthDate11, gender11, prePermanentAddress11;
	@FXML
	private Text memberName12, personID12, relationshipWithHead12, birthDate12, gender12, prePermanentAddress12;
	
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
					String createProcedure = Files.readString(Paths.get("src/database/getBookInfo.sql"));
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
		error.setVisible(false);
		error.setText("");
		
		mainPane.setVisible(false);
		person1Pane.setVisible(false);
		person2Pane.setVisible(false);
		person3Pane.setVisible(false);
		person4Pane.setVisible(false);
		person5Pane.setVisible(false);
		person6Pane.setVisible(false);
		person7Pane.setVisible(false);
		person8Pane.setVisible(false);
		person9Pane.setVisible(false);
		person10Pane.setVisible(false);
		person11Pane.setVisible(false);
		person12Pane.setVisible(false);
		
		mainPane.setVvalue(0);
		
		memberName1.setText("");
		personID1.setText("");
		birthDate1.setText("");
		gender1.setText("");
		prePermanentAddress1.setText("");
		
		memberName2.setText("");
		personID2.setText("");
		relationshipWithHead2.setText("");
		birthDate2.setText("");
		gender2.setText("");
		prePermanentAddress2.setText("");
		
		memberName3.setText("");
		personID3.setText("");
		relationshipWithHead3.setText("");
		birthDate3.setText("");
		gender3.setText("");
		prePermanentAddress3.setText("");
		
		memberName4.setText("");
		personID4.setText("");
		relationshipWithHead4.setText("");
		birthDate4.setText("");
		gender4.setText("");
		prePermanentAddress4.setText("");
		
		memberName5.setText("");
		personID5.setText("");
		relationshipWithHead5.setText("");
		birthDate5.setText("");
		gender5.setText("");
		prePermanentAddress5.setText("");
		
		memberName6.setText("");
		personID6.setText("");
		relationshipWithHead6.setText("");
		birthDate6.setText("");
		gender6.setText("");
		prePermanentAddress6.setText("");
		
		memberName7.setText("");
		personID7.setText("");
		relationshipWithHead7.setText("");
		birthDate7.setText("");
		gender7.setText("");
		prePermanentAddress7.setText("");
		
		memberName8.setText("");
		personID8.setText("");
		relationshipWithHead8.setText("");
		birthDate8.setText("");
		gender8.setText("");
		prePermanentAddress8.setText("");
		
		memberName9.setText("");
		personID9.setText("");
		relationshipWithHead9.setText("");
		birthDate9.setText("");
		gender9.setText("");
		prePermanentAddress9.setText("");
		
		memberName10.setText("");
		personID10.setText("");
		relationshipWithHead10.setText("");
		birthDate10.setText("");
		gender10.setText("");
		prePermanentAddress10.setText("");
		
		memberName11.setText("");
		personID11.setText("");
		relationshipWithHead11.setText("");
		birthDate11.setText("");
		gender11.setText("");
		prePermanentAddress11.setText("");
		
		memberName12.setText("");
		personID12.setText("");
		relationshipWithHead12.setText("");
		birthDate12.setText("");
		gender12.setText("");
		prePermanentAddress12.setText("");
	}
	
	public void showBook(ActionEvent event) throws Exception {
		reset();
		
		try {
			String inputString = input.getText();
			String BookID = new String();
			if (inputString.matches("[0-9]+")) {
				BookID = inputString;
			}
			else {
				String PersonID = inputString.substring(0, inputString.indexOf(" -"));
				
				try {
					String query = "select BookID from Person.Residence where PersonID = " + PersonID;
					ResultSet r = SQLConnection.statement.executeQuery(query);
					r.next();
					BookID = r.getString("BookID");
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
			
			String queryData = "execute getBookInfo " + BookID;
			ResultSet result = SQLConnection.statement.executeQuery(queryData);
			result.next();
			
			if (result.getString("BookID") == null) throw new IOException();
			
			bookID.setText(BookID);
			residencePlace.setText(result.getString("ResidencePlace"));
			
			person1Pane.setVisible(true);
			memberName1.setText(result.getString("FullName"));
			personID1.setText(result.getString("PersonID"));
			birthDate1.setText(result.getString("BirthDate"));
			gender1.setText(result.getString("Gender"));
			prePermanentAddress1.setText(result.getString("PrePermanentAddress"));
			
			if (result.next()) {
				person2Pane.setVisible(true);
				
				memberName2.setText(result.getString("FullName"));
				personID2.setText(result.getString("PersonID"));
				relationshipWithHead2.setText(result.getString("RelationshipWithHead"));
				birthDate2.setText(result.getString("BirthDate"));
				gender2.setText(result.getString("Gender"));
				prePermanentAddress2.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person3Pane.setVisible(true);
				
				memberName3.setText(result.getString("FullName"));
				personID3.setText(result.getString("PersonID"));
				relationshipWithHead3.setText(result.getString("RelationshipWithHead"));
				birthDate3.setText(result.getString("BirthDate"));
				gender3.setText(result.getString("Gender"));
				prePermanentAddress3.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person4Pane.setVisible(true);
				
				memberName4.setText(result.getString("FullName"));
				personID4.setText(result.getString("PersonID"));
				relationshipWithHead4.setText(result.getString("RelationshipWithHead"));
				birthDate4.setText(result.getString("BirthDate"));
				gender4.setText(result.getString("Gender"));
				prePermanentAddress4.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person5Pane.setVisible(true);
				
				memberName5.setText(result.getString("FullName"));
				personID5.setText(result.getString("PersonID"));
				relationshipWithHead5.setText(result.getString("RelationshipWithHead"));
				birthDate5.setText(result.getString("BirthDate"));
				gender5.setText(result.getString("Gender"));
				prePermanentAddress5.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person6Pane.setVisible(true);
				
				memberName6.setText(result.getString("FullName"));
				personID6.setText(result.getString("PersonID"));
				relationshipWithHead6.setText(result.getString("RelationshipWithHead"));
				birthDate6.setText(result.getString("BirthDate"));
				gender6.setText(result.getString("Gender"));
				prePermanentAddress6.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person7Pane.setVisible(true);
				
				memberName7.setText(result.getString("FullName"));
				personID7.setText(result.getString("PersonID"));
				relationshipWithHead7.setText(result.getString("RelationshipWithHead"));
				birthDate7.setText(result.getString("BirthDate"));
				gender7.setText(result.getString("Gender"));
				prePermanentAddress7.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person8Pane.setVisible(true);
				
				memberName8.setText(result.getString("FullName"));
				personID8.setText(result.getString("PersonID"));
				relationshipWithHead8.setText(result.getString("RelationshipWithHead"));
				birthDate8.setText(result.getString("BirthDate"));
				gender8.setText(result.getString("Gender"));
				prePermanentAddress8.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person9Pane.setVisible(true);
				
				memberName9.setText(result.getString("FullName"));
				personID9.setText(result.getString("PersonID"));
				relationshipWithHead9.setText(result.getString("RelationshipWithHead"));
				birthDate9.setText(result.getString("BirthDate"));
				gender9.setText(result.getString("Gender"));
				prePermanentAddress9.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person10Pane.setVisible(true);
				
				memberName10.setText(result.getString("FullName"));
				personID10.setText(result.getString("PersonID"));
				relationshipWithHead10.setText(result.getString("RelationshipWithHead"));
				birthDate10.setText(result.getString("BirthDate"));
				gender10.setText(result.getString("Gender"));
				prePermanentAddress10.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person11Pane.setVisible(true);
				
				memberName11.setText(result.getString("FullName"));
				personID11.setText(result.getString("PersonID"));
				relationshipWithHead11.setText(result.getString("RelationshipWithHead"));
				birthDate11.setText(result.getString("BirthDate"));
				gender11.setText(result.getString("Gender"));
				prePermanentAddress11.setText(result.getString("PrePermanentAddress"));
			}
			
			if (result.next()) {
				person12Pane.setVisible(true);
				
				memberName12.setText(result.getString("FullName"));
				personID12.setText(result.getString("PersonID"));
				relationshipWithHead12.setText(result.getString("RelationshipWithHead"));
				birthDate12.setText(result.getString("BirthDate"));
				gender12.setText(result.getString("Gender"));
				prePermanentAddress12.setText(result.getString("PrePermanentAddress"));
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
			
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/searchPerson/SearchPerson.fxml"));
			root.getSelectionModel().select(4);
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

			
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/searchBook/SearchBook.fxml"));
			root.getSelectionModel().select(4);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToThongKeDoTuoi(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/dotuoi/DoTuoi.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToThongKeGioiTinh(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/gioitinh/GioiTinh.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void changeToThongKeTamVangTru(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/tamvang_tamtru/TamVang_TamTru.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeToThongKeThoiGian(ActionEvent event) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/thoigian/ThoiGian.fxml"));
			root.getSelectionModel().select(3);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
