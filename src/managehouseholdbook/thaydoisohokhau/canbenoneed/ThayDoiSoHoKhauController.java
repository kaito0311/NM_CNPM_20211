package managehouseholdbook.thaydoisohokhau.canbenoneed;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class ThayDoiSoHoKhauController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    @FXML 
    public TabPane tabPaneThemNhanKhau;

    @FXML
    public Button buttonThemNhanKhau; 

    public void changeToTabPaneThemNhanKhau(ActionEvent event) throws Exception{
        try{

            TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("ThemNhanKhauMoi.fxml"));
            tabPaneThemNhanKhau.getTabs().setAll(root.getTabs());
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
        }

    }
    
}
