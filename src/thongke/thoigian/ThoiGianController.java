package thongke.thoigian;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.SQLConnection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class ThoiGianController implements Initializable{
	private Stage stage;
	private Scene scene;

	@FXML
	private DatePicker batdauDP;
	@FXML
	private DatePicker ketthucDP;
	@FXML
	private Label errorLabel;
	@FXML
	private TableView<InforPerson> thoiGianTable;
	@FXML
	private TableColumn<InforPerson, Number> STTCol;
	@FXML
	private TableColumn<InforPerson, String> fullNameCol;
	@FXML
	private TableColumn<InforPerson, String> birthCol;
	@FXML
	private TableColumn<InforPerson, Number> maHKCol;
	@FXML
	private TableColumn<InforPerson, String> chuHoCol;
	@FXML
	private TableColumn<InforPerson, String> qHevsChuHoCol;
	
	@SuppressWarnings("exports")
	public ObservableList<InforPerson> thoiGianList = FXCollections.observableArrayList(SQLConnection.danhSachNhanKhau);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
	}
	
	private void loadTable() {
		STTCol.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + thoiGianTable.getItems().indexOf(column.getValue())));
		fullNameCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
		birthCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getBirthDate().toString()));
		maHKCol.setCellValueFactory(cellData -> 
			new SimpleIntegerProperty(cellData.getValue().getResidence().getBookID()));
		chuHoCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
		qHevsChuHoCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getResidence().getRelaWithHead()));
		
		thoiGianTable.setItems(thoiGianList);

		
	}

	@SuppressWarnings("exports")
	@FXML
	public void handleLocButton(ActionEvent event) {
		LocalDate batdauDate = batdauDP.getValue();
		LocalDate ketthucDate = ketthucDP.getValue();
		
		if(batdauDate.isAfter(ketthucDate) == true) {
			errorLabel.setText("Ngày bắt đầu sau ngày kết thúc! Vui lòng chọn lại!");
			return;
		}
		
		errorLabel.setText(null);
		
		thoiGianList.clear();
		for(InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
			if(nhanKhau.getResidence().getResidencetypeID() == 1)
				thoiGianList.add(nhanKhau);
			else if(nhanKhau.getResidence().getResidencetypeID() == 2) {
				if (nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(ketthucDate)) {
				
				if(nhanKhau.getTemporaryResidence().getToDate() == null)
					thoiGianList.add(nhanKhau);
				
				else if (nhanKhau.getTemporaryResidence().getToDate().toLocalDate().isAfter(batdauDate))
					thoiGianList.add(nhanKhau);
				}
			}

		}
		thoiGianTable.setItems(thoiGianList);

	}

	@SuppressWarnings("exports")
	@FXML
	public void handleShowDetail(ActionEvent event) {

		PersonDetailController.nhanKhauSelected = thoiGianTable.getSelectionModel().getSelectedItem();
        if (PersonDetailController.nhanKhauSelected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        
//	        System.out.println(nhanKhauSelected.getPerson().getFullName());
			try {
				FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("/thongke/PersonDetail.fxml"));
				Parent showDetail = loader.load();
		        Stage stage = new Stage();
		        stage.setTitle("Show");
		        Scene scene = new Scene(showDetail);
		        stage.setScene(scene);
		        stage.show();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	}

	@SuppressWarnings("exports")
	public void changeToThongKeHome(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/thongkehome/Thong_Ke.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//			scene.getStylesheets().add(getClass().getResource("thong-ke.css").toExternalForm());
			scene = new Scene(root);
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("exports")
	public void changeToThongKeDoTuoi(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/dotuoi/Do_Tuoi.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("exports")
	public void changeToThongKeGioiTinh(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/gioitinh/Gioi_Tinh.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("exports")
	public void changeToThongKeTamVangTru(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/tamvang_tamtru/TamVang_TamTru.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("exports")
	public void changeToThongKeThoiGian(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/thoigian/Thoi_Gian.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("exports")
	public void changeToHomePage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Main/UI_HomePage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("exports")
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
	
	@SuppressWarnings("exports")
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

	@SuppressWarnings("exports")
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
	@SuppressWarnings("exports")
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
