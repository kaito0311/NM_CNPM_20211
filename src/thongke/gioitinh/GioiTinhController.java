package thongke.gioitinh;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class GioiTinhController implements Initializable {
	
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
	@SuppressWarnings("exports")


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
			new SimpleStringProperty(cellData.getValue().getPerson().getBirthDate().toString()));
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

}
