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

import database.SQLConnection;
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
import javafx.scene.Parent;
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
import model.person.Person;

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
	public DatePicker ngayCap;
	@FXML
	public TextField thanhPhoSinhRa;
	@FXML
	public TextField thanhPhoCuTru;

	@FXML
	public TextField tenKhac;
	@FXML
	public TextField quocTich;

	@FXML
	public TextField noiCap;
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
	ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "N???");

	@FXML
	public TextField quanHeVoiChuHo;
	@FXML
	public TextField noiLamViec;

	@FXML
	public Button xacNhan;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gioiTinh.setItems(listGioiTinh);
		initializeNation();
		initializeBirthNation();
		setEthnic();
		setNationality();
	}

	/**
	 *
	 * @author T???n Minh s???a ph???n comboBox cho nguy??n qu??n, n??i sinh
	 */
	// =====================================================================
	@FXML
	public ComboBox<String> comboBoxBirthProvince;
	@FXML
	public ComboBox<String> comboBoxBirthDistrict;
	@FXML
	public ComboBox<String> comboBoxBirthCommune;

	@FXML
	ComboBox<String> comboBoxBirthNation;

	ObservableList<String> listBirthNationName = FXCollections.observableArrayList();
	ObservableList<String> listBirthNationID = FXCollections.observableArrayList();

	ObservableList<String> listBirthProvinceName = FXCollections.observableArrayList();
	ObservableList<String> listBirthProvinceID = FXCollections.observableArrayList();
	ObservableList<String> listBirthProvinceNationID = FXCollections.observableArrayList();
	private int indexBirthProvince = -1, indexBirthDistrict = -1, indexBirthCommune = -1;

	ObservableList<String> listBirthDistrictName = FXCollections.observableArrayList();
	ObservableList<String> listBirthDistrictID = FXCollections.observableArrayList();
	ObservableList<String> listBirthDistrictProvinceID = FXCollections.observableArrayList();

	ObservableList<String> listBirthCommuneName = FXCollections.observableArrayList();
	ObservableList<String> listBirthCommuneID = FXCollections.observableArrayList();
	ObservableList<String> listBirthCommnueDistrictID = FXCollections.observableArrayList();
	int indexBirthNation = -1;

	// ============================================================================

	//
	/**
	 *
	 * @TanMInh s???a
	 */
	@FXML
	ComboBox<String> comboBoxOriginNation;
	@FXML
	public ComboBox<String> comboBoxOriginProvince;
	@FXML
	public ComboBox<String> comboBoxOriginDistrict;
	@FXML
	public ComboBox<String> comboBoxOriginCommune;

	ObservableList<String> listOriginNationName = FXCollections.observableArrayList();
	ObservableList<String> listOriginNationID = FXCollections.observableArrayList();

	ObservableList<String> listOriginProvinceName = FXCollections.observableArrayList();
	ObservableList<String> listOriginProvinceID = FXCollections.observableArrayList();
	ObservableList<String> listOriginProvinceNationID = FXCollections.observableArrayList();
	private int indexOriginNation = -1, indexOriginProvince = -1, indexOriginDistrict = -1, indexOriginCommune = -1;

	ObservableList<String> listOriginDistrictName = FXCollections.observableArrayList();
	ObservableList<String> listOriginDistrictID = FXCollections.observableArrayList();
	ObservableList<String> listOriginDistrictProvinceID = FXCollections.observableArrayList();

	ObservableList<String> listOriginCommuneName = FXCollections.observableArrayList();
	ObservableList<String> listOriginCommuneID = FXCollections.observableArrayList();
	ObservableList<String> listOriginCommnueDistrictID = FXCollections.observableArrayList();

	// =================================================================================================
	void initializeNation() {
		String sql = "select * from Address.Nation;";

		try {
			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listOriginNationID.clear();
			listOriginNationName.clear();
			while (result.next()) {
				listOriginNationID.add(result.getString("nationid"));
				listOriginNationName.add(result.getString("name"));
			}

			comboBoxOriginNation.setItems(listOriginNationName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			System.out.println("method: initializeNation");
		}
	}

	public void comboBoxOriginNationChange(ActionEvent event) {
		int index = comboBoxOriginNation.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			return;
		}
		String nationID = listOriginNationID.get(index);
		takeOriginProvinceCorrespondNation(nationID);

	}

	// Action lien quan den province
	public void takeOriginProvinceCorrespondNation(String nationID) {
		if (nationID.length() == 1) {
			nationID = "0" + nationID;
		}
		String sql = "select * from Address.Province where nationID = '" + nationID + "';";
		try {

			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listOriginProvinceID.clear();
			listOriginProvinceName.clear();
			listOriginProvinceNationID.clear();

			while (result.next()) {
				listOriginProvinceID.add(result.getString("provinceID"));
				listOriginProvinceName.add(result.getNString("name"));
				listOriginProvinceNationID.add(result.getString("nationID"));
			}

			comboBoxOriginProvince.setItems(listOriginProvinceName);
		} catch (SQLException e) {
			System.out.println("Loi load " + sql + " " + getClass());
			System.out.println(e.getMessage());
		}
	}

	public void comboBoxOriginProvinceChanged(ActionEvent event) {
		int index = comboBoxOriginProvince.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			comboBoxOriginDistrict.getItems().clear();
			return;
		}
		takeOriginDistrictCorrespondProvince(listOriginProvinceID.get(index).toString());
	}

	// Action lien quan den quan
	private void takeOriginDistrictCorrespondProvince(String provinceID) {
		if (provinceID.length() == 1)
			provinceID = "0" + provinceID;
		String sql = "select * from Address.District where ProvinceID ='" + provinceID + "'; ";

		try {

			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listOriginDistrictID.clear();
			listOriginDistrictProvinceID.clear();
			listOriginDistrictName.clear();

			int change = 0;
			while (result.next()) {
				listOriginDistrictID.add(result.getString("districtID"));
				listOriginDistrictName.add(result.getString("Name"));
				listOriginDistrictProvinceID.add(result.getString("provinceID"));

			}
			if (listOriginDistrictName.size() == 0)
				comboBoxOriginDistrict.setValue("Qu???n/Huy???n");
			comboBoxOriginDistrict.setItems(listOriginDistrictName);
			if (change == 1) {
				comboBoxOriginDistrict.getSelectionModel().select(indexOriginDistrict);
			}
			// comboBoxQuan.getSelectionModel().select(-1);
			// System.out.println(comboBoxQuan.getPromptText());
			// comboBoxQuan.getSelectionModel().clearAndSelect(-1);

		} catch (Exception e) {

			System.out.println("L???i r???i b???n ei");
		}
	}

	public void comboBoxOriginDistrictChanged(ActionEvent event) {
		comboBoxOriginCommune.setPromptText("X??/Ph?????ng");
		int index = comboBoxOriginDistrict.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			comboBoxOriginCommune.getItems().clear();
			return;
		}
		takeOriginCommuneCorrespondDistrict(listOriginDistrictID.get(index).toString());
	}

	// Action lien quan den xa
	private void takeOriginCommuneCorrespondDistrict(String districtID) {
		if (districtID.length() == 1)
			districtID = "0" + districtID;
		String sql = "select * from Address.Commune where districtID ='" + districtID + "'; ";
		try {
			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listOriginCommuneID.clear();
			listOriginCommuneName.clear();
			listOriginCommnueDistrictID.clear();

			while (result.next()) {
				listOriginCommuneID.add(result.getString("communeID"));
				listOriginCommuneName.add(result.getString("Name"));
				listOriginCommnueDistrictID.add(result.getString("districtID"));

			}
			comboBoxOriginCommune.getItems().setAll(listOriginCommuneName);

		} catch (SQLException e) {
			// System.out.println("");
			System.out.println(e.getSQLState() + " " + getClass());
		}
	}

	///////////////////// BIRTH PLACE /////////////////////////////////

	void initializeBirthNation() {
		String sql = "select * from Address.Nation;";
		try {
			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listBirthNationID.clear();
			listBirthNationName.clear();
			int change = 0;
			while (result.next()) {
				listBirthNationID.add(result.getString("nationid"));
				listBirthNationName.add(result.getString("name"));
			}

			comboBoxBirthNation.setItems(listBirthNationName);
			if (change == 1) {
				comboBoxBirthNation.getSelectionModel().select(indexBirthNation);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			System.out.println("method: initializeBirthNation");
		}
	}

	public void comboBoxBirthNationChange(ActionEvent event) {
		int index = comboBoxBirthNation.getSelectionModel().getSelectedIndex();
		if (index == -1)
			return;
		String nationID = listBirthNationID.get(index);
		takeBirthProvinceCorrespondNation(nationID);
	}

	// Action lien quan den province

	public void takeBirthProvinceCorrespondNation(String nationID) {
		if (nationID.length() == 1) {
			nationID = "0" + nationID;
		}
		String sql = "select * from Address.Province where nationID = '" + nationID + "';";
		try {

			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listBirthProvinceID.clear();
			listBirthProvinceName.clear();
			listBirthProvinceNationID.clear();
			int change = 0;
			while (result.next()) {
				listBirthProvinceID.add(result.getString("provinceID"));
				listBirthProvinceName.add(result.getNString("name"));
				listBirthProvinceNationID.add(result.getString("nationID"));

			}

			comboBoxBirthProvince.setItems(listBirthProvinceName);
			if (change == 1) {
				comboBoxBirthProvince.getSelectionModel().select(indexBirthProvince);
			}
		} catch (SQLException e) {
			System.out.println("Loi load " + sql + " " + getClass());
			System.out.println(e.getMessage());
		}
	}

	public void comboBoxBirthProvinceChanged(ActionEvent event) {
		int index = comboBoxBirthProvince.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			comboBoxBirthDistrict.getItems().clear();
			return;
		}
		takeBirthDistrictCorrespondProvince(listBirthProvinceID.get(index).toString());
	}

	// Action lien quan den quan
	private void takeBirthDistrictCorrespondProvince(String provinceID) {
		if (provinceID.length() == 1)
			provinceID = "0" + provinceID;
		String sql = "select * from Address.District where ProvinceID ='" + provinceID + "'; ";

		try {

			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listBirthDistrictID.clear();
			listBirthDistrictProvinceID.clear();
			listBirthDistrictName.clear();
			int change = 0;

			while (result.next()) {
				listBirthDistrictID.add(result.getString("districtID"));
				listBirthDistrictName.add(result.getString("Name"));
				listBirthDistrictProvinceID.add(result.getString("provinceID"));
			}
			if (listBirthDistrictName.size() == 0)
				comboBoxBirthDistrict.setValue("Qu???n/Huy???n");
			comboBoxBirthDistrict.setItems(listBirthDistrictName);
			if (change == 1) {
				comboBoxBirthDistrict.getSelectionModel().select(indexBirthDistrict);
			}
			// comboBoxQuan.getSelectionModel().select(-1);
			// System.out.println(comboBoxQuan.getPromptText());
			// comboBoxQuan.getSelectionModel().clearAndSelect(-1);

		} catch (Exception e) {

			System.out.println("L???i r???i b???n ei");
		}
	}

	public void comboBoxBirthDistrictChanged(ActionEvent event) {
		comboBoxBirthCommune.setPromptText("X??/Ph?????ng");
		int index = comboBoxBirthDistrict.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			comboBoxBirthCommune.getItems().clear();
			return;
		}
		takeBirthCommuneCorrespondDistrict(listBirthDistrictID.get(index).toString());
	}

	// Action lien quan den xa
	private void takeBirthCommuneCorrespondDistrict(String districtID) {
		if (districtID.length() == 1)
			districtID = "0" + districtID;
		String sql = "select * from Address.Commune where districtID ='" + districtID + "'; ";

		try {
			ResultSet result = ConnectDatabase.statement.executeQuery(sql);
			listBirthCommuneID.clear();
			listBirthCommuneName.clear();
			listBirthCommnueDistrictID.clear();
			int change = 0;
			while (result.next()) {
				listBirthCommuneID.add(result.getString("communeID"));
				listBirthCommuneName.add(result.getString("Name"));
				listBirthCommnueDistrictID.add(result.getString("districtID"));
			}
			comboBoxBirthCommune.getItems().setAll(listBirthCommuneName);
			if (change == 1) {
				comboBoxBirthCommune.getSelectionModel().select(indexBirthCommune);
				// System.out.println(inforPerson.getBirthPlace().getCommuneID());
			}

		} catch (SQLException e) {
			// System.out.println("");
			System.out.println(e.getSQLState() + " " + getClass());
		}
	}

	// ========================================================================================
	public void xacNhan(ActionEvent e) throws SQLException {

		// // kiem tra da nhap du c??c th??ng tin c???n thi???t hay ch??a
		// if (this.hoTen.getText().equals("") ||
		// this.gioiTinh.getPromptText().equals("")
		// || this.danToc.getText().equals("")
		// || this.quocGiaSinhRa.getText().equals("")
		// || this.thanhPhoSinhRa.getText().equals("")
		// || this.huyenSinhRa.getText().equals("")
		// || this.phuongSinhRa.getText().equals("")

		// || this.quocGiaCuTru.getText().equals("")
		// || this.thanhPhoCuTru.getText().equals("")
		// || this.huyenCuTru.getText().equals("")
		// || this.phuongCuTru.getText().equals("")) {
		// JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????? th??ng tin");
		// } else {
		// this.addNewPerson();
		// JOptionPane.showMessageDialog(null, "Th??m nh??n kh???u th??nh c??ng");
		// // System.out.println(this.thanhPhoCuTru.getText());
		// // System.out.println(this.getProvinceID(this.thanhPhoCuTru.getText()));
		// // System.out.println(this.getNationID(this.quocGiaCuTru.getText()));
		// // System.out.println(this.getCommuneID(this.phuongSinhRa.getText()));
		// }

		if (this.hoTen.getText().equals("") || this.gioiTinh.getPromptText().equals("")
				|| this.ngaySinh.getValue().toString().equals("")
				|| comboBoxNationality.getSelectionModel().getSelectedIndex() == -1
				|| comboBoxEthnic.getSelectionModel().getSelectedIndex() == -1 ||
				this.comboBoxBirthNation.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxBirthProvince.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxBirthDistrict.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxBirthCommune.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxOriginNation.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxOriginProvince.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxOriginDistrict.getSelectionModel().getSelectedIndex() == -1
				|| this.comboBoxOriginCommune.getSelectionModel().getSelectedIndex() == -1

				/*|| this.quanHeVoiChuHo.getText().length() == 0
				|| this.maHoKhau.getText().length() == 0*/) {
			JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????? th??ng tin");
		} else {
			if(!this.soCCCD.getText().equals("")) {
				if(this.getCMND(this.soCCCD.getText()).equals(this.soCCCD.getText()))
					JOptionPane.showMessageDialog(null, "C?? nh??n n??y ???? ???????c khai b??o trong d??? li???u d??n c??");
				else {
					this.addNewPerson();
					JOptionPane.showMessageDialog(null, "Th??m nh??n kh???u th??nh c??ng");
				}
			}

		}
	}

	int getNewPersonID() {
		String sql = "select personID from Person.Person where PersonID >= all(select PersonID from Person.Person)";
		int id = -1;
		try {
			ResultSet resultSet = ConnectDatabase.statement.executeQuery(sql);
			if (resultSet.next()) {
				int a = resultSet.getInt("personID") + 1;
				id = a;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(getClass());
			System.out.println("getNewPersonID()");
		}
		return id;
	}

	// Ethnic
	@FXML
	ComboBox<String> comboBoxNationality;
	@FXML
	ComboBox<String> comboBoxEthnic;

	ObservableList<String> listNationalityID = FXCollections.observableArrayList();
	ObservableList<String> listNationality = FXCollections.observableArrayList();
	ObservableList<String> listEthnicID = FXCollections.observableArrayList();
	ObservableList<String> listEthnic = FXCollections.observableArrayList();

	void setNationality() {
		String sql = null;
		try {
			sql = "select * from Person.nationality";
			ResultSet resultSet = ConnectDatabase.statement.executeQuery(sql);

			while (resultSet.next()) {
				listNationality.add(resultSet.getString("name"));
				listNationalityID.add(resultSet.getString("nationalityid"));
			}
			comboBoxNationality.setItems(listNationality);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(getClass());
			System.out.println("setNationality();");
		}
	}

	void setEthnic() {
		String sql = null;
		try {
			sql = "select * from Person.Ethnic";
			ResultSet resultSet = ConnectDatabase.statement.executeQuery(sql);

			while (resultSet.next()) {
				listEthnic.add(resultSet.getString("name"));
				listEthnicID.add(resultSet.getString("EthnicID"));
			}
			comboBoxEthnic.setItems(listEthnic);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(getClass());
			System.out.println("setEthnic();");
		}
	}

	//
	// them nhan khau moi
	public void addNewPerson() throws SQLException {
		ConnectDatabase.ConnectData();
		int id = getNewPersonID();

		if (id == -1) {
			JOptionPane.showMessageDialog(null, "Vui l??ng th??? l???i.");
			;
			return;
		}

		// L???y personid
		// them vao bang Person
		String sql = "INSERT INTO [Person].[Person](PersonID, Fullname,Nickname,BirthDate,Gender,EthnicID,NationalityID) VALUES(?,?,?,?,?,?,?)";
		try {

			PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql);
			// ps.setInt(1,20);
			ps.setInt(1, id);
			ps.setString(2, this.hoTen.getText());
			ps.setString(3, this.tenKhac.getText());
			Date sd = Date.valueOf(this.ngaySinh.getValue());
			ps.setDate(4, sd);
			int genderID;
			if (this.gioiTinh.getPromptText().equals("N???"))
				genderID = 0;
			else
				genderID = 1;
			ps.setInt(5, (genderID));
			ps.setString(6, listEthnicID.get(comboBoxEthnic.getSelectionModel().getSelectedIndex()));
			ps.setString(7, listNationalityID.get(comboBoxNationality.getSelectionModel().getSelectedIndex()));
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sql);
			return;
		}
		// return;

		// System.out.println(id);
		// them vao bang Person.BirthPlace

		// T???m th???i ????? ch?? th??ch v?? l???i hi???n th??? Ti???ng Vi???t
		String sql1 = "insert into [Person].[BirthPlace] (PersonID,NationID,ProvinceID,DistrictID,CommuneID) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql1);
			ps.setInt(1, id);
			ps.setString(2, listBirthNationID.get(this.comboBoxBirthNation.getSelectionModel().getSelectedIndex()));
			ps.setString(3, listBirthProvinceID.get(this.comboBoxBirthProvince.getSelectionModel().getSelectedIndex()));
			ps.setString(4, listBirthDistrictID.get(this.comboBoxBirthDistrict.getSelectionModel().getSelectedIndex()));
			ps.setString(5, listBirthCommuneID.get(this.comboBoxBirthCommune.getSelectionModel().getSelectedIndex()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sql1);
			return;
		}
		// them vao bang Person.Work

		String sql2 = "insert into [Person].[Work] (PersonID,Job,Place,ModifiedDate) values(?,?,?,?)";
		if (this.ngheNghiep.getText().length() != 0) {
			try {
				PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql2);
				ps.setInt(1, id);
				ps.setString(2, this.ngheNghiep.getText());
				ps.setString(3, this.noiLamViec.getText());
				long millis = System.currentTimeMillis();
				Date modifiedDate = new Date(millis);
				ps.setDate(4, modifiedDate);
				ps.executeUpdate();
			} catch (Exception e) {
				System.out.println(sql2);
				e.printStackTrace();
				return;
			}

		}

		// them vao bang OriginPlace
		String sql3 = "insert into [Person].[OriginPlace] (PersonID,NationID,ProvinceID,DistrictID,CommuneID) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql3);
			ps.setInt(1, id);
			ps.setString(2, listOriginNationID.get(this.comboBoxOriginNation.getSelectionModel().getSelectedIndex()));
			ps.setString(3,
					listOriginProvinceID.get(this.comboBoxOriginProvince.getSelectionModel().getSelectedIndex()));
			ps.setString(4,
					listOriginDistrictID.get(this.comboBoxOriginDistrict.getSelectionModel().getSelectedIndex()));
			ps.setString(5, listOriginCommuneID.get(this.comboBoxOriginCommune.getSelectionModel().getSelectedIndex()));
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(sql3);
			e.printStackTrace();
			return;
		}

		// them vao bang IdentityCard
		if (this.soCCCD.getText() != "") {
			String sql4 = "insert into [Person].[IdentityCard] (PersonID,Number,RegisterDate,RegisterPlace) values(?,?,?,?)";
			try {
				PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql4);
				ps.setInt(1, id);
				ps.setString(2, this.soCCCD.getText());
				Date sd = Date.valueOf(this.ngayCap.getValue());
				ps.setDate(3, sd);
				ps.setString(4, this.noiCap.getText());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// them vao bang Education
		if (this.ngheNghiep.getText().equals("H???c sinh")) {
			String sql5 = "insert into [Person].[Education] (PersonID,StatusID,PrimarySchool,JuniorHighSchool,HighSchool) values(?,?,?,?,?)";
			try {
				PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql5);
				ps.setInt(1, id);
				int statusid = 0;
				if (this.ngaySinh.getValue().getYear() >= 2016 && this.ngaySinh.getValue().getYear() <= 2017)
					statusid = 1;
				if (this.ngaySinh.getValue().getYear() >= 2011 && this.ngaySinh.getValue().getYear() <= 2015)
					statusid = 2;
				if (this.ngaySinh.getValue().getYear() >= 2007 && this.ngaySinh.getValue().getYear() <= 2010)
					statusid = 3;
				if (this.ngaySinh.getValue().getYear() >= 2004 && this.ngaySinh.getValue().getYear() <= 2006)
					statusid = 4;
				ps.setInt(2, statusid);
				if (statusid == 0 || statusid == 1) {
					ps.setString(3, "NULL");
					ps.setString(4, "NULL");
					ps.setString(5, "NULL");
				}
				if (statusid == 2) {
					ps.setString(3, this.noiLamViec.getText());
					ps.setString(4, "NULL");
					ps.setString(5, "NULL");
				}
				if (statusid == 3) {
					ps.setString(4, this.noiLamViec.getText());
					ps.setString(3, "NULL");
					ps.setString(5, "NULL");
				}
				if (statusid == 4) {
					ps.setString(3, "NULL");
					ps.setString(4, "NULL");
					ps.setString(5, this.noiLamViec.getText());
				}

				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.getBookID(this.getHostID(this.maHoKhau.getText()))!=0) {
    	String sql6 = "insert into [Person].[Residence] (PersonID,ResidenceTypeID,PrePermanentAddress,BookID,RelationshipWithHead) values(?,?,?,?,?)";
    	try {
    		PreparedStatement ps = ConnectDatabase.connection.prepareStatement(sql6);
    		ps.setInt(1, id);
    		ps.setInt(2,1);
    		ps.setString(3,"NULL");
    		if (this.maHoKhau.getText().length() == 0)
    			ps.setInt(4, 0);
    		else 
    			ps.setInt(4, this.getBookID(this.getHostID(this.maHoKhau.getText())));
    		if (this.quanHeVoiChuHo.getText().length() == 0)
    			ps.setString(5, null);
    		else 
    			ps.setString(5,this.quanHeVoiChuHo.getText());
    		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		}
    	
	}
	
	public String getCMND(String number) {
		   ConnectDatabase.ConnectData();
		   	try {
		       	Statement st = ConnectDatabase.connection.createStatement();
		       	ResultSet rs;
		       	rs = st.executeQuery("SELECT Number FROM [Person].[IdentityCard] WHERE Number ='"+ number + "'; ");
		       	String id = "";
		       	while(rs.next()) {
		       	  id = rs.getString("Number");
		       
		       	}
		       	return id;
		       	}
		       	catch (Exception e){
		       		System.err.println("Error");
		       	}
		   		return null;  
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

	public int getPersonID(String Fullname) throws SQLException {
		ConnectDatabase.ConnectData();
		try {
			Statement st = ConnectDatabase.connection.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT PersonID FROM [Person].[Person] WHERE Fullname like Fullname");
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);

			}
			return id;
		} catch (Exception e) {
			System.err.println("Error");
		}
		return 0;

	}

	public String getNationID(String Nationality) throws SQLException {
		ConnectDatabase.ConnectData();
		try {
			Statement st = ConnectDatabase.connection.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT NationID FROM [Address].[Nation] WHERE Name = N'" + Nationality + "';");
			String id = null;
			while (rs.next()) {
				id = rs.getString("NationID");

			}
			return id;
		} catch (Exception e) {
			System.err.println("Error");
		}
		return null;
	}

	public String getProvinceID(String Province) throws SQLException {
		ConnectDatabase.ConnectData();
		try {
			Statement st = ConnectDatabase.connection.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT ProvinceID FROM [Address].[Province] WHERE Name = N'" + Province + "'; ");
			// rs = st.executeQuery("select * from [Address].[Province]");
			String id = null;
			while (rs.next()) {
				id = rs.getString("ProvinceID");
				// System.out.println(rs.getString("Name"));
			}
			// st.close();
			return id;
		} catch (Exception e) {
			System.err.println("Error");
		}
		return null;
	}

	public String getDistrictID(String District) throws SQLException {
		ConnectDatabase.ConnectData();
		try {
			Statement st = ConnectDatabase.connection.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT DistrictID FROM [Address].[District] WHERE Name = N'" + District + "'; ");
			String id = null;
			while (rs.next()) {
				id = rs.getString(1);

			}
			return id;
		} catch (Exception e) {
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
			rs = st.executeQuery("SELECT CommuneID FROM [Address].[Commune] WHERE Name = N'" + Commune + "';");
			String id = null;
			while (rs.next()) {
				id = rs.getString("CommuneID");

			}
			return id;
		} catch (Exception e) {
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
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabAddNewPerson(event);");
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabChangePerson(event);");
        }
    }

    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/movehousehold/MoveHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabMoveHouseHold(event);");
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabDeleteHousehold(event);");
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

	
    @FXML
    Button buttonTemporaryAbsence_Residence;

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
