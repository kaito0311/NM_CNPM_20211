package managehouseholdbook.thaydoisohokhau.changeperson;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.IntPredicate;

import database.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import managehouseholdbook.thaydoisohokhau.changeperson.changeinformation.PopupInforPersonController;
import managehouseholdbook.thaydoisohokhau.changeperson.death.DeathController;

public class ChangePersonController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    @FXML
    Label inforPopup;

    // Xóa nhân khẩu


    // Thay đổi thông tin nhân khẩu
    public void changeInformation(ActionEvent event)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("changeinformation/PopupChangeInforPerson.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            PopupInforPersonController controller = loader.getController();
            if(controller.setInfor(textFieldHoTen.getText(), LocalDate.parse(textFieldNgaySinh.getText()) , Integer.parseInt(textFieldIDSo.getText()))){
                inforPopup.setText("Tìm thành công");
                popupStage = new Stage();
                popupStage.setScene(new Scene(root));
                popupStage.show();
            }
            else{
                inforPopup.setText("Tìm không thành công");
            }
        }
        catch(Exception e){
            System.out.println("Loi takeInformation " + getClass());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    
    }
    public void deathConfirm(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("death/Death.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            DeathController controller = loader.getController();
            if(controller.setInfor(textFieldHoTen.getText(), textFieldNgaySinh.getText() , Integer.parseInt(textFieldIDSo.getText()))){
                inforPopup.setText("Tìm thành công");
                popupStage = new Stage();
                popupStage.setScene(new Scene(root));
                popupStage.show();
            }
            else{
                inforPopup.setText("Tìm không thành công");
            }
        }
        catch(Exception e){
            System.out.println("Loi deathConfirm " + getClass());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }
    int takePersonID(String name, String idbook, String date){
        try {
            // take personID Người khai báo 
            String sql = "select Person.PersonID from Person.Person inner join Person.Residence "
            + "on Person.PersonID = Residence.PersonID " 
            + "where Residence.BookID = ? " 
            + "and Person.FullName like ? "
            + "and Person.BirthDate = ?";

            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setString(1, idbook);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, date);
            ResultSet result =preparedStatement.executeQuery();
            if(result.next()){
                 return result.getInt("personid");
            }
            else{
                inforPopup.setText("Người cần xóa không nằm trong csdl");
                return -1;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("takeInforDeclarePerson();");
        }
        return -1;
    }

    @FXML
    void deletePerson(){
        // Lấy personid 
        int personID = takePersonID(textFieldHoTen.getText(), textFieldIDSo.getText(), textFieldNgaySinh.getText());
        if(personID == -1){
            return;
        }
        // Kiểm tra chủ hộ 
        String sql = "select headid from household.book where bookid = ?";
            try {
                PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
                preparedStatement.setString(1, textFieldIDSo.getText());
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    if(result.getInt("headid") == personID){
                        inforPopup.setText("Người cần xóa là chủ hộ. Cần thay đổi thông tin chủ hộ trước");
                        return;
                    } 
                }
            } catch (Exception e) {
                
            }

        sql = "update person.residence set bookid = '0' where personid = ?";
        try {
            PreparedStatement preparedStatement = ConnectDatabase.connection.prepareStatement(sql);
            preparedStatement.setInt(1, personID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println(sql);
        }
        // Nếu không là chủ hộ -> chuyển book id = 0; 

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
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/addnewperson/AddNewPerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabAddNewPerson(event);");
        }
    }

    public void changeToTabChangePerson(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/changeperson/ChangePerson.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabChangePerson(event);");
        }
    }

    public void changeToTabMoveHouseHold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/movehousehold/MoveHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabMoveHouseHold(event);");
        }
    }

    public void changeToTabDeleteHousehold(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/thaydoisohokhau/deletehousehold/DeleteHouseholdBook.fxml", event);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToTabDeleteHousehold(event);");
        }
    }

    public void changeToCreateNewBook(ActionEvent event) throws Exception {
        try {
            setNewSceneInSameWindow("/managehouseholdbook/createhouseholdbook/CreateNewHouseholdBook.fxml", event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getClass());
            System.out.println("changeToCreateNewBook(event);");
        }
    }
    
	public void changeToHomePage(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Main/UI_HomePage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	public void changeToChangeInfo(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainChangeInfo/UI_ChangeInfo.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void changeToCreateListScholar(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/MainCreateListScholar/UI_CreateListScholar.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void changeToThongKeHome(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/thongke/thongkehome/Thong_Ke.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void changeToTimKiem(ActionEvent event) {
		try {
			SQLConnection.ConnectData();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/application/Application.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setX(220);
			stage.setY(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
