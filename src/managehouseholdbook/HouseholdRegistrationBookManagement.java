package managehouseholdbook;

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

public class HouseholdRegistrationBookManagement implements Initializable {

    @FXML 
    Button buttonCreateNewBook; 
    @FXML 
    Button buttonChangeBook; 
    @FXML 
    Button buttonTemporaryAbsence_Residence; 

    private Stage stage; 
    private Scene scene; 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    private void setNewSceneInSameWindow( String source, ActionEvent event) throws Exception{

            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(source));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root); 
            stage.setScene(scene); 

    }
    public void changeToCreateNewBook(ActionEvent event)throws Exception{
        try{
            setNewSceneInSameWindow("createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToCreateNewBook");
        }
    }
    public void changeToChangeHouseholdBook(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("thaydoisohokhau/ChangeHouseholdBook.fxml", event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToChangeHouseholdBook(event);");
        }
    }

    // public void changeToTemporaryResidenceAbsence(ActionEvent event) throws Exception {
    //     try{
    //         TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("thaydoisohokhau/ChangeHouseholdBook.fxml"));
    //         root.getSelectionModel().select(1);
    //         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //         scene = new Scene(root); 
    //         stage.setScene(scene); 
    //     }
    //     catch (Exception e){
    //         System.out.println("Loi changeToTemporaryResidence ... trong class HouseholdBookRegistr... ");
    //         System.out.println(e.getStackTrace());
    //     }
    // }
}
