package managehouseholdbook.createhouseholdbook;

import java.net.URL;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.NumberFormat.Style;
import java.util.ResourceBundle;
import java.text.Format;
import javax.swing.Action;
import javax.swing.JOptionPane;

import database.SQLConnection;
import model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
//import managehouseholdbook.thaydoisohokhau.addnewperson.AddNewPersonController;
import javafx.scene.Node;
import javafx.scene.Parent;

public class CreateNewHouseholdBookController implements Initializable {



    @FXML
    public Label labelNhapMaHoKhau;
    @FXML
    public Label labelChonChuHo;
//    @FXML
//    public ComboBox<String> comboBoxMaHoKhau;
//    @FXML
//    public ComboBox<String> comboBoxChuHo;
    @FXML
    public ComboBox<String> comboBoxThanhPho;
    @FXML
    public ComboBox<String> comboBoxQuan;
    @FXML
    public ComboBox<String> comboBoxPhuong;
    @FXML
    public Button xacNhan;
    public TextField soCMND;
    public TextField detailAddress;
    //   public AddNewPersonController addPerson;

    ObservableList<String> listMaHoKhau = FXCollections.observableArrayList();

    ObservableList<String> listProvinceName = FXCollections.observableArrayList();
    ObservableList<Integer> listProvinceID = FXCollections.observableArrayList();
    ObservableList<String> listProvinceNationID = FXCollections.observableArrayList();

    ObservableList<String> listDistrictName = FXCollections.observableArrayList();
    ObservableList<Integer> listDistrictID = FXCollections.observableArrayList();
    ObservableList<String> listDistrictProvinceID = FXCollections.observableArrayList();

    ObservableList<String> listCommuneName = FXCollections.observableArrayList();
    ObservableList<Integer> listCommuneID = FXCollections.observableArrayList();
    ObservableList<String> listCommnueDistrictID = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeListProvince();
        comboBoxThanhPho.setItems(listProvinceName);
  //      comboBoxMaHoKhau.setItems(listMaHoKhau);
    }

    // Action lien quan den province
    public void initializeListProvince() {
        String sql = "select * from Address.Province";
        try {

            ResultSet result = ConnectDatabase.statement.executeQuery(sql);

            while (result.next()) {
                listProvinceID.add(result.getInt("provinceID"));
                listProvinceName.add(result.getNString("name"));
                listProvinceNationID.add(result.getString("nationID"));
            }
        } catch (SQLException e) {
            System.out.println("Loi load " + sql + " " + getClass());
            System.out.println(e.getMessage());
        }

    }

    public void comboBoxProvinceChanged(ActionEvent event) {
        int index = comboBoxThanhPho.getSelectionModel().getSelectedIndex();
        takeDistrictCorrespondProvince(listProvinceID.get(index).toString());
    }

    // Action lien quan den quan
    private void takeDistrictCorrespondProvince(String provinceID) {
        if (provinceID.length() == 1)
            provinceID = "0" + provinceID;
        String sql = "select * from Address.District where ProvinceID ='" + provinceID + "'; ";

        System.out.println(sql);

        try {

            ResultSet result = ConnectDatabase.statement.executeQuery(sql);
            listDistrictID.clear();
            listDistrictProvinceID.clear();
            listDistrictName.clear();
            while (result.next()) {
                listDistrictID.add(result.getInt("districtID"));
                listDistrictName.add(result.getString("Name"));
                listDistrictProvinceID.add(result.getString("provinceID"));
            }
            if(listDistrictName.size() == 0)
                comboBoxQuan.setValue("Quận huyện");
            comboBoxQuan.setItems(listDistrictName);
            // comboBoxQuan.getSelectionModel().select(-1);
            // System.out.println(comboBoxQuan.getPromptText());
            // comboBoxQuan.getSelectionModel().clearAndSelect(-1);

        } catch (Exception e) {

            System.out.println("Lá»—i rá»“i báº¡n ei");
        }
    }

    public void comboBoxDistrictChanged(ActionEvent event) {
        comboBoxPhuong.setPromptText("Xã phường");
        int index = comboBoxQuan.getSelectionModel().getSelectedIndex();
        System.out.println("index district : " + index);
        if(index == -1){
        comboBoxPhuong.getItems().clear();
        return;
        }
        takeCommuneCorrespondDistrict(listDistrictID.get(index).toString());
    }

    // Action lien quan den xa
    private void takeCommuneCorrespondDistrict(String districtID) {
        if (districtID.length() == 1)
            districtID = "0" + districtID;
        String sql = "select * from Address.Commune where districtID ='" + districtID + "'; ";
        System.out.println(sql);
        try {
            ResultSet result = ConnectDatabase.statement.executeQuery(sql);
            listCommuneID.clear();
            listCommuneName.clear();
            listCommnueDistrictID.clear();
            while (result.next()) {
                listCommuneID.add(result.getInt("communeID"));
                listCommuneName.add(result.getString("Name"));
                listCommnueDistrictID.add(result.getString("districtID"));
            }
            comboBoxPhuong.getItems().setAll(listCommuneName);

        } catch (SQLException e) {
            // System.out.println("");
            System.out.println(e.getSQLState() + " " + getClass());
        }
    }

    public void comboChanged(ActionEvent Event) {
 //       System.out.println(comboBoxMaHoKhau.getSelectionModel().getSelectedIndex());
 //       System.out.println(comboBoxMaHoKhau.getValue());
    }

    public void xacNhan(ActionEvent event) throws Exception {
        System.out.println(this.soCMND.getText());
        if(this.soCMND.getText().length() == 0) {
        	JOptionPane.showMessageDialog(null,"Vui lòng nhập đủ thông tin");
        	return;        	
        }
        
        if(this.getHostID(this.soCMND.getText()) == 0) {
        	JOptionPane.showMessageDialog(null,"Chủ hộ chưa tồn tại trong dữ liệu dân cư. Vui lòng thêm thông tin về chủ hộ trong phần thêm nhân khẩu");
        	return;
        }
        if(this.getBookID(this.getHostID(this.soCMND.getText())) != 0) {
        	JOptionPane.showMessageDialog(null, "Chủ hộ hiện đang nằm trong một sổ hộ khẩu khác.Để tạo sổ hộ khẩu mới vui lòng chọn chức năng tách sổ hộ khẩu");
        	return;
        }
        
      //  System.out.println(this.getHostID(this.soCMND.getText()));

        if(this.comboBoxThanhPho.getSelectionModel().getSelectedIndex() == -1
           || this.comboBoxPhuong.getSelectionModel().getSelectedIndex() == -1 || this.comboBoxQuan.getSelectionModel().getSelectedIndex() == -1) {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin ");
        }else
        	{
            this.createNewBook();
        	JOptionPane.showMessageDialog(null,"Tạo sổ hộ khẩu thành công. Vui lòng chuyển sang chức năng thêm nhân khẩu để thêm thông tin các thành viên trong hộ khẩu");
 //       	this.changeToAddNewPerson(event);
        	}
        	
        
        System.out.println(this.comboBoxQuan.getSelectionModel().getSelectedItem());
        System.out.println(this.getDistrictID(this.comboBoxQuan.getSelectionModel().getSelectedItem()));
    }
    
    public int getHostID(String identitycard) throws SQLException {
    	ConnectDatabase.ConnectData();
    	try {
        	Statement st = ConnectDatabase.connection.createStatement();
        	ResultSet rs;
        	rs = st.executeQuery("SELECT PersonID FROM [Person].[IdentityCard] WHERE Number ='" + identitycard +"'; ");
        	int id = 0;
        	while(rs.next()) {
        	  id = rs.getInt("PersonID");
        	}
        	return id;
        	}
        	catch (Exception e){
        		return 0;
        	}
    		
    }
    
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
    	rs = st.executeQuery("SELECT DistrictID FROM [Address].[District] WHERE Name = N'"+ District + "'; ");
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
    
    public void createNewBook() throws SQLException {
    	ConnectDatabase.ConnectData();
    	String sql = "INSERT INTO [Household].[Book] (HeadID,ProvinceID,DistrictID,CommuneID,DetailAddress,CreatedDate) VALUES (?,?,?,?,?,?) ";
    	String sql1 = "update Person.Residence set BookID = (select max(bookID) from Household.Book), RelationshipWithHead = N'Chủ hộ' where PersonID = ?";
    	int headID = 0;
    	headID = this.getHostID(this.soCMND.getText());
    	try {
    		
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql);
    //		ps.setInt(1,20);
    		ps.setInt(1,headID);
    		ps.setString(2,this.getProvinceID(this.comboBoxThanhPho.getSelectionModel().getSelectedItem()));
    		ps.setString(3,this.getDistrictID(this.comboBoxQuan.getSelectionModel().getSelectedItem()));
    		ps.setString(4,this.getCommuneID(this.comboBoxPhuong.getSelectionModel().getSelectedItem()));
    		ps.setString(5,this.detailAddress.getText());
    		long millis = System.currentTimeMillis();
    		Date modifiedDate = new Date(millis);
    		ps.setDate(6, modifiedDate);
    		ps.executeUpdate();
    	
    		ps = ConnectDatabase.connection.prepareStatement(sql1);
    		ps.setInt(1, headID);
    		ps.executeUpdate();
    		
    	}catch (Exception e){
    		e.printStackTrace();
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
    
    

    @FXML
    Button buttonChangeBook;
    @FXML
    Button buttonTemporaryAbsence_Residence;

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


    public void changeToTemporaryAbsence(ActionEvent event){
        try {
            setNewSceneInSameWindow("/managehouseholdbook/tamtrutamvang/tamtrutamvang.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTemporaryAbsence");
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