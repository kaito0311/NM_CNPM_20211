package thongke.tamvang_tamtru;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import database.SQLConnection;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class TamVangTamTruController implements Initializable{
	private Stage stage;
	private Scene scene;

	@FXML
	private ComboBox<String> tamBox;
	@FXML
	private DatePicker batdauDP;
	@FXML
	private DatePicker ketthucDP;
	@FXML
	private Label errorLabel;
	@FXML
	private TableView<InforPerson> tamTable;
	@FXML
	private TableColumn<InforPerson, Number> STTCol;
	@FXML
	private TableColumn<InforPerson, String> fullNameCol;
	@FXML
	private TableColumn<InforPerson, String> tamCol;
	@FXML
	private TableColumn<InforPerson, String> batdauCol;
	@FXML
	private TableColumn<InforPerson, String> ketthucCol;
	@FXML
	private TableColumn<InforPerson, String> diaChiCol;
	@FXML
	private TextField tamvang;
	@FXML
	private TextField tamtru;

	@SuppressWarnings("exports")
	public ObservableList<InforPerson> tamList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
		loadTK();
	}
	
	private void loadTable() {
		STTCol.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + tamTable.getItems().indexOf(column.getValue())));
		fullNameCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
		tamCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getResidenceType().getName()));
		batdauCol.setCellValueFactory(cellData -> {
			if (cellData.getValue().getResidence().getResidencetypeID() == 2)
				return new SimpleStringProperty(String.valueOf(cellData.getValue().getTemporaryResidence().getFromDate()));
			if (cellData.getValue().getResidence().getResidencetypeID() == 3)
				return new SimpleStringProperty(String.valueOf(cellData.getValue().getTemporaryAbsence().getFromDate()));
			return null;
		});	
		ketthucCol.setCellValueFactory(cellData -> {
			if (cellData.getValue().getResidence().getResidencetypeID() == 2)
				return new SimpleStringProperty(String.valueOf(cellData.getValue().getTemporaryResidence().getToDate()));
			if (cellData.getValue().getResidence().getResidencetypeID() == 3)
				return new SimpleStringProperty(String.valueOf(cellData.getValue().getTemporaryAbsence().getToDate()));
			return null;
	});	
		diaChiCol.setCellValueFactory(cellData -> {
			if (cellData.getValue().getResidence().getResidencetypeID() == 2)
				return new SimpleStringProperty(cellData.getValue().getTemporaryResidence().getAddress());
			if (cellData.getValue().getResidence().getResidencetypeID() == 3)
				return new SimpleStringProperty(cellData.getValue().getTemporaryAbsence().getTempResidencePlace());
			return null;
	});	
	
		for (InforPerson nk : SQLConnection.danhSachNhanKhau)
			if(nk.getResidence().getResidencetypeID() == 2 || nk.getResidence().getResidencetypeID() == 3)
				tamList.add(nk);
		tamTable.setItems(tamList);
	}
	
	private void loadTK() {
		int tam_tru = 0, tam_vang = 0;
		for (InforPerson nk : SQLConnection.danhSachNhanKhau) {
			if (nk.getResidence().getResidencetypeID() == 2)
				tam_tru++;
			else if (nk.getResidence().getResidencetypeID() == 2)
				tam_vang++;
		}
		
		tamtru.setText(String.valueOf(tam_tru));
		tamvang.setText(String.valueOf(tam_vang));
		
	}

	@SuppressWarnings("exports")
	@FXML
	public void handleLocButton(ActionEvent event) {
		LocalDate batdauDate = batdauDP.getValue();
		LocalDate ketthucDate = ketthucDP.getValue();
		System.out.println(String.valueOf(batdauDate));
		System.out.println(String.valueOf(ketthucDate));
		
		if(batdauDate != null && ketthucDate == null) {
			errorLabel.setText("Ch??a ch???n ng??y b???t ?????u! Vui l??ng ch???n l???i!");
			return;
		}

		if(batdauDate == null && ketthucDate != null) {
			errorLabel.setText("Ch??a ch???n ng??y k???t th??c! Vui l??ng ch???n l???i!");
			return;
		}
		
		if(batdauDate != null && ketthucDate != null && batdauDate.isAfter(ketthucDate) == true) {
			errorLabel.setText("Ng??y b???t ?????u sau ng??y k???t th??c! Vui l??ng ch???n l???i!");
			return;
		}
		
		errorLabel.setText("L???c th??nh c??ng!");
		
		
		String tam = tamBox.getSelectionModel().getSelectedItem();
		if(tam == null)
			return;
		
		tamList.clear();
		for (InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
			if (tam.equals("T???m tr??") && nhanKhau.getResidence().getResidencetypeID() == 2) {
				if(batdauDate == null && ketthucDate == null) {
					tamList.add(nhanKhau);
					continue;
				}
				
				if (nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(ketthucDate)) {
				
				if(nhanKhau.getTemporaryResidence().getToDate() == null)
					tamList.add(nhanKhau);
				
				else if (nhanKhau.getTemporaryResidence().getToDate().toLocalDate().isAfter(batdauDate))
					tamList.add(nhanKhau);				
				}
			}
			
			else if (tam.equals("T???m v???ng") && nhanKhau.getResidence().getResidencetypeID() == 3){
				if(batdauDate == null && ketthucDate == null) {
					tamList.add(nhanKhau);
					continue;
				}
				if (nhanKhau.getTemporaryAbsence().getFromDate().toLocalDate().isBefore(ketthucDate)) {
					
				if(nhanKhau.getTemporaryAbsence().getToDate() == null)
					tamList.add(nhanKhau);
				
				else if (nhanKhau.getTemporaryAbsence().getToDate().toLocalDate().isAfter(batdauDate))
					tamList.add(nhanKhau);				
				}				
			}
		}
		
		tamTable.setItems(tamList);

	}
	
	@SuppressWarnings("exports")
	@FXML
	public void handleShowDetail(ActionEvent event) {
		PersonDetailController.nhanKhauSelected = tamTable.getSelectionModel().getSelectedItem();
        if (PersonDetailController.nhanKhauSelected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Kh??ng nh??n kh???u n??o ???????c ch???n.");
            m.setContentText("Vui l??ng ch???n l???i.");
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
