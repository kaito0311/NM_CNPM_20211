package thongke.gioitinh;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import ConnectDB.ConnectToDB;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class GioiTinhController implements Initializable {
	private Stage stage;
	private Scene scene;
	
	@FXML
	private CheckComboBox<String> gioiTinhBox;
	@FXML
	private TableView<InforPerson> gioiTinhTable;
	@FXML
	private TableColumn<InforPerson, Number> STTCol;
	@FXML
	private TableColumn<InforPerson, String> fullNameCol;
	@FXML
	private TableColumn<InforPerson, String> birthCol;
	@FXML
	private TableColumn<InforPerson, String> genderCol;
	@FXML
	private TextField namText;
	@FXML
	private TextField nuText;
	@FXML
	private TextField tiLeText;
	
	@SuppressWarnings("exports")
	public ObservableList<InforPerson> gioiTinhList = FXCollections.observableArrayList(SQLConnection.danhSachNhanKhau);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
		loadSoLieu();
	}


	private void loadTable() {
		STTCol.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + gioiTinhTable.getItems().indexOf(column.getValue())));
		fullNameCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
		birthCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(ConnectToDB.VNDF.format(Date.valueOf(cellData.getValue().getPerson().getBirthDate().toString()))));
		genderCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getGender()));

		gioiTinhTable.setItems(gioiTinhList);
	}
	
	private void loadSoLieu() {
		ArrayList<InforPerson> namNK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> nuNK = new ArrayList<InforPerson>();

		for (InforPerson nk : SQLConnection.danhSachNhanKhau) {
			switch(nk.getPerson().getGender()) {
				case "Nam": namNK.add(nk); break;
				case "Nữ": nuNK.add(nk); break;
				default: System.out.println("???");
			}
		}
		
		namText.setEditable(false);
		namText.setText(String.valueOf(namNK.size()) + " nhân khẩu nam");
		nuText.setEditable(false);
		nuText.setText(String.valueOf(nuNK.size()) + " nhân khẩu nữ");
		tiLeText.setEditable(false);
		tiLeText.setText(String.valueOf(Math.round(10000.0*namNK.size()/nuNK.size() ) /100.0 ) + " nam/100 nữ");
	}
	
	@SuppressWarnings("exports")
	@FXML
	public void handleLocButton(ActionEvent event) {
		
		gioiTinhList.clear();
		
		ObservableList<String> list = gioiTinhBox.getCheckModel().getCheckedItems();

		if (list.size() == 0)
			gioiTinhList.addAll(SQLConnection.danhSachNhanKhau);
		
		for(InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
			for (String gioiTinh : list) {
				if (nhanKhau.getPerson().getGender().equals(gioiTinh)) {
					gioiTinhList.add(nhanKhau);
					System.out.println(nhanKhau.getPerson().getPersonID());
				}
			}
		}
		gioiTinhTable.setItems(gioiTinhList);
	}

	@SuppressWarnings("exports")
	@FXML
	public void handleShowDetail(ActionEvent event) {

		PersonDetailController.nhanKhauSelected = gioiTinhTable.getSelectionModel().getSelectedItem();
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
