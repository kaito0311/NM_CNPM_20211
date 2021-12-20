package managehouseholdbook.thaydoisohokhau.changeperson.changeinformation;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import Model.person.Birth;
import Model.person.BirthPlace;
import Model.person.Ethnic;
import Model.person.IdentityCard;
import Model.person.InforPerson;
import Model.person.Nationality;
import Model.person.OriginPlace;
import Model.person.Person;
import Model.person.Work;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class PopupInforPersonController extends AnchorPane implements Initializable {

    public String hoTen_;
    public LocalDate ngaySinh_;
    public int idSoHoKhau = 2;
    private InforPerson inforPerson; 

    Stage primaryStage;

    public PopupInforPersonController() {

    }

    @FXML
    public TextField danToc;
    @FXML
    public TextField soCCCD;
    @FXML
    public TextField quocGiaSinhRa;
    @FXML
    public TextField quocGiaCuTru;

    @FXML
    public TextField textFieldHoTen;
    @FXML
    public TextField tonGiao;
    @FXML
    public TextField ngayCap;
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
    public TextField ngaySinh;
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
    ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ", "LGBT");

    @FXML
    public TextField quanHeVoiChuHo;
    @FXML
    public TextField noiLamViec;
    @FXML
    public TextField soNhaSinhRa;
    @FXML
    public TextField soNhaCuTru;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInfor(String hoTenNguoi, LocalDate ngaySinh, int idSoHoKhau) throws Exception {
        this.hoTen_ = hoTenNguoi;
        this.ngaySinh_ = ngaySinh;
        this.idSoHoKhau = idSoHoKhau;

        // Lấy tên người
        String sql = "select person.PersonID, FullName, Nickname, BirthDate, Gender, Ethnic.EthnicID as EthnicID,Ethnic.Name as Ethnic, Person.NationalityID as NationalityID, Nationality.Name as Nationality, PrePermanentAddress, RelationshipWithHead"
                + " from  Person.Residence inner join Person.person "
                + " on  Person.PersonID = Residence.PersonID "
                + " inner join Person.Ethnic "
                + " on Person.EthnicID = Ethnic.EthnicID "
                + " inner join Person.Nationality "
                + " on Person.NationalityID = Nationality.NationalityID where Residence.BookID = '" + this.idSoHoKhau
                + "' and person.FullName like N'" + this.hoTen_ + "' and person.BirthDate = '" + this.ngaySinh_ + "'";
        ResultSet result = ConnectDatabase.statement.executeQuery(sql);

        
        if (result.next() == true) {
            inforPerson.setPerson(new Person(result.getInt("personID"), result.getString("fullname"),
                    result.getString("nickname"), result.getDate("birthDate"), result.getInt("gender"),
                    result.getString("ethnicid"), result.getString("NationalityID")));
            inforPerson.getPerson().printtDetail();

            nationality = new Nationality(result.getString("nationalityid"), result.getString("nationality"));
            nationality.printDetail();

            ethnic = new Ethnic(result.getString("ethnicid"), result.getString("ethnic"));
            ethnic.printDetail();
        }

        if (person != null) {

            // Lấy thông tin nơi làm việc
            sql = "select * from person.work where person.work.personID = '" + person.getPersonID() + "'";
            result = ConnectDatabase.statement.executeQuery(sql);
            // result.next();
            
            if (result.next() == true) {
                work = new Work(result.getInt("personID"), result.getString("job"), result.getString("place"),
                        result.getDate("modifiedDate"));
                work.printDetail();
            }

            // Lấy thông tin nguyên quán
            sql = "select * from person.originplace where personID = '" + person.getPersonID() + "'";
            result = ConnectDatabase.statement.executeQuery(sql);
             
            if (result.next() == true) {

                originplace = new OriginPlace(result.getInt("personID"), result.getString("nationID"),
                        result.getString("provinceID"), result.getString("Districtid"), result.getString("communeID"));
                originplace.printDetail();
            }

            // Lấy thông tin nơi sinh
            sql = "select * from person.originplace where personID = '" + person.getPersonID() + "'";
            result = ConnectDatabase.statement.executeQuery(sql);
            
            if (result.next() == true) {
                 birhtPlace = new BirthPlace(result.getInt("personID"), result.getString("nationID"),
                        result.getString("provinceID"), result.getString("Districtid"), result.getString("communeID"));
                birhtPlace.printDetail();
            }

            // Lấy thông tin căn cước công dân
            sql = "select * from Person.IdentityCard where Person.IdentityCard.PersonID = '"
                    + person.getPersonID() + "'";
            result = ConnectDatabase.statement.executeQuery(sql);
    
            if (result.next() == true) {
                card = new IdentityCard(result.getInt("personID"), result.getString("number"),
                        result.getDate("registerDate"), result.getString("registerPlace"));
                card.printDetail();
            }

            // Lấy liên quan thông qua id

        }
    }

}
