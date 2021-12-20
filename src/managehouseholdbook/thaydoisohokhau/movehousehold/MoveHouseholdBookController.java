package managehouseholdbook.thaydoisohokhau.movehousehold;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;

public class MoveHouseholdBookController implements Initializable {

    @FXML
    private ComboBox<String> district;
    ObservableList<String> listDistrict = FXCollections.observableArrayList();

    public void initializeListDistrict() {
        try {
            String sql = "select * from Address.District";
        
            Statement statement = ConnectDatabase.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                listDistrict.add(result.getNString("name"));
            }

        } catch (SQLException ex) {
            System.out.println("Cannot take data in MovehouseholdBookcontroller");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeListDistrict();
        district.setItems(listDistrict);
    }

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonChangePerson;
    @FXML
    private Button buttonDeleteHouseholdBook;

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
            System.out.println("Error in class MoveHouseholdBookController");
        }
    }

    public void changeToTabAddNewPerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToTabAddNewPerson in MoveHouseholdBookController");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToChangePerson in MoveHouseholdBookController");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToTabDeleteHousehold in MoveHouseholdBookController");
            System.out.println(e.getStackTrace());
        }
    }

    @FXML
    Button buttonCreateNewBook;

    public void changeToCreateNewBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../../createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
