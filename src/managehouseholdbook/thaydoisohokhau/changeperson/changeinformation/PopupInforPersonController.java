package managehouseholdbook.thaydoisohokhau.changeperson.changeinformation;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import Model.person.BirthPlace;
import Model.person.Ethnic;
import Model.person.IdentityCard;
import Model.person.InforPerson;
import Model.person.Nationality;
import Model.person.OriginPlace;
import Model.person.Person;
import Model.person.Residence;
import Model.person.ResidenceType;
import Model.person.Work;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class PopupInforPersonController extends AnchorPane implements Initializable {

    public String hoTen_;
    public LocalDate ngaySinh_;
    public int idSoHoKhau = 2;
    private InforPerson inforPerson = new InforPerson();
    boolean isHead = false;

    Stage primaryStage;
    List<String> relation11 = new ArrayList<>();

    /////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////
    ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nữ", "Nam", "LGBT");
    @FXML
    public ComboBox<String> gender;
    @FXML
    Label textFieldPersonID;
    @FXML
    TextField textFieldNamePerson;
    @FXML
    TextField textFieldOtherNamePerson;
    @FXML
    TextField textFieldBirthDate;
    @FXML
    TextField textFieldNationality;
    @FXML
    TextField textFieldBookID;
    @FXML
    TextField textFieldRelationWithHead;
    @FXML
    TextField textFieldEthnic;
    @FXML
    TextField textFieldWork;
    @FXML
    TextField textFieldWorkPlace;
    @FXML
    TextField textFieldIdentityCard;
    @FXML
    TextField textFieldRegisterDateIdentityCard;
    @FXML
    TextField textFieldRegisterPlaceIdentityCard;

    @FXML
    Button submiButton;

    @FXML
    Label confirmLabel;

    Alert alertConfirm = new Alert(javafx.scene.control.Alert.AlertType.NONE);

    //////////////////////////////////////////////////////
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

    public PopupInforPersonController() {
        relation11.add("chủ hộ");
        relation11.add("vợ");
        relation11.add("mẹ");
        relation11.add("bố");
    }

    ////////////////// ORIGIN PLACE //////////////////////
    void initializeNation() {
        String sql = "select * from Address.Nation;";

        try {
            ResultSet result = ConnectDatabase.statement.executeQuery(sql);
            listOriginNationID.clear();
            listOriginNationName.clear();
            int change = 0;
            while (result.next()) {
                listOriginNationID.add(result.getString("nationid"));
                listOriginNationName.add(result.getString("name"));
                if (indexOriginNation == -1 && inforPerson.getOriginPlace() != null) {
                    if (listOriginNationID.get(listOriginNationID.size() - 1)
                            .equals(inforPerson.getOriginPlace().getNationID())) {
                        indexOriginNation = listOriginNationID.size() - 1;
                        change = 1;
                    }
                }
            }

            comboBoxOriginNation.setItems(listOriginNationName);
            if (change == 1) {
                comboBoxOriginNation.getSelectionModel().select(indexOriginNation);
            }
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
            int change = 0;
            while (result.next()) {
                listOriginProvinceID.add(result.getString("provinceID"));
                listOriginProvinceName.add(result.getNString("name"));
                listOriginProvinceNationID.add(result.getString("nationID"));
                if (indexOriginProvince == -1 && inforPerson.getOriginPlace() != null)
                    if (listOriginProvinceID.get(listOriginProvinceID.size() - 1)
                            .equals(inforPerson.getOriginPlace().getProvinceID())) {
                        indexOriginProvince = listOriginProvinceID.size() - 1;
                        change = 1;
                    }
            }

            comboBoxOriginProvince.setItems(listOriginProvinceName);
            if (change == 1) {
                comboBoxOriginProvince.getSelectionModel().select(indexOriginProvince);
            }
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
                if (indexOriginDistrict == -1 && inforPerson.getOriginPlace() != null) {
                    if (listOriginDistrictID.get(listOriginDistrictID.size() - 1)
                            .equals(inforPerson.getOriginPlace().getDistrictID())) {
                        indexOriginDistrict = listOriginDistrictID.size() - 1;
                        change = 1;
                    }
                }
            }
            if (listOriginDistrictName.size() == 0)
                comboBoxOriginDistrict.setValue("Quận/Huyện");
            comboBoxOriginDistrict.setItems(listOriginDistrictName);
            if (change == 1) {
                comboBoxOriginDistrict.getSelectionModel().select(indexOriginDistrict);
            }
            // comboBoxQuan.getSelectionModel().select(-1);
            // System.out.println(comboBoxQuan.getPromptText());
            // comboBoxQuan.getSelectionModel().clearAndSelect(-1);

        } catch (Exception e) {

            System.out.println("Lỗi rồi bạn ei");
        }
    }

    public void comboBoxOriginDistrictChanged(ActionEvent event) {
        comboBoxOriginCommune.setPromptText("Xã/Phường");
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
            int change = 0;
            while (result.next()) {
                listOriginCommuneID.add(result.getString("communeID"));
                listOriginCommuneName.add(result.getString("Name"));
                listOriginCommnueDistrictID.add(result.getString("districtID"));

                if (indexOriginCommune == -1 && inforPerson.getOriginPlace() != null) {
                    if (listOriginCommuneID.get(listOriginCommuneID.size() - 1)
                            .equals(inforPerson.getOriginPlace().getCommuneID())) {
                        indexOriginCommune = listOriginCommnueDistrictID.size() - 1;
                        change = 1;
                    }
                }
            }
            comboBoxOriginCommune.getItems().setAll(listOriginCommuneName);
            if (change == 1) {
                comboBoxOriginCommune.getSelectionModel().select(indexOriginCommune);
                // System.out.println(inforPerson.getOriginPlace().getCommuneID());
            }

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
                if (indexBirthNation == -1 && inforPerson.getBirthPlace() != null) {
                    if (listBirthNationID.get(listBirthNationID.size() - 1)
                            .equals(inforPerson.getBirthPlace().getNationID())) {
                        indexBirthNation = listBirthNationID.size() - 1;
                        change = 1;
                    }
                }
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
                if (indexBirthProvince == -1 && inforPerson.getBirthPlace() != null) {
                    if (listBirthProvinceID.get(listBirthProvinceID.size() - 1)
                            .equals(inforPerson.getBirthPlace().getProvinceID())) {
                        indexBirthProvince = listBirthProvinceID.size() - 1;
                        change = 1;
                    }
                }

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
                if (indexBirthDistrict == -1 && inforPerson.getBirthPlace() != null) {
                    if (listBirthDistrictID.get(listBirthDistrictID.size() - 1)
                            .equals(inforPerson.getBirthPlace().getDistrictID())) {
                        indexBirthDistrict = listBirthDistrictID.size() - 1;
                        change = 1;
                    }
                }
            }
            if (listBirthDistrictName.size() == 0)
                comboBoxBirthDistrict.setValue("Quận/Huyện");
            comboBoxBirthDistrict.setItems(listBirthDistrictName);
            if (change == 1) {
                comboBoxBirthDistrict.getSelectionModel().select(indexBirthDistrict);
            }
            // comboBoxQuan.getSelectionModel().select(-1);
            // System.out.println(comboBoxQuan.getPromptText());
            // comboBoxQuan.getSelectionModel().clearAndSelect(-1);

        } catch (Exception e) {

            System.out.println("Lỗi rồi bạn ei");
        }
    }

    public void comboBoxBirthDistrictChanged(ActionEvent event) {
        comboBoxBirthCommune.setPromptText("Xã/Phường");
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

                if (indexBirthCommune == -1 && inforPerson.getBirthPlace() !=null) {
                    if (listBirthCommuneID.get(listBirthCommuneID.size() - 1)
                            .equals(inforPerson.getBirthPlace().getCommuneID())) {
                        indexBirthCommune = listBirthCommnueDistrictID.size() - 1;
                        change = 1;
                    }
                }
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

    //////////////////////////////////////////////////////
    // ====================================== Lấy thông tin từ cơ sở dữ liệu
    ////////////////////////////////////////////////////// ===============================================

    private boolean takeInforFromDatabase() {
        boolean hasResult = false;

        try {
            String sql = "select person.PersonID, FullName, Nickname, BirthDate, Gender, Ethnic.EthnicID as EthnicID,Ethnic.Name as Ethnic, Person.NationalityID as NationalityID, Nationality.Name as Nationality, PrePermanentAddress, RelationshipWithHead"
                    + " from  Person.Residence inner join Person.person "
                    + " on  Person.PersonID = Residence.PersonID "
                    + " inner join Person.Ethnic "
                    + " on Person.EthnicID = Ethnic.EthnicID "
                    + " inner join Person.Nationality "
                    + " on Person.NationalityID = Nationality.NationalityID where "
                    + "Residence.BookID = '" + this.idSoHoKhau + "' and"
                    + " person.FullName like N'" + this.hoTen_ + "' and person.BirthDate = '" + this.ngaySinh_
                    + "'";
            ResultSet result = ConnectDatabase.statement.executeQuery(sql);

            if (result.next() == true) {
                inforPerson.setPerson(new Person(result.getInt("personID"), result.getString("fullname"),
                        result.getString("nickname"), result.getDate("birthDate"), result.getInt("gender"),
                        result.getString("ethnicid"), result.getString("NationalityID")));
                inforPerson.setNationality(
                        new Nationality(result.getString("nationalityid"), result.getString("nationality")));
                inforPerson.setEthnic(new Ethnic(result.getString("ethnicid"), result.getString("ethnic")));
                hasResult = true;
                if(result.getString("relationshipwithhead").toLowerCase().equals("chủ hộ")) isHead = true;
            }

            if (inforPerson.getPerson() != null) {

                // Lấy thông tin nơi làm việc
                sql = "select * from person.work where person.work.personID = '" + inforPerson.getPerson().getPersonID()
                        + "'";
                result = ConnectDatabase.statement.executeQuery(sql);
                // result.next();
                if (result.next() == true) {
                    inforPerson.setWork(
                            new Work(result.getInt("personID"), result.getString("job"), result.getString("place"),
                                    result.getDate("modifiedDate")));
                }

                // Lấy thông tin nguyên quán
                sql = "select * from person.originplace where personID = '" + inforPerson.getPerson().getPersonID()
                        + "'";
                result = ConnectDatabase.statement.executeQuery(sql);

                if (result.next() == true) {
                    inforPerson.setOriginPlace(new OriginPlace(result.getInt("personID"), result.getString("nationID"),
                            result.getString("provinceID"), result.getString("Districtid"),
                            result.getString("communeID")));
                }

                // Lấy thông tin nơi sinh
                sql = "select * from person.birthplace where personID = '" + inforPerson.getPerson().getPersonID()
                        + "'";
                result = ConnectDatabase.statement.executeQuery(sql);

                if (result.next() == true) {
                    inforPerson.setBirthPlace(new BirthPlace(result.getInt("personID"), result.getString("nationID"),
                            result.getString("provinceID"), result.getString("Districtid"),
                            result.getString("communeID")));
                }

                // Lấy thông tin căn cước công dân
                sql = "select * from Person.IdentityCard where Person.IdentityCard.PersonID = '"
                        + inforPerson.getPerson().getPersonID() + "'";
                result = ConnectDatabase.statement.executeQuery(sql);

                if (result.next() == true) {
                    inforPerson.setCard(new IdentityCard(result.getInt("personID"), result.getString("number"),
                            result.getDate("registerDate"), result.getString("registerPlace")));
                }

                // Lấy thông tin quan hệ với chủ hộ
                if (isHead == false) {
                    sql = "select PersonID, Residence.ResidenceTypeID, PrePermanentAddress, BookID, RelationshipWithHead, Name from Person.Residence inner join Person.ResidenceType on Person.Residence.ResidenceTypeID = Person.ResidenceType.ResidenceTypeID "
                            + "where Person.Residence.BookID = '" + this.idSoHoKhau + "'"
                            + " and Person.Residence.PersonID = '"
                            + inforPerson.getPerson().getPersonID() + "';";
                    result = ConnectDatabase.statement.executeQuery(sql);
                    if (result.next() == true) {
                        inforPerson
                                .setResidence(new Residence(result.getInt("personID"), result.getInt("residenceTypeID"),
                                        result.getString("prePermanentAddress"), result.getInt("bookID"),
                                        result.getString("RelationshipWithHead")));
                        inforPerson.setResidenceType(
                                new ResidenceType(result.getInt("residenceTypeID"), result.getString("Name")));
                    }
                } else {

                    inforPerson.setResidence(new Residence(inforPerson.getPerson().getPersonID(), 1,
                            "", this.idSoHoKhau,
                            "Chủ hộ"));
                    inforPerson.setResidenceType(
                            new ResidenceType(1, "Thường trú"));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass() + " Lỗi ở popup take infor from database");
        }

        return hasResult;

    }

    // ===================================Show thông
    // tin================================

    @FXML
    public void fillPopup() {
        gender.setItems(listGioiTinh);
        gender.getSelectionModel().select(inforPerson.getPerson().getGender());
        textFieldPersonID.setText(Integer.toString(inforPerson.getPerson().getPersonID()));
        textFieldNamePerson.setText(inforPerson.getPerson().getFullName());
        textFieldOtherNamePerson.setText(inforPerson.getPerson().getNickName());
        textFieldBirthDate.setText(inforPerson.getPerson().getBirthDate().toString());
        textFieldBookID.setText(Integer.toString(inforPerson.getResidence().getBookID()));
        textFieldRelationWithHead.setText(inforPerson.getResidence().getRelationshipWithHead());

        if (inforPerson.getCard() != null) {
            textFieldIdentityCard.setText(inforPerson.getCard().getNumber());
            textFieldRegisterDateIdentityCard.setText(inforPerson.getCard().getRegisterDate().toString());
            textFieldRegisterPlaceIdentityCard.setText(inforPerson.getCard().getRegisterPlace());

        }

        //
        textFieldNationality.setText(inforPerson.getNationality().getName());
        textFieldEthnic.setText(inforPerson.getEthnic().getName());
        if (inforPerson.getWork() != null) {
            textFieldWork.setText(inforPerson.getWork().getJob());

            textFieldWorkPlace.setText(inforPerson.getWork().getPlace());
        }
        initializeBirthNation();
        if (inforPerson.getBirthPlace() != null) {
            takeBirthProvinceCorrespondNation(inforPerson.getBirthPlace().getNationID());
            // comboBoxBirthProvince.setItems(listBirthProvinceName);
            // comboBoxBirthProvince.getSelectionModel().select(indexBirthProvince);
            takeBirthDistrictCorrespondProvince(inforPerson.getBirthPlace().getProvinceID());
            takeBirthCommuneCorrespondDistrict(inforPerson.getBirthPlace().getDistrictID());
        }
        // comboBoxOriginProvince.setItems(listOriginProvinceName);
        // comboBoxOriginProvince.getSelectionModel().select(indexOriginProvince);
        initializeNation();
        if (inforPerson.getOriginPlace() != null) {
            takeOriginProvinceCorrespondNation(inforPerson.getOriginPlace().getNationID());
            takeOriginDistrictCorrespondProvince(inforPerson.getOriginPlace().getProvinceID());
            takeOriginCommuneCorrespondDistrict(inforPerson.getOriginPlace().getDistrictID());

        }

    }
    // ==========================================================================================================

    public boolean setInfor(String hoTenNguoi, LocalDate ngaySinh, int idSoHoKhau) throws Exception {
        this.hoTen_ = hoTenNguoi;
        this.ngaySinh_ = ngaySinh;
        this.idSoHoKhau = idSoHoKhau;
        // Lấy tên người

        if (takeInforFromDatabase()) {
            fillPopup();
            return true;
        } else
            return false;

    }

    // ===================== check thông tin ==============
    private void changeBookIDAndRelationshipWithHead() {
        // Lay book ID rồi so sanh
        int currentID = Integer.parseInt(textFieldBookID.getText());
        if (isHead == true) {

            if (currentID != inforPerson.getResidence().getBookID()
                    || !textFieldRelationWithHead.getText().toString().toLowerCase().equals("chủ hộ"))
                confirmLabel.setText("Không thể thay đổi mã sổ hộ khẩu và quan hệ của chủ hộ");
            return;
        }
        // Kiểm tra xem có tồn tại ID không nếu nó thay đổi
        int hasCurrentID = 0;

        String sql = "select bookid from household.book;";
        try {

            ResultSet result = ConnectDatabase.statement.executeQuery(sql);
            while (result.next()) {
                if (currentID == result.getInt("bookID")) {

                    hasCurrentID = 1;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeBookID");
            return;
        }

        if ((hasCurrentID == 1)) {
            if (currentID != inforPerson.getResidence().getBookID() || !textFieldRelationWithHead.getText()
                    .equals(inforPerson.getResidence().getRelationshipWithHead())) {
                if (isDuplicateRelation11(currentID))
                    return;

                sql = "update Person.Residence"
                        + " set BookID = '" + currentID + "', relationshipwithhead = N'" +
                        textFieldRelationWithHead.getText() + "'"
                        + " where PersonID = '" + inforPerson.getPerson().getPersonID() + "' ;";
                try {
                    // System.out.println(sql);
                    // int rows =
                    ConnectDatabase.statement.executeUpdate(sql);
                    // System.out.println("rows: " + rows);
                    inforPerson.getResidence().setBookID(currentID);
                    inforPerson.getResidence().setRelationshipWithHead(textFieldRelationWithHead.getText().toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("changeBookID");
                }
            }
        }

    }

    private void changeOriginPlace() {
        int hasChange = 0;
        if (comboBoxOriginNation.getSelectionModel().getSelectedIndex() != indexOriginNation
                || comboBoxOriginProvince.getSelectionModel().getSelectedIndex() != indexOriginProvince
                || comboBoxOriginDistrict.getSelectionModel().getSelectedIndex() != indexOriginDistrict
                || comboBoxOriginCommune.getSelectionModel().getSelectedIndex() != indexOriginCommune) {
            hasChange = 1;
        }
        if (comboBoxOriginNation.getSelectionModel().getSelectedIndex() == -1
                || comboBoxOriginProvince.getSelectionModel().getSelectedIndex() == -1
                || comboBoxOriginDistrict.getSelectionModel().getSelectedIndex() == -1
                || comboBoxOriginCommune.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        if (hasChange == 1) {
            String nationID, proviceID, districtID, communeID;
            nationID = listOriginNationID.get(comboBoxOriginNation.getSelectionModel().getSelectedIndex());
            proviceID = listOriginProvinceID.get(comboBoxOriginProvince.getSelectionModel().getSelectedIndex());
            districtID = listOriginDistrictID.get(comboBoxOriginDistrict.getSelectionModel().getSelectedIndex());
            communeID = listOriginCommuneID.get(comboBoxOriginCommune.getSelectionModel().getSelectedIndex());
            if(inforPerson.getOriginPlace() ==null){
                String sql = "insert into Person.OriginPlace values  (?, ?, ?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
                    preparedStatement.setString(1, Integer.toString(inforPerson.getPerson().getPersonID()));
                    preparedStatement.setString(2, nationID);
                    preparedStatement.setString(3, proviceID);
                    preparedStatement.setString(4, districtID);
                    preparedStatement.setString(5, communeID);
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                   System.out.println(e.getMessage());
                   System.out.println(getClass());
                   System.out.println("Change Origin Place " + sql);
                }                
            }
            else{
                String sql = "update Person.OriginPlace set NationID = '" + nationID + "', ProvinceID = '" + proviceID
                        + "', DistrictID = '" + districtID + "', CommuneID = '" + communeID + "' where PersonID = '"
                        + Integer.toString(inforPerson.getPerson().getPersonID()) + "'";
    
                try {
                    System.out.println(sql);
                    // int rows =
                    ConnectDatabase.statement.executeUpdate(sql);
    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("changeOriginPlace");
                }
            }
        }
    }

    private void changeBirthPlace() {
        int hasChange = 0;
        if (comboBoxBirthNation.getSelectionModel().getSelectedIndex() != indexBirthNation
                || comboBoxBirthProvince.getSelectionModel().getSelectedIndex() != indexBirthProvince
                || comboBoxBirthDistrict.getSelectionModel().getSelectedIndex() != indexBirthDistrict
                || comboBoxBirthCommune.getSelectionModel().getSelectedIndex() != indexBirthCommune) {
            hasChange = 1;
        }
        if (comboBoxBirthNation.getSelectionModel().getSelectedIndex() == -1
                || comboBoxBirthProvince.getSelectionModel().getSelectedIndex() == -1
                || comboBoxBirthDistrict.getSelectionModel().getSelectedIndex() == -1
                || comboBoxBirthCommune.getSelectionModel().getSelectedIndex() == -1) {
            return; // add alert
        }
        if (hasChange == 1) {
            String nationID, proviceID, districtID, communeID;
            nationID = listBirthNationID.get(comboBoxBirthNation.getSelectionModel().getSelectedIndex());
            proviceID = listBirthProvinceID.get(comboBoxBirthProvince.getSelectionModel().getSelectedIndex());
            districtID = listBirthDistrictID.get(comboBoxBirthDistrict.getSelectionModel().getSelectedIndex());
            communeID = listBirthCommuneID.get(comboBoxBirthCommune.getSelectionModel().getSelectedIndex());
            if(inforPerson.getBirthPlace() ==null){
                String sql = "insert into Person.BirthPlace values (?, ?, ?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, inforPerson.getPerson().getPersonID());
                    preparedStatement.setString(2, nationID);
                    preparedStatement.setString(3, proviceID);
                    preparedStatement.setString(4, districtID);
                    preparedStatement.setString(5, communeID);
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                   System.out.println(e.getMessage());
                   System.out.println(getClass());
                   System.out.println("Change Brith Place " + sql);
                }                
            }
            else{

                String sql = "update Person.BirthPlace set NationID = '" + nationID + "', ProvinceID = '" + proviceID
                        + "', DistrictID = '" + districtID + "', CommuneID = '" + communeID + "' where PersonID = '"
                        + Integer.toString(inforPerson.getPerson().getPersonID()) + "'";
    
                try {
                    System.out.println(sql);
                    int rows = ConnectDatabase.statement.executeUpdate(sql);
                    System.out.println("rows: " + rows);
    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("changeBirthPlace");
                }
            }
        }
    }

    @FXML
    void changeWorkAndWorkPlace() {
        if(inforPerson.getWork() == null){
            if(textFieldWork.getText().length()!=0 || textFieldWorkPlace.getText().length() !=0){
                String sql = "insert into Person.Work values (?, ?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
                    preparedStatement.setString(1, Integer.toString(inforPerson.getPerson().getPersonID()));
                    preparedStatement.setString(2, textFieldWork.getText());
                    preparedStatement.setString(3, textFieldWorkPlace.getText());
                    preparedStatement.setString(4, "");
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("Change Work and Place " + sql);
                }

            }
        }
        else{
            if(!textFieldWork.getText().toLowerCase().equals(inforPerson.getWork().getJob().toLowerCase())|| !textFieldWorkPlace.getText().toLowerCase().equals(inforPerson.getWork().getPlace())){
                String sql = "update Person.Work set Person.Work.Job = ?, Person.Work.Place = ? where Person.Work.PersonID = ?";
                try {
                    PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
                    preparedStatement.setString(1, textFieldWork.getText());
                    preparedStatement.setString(2,textFieldWorkPlace.getText());
                    preparedStatement.setString(3, Integer.toString(inforPerson.getPerson().getPersonID()));
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("Change Work and Place " + sql);
                }
            }
        }
    }

    void changeIdentityCard() {
        confirmLabel.setAlignment(Pos.CENTER);
        if (inforPerson.getCard() == null) {
            if (textFieldIdentityCard.getText().length() == 0
                    || textFieldRegisterDateIdentityCard.getText().length() == 0
                    || textFieldRegisterPlaceIdentityCard.getText().length() == 0) {
                return; // add alert nhaajp thieu
            }
            {

            }
            if (textFieldIdentityCard.getText().length() != 0
                    || textFieldRegisterDateIdentityCard.getText().length() != 0
                    || textFieldRegisterPlaceIdentityCard.getText().length() != 0) {
                String sql = "insert into Person.IdentityCard  values  ('" + inforPerson.getPerson().getPersonID()
                        + "', '" + textFieldIdentityCard.getText() + "', '"
                        + textFieldRegisterDateIdentityCard.getText() + "', N'"
                        + textFieldRegisterPlaceIdentityCard.getText() + "') ;";
                System.out.println(sql);
                try {
                    int rows = ConnectDatabase.statement.executeUpdate(sql);
                    System.out.println("rows: " + rows);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("changeIdentityCard + insert value");
                    return;

                }

            }
        } else {
            if (!textFieldIdentityCard.getText().equals(inforPerson.getCard().getNumber())
                    || !textFieldRegisterDateIdentityCard.getText()
                            .equals(inforPerson.getCard().getRegisterDate().toString())
                    || !textFieldRegisterPlaceIdentityCard.getText().equals(inforPerson.getCard().getRegisterPlace())) {
                String sql = "update Person.identityCard set number = '" + textFieldIdentityCard.getText()
                        + "', registerdate = '" + textFieldRegisterDateIdentityCard.getText() + "', registerplace = N'"
                        + textFieldRegisterPlaceIdentityCard.getText() + "' where personid = '"
                        + inforPerson.getPerson().getPersonID() + "'; ";
                try {
                    // int rows =
                    ConnectDatabase.statement.executeUpdate(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(getClass());
                    System.out.println("change identitycard + change value");
                    return;
                }
            }

        }

        // confirmLabel.setText("Lưu thành công");
    }

    void changeFullName() {
        if (textFieldNamePerson.getText().length() == 0) {
            return;
        }
        if (!textFieldNamePerson.getText().equals(inforPerson.getPerson().getFullName())) {
            String sql = "update Person.Person set fullname = N'" + textFieldNamePerson.getText()
                    + "' where personID = '" + inforPerson.getPerson().getPersonID() + "'; ";
            try {
                System.out.println(sql);
                // int rows =
                ConnectDatabase.statement.executeUpdate(sql);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(getClass());
                System.out.println("changeFullName");

                return;

            }
        } else {
            return;
        }

    }

    void changeNickName() {

        if (textFieldOtherNamePerson.getText() == null) {
            if (inforPerson.getPerson().getNickName() == null)
                return;
            if (inforPerson.getPerson().getNickName().length() == 0)
                return;
            String sql = "update Person.Person set nickname = NULL where personID = '"
                    + inforPerson.getPerson().getPersonID() + "'; ";
            try {
                System.out.println(sql);
                // int rows =
                ConnectDatabase.statement.executeUpdate(sql);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(getClass());
                System.out.println("changeNickName");

                return;

            }

            return;
        } else if (!textFieldOtherNamePerson.getText().equals(inforPerson.getPerson().getNickName())) {
            String sql = "update Person.Person set nickname = N'" + textFieldOtherNamePerson.getText()
                    + "' where personID = '" + inforPerson.getPerson().getPersonID() + "'; ";
            try {
                System.out.println(sql);
                // int rows =
                ConnectDatabase.statement.executeUpdate(sql);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(getClass());
                System.out.println("changeNickName");

                return;

            }
        } else {
            return;
        }

    }

    void changeGender() {
        if (gender.getSelectionModel().getSelectedIndex() != inforPerson.getPerson().getGender()) {
            String sql = "update person.person set gender = '" + gender.getSelectionModel().getSelectedIndex()
                    + "' where personid = '" + inforPerson.getPerson().getPersonID() + "';";
            try {
                // int rows =
                ConnectDatabase.statement.executeUpdate((sql));

            } catch (Exception e) {
                System.out.println((e.getMessage()));
                System.out.println((getClass()));
                System.out.println("Change gender");

                return;
            }

        }

    }

    void changeBirthDate() {

        if (textFieldBirthDate.getText() == null) {
            return;
        }

        if (!textFieldBirthDate.getText().equals(inforPerson.getPerson().getBirthDate().toString())) {
            if (textFieldBirthDate.getText().length() == 0
                    || LocalDate.parse(textFieldBirthDate.getText()).isAfter(LocalDate.now())) {
                System.out.println("oke ổn");
                return;
            }
            inforPerson.getPerson().getBirthDate().compareTo(Date.valueOf(LocalDate.now()));
            String sql = "update Person.person set birthdate = '" + textFieldBirthDate.getText()
                    + "' where personid = '" + inforPerson.getPerson().getPersonID() + "'; ";
            try {
                // int rows =
                ConnectDatabase.statement.executeUpdate(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(getClass());
                System.out.println("change Birth Date");
                return;
            }

        }
    }

    boolean isDuplicateRelation11(int currentID) {
        // check xem co o trong quan he chi ton tai mot khong

        for (int i = 0; i < relation11.size(); i++) {
            if (textFieldRelationWithHead.getText().toLowerCase().equals(relation11.get(i).toLowerCase())) {

                // Nếu có kiểm tra xem trong hộ khẩu đã có chưa ?
                {
                    String sql = "select * from Person.residence where bookid ='" + currentID
                            + "' and RelationshipWithHead = N'" + textFieldRelationWithHead.getText() + "' ;";
                    try {
                        ResultSet result = ConnectDatabase.statement.executeQuery(sql);
                        if (result.next()) {
                            System.out.println(textFieldBookID.getText());
                            return true;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println(getClass());
                        System.out.println("changeRelationWithHead" + "check ");

                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    void showAlertConfirm(int success, String informationString) {
        if (success == 1) {
            alertConfirm.setAlertType(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Thông báo ( •̀ ω •́ )✧");
            alertConfirm.setHeaderText("Lưu thành công");
            alertConfirm.setContentText("");

        } else {
            alertConfirm.setAlertType(javafx.scene.control.Alert.AlertType.INFORMATION);
            alertConfirm.setTitle("Thông báo ⊙﹏⊙∥");
            alertConfirm.setHeaderText("Bạn nhập chưa đúng. Vui lòng xem lại");
            alertConfirm.setContentText(informationString);
        }
        alertConfirm.show();
    }

    @FXML
    void submitAndSave(ActionEvent event) {
        confirmLabel.setText("");
        changeBookIDAndRelationshipWithHead();
        changeOriginPlace();
        changeBirthPlace();
        changeWorkAndWorkPlace();
        changeIdentityCard();
        changeFullName();
        changeNickName();
        changeGender();
        changeBirthDate();

        // Stage stage = (Stage) submiButton.getScene().getWindow();
        // stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
