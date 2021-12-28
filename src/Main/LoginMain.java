package Main;
	
import database.SQLConnection;
import gift.thongkegift.GetDataGiving;
import gift.thongkescholar.GetDataScholar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Main/UI_Login.fxml"));
			Scene scene = new Scene(root,800,670);
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		SQLConnection.ConnectData();
//		SQLConnection.setList();
//		GetDataGiving.setGivingList();
//		GetDataScholar.setScholarList();
//		SQLConnection.DisconnectData();
		launch(args);
	}
}
