package managehouseholdbook.thaydoisohokhau.movehousehold;

import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.thaydoisohokhau.movehousehold.changeHead.ChangeHeadController;
import managehouseholdbook.thaydoisohokhau.movehousehold.splithousehold.ChooseHeadController;

public class MoveHouseholdBookController implements Initializable {

    @FXML
    private ComboBox<String> district;
    ObservableList<String> listDistrict = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonChangePerson;
    @FXML
    private Button buttonDeleteHouseholdBook;

    private Stage stage;
    private Scene scene;
    ////////////////////////////////////////////
    @FXML
    void changeHead()throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./changeHead/ChangeHead.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            ChangeHeadController controller = loader.getController();
            Stage state = new Stage();
            state.setScene(new Scene(root));
            state.show();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    @FXML
    void splitHousehold()throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./splithousehold/chooseHead.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            ChooseHeadController controller = loader.getController();
            Stage state = new Stage();
            state.setScene(new Scene(root));
            state.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println();
        }
    }

    ///////////////////////////////////////////////

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