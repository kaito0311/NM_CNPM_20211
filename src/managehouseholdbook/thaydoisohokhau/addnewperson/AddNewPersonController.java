package managehouseholdbook.thaydoisohokhau.addnewperson;

import java.net.URL;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.text.*;
import javax.swing.JOptionPane;

import Model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.sql.Date;
public class AddNewPersonController implements Initializable {
    @FXML
    public TextField danToc;
    @FXML
    public TextField soCCCD;
    @FXML
    public TextField quocGiaSinhRa; 
    @FXML
    public TextField quocGiaCuTru; 
    
    @FXML
    public TextField hoTen; 
    @FXML
    public TextField tonGiao; 
    @FXML
    public DatePicker ngayCap ;
    @FXML
    public TextField thanhPhoSinhRa; 
    @FXML
    public TextField thanhPhoCuTru; 
    
    @FXML
    public TextField tenKhac; 
    @FXML
    public TextField quocTich; 
    
    @FXML
    public TextField noiCap ;
    @FXML
    public TextField huyenSinhRa;  
    @FXML
    public TextField huyenCuTru;  
    @FXML
    public DatePicker ngaySinh;
    @FXML
    public TextField maHoKhau; 
    @FXML
    public TextField ngheNghiep;
    @FXML
    public TextField phuongCuTru; 
    @FXML
    public TextField phuongSinhRa; 
    
    
    @FXML
    public ComboBox<String> gioiTinh;
    ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");
    
    @FXML
    public TextField quanHeVoiChuHo; 
    @FXML
    public TextField noiLamViec; 
    @FXML
    public TextField soNhaSinhRa;
    @FXML
    public TextField soNhaCuTru;

    @FXML
    public Button xacNhan;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioiTinh.setItems(listGioiTinh);
    } 

    public void xacNhan(ActionEvent e) throws SQLException{

    	// kiem tra da nhap du các thông tin cần thiết hay chưa
    	if(this.hoTen.getText().equals("") || this.gioiTinh.getPromptText().equals("")
    			|| this.danToc.getText().equals("") || this.quocGiaSinhRa.getText().equals("") || this.thanhPhoSinhRa.getText().equals("")
    			|| this.huyenSinhRa.getText().equals("") || this.phuongSinhRa.getText().equals("") || this.quocGiaCuTru.getText().equals("")
    			|| this.thanhPhoCuTru.getText().equals("") || this.huyenCuTru.getText().equals("") || this.phuongCuTru.getText().equals("")) {
    		JOptionPane.showMessageDialog(null,"Vui lòng nhập đủ thông tin");
    	}else {	
        this.addNewPerson();
        JOptionPane.showMessageDialog(null,"Thêm nhân khẩu thành công");
//    	    System.out.println(this.thanhPhoCuTru.getText());
//    		System.out.println(this.getProvinceID(this.thanhPhoCuTru.getText()));
//            System.out.println(this.getNationID(this.quocGiaCuTru.getText()));
//            System.out.println(this.getCommuneID(this.phuongSinhRa.getText()));
    	}
    }

    // them nhan khau moi
    public void addNewPerson() throws SQLException  {
        ConnectDatabase.ConnectData();
    	// them vao bang Person
    	String sql = "INSERT INTO [Person].[Person](Fullname,Nickname,BirthDate,Gender,EthnicID,NationalityID) VALUES(?,?,?,?,?,?)";
    	try {
    		
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql);
    //		ps.setInt(1,20);
    		ps.setString(1,this.hoTen.getText());
    		ps.setString(2,this.tenKhac.getText());
    		Date sd = Date.valueOf(this.ngaySinh.getValue());
    		ps.setDate(3, sd);
    		int genderID;
    	    if (this.gioiTinh.getPromptText().equals("Nữ"))genderID = 0;
    	    else genderID = 1; 
    		ps.setInt(4,(genderID));
    		ps.setString(5,this.danToc.getText());
    		ps.setString(6,this.getNationID(this.quocGiaSinhRa.getText()));
    		ps.executeUpdate();
    	
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	int id = this.getPersonID(this.hoTen.getText());
//    	System.out.println(id);
    	// them vao bang Person.BirthPlace
    	// Tạm thời để chú thích vì lỗi hiển thị Tiếng Việt
    	String sql1 = "insert into [Person].[BirthPlace] (PersonID,NationID,ProvinceID,DistrictID,CommuneID) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql1);
    		ps.setInt(1,id);
    		ps.setString(2,this.getNationID(this.quocGiaSinhRa.getText()));
    		ps.setString(3,this.getProvinceID(this.thanhPhoSinhRa.getText()));
    		ps.setString(4,this.getDistrictID(this.huyenSinhRa.getText()));
    		ps.setString(5, this.getCommuneID(this.phuongSinhRa.getText()));
    		ps.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	// them vao bang Person.Work
    	
    	String sql2 = "insert into [Person].[Work] (PersonID,Job,Place,ModifiedDate) values(?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql2);
    		ps.setInt(1, id);
    		ps.setString(2,this.ngheNghiep.getText());
    		ps.setString(3, this.noiLamViec.getText());
    		long millis = System.currentTimeMillis();
    		Date modifiedDate = new Date(millis);
    		ps.setDate(4, modifiedDate);
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	// them vao bang OriginPlace
    	String sql3 = "insert into [Person].[OriginPlace] (PersonID,NationID,ProvinceID,DistrictID,CommuneID) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql3);
    		ps.setInt(1, id);
    		ps.setString(2,this.getNationID(this.quocGiaCuTru.getText()));
    		ps.setString(3, this.getProvinceID(this.thanhPhoCuTru.getText()));
    		ps.setString(4,this.getDistrictID(this.huyenCuTru.getText()));
    		ps.setString(5,this.getCommuneID(this.phuongCuTru.getText()));
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	// them vao bang IdentityCard
    	if(this.soCCCD.getText()!="") {
    	String sql4 = "insert into [Person].[IdentityCard] (PersonID,Number,RegisterDate,RegisterPlace) values(?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql4);
    		ps.setInt(1, id);
    		ps.setString(2,this.soCCCD.getText());
    		Date sd = Date.valueOf(this.ngayCap.getValue());
    		ps.setDate(3,sd);
    		ps.setString(4,this.noiCap.getText());
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    	
    	// them vao bang Education
    	if(this.ngheNghiep.getText().equals("Học sinh")) {
    		String sql5 = "insert into [Person].[Education] (PersonID,StatusID,PrimarySchool,JuniorHighSchool,HighSchool) values(?,?,?,?,?)";
        	try {
        		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql5);
        		ps.setInt(1, id);
        		int statusid=0;
        		if(this.ngaySinh.getValue().getYear() >= 2016 && this.ngaySinh.getValue().getYear() <=2017) statusid = 1;
        		if(this.ngaySinh.getValue().getYear() >= 2011 && this.ngaySinh.getValue().getYear() <=2015) statusid = 2;
        		if(this.ngaySinh.getValue().getYear() >= 2007 && this.ngaySinh.getValue().getYear() <=2010) statusid = 3;
        		if(this.ngaySinh.getValue().getYear() >= 2004 && this.ngaySinh.getValue().getYear() <=2006) statusid = 4;
        		ps.setInt(2, statusid);
        		if(statusid == 0 || statusid ==1) {
        			ps.setString(3,"NULL");
        			ps.setString(4,"NULL");
        			ps.setString(5,"NULL");
        		}
        		if(statusid == 2) {
        			ps.setString(3,this.noiLamViec.getText());
        			ps.setString(4,"NULL");
        			ps.setString(5,"NULL");
        		}
        		if(statusid == 3) {
        			ps.setString(4,this.noiLamViec.getText());
        			ps.setString(3,"NULL");
        			ps.setString(5,"NULL");
        		}
        		if(statusid == 4) {
        			ps.setString(3,"NULL");
        			ps.setString(4,"NULL");
        			ps.setString(5,this.noiLamViec.getText());
        		}
        		
        		ps.executeUpdate();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	// thêm vào bảng Residence
    	String sql6 = "insert into [Person].[Residence] (PersonID,ResidenceTypeID,PrePermanentAddress,BookID,RelationshipWithHead) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql6);
    		ps.setInt(1, id);
    		ps.setInt(2,1);
    		ps.setString(3,"NULL");
    		ps.setInt(4, Integer.parseInt(this.maHoKhau.getText()));
    		ps.setString(5,this.quanHeVoiChuHo.getText());
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }
    
    public int getPersonID(String Fullname) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
    	Statement st = ConnectDatabase.connection.createStatement();
    	ResultSet rs;
    	rs = st.executeQuery("SELECT PersonID FROM [Person].[Person] WHERE Fullname like Fullname");
    	int id = 0;
    	while(rs.next()) {
    	  id = rs.getInt(1);
    
    	}
    	return id;
    	}
    	catch (Exception e){
    		System.err.println("Error");
    	}
		return 0;
    	
    }
    
    public String getNationID(String Nationality) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
    	Statement st = ConnectDatabase.connection.createStatement();
    	ResultSet rs;
    	rs = st.executeQuery("SELECT NationID FROM [Address].[Nation] WHERE Name = N'"+ Nationality +"';");
    	String id = null;
    	while(rs.next()) {
    	  id = rs.getString("NationID");
    
    	}
    	return id;
    	}
    	catch (Exception e){
    		System.err.println("Error");
    	}
		return null;
    }
   
    public String getProvinceID(String Province) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
    	Statement st = ConnectDatabase.connection.createStatement();
    	ResultSet rs;
    	rs = st.executeQuery("SELECT ProvinceID FROM [Address].[Province] WHERE Name = N'" + Province +"'; ");
//    	rs = st.executeQuery("select * from [Address].[Province]");
    	String id = null;
    	while(rs.next()) {
    	   id = rs.getString("ProvinceID");
  //  	  System.out.println(rs.getString("Name"));
    	}
   // 	st.close();
    	return id;
    	}
    	catch (Exception e){
    		System.err.println("Error");
    	}
		return null;
    }
    
    
    public String getDistrictID(String District) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
    	Statement st = ConnectDatabase.connection.createStatement();
    	ResultSet rs;
    	rs = st.executeQuery("SELECT NationID FROM [Address].[District] WHERE Name = N'"+ District + "'; ");
    	String id = null;
    	while(rs.next()) {
    	  id = rs.getString(1);
    
    	}
    	return id;
    	}
    	catch (Exception e){
    		System.err.println("Error");
    	}
		return null;
    }
   EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	   public void handle(ActionEvent e) {
		   
	   }
   };
   
   public String getCommuneID(String Commune) throws SQLException {
   	ConnectDatabase.ConnectData();
   	try {
   	Statement st = ConnectDatabase.connection.createStatement();
   	ResultSet rs;
   	rs = st.executeQuery("SELECT CommuneID FROM [Address].[Commune] WHERE Name = N'"+ Commune +"';");
   	String id = null;
   	while(rs.next()) {
   	  id = rs.getString("CommuneID");
   
   	}
   	return id;
   	}
   	catch (Exception e){
   		System.err.println("Error");
   	}
		return null;
   }
    
   

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonChangePerson;
    @FXML
    private Button buttonMoveHouseholdBook;
    @FXML
    private Button buttonDeleteHouseholdBook;

    private Stage stage; 
    private Scene scene; 

    @FXML
    Button buttonCreateNewBook;
    private void setNewSceneInSameWindow(String source, ActionEvent event) throws Exception {

        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(source));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void changeToTabAddNewPerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabAddNewPerson(event);");
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabChangePerson(event);");
        }
    }

    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabMoveHouseHold(event);");
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabDeleteHousehold(event);");
        }
    }

    public void changeToCreateNewBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../../createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToCreateNewBook(event);");
        }
    }

}
