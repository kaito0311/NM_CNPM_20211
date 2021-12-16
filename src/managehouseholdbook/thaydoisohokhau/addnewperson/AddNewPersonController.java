package managehouseholdbook.thaydoisohokhau.addnewperson;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddNewPersonController implements Initializable {
    @FXML
    public TextField danToc; 
    @FXML
    public TextField soCCCD;
    @FXML
    public TextField quocGiaSinhRa; 
    @FXML
    public TextField quocGiaCuTru; 
    
    @FXML
    public TextField hoTen; 
    @FXML
    public TextField tonGiao; 
    @FXML
    public TextField ngayCap ;
    @FXML
    public TextField thanhPhoSinhRa; 
    @FXML
    public TextField thanhPhoCuTru; 
    
    @FXML
    public TextField tenKhac; 
    @FXML
    public TextField quocTich; 
    
    @FXML
    public TextField noiCap ;
    @FXML
    public TextField huyenSinhRa;  
    @FXML
    public TextField huyenCuTru;  
    @FXML
    public TextField ngaySinh;
    @FXML
    public TextField maHoKhau; 
    @FXML
    public TextField ngheNghiep;
    @FXML
    public TextField phuongCuTru; 
    @FXML
    public TextField phuongSinhRa; 
    
    
    @FXML
    public ComboBox<String> gioiTinh;
    ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam", "Nữ", "LGBT");
    
    @FXML
    public TextField quanHeVoiChuHo; 
    @FXML
    public TextField noiLamViec; 
    @FXML
    public TextField soNhaSinhRa;
    @FXML
    public TextField soNhaCuTru;

    @FXML
    public Button xacNhan; 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioiTinh.setItems(listGioiTinh);
    } 

    public void xacNhan(ActionEvent event){
        System.out.println(danToc.getText());
    }


    

    @FXML
    private Button buttonAddNewPerson;
    @FXML
    private Button buttonChangePerson;
    @FXML
    private Button buttonMoveHouseholdBook;
    @FXML
    private Button buttonDeleteHouseholdBook;

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
            System.out.println("Error in class AddNewPersonController");
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
           setNewSceneInSameWindow("../changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println("Error in changeToChangePerson in AddNewPersonController");
            System.out.println(e.getStackTrace());
        }
    }
    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../movehousehold/MoveHouseholdBook.fxml", event);

        }
        catch(Exception e){
            System.out.println("Error in changeToTabMoveHouseHold method in AddNewPersonController");
            System.out.println(e.getStackTrace());
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception{
        try{
            setNewSceneInSameWindow("../deletehousehold/DeleteHouseholdBook.fxml", event);

        }
        catch(Exception e){
            System.out.println("Error in changeToTabDeleteHousehold in AddNewPersonController"); 
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
