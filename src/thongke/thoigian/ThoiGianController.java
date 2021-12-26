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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.person.InforPerson;
import thongke.PersonDetailController;

public class ThoiGianController implements Initializable{

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
		STTCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(1 + thoiGianTable.getItems().indexOf(column.getValue())));
		fullNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getFullName()));
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
		
		System.out.println("Run handleLocButton in ThoiGianController in thongke");

		LocalDate batdauDate = batdauDP.getValue();
		LocalDate ketthucDate = ketthucDP.getValue();
		
		if(batdauDate.isAfter(ketthucDate) == true) {
			errorLabel.setText("NgÃ y báº¯t Ä‘áº§u sau ngÃ y káº¿t thÃºc! Vui lÃ²ng chá»�n láº¡i!");
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
//				&& (nhanKhau.getTemporaryResidence().getToDate().toLocalDate().isBefore || nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(ketthucDate) || nhanKhau.getTemporaryResidence().getFromDate().toLocalDate().isBefore(LocalDate.now())))
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
            m.setHeaderText("KhÃ´ng nhÃ¢n kháº©u nÃ o Ä‘Æ°á»£c chá»�n.");
            m.setContentText("Vui lÃ²ng chá»�n láº¡i.");
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
