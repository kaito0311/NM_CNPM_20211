package managehouseholdbook.thaydoisohokhau.movehousehold.splithousehold;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.RunElement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import managehouseholdbook.ConnectDatabase;

public class SplitHouseHoldController implements Initializable {

    @FXML
    TableView<ChangeMember> tableViewMember;

    @FXML
    private TableColumn<ChangeMember, String> personIdColumn;
    @FXML
    private TableColumn<ChangeMember, String> relatioinshipWithHeadColumn;

    @FXML
    private TableColumn<ChangeMember, String> idHokhauColumn;
    
    @FXML
    Button submit; 

    ObservableList<ChangeMember> listMem = FXCollections.observableArrayList();

    ObservableList<String> listID = FXCollections.observableArrayList();

    void takeListMember(){
        try {
            String sql = "select Person.FullName, Person.PersonID, Residence.BookID, Residence.RelationshipWithHead from Person.Residence inner join Person.Person on Person.PersonID = Residence.PersonID where Residence.BookID = ?";
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, this.bookID1);
            ResultSet resultSet = preparedStatement.executeQuery();
            listMem.clear();
            while(resultSet.next()){
                if(resultSet.getString("personID").toLowerCase().equals(this.personIDHead1)||resultSet.getString("personID").equals(this.personIDHead2))
                    continue;
                listMem.add(new ChangeMember(resultSet.getString("personID"), resultSet.getString("fullname"), resultSet.getString("relationshipwithhead"), listID));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    void changeBookForMember(int index){

    }

    void updateToDataBase(){
        // Update chu ho mot 
        String sql = null ; 
        try {
            // update o Household.book 
            sql = "Update Household.book set headid = ? where bookid = ?";
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, personIDHead1);
            preparedStatement.setString(2, bookID1);
            preparedStatement.executeUpdate();

            // Update o person.residence 

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println(sql);
            return;
        }
        // Insert chu ho hai 
        try {
            // insert new value to household.book 
            String provinceID, districtID, communeID; 
            sql = "select ProvinceID, DistrictID, CommuneID from Household.Book where BookID = ?";
            PreparedStatement preparedStatement  = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, bookID1);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                provinceID = resultSet.getString("provinceID");
                districtID = resultSet.getString("districtID");
                communeID = resultSet.getString("communeID");
            }


            // Update o person.residence 
        } catch (Exception e) {
        }

        // Thay dooi ho khau cho cac thanh vien
        for(int i = 0; i < listMem.size(); i++){
            changeBookForMember(i);
        }
    }

    String bookID1;
    String bookID2;
    String personIDHead1;
    String personIDHead2;


    void init(String idHead1, String idHead2, String idHoKhau1, String idHoKhau2){ 
        listID.add(idHoKhau1);
        listID.add(idHoKhau2);
        tableViewMember.setEditable(true);
        this.bookID1 = idHoKhau1;
        this.bookID2 = idHoKhau2; 
        this.personIDHead1 = idHead1;
        this.personIDHead2 = idHead2;
        takeListMember();

        tableViewMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("namePerson"));
        tableViewMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("relationshipWithHead"));
        relatioinshipWithHeadColumn.setCellFactory(TextFieldTableCell.<ChangeMember>forTableColumn());
        relatioinshipWithHeadColumn.setOnEditCommit((CellEditEvent<ChangeMember, String> event)->{
            TablePosition<ChangeMember, String> pos = event.getTablePosition();
            String newRelatioin = event.getNewValue();
            int row = pos.getRow();
            listMem.get(row).setRelationshipWithHead(newRelatioin);
        });
        idHokhauColumn.setCellValueFactory(new PropertyValueFactory<ChangeMember, String>("idHoKhau"));

        tableViewMember.setItems(listMem);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(Integer.toString(3), Integer.toString(4), "2", "3");
    }


    @FXML
    void show(){
        for(int i = 0; i < listMem.size(); i++){
            // System.out.println(listMem.get(i).getRelatioinshipWithHead());
            System.out.println(listMem.get(i).idHoKhau.getSelectionModel().getSelectedIndex());;
        }

    }



}
