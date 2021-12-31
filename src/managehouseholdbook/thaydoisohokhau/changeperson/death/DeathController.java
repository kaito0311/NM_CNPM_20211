package managehouseholdbook.thaydoisohokhau.changeperson.death;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.print.FlavorException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import managehouseholdbook.ConnectDatabase;

public class DeathController implements Initializable {
    String nameDeathPerson;
    int bookIDDeathPerson;
    String birthDateDeathPerson;

    public DeathController() {
    }

    public boolean setInfor(String nameDeathPerson, String birthDateDeathPerson, int bookIDDeathPerson) {
        this.nameDeathPerson = nameDeathPerson;
        this.bookIDDeathPerson = bookIDDeathPerson;
        this.birthDateDeathPerson = birthDateDeathPerson;
        checkIsHead();
        return true;
    }

    public boolean checkIsHead() {
        personIDdeath = takePersonID(this.nameDeathPerson, Integer.toString(this.bookIDDeathPerson),
                this.birthDateDeathPerson.toString());
        if (personIDdeath == -1) {
            labelConfirm.setText("Người mất không tồn tại");
            return false;
        }
        personDeath.setText(this.nameDeathPerson);
        // Kiểm tra thông tin chủ hộ nếu là chủ hộ yêu cầu thay đổi thông tin chủ hộ rồi
        // mới khai tử :)
        String sql = "select headid from household.book where bookid = ?";
        try {
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setInt(1, this.bookIDDeathPerson);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                if (result.getInt("headid") == this.personIDdeath) {
                    labelConfirm.setText("Người mất là chủ hộ. Cần thay đổi thông tin chủ hộ trước");
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    int personIDdeath;
    int personIdDeclare;
    @FXML
    TextField textFieldNameDeclarePerson;
    @FXML
    TextField textFieldBirthDateDeclarePerson;
    @FXML
    TextField textFieldBookIdDeclarePerson;
    @FXML
    Label labelConfirm;

    @FXML
    TextField textFieldDateDeath;
    @FXML
    TextField reason;

    @FXML
    TextField personDeath;

    @FXML
    void deathConfirm() {
        labelConfirm.setText("");
        String sql;
        if (!checkIsHead()) {
            return;
        }
        // take person declare

        personIdDeclare = takePersonID(textFieldNameDeclarePerson.getText(), textFieldBookIdDeclarePerson.getText(),
                textFieldBirthDateDeclarePerson.getText());
        if (personIdDeclare == -1) {
            labelConfirm.setText("Người khai báo không tồn tại");
            return;
        }
        try {
            
            if (textFieldDateDeath.getText().length() == 0
                    || LocalDate.parse(textFieldDateDeath.getText()).isAfter(LocalDate.now())) {
                labelConfirm.setText("Lỗi ngày mất");
                return;
            }
        } catch (Exception e) {
            labelConfirm.setText("Lỗi ngày mất");
            
        }

        try {

            //
            sql = "{call dbo.deathConfirm(?, ?, ?, ? ,? ,?)}";
            try {
                CallableStatement callableStatement = ConnectDatabase.connection.prepareCall(sql);
                callableStatement.setString(1, Integer.toString(personIDdeath));
                callableStatement.setString(2, textFieldDateDeath.getText());
                callableStatement.setString(3, reason.getText());
                callableStatement.setString(4, LocalDate.now().toString());
                callableStatement.setString(5, Integer.toString(personIdDeclare));
                callableStatement.registerOutParameter(6, Types.NVARCHAR);
                callableStatement.execute();

                labelConfirm.setText(callableStatement.getString(6));
                if (labelConfirm.getText() == null) {
                    labelConfirm.setText("Không thành công");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(getClass());
                System.out.println(sql);
                labelConfirm.setText("Lỗi khi khai tử. Mời nhập lại");

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    int takePersonID(String name, String idbook, String date) {
        try {
            if (name.length() == 0 || idbook.length() == 0 || date.length() == 0) {
                labelConfirm.setText("Điên đầy đủ thông tin người khai báo");
                return -1;
            }
            labelConfirm.setText("");
            // take personID Người khai báo
            String sql = "select Person.PersonID from Person.Person inner join Person.Residence "
                    + "on Person.PersonID = Residence.PersonID "
                    + "where Residence.BookID = ? "
                    + "and Person.FullName like ? "
                    + "and Person.BirthDate = ?";

            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, idbook);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, date);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("personid");
            } else {
                labelConfirm.setText("Người khai báo không có trong cơ sở dữ liệu");
                return -1;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("takeInforDeclarePerson();");
        }
        return -1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
