package managehouseholdbook.thaydoisohokhau.deletehousehold;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeleteHouseholdBookController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonChangePerson;
    @FXML
    private Button buttonMoveHouseholdBook;

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
            setNewSceneInSameWindow("../addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabAddNewPerson(event);");
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabChangePerson(event);");
        }
    }

    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabMoveHouseHold(event);");
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabDeleteHousehold(event);");
        }
    }

    public void changeToCreateNewBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../../createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToCreateNewBook(event);");
        }
    }

}
