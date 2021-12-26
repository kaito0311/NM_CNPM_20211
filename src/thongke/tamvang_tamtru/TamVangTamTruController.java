package thongke.tamvang_tamtru;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.SQLConnection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class TamVangTamTruController implements Initializable{

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

	@SuppressWarnings("exports")
	public ObservableList<InforPerson> tamList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTable();
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
				return new SimpleStringProperty(cellData.getValue().getTemporaryResidence().getCommuneID());
			if (cellData.getValue().getResidence().getResidencetypeID() == 3)
				return new SimpleStringProperty(cellData.getValue().getTemporaryAbsence().getTempResidencePlace());
			return null;
	});	
	
		for (InforPerson nk : SQLConnection.danhSachNhanKhau)
			if(nk.getResidence().getResidencetypeID() == 2 || nk.getResidence().getResidencetypeID() == 3)
				tamList.add(nk);
		tamTable.setItems(tamList);
	}

	@SuppressWarnings("exports")
	@FXML
	public void handleLocButton(ActionEvent event) {
		LocalDate batdauDate = batdauDP.getValue();
		LocalDate ketthucDate = ketthucDP.getValue();
		System.out.println(String.valueOf(batdauDate));
		System.out.println(String.valueOf(ketthucDate));
		
		if(batdauDate != null && ketthucDate == null) {
			errorLabel.setText("Chưa chọn ngày bắt đầu! Vui lòng chọn lại!");
			return;
		}

		if(batdauDate == null && ketthucDate != null) {
			errorLabel.setText("Chưa chọn ngày kết thúc! Vui lòng chọn lại!");
			return;
		}
		
		if(batdauDate != null && ketthucDate != null && batdauDate.isAfter(ketthucDate) == true) {
			errorLabel.setText("Ngày bắt đầu sau ngày kết thúc! Vui lòng chọn lại!");
			return;
		}
		
		errorLabel.setText("Lọc thành công!");
		
		
		String tam = tamBox.getSelectionModel().getSelectedItem();
		if(tam == null)
			return;
		
		tamList.clear();
		for (InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
			if (tam.equals("Tạm trú") && nhanKhau.getResidence().getResidencetypeID() == 2) {
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
			
			else if (tam.equals("Tạm vắng") && nhanKhau.getResidence().getResidencetypeID() == 3){
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
//			if(nk.getResidenceType().getName().equals(tam))
//				tamList.add(nk);
		
		tamTable.setItems(tamList);

		
//		thoiGianList.clear();
//		for(InforPerson nhanKhau : SQLConnection.danhSachNhanKhau) {
//			if(nhanKhau.getResidence().getResidencetypeID() == 1)
//				thoiGianList.add(nhanKhau);
//			else if(nhanKhau.getResidence().getResidencetypeID() == 2) {
//				if (nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(ketthucDate)) {
//				
//				if(nhanKhau.getTemporaryResidence().getToDate() == null)
//					thoiGianList.add(nhanKhau);
//				
//				else if (nhanKhau.getTemporaryResidence().getToDate().toLocalDate().isAfter(batdauDate))
//					thoiGianList.add(nhanKhau);
////				&& (nhanKhau.getTemporaryResidence().getToDate().toLocalDate().isBefore || nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(ketthucDate) || nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(LocalDate.now())))
//				}
//			}
//
//		}
//		thoiGianTable.setItems(thoiGianList);

	}
	
	@SuppressWarnings("exports")
	@FXML
	public void handleShowDetail(ActionEvent event) {
		PersonDetailController.nhanKhauSelected = tamTable.getSelectionModel().getSelectedItem();
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


}
