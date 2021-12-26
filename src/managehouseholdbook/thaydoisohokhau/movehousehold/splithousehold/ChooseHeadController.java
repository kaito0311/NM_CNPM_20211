package managehouseholdbook.thaydoisohokhau.movehousehold.splithousehold;

import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.LabelSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class ChooseHeadController implements Initializable {

    String bookID1 = "2";
    int bookID2;
    // bookID 1 là người dùng nhập vào

    // bookID 2 là lấy max + 1 của bảng csdl
    int takeNewBookID() {
        String sql;
        try {
            sql = "select bookid from Household.book where bookid >= all(select bookID from household.book)";
            ResultSet result = ConnectDatabase.statement.executeQuery(sql);
            if (result.next()) {
                return result.getInt("bookid") + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("take new BookID");
        }
        return -1;
    }

    // chọn chủ hộ cho

    // lọc tất cả nhân khẩu có số cmnd trong hộ
    ObservableList<String> listNameCandidateHead = FXCollections.observableArrayList();
    ObservableList<String> listIdenityCardCandidateHead = FXCollections.observableArrayList();
    ObservableList<String> listPersonIDCandidateHead = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> comboBoxCandidateHead1;
    @FXML
    ComboBox<String> comboBoxCandidateHead2;
    @FXML
    Label id1, id2; 
    @FXML
    TextField idHoKhauCanTach;

    @FXML
    void takeInforCandidateHead() {
        bookID1 = idHoKhauCanTach.getText();
        if(bookID1.length() == 0){
            labelInfor.setText("Mời nhập ID hộ khẩu");
        }
        String sql = "select Person.PersonID, Person.FullName, IdentityCard.Number "
                + " from Person.Person inner join Person.Residence "
                + " on Person.PersonID = Residence.PersonID "
                + "inner join Person.IdentityCard "
                + "on Residence.PersonID = IdentityCard.PersonID "
                + " where Residence.BookID = ? ";

        try {
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, bookID1);

            ResultSet resultSet = preparedStatement.executeQuery();
            listIdenityCardCandidateHead.clear();
            listNameCandidateHead.clear();
            listPersonIDCandidateHead.clear();

            while (resultSet.next()) {
                listNameCandidateHead.add(resultSet.getString("fullname"));
                listIdenityCardCandidateHead.add(resultSet.getString("number"));
                listPersonIDCandidateHead.add(resultSet.getString("personid"));
            }

            comboBoxCandidateHead1.setItems(listNameCandidateHead);
            comboBoxCandidateHead2.setItems(listNameCandidateHead);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("take Infor CandidateHead");
        }

        bookID2 = takeNewBookID();
        inforLabel2.setText("Chủ của hộ có ID " + Integer.toString(bookID2));
        inforLabel1.setText("Chủ của hộ có ID " + bookID1);



    }

    @FXML 
    Label labelInfor ; 
    @FXML
    Label inforLabel1;
    @FXML
    Label inforLabel2; 

    @FXML
    void changeMember(ActionEvent event){

        String idHead1, idHead2, idHoKhau1, idHoKhau2;
        idHead1 = listPersonIDCandidateHead.get(comboBoxCandidateHead1.getSelectionModel().getSelectedIndex());
        idHead2 = listPersonIDCandidateHead.get(comboBoxCandidateHead2.getSelectionModel().getSelectedIndex());

        idHoKhau1 = bookID1;
        if(idHead1 == idHead2){
            labelInfor.setText("Một người không thể có hai khẩu");
            return;
        }
        bookID2 = takeNewBookID();
        if(bookID2 == -1){
            labelInfor.setText("Không tạo được hộ mới.");
            return;
        }
        else{
            idHoKhau2 = Integer.toString(bookID2);
            inforLabel1.setText("Chủ hộ " + idHoKhau1);
            inforLabel1.setText("Chủ hộ " + idHoKhau2);

        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("split.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            SplitHouseHoldController controller = loader.getController();
            controller.init(idHead1, idHead2, idHoKhau1, idHoKhau2);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changmember");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
