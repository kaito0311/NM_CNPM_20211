package managehouseholdbook.tamtrutamvang;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import javax.swing.JOptionPane;

import database.SQLConnection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class TamTruTamVangController implements Initializable {

    @FXML
    private TextField diaChiThuongTru;

    @FXML
    private Button dangKyTamVang;

    @FXML
    private TextField soCMND;

    @FXML
    private TextField diaChiTamTru;

    @FXML
    private Button buttonTemporaryAbsence_Residence;

    @FXML
    private ComboBox<String> gioiTinh;

    @FXML
    private TextField queQuan;

    @FXML
    private TextField chuHo;

    @FXML
    private Button buttonChangeBook;

    @FXML
    private DatePicker ngayTamTru;

    @FXML
    private Button dangKyTamTru;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private Button buttonCreateNewBook;

    @FXML
    private TextField hoTen;
    @FXML
    private TextField quanHeChuHo;
    
    ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ");



  
    @FXML
    void dangKyTamTru(ActionEvent event) throws SQLException {
    	if(this.hoTen.getText().equals("") || this.diaChiTamTru.getText().equals("")
    			|| this.diaChiThuongTru.getText().equals("") )
    	{
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
				return;
    	}
    	if(this.getPersonID(this.hoTen.getText()) == 0) {
    		JOptionPane.showMessageDialog(null, "Cá nhân chưa có ID. Vui lòng chọn chức năng thêm nhân khẩu để cập nhật ID");
    		return;
    	}
       //System.out.println("abc");
    	this.updateResidence(2);;
    	this.addTemporaryResidence();
//    	System.out.println(this.getPersonID(this.hoTen.getText()));
    	JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
    }

    @FXML
    void dangKyTamVang(ActionEvent event) throws HeadlessException, SQLException {
    	if(this.hoTen.getText().equals("") || this.diaChiTamTru.getText().equals("")
    			|| this.diaChiThuongTru.getText().equals("") )
    	{
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
				return;
    	}
    	if(this.getPersonID(this.hoTen.getText()) == 0) {
    		JOptionPane.showMessageDialog(null, "Cá nhân không thuộc nhân khẩu tại địa phương. Không thể khai báo tạm vắng");
    		return;
    	}
       //System.out.println("abc");
    	this.updateResidence(3);;
    	this.addAbsent();
//    	System.out.println(this.getPersonID(this.hoTen.getText()));
    	JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
    }
    // them vao bang tam tru
    public void addTemporaryResidence() throws SQLException {
    	ConnectDatabase.ConnectData();
    	if(this.getPersonID(this.hoTen.getText())>0) {
    	String sql1 = "insert into [Person].[TemporaryResidence] (PersonID,Name,FromDate,DetailAddress,CertifiedDate) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql1);
    		ps.setInt(1, this.getPersonID(this.hoTen.getText()));
    		ps.setString(2,this.hoTen.getText());
    		ps.setDate(3, Date.valueOf(this.ngayTamTru.getValue()));
    		ps.setString(4,this.diaChiTamTru.getText());
    		long millis = System.currentTimeMillis();
    		Date modifiedDate = new Date(millis);
    		ps.setDate(5, modifiedDate);
    		ps.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
        } 	
    	
    }
    // them vao bang tam vang
    public void addAbsent() throws HeadlessException, SQLException  {
    	ConnectDatabase.ConnectData();
        if(this.getPersonID(this.hoTen.getText()) == 0) {
        	JOptionPane.showMessageDialog(null,"Cá nhân này không thuộc nhân khẩu tại địa phương. Không thể khai báo tạm vắng.");
        }
    	String sql1 = "insert into [Person].[TemporaryAbsent] (PersonID,Name,FromDate,TempResidencePlace,CertifiedDate) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql1);
    		ps.setInt(1,this.getPersonID(this.hoTen.getText()));
    		ps.setString(2,this.hoTen.getText());
    		ps.setDate(3, Date.valueOf(this.ngayTamTru.getValue()));
    		ps.setString(4,this.diaChiTamTru.getText());
    		long millis = System.currentTimeMillis();
    		Date modifiedDate = new Date(millis);
    		ps.setDate(5, modifiedDate);
    		ps.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
    public void addResidence(int i) throws SQLException {
    	ConnectDatabase.ConnectData();
        if(this.getPersonID(this.hoTen.getText()) > 0) {
    	String sql1 = "insert into [Person].[Residence] (PersonID,ResidenceTypeID,PrePermanentAddress,RelationshipWithHead) values(?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql1);
    		ps.setInt(1,this.getPersonID(this.hoTen.getText()));
    		ps.setInt(2,i);
    		ps.setString(3,this.diaChiThuongTru.getText());
    		ps.setString(4,this.quanHeChuHo.getText());
    		ps.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
        }
    }
    
    public int getPersonID(String Fullname) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
    	Statement st = ConnectDatabase.connection.createStatement();
    	ResultSet rs;
    	rs = st.executeQuery("SELECT PersonID FROM [Person].[Person] WHERE Fullname = N'"+ Fullname +"'"); // AND BirthDate == '" + ngaySinh + "';");
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

    public void updateResidence(int i) throws SQLException {
    	ConnectDatabase.ConnectData();
    	if(this.getPersonID(this.hoTen.getText())>0) {
    		String sql2 = "update [Person].[Residence]" + " set ResidenceTypeID = ?"  +" where PersonID = ?";
    		try {
    			PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql2);
    			ps.setInt(1, i);
    			ps.setInt(2,this.getPersonID(this.hoTen.getText()));
    			ps.executeUpdate();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public int getBookID(int personID) {   // kiem tra mot nguoi nam trong ho khau nao
    	ConnectDatabase.ConnectData();
    	try {
        	Statement st = ConnectDatabase.connection.createStatement();
        	ResultSet rs;
        	rs = st.executeQuery("SELECT BookID FROM [Person].[Residence] WHERE PersonID ='"+ personID + "'; ");
        	int id = 0;
        	while(rs.next()) {
        	  id = rs.getInt("BookID");
        
        	}
        	return id;
        	}
        	catch (Exception e){
        		System.err.println("Error");
        	}
    		return 0;
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		gioiTinh.setItems(listGioiTinh);
		
	}




	//////////////////////////////////////////////////////////////////
	private Stage stage;
    private Scene scene;
	private void setNewSceneInSameWindow(String source, ActionEvent event) throws Exception {

        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(source));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void changeToChangeHouseholdBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/ChangeHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changetoChangeHouseholdBook");
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

	public void changeToCreateNewBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToCreateNewBook(event);");
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
