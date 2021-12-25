package managehouseholdbook.thaydoisohokhau.movehousehold.changeHead;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.person.Residence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import managehouseholdbook.ConnectDatabase;

public class ChangeHeadController implements Initializable {

    @FXML
    TextField textFieldNumberIdentiycardNewHead;
    @FXML
    Label labelBookID;
    @FXML
    Label labelNumberIdentityCardNewHead;

    @FXML
    TextField textFieldBookID;

    @FXML
    ComboBox<String> comboBoxMember;

    int personIdNewHead; 

    List<Residence> listMemeber = new ArrayList<>();
    ObservableList<String> listNameMember = FXCollections.observableArrayList();
    ObservableList<String> listBirthMember = FXCollections.observableArrayList();


    @FXML
    Label labelDateBirthMember;
    @FXML
    TextField textFieldRelationWithHead;

    @FXML
    Button buttonSaveAndSubmit;

    int currentHeadID; 
    @FXML
    void changeHead() {
        try {
            // Lấy thông tin của id sổ hộ khẩu và cmnd của chủ hộ mới
            if (textFieldBookID.getText().length() == 0) {
                labelBookID.setText("Nhập BookID");
                return;
            }
            if (textFieldNumberIdentiycardNewHead.getText().length() == 0) {
                labelNumberIdentityCardNewHead.setText("Nhập CCCD");
                return;
            }
           
    
            // Thực Hiện thay đổi 
           String sql =  "{call dbo.changeHead(?, ?, ?)}";
           try {
               CallableStatement callableStatement = ConnectDatabase.connection.prepareCall(sql);
               callableStatement.setString(1, textFieldBookID.getText());
               callableStatement.setString(2, textFieldNumberIdentiycardNewHead.getText());
               callableStatement.registerOutParameter(3, Types.NVARCHAR);
               callableStatement.executeUpdate();
               labelConfirm.setText(callableStatement.getString(3));
    
           } catch (Exception e) {
               System.out.println("Lỗi to đùng");
              System.out.println(e.getMessage());
              System.out.println(getClass());
              System.out.println("Change Head");
           }

        //    takeInforMember(Integer.parseInt(textFieldBookID.getText()));
           // Lấy thông tin nhân khẩu 
           
           bookID = Integer.parseInt(textFieldBookID.getText());
           takeInforMember();
           
           if(labelConfirm.getText().toLowerCase().equals("Thành Công".toLowerCase()) || labelConfirm.getText().toLowerCase().equals(("người này đang là chủ hộ").toLowerCase())){
               textFieldRelationWithHead.clear();
               labelDateBirthMember.setText("Ngày sinh");
           }
           else{
               comboBoxMember.getItems().clear();
           }

            
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
    public ChangeHeadController(){
        relationship11.add("chủ hộ");
        relationship11.add("vợ");
    }
    int bookID = 1; 
    List<String> relationship11 = new ArrayList<>();
    @FXML
    void takeInforMember(){
        String sql = "select Person.PersonID as PersonID, Person.FullName as Fullname, BirthDate, RelationshipWithHead from Person.Person inner join Person.Residence on Person.PersonID = Residence.PersonID where BookID = ?";
        try {
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            listMemeber.clear();
            listNameMember.clear();
            comboBoxMember.getItems().clear();
            listBirthMember.clear();
            int index = 0; 
            while(resultSet.next()){
                if(resultSet.getString("relationshipWithHead").toLowerCase().equals("chủ hộ"))
                    continue;
                listMemeber.add(new Residence()); 
                listMemeber.get(index).setPersonID(resultSet.getInt("PersonID"));
                listMemeber.get(index).setRelationshipWithHead(resultSet.getString("relationshipwithHead"));

                listNameMember.add(resultSet.getString("fullname"));

                listBirthMember.add(resultSet.getString("birthdate"));
                index += 1;
            }
            comboBoxMember.setItems(listNameMember);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("take Infor Member" + sql);
        }

    }

    @FXML
    void comboBoxNamePersonChange(ActionEvent event){
        try {
            int index = comboBoxMember.getSelectionModel().getSelectedIndex(); 
            if(index == -1) return; 
            else{
                // set value for label birth 
                labelDateBirthMember.setText(listBirthMember.get(index));
                // set value for textfield 
                textFieldRelationWithHead.setText(listMemeber.get(index).getRelationshipWithHead());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    @FXML
    void changeRelationWithHead(ActionEvent event){
        try {
            int index = comboBoxMember.getSelectionModel().getSelectedIndex();
            String before = listMemeber.get(index).getRelationshipWithHead();
            listMemeber.get(index).setRelationshipWithHead(textFieldRelationWithHead.getText());
            
            if(SaveAndSubmit()== 0)
            {
                listMemeber.get(index).setRelationshipWithHead(before);
                return;
            }
            String sql = "update Person.Residence set relationshipwithhead = ? where personID = ?";
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, textFieldRelationWithHead.getText());
            preparedStatement.setInt(2, listMemeber.get(index).getPersonID());
            preparedStatement.executeUpdate();

            labelConfirm.setText("Sửa thành công");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(getClass());
            System.err.println("changeRelationwithHEad");

        }
    }
    @FXML
    Label labelConfirm; 
    @FXML
    int SaveAndSubmit(){
        int count = 0; 
        for(int i = 0; i < relationship11.size(); i++){
            count = 0; 
            for(int j = 0; j < listMemeber.size(); j++){
                if(listMemeber.get(j).getRelationshipWithHead().toLowerCase().equals(relationship11.get(i).toLowerCase())){
                    count += 1; 
                }
                if(count >= 2){
                    labelConfirm.setText("Không hợp lệ (1 " + relationship11.get(i) + ")");
                    return 0;
                }
            }
        }
        return 1; 
    }
    @FXML
    void checkSql(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
