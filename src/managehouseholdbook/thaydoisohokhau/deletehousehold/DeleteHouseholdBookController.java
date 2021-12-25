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

    private void setNewSceneInSameWindow( String source, ActionEvent event) throws Exception{
        try{

            TabPane root = (TabPane)FXMLLoader.load(getClass().getResource(source));
            root.getSelectionModel().select(1);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root); 
            stage.setScene(scene); 
        }
        catch(Exception e){
            System.out.println("Error in class DeleteHouseholdBookController");
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
    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
           setNewSceneInSameWindow("../changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToChangePerson");
            System.out.println(e.getStackTrace());
        }
    }
    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        }
        catch(Exception e){
            System.out.println("Error in changeToTabMoveHouseHold method in DeletehouseholdBookController");
            System.out.println(e.getStackTrace());
        }
    }

    
    @FXML 
    Button buttonCreateNewBook;  
    public void changeToCreateNewBook(ActionEvent event)throws Exception{
        try{
            setNewSceneInSameWindow("../../createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    
}
