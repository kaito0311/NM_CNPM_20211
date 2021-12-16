package managehouseholdbook.createhouseholdbook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.Node;



public class CreateNewHouseholdBookController implements Initializable{
    

    @FXML
    public Label labelNhapMaHoKhau; 
    @FXML 
    public Label labelChonChuHo; 
    @FXML 
    public ComboBox<String> comboBoxMaHoKhau; 

    @FXML 
    public ComboBox<String> comboBoxChuHo; 
    @FXML
    public ComboBox<String> comboBoxThanhPho; 
    @FXML
    public ComboBox<String> comboBoxQuan; 
    @FXML
    public ComboBox<String> comboBoxPhuong; 
    @FXML
    public ComboBox<String> comboBoxXa; 
    @FXML 
    public Button xacNhan;


    ObservableList<String> listMaHoKhau = FXCollections.observableArrayList("HK00001", "HK0002", "HK0002");
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { 
       
        comboBoxMaHoKhau.setItems(listMaHoKhau);
        
    }

    public void comboChanged(ActionEvent Event){
        System.out.println(comboBoxMaHoKhau.getValue());
    }

    public void xacNhan(ActionEvent event){
        System.out.println("xac nhan");
    }


    @FXML 
    Button buttonChangeBook; 
    @FXML 
    Button buttonTemporaryAbsence_Residence; 

    private Stage stage; 
    private Scene scene; 

    

    private void setNewSceneInSameWindow( String source, ActionEvent event) {
        try{

            TabPane root = (TabPane)FXMLLoader.load(getClass().getResource(source));
            root.getSelectionModel().select(1);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root); 
            stage.setScene(scene); 
        }
        catch(Exception e){
            System.out.println("Error in class ChangeHouseholdBookController");
        }
    }

  
    public void changeToChangeHouseholdBook(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../thaydoisohokhau/ChangeHouseholdBook.fxml", event);
        }
        catch (Exception e){
            System.out.println("Loi changeToChangeHouseholdBook in class CreateNewHouseholdBookController...");
            System.out.println(e.getStackTrace());
        }
    }

}