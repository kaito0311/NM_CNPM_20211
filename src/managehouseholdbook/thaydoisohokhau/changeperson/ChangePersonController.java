package managehouseholdbook.thaydoisohokhau.changeperson;

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

public class ChangePersonController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonMoveHouseholdBook;
    @FXML
    private Button buttonDeleteHouseholdBook;

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
            System.out.println("Error in class ChangePersonController");
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
    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        }
        catch(Exception e){
            System.out.println("Error in changeToTabMoveHouseHold method in ChangePersonController");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        }
        catch(Exception e){
            System.out.println("Error in changeToTabDeleteHousehold in ChangePersonController"); 
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
