package thongke.dotuoi;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import ConnectDB.ConnectToDB;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class DoTuoiController implements Initializable{
	private Stage stage;
	private Scene scene;

	@FXML
	private CheckComboBox<String> doTuoiBox;
	@FXML
	private TableView<InforPerson> doTuoiTable;
	@FXML
	private TableColumn<InforPerson, Number> STTCol;
	@FXML
	private TableColumn<InforPerson, String> fullNameCol;
	@FXML
	private TableColumn<InforPerson, String> birthCol;
	@FXML
	private TableColumn<InforPerson, String> ageCol;
	@FXML
	private TableColumn<InforPerson, String> jobCol;
	@FXML
	private TableColumn<InforPerson, String> jobPlaceCol;
	@FXML
	private NumberAxis nhanKhauAxis;
	@FXML
	private CategoryAxis doTuoiAxis;
	@FXML
	private BarChart<String, Number> doTuoiChart;
	@FXML
	private TableView<DoTuoi> doTuoiThongKeTable;
	@FXML
	private TableColumn<DoTuoi, String> doTuoi;
	@FXML
	private TableColumn<DoTuoi, Number> soNK;
	@FXML
	private TableColumn<DoTuoi, String> tiLe;
	
	
	@SuppressWarnings("exports")
	public ObservableList<InforPerson> doTuoiList = FXCollections.observableArrayList(SQLConnection.danhSachNhanKhau);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTable();
		loadChart();
	}
	
	private void loadTable() {
		STTCol.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<Number>(1 + doTuoiTable.getItems().indexOf(column.getValue())));
		fullNameCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
		birthCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(ConnectToDB.VNDF.format(Date.valueOf(cellData.getValue().getPerson().getBirthDate().toString()))));
		ageCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(toAge(cellData.getValue())));
		jobCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(toJob(cellData.getValue())));
		jobPlaceCol.setCellValueFactory(cellData -> 
			new SimpleStringProperty(toJobPlace(cellData.getValue())));
		
		doTuoiTable.setItems(doTuoiList);
	}
	
	private String toAge(InforPerson nhanKhau) {
		int age = nhanKhau.getPerson().getAge();
		if (age < 6)
			return "M???u gi??o";
		
		if (age < 12)
			return "C???p 1";
		
		if (age < 16)
			return "C???p 2";

		if (age < 19)
			return "C???p 3";

		if (age < 56)
			return "????? tu???i lao ?????ng";
		
		return "Ngh??? h??u";

	}
	
	private String toJob(InforPerson nhanKhau) {
		int age = nhanKhau.getPerson().getAge();		
		if (age < 19)
			return "H???c sinh";
		
		if (age < 56)
			return nhanKhau.getWork().getJob();
		
		return "???? ngh??? h??u";			
		
	}
	
	private String toJobPlace(InforPerson nhanKhau) {
		String age = toAge(nhanKhau);
		if (age.equals("M???u gi??o"))
			return "??ang h???c m???u gi??o";		
		
		if (age.equals("C???p 1"))
			return nhanKhau.getEducation().getPrimarySchool();
		
		if (age.equals("C???p 2"))
			return nhanKhau.getEducation().getJuniorHighSchool();
		
		if (age.equals("C???p 3"))
			return nhanKhau.getEducation().getHighSchool();
		
		if (age.equals("????? tu???i lao ?????ng"))
			return nhanKhau.getWork().getPlace();
		
		return "???? ngh??? h??u";
	}
	
	private void loadChart() {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("????? tu???i");
		
		ArrayList<InforPerson> mauGiaoNK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> cap1NK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> cap2NK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> cap3NK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> laoDongNK = new ArrayList<InforPerson>();
		ArrayList<InforPerson> nghiHuuNK = new ArrayList<InforPerson>();
		
		for (InforPerson nk : SQLConnection.danhSachNhanKhau) {
			switch(toAge(nk)) {
				case "M???u gi??o": mauGiaoNK.add(nk); break;
				case "C???p 1": cap1NK.add(nk); break;
				case "C???p 2": cap2NK.add(nk); break;
				case "C???p 3": cap3NK.add(nk); break;
				case "????? tu???i lao ?????ng": laoDongNK.add(nk); break;
				case "Ngh??? h??u": nghiHuuNK.add(nk); break;
				default: System.out.println("???");
			}
		}
		
		series.getData().add(new XYChart.Data<>("M???u gi??o", mauGiaoNK.size()));
		series.getData().add(new XYChart.Data<>("C???p 1", cap1NK.size()));
		series.getData().add(new XYChart.Data<>("C???p 2", cap2NK.size()));
		series.getData().add(new XYChart.Data<>("C???p 3", cap3NK.size()));
		series.getData().add(new XYChart.Data<>("????? tu???i lao ?????ng", mauGiaoNK.size()));
		series.getData().add(new XYChart.Data<>("Ngh??? h??u", mauGiaoNK.size()));
		
		doTuoiChart.getData().add(series);
		
		ObservableList<DoTuoi> list = FXCollections.observableArrayList(
				new DoTuoi("M???u gi??o", mauGiaoNK.size(), String.valueOf(Math.round(10000.0 * mauGiaoNK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("C???p 1", cap1NK.size(), String.valueOf(Math.round(10000.0 * cap1NK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("C???p 2", cap2NK.size(), String.valueOf(Math.round(10000.0 * cap2NK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("C???p 3", cap3NK.size(), String.valueOf(Math.round(10000.0 * cap3NK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("????? tu???i lao ?????ng", laoDongNK.size(), String.valueOf(Math.round(10000.0 * laoDongNK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("Ngh??? h??u", nghiHuuNK.size(), String.valueOf(Math.round(10000.0 * nghiHuuNK.size()/SQLConnection.danhSachNhanKhau.size()) / 100.0) + "%"),
				new DoTuoi("T???ng", SQLConnection.danhSachNhanKhau.size(), "100%"));
		
		doTuoi.setCellValueFactory(new PropertyValueFactory<DoTuoi, String>("doTuoi"));
		soNK.setCellValueFactory(new PropertyValueFactory<DoTuoi, Number>("soNK"));
		tiLe.setCellValueFactory(new PropertyValueFactory<DoTuoi, String>("tiLe"));
		
		doTuoiThongKeTable.setItems(list);
		
	}

	
	@SuppressWarnings("exports")
	@FXML
	public void handleLocButton(ActionEvent event) {
		
		doTuoiList.clear();
		
		ObservableList<String> list = doTuoiBox.getCheckModel().getCheckedItems();

		if (list.size() == 0)
			doTuoiList.addAll(SQLConnection.danhSachNhanKhau);
		
		for(InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
			for (String tuoi : list) {
				if (toAge(nhanKhau).equals(tuoi)) {
					doTuoiList.add(nhanKhau);
					System.out.println(nhanKhau.getPerson().getPersonID());
				}
			}
		}
		doTuoiTable.setItems(doTuoiList);
	}
	
	@SuppressWarnings("exports")
	@FXML
	public void handleShowDetail(ActionEvent event) {

		PersonDetailController.nhanKhauSelected = doTuoiTable.getSelectionModel().getSelectedItem();
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
