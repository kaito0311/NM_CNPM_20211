package managehouseholdbook.createhouseholdbook;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat.Style;
import java.util.ResourceBundle;

import javax.swing.Action;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import javafx.scene.Node;

public class CreateNewHouseholdBookController implements Initializable {

    // ============= Phần chức năng tạo sổ hộ khẩu ==============

    @FXML
    public Label labelNhapMaHoKhau;
    @FXML
    public Label labelChonChuHo;
    @FXML
    public ComboBox<String> comboBoxMaHoKhau;
    @FXML
    public ComboBox<String> comboBoxChuHo;
    @FXML
    public ComboBox<String> comboBoxThanhPho;
    @FXML
    public ComboBox<String> comboBoxQuan;
    @FXML
    public ComboBox<String> comboBoxPhuong;
    @FXML
    public Button xacNhan;

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
        comboBoxMaHoKhau.setItems(listMaHoKhau);
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
                comboBoxQuan.setValue("Quận/Huyện");
            comboBoxQuan.setItems(listDistrictName);
            // comboBoxQuan.getSelectionModel().select(-1);
            // System.out.println(comboBoxQuan.getPromptText());
            // comboBoxQuan.getSelectionModel().clearAndSelect(-1);

        } catch (Exception e) {

            System.out.println("Lỗi rồi bạn ei");
        }
    }

    public void comboBoxDistrictChanged(ActionEvent event) {
        comboBoxPhuong.setPromptText("Xã/Phường");
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
        System.out.println(comboBoxMaHoKhau.getSelectionModel().getSelectedIndex());
        System.out.println(comboBoxMaHoKhau.getValue());
    }

    public void xacNhan(ActionEvent event) {
        System.out.println("xac nhan");
    }

    // =========== Phần chuyển qua lại giữa các chức năng =================

    @FXML
    Button buttonChangeBook;
    @FXML
    Button buttonTemporaryAbsence_Residence;

    private Stage stage;
    private Scene scene;

    private void setNewSceneInSameWindow(String source, ActionEvent event) {
        try {

            TabPane root = (TabPane) FXMLLoader.load(getClass().getResource(source));
            root.getSelectionModel().select(1);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Error in class ChangeHouseholdBookController");
        }
    }

    public void changeToChangeHouseholdBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../thaydoisohokhau/ChangeHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println("Loi changeToChangeHouseholdBook in class CreateNewHouseholdBookController...");
            System.out.println(e.getStackTrace());
        }
    }

}