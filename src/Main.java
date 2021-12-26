import javafx.application.Application;
import javafx.stage.Stage;
import managehouseholdbook.ConnectDatabase;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static double Width = 1440;
	public static double Height = 1024;
	@Override

	public void start(Stage primaryStage) {
		try {
			ConnectDatabase.ConnectData();
			// TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("managehouseholdbook/createhouseholdbook/CreateNewHouseholdBook.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("managehouseholdbook/HouseholdRegistrationBookManagement.fxml"));
			
			// AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("managehouseholdbook/thaydoisohokhau/movehousehold/MoveHouseholdBook.fxml"));
			double _scale = 0.75; 

			Scene scene = new Scene(root);
			root.setScaleX(_scale);
			root.setScaleY(_scale);
			// resize.letterbox(scene, root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
