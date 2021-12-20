package managehouseholdbook.thaydoisohokhau.changeperson;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import managehouseholdbook.thaydoisohokhau.changeperson.changeinformation.PopupInforPersonController;

public class ChangePersonController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
    // ==================== Thay đổi nhân khẩu ===========================
    @FXML
    Button buttonTakeInformation; 
    @FXML 
    TextField textFieldIDSo; 

    @FXML 
    TextField textFieldNgaySinh;
    @FXML
    TextField textFieldHoTen;
    Stage popupStage; 

    // Xóa nhân khẩu


    // Thay đổi thông tin nhân khẩu
    public void changeInformation(ActionEvent event)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("changeinformation/PopupChangeInforPerson.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            PopupInforPersonController controller = loader.getController();
            controller.setInfor(textFieldHoTen.getText(), LocalDate.parse(textFieldNgaySinh.getText()) , Integer.parseInt(textFieldIDSo.getText()));
            popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.show();
        }
        catch(Exception e){
            System.out.println("Loi takeInformation " + getClass());
            System.out.println(e.getMessage());
        }
    
    }


    // ===================== Chuyển qua lại giữa giao diện =====================

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonMoveHouseholdBook;
    @FXML
    private Button buttonDeleteHouseholdBook;

    private Stage stage;
    private Scene scene;

    private void setNewSceneInSameWindow(String source, ActionEvent event) throws Exception {
        try {

            TabPane root = (TabPane) FXMLLoader.load(getClass().getResource(source));
            root.getSelectionModel().select(1);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {

            System.out.println("Error in class ChangePersonController");
            System.out.println(e.getMessage());
        }
    }

    public void changeToTabAddNewPerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToTabAddNewPerson");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToTabMoveHouseHold method in ChangePersonController");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToTabDeleteHousehold in ChangePersonController");
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
