
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static double Width = 1440;
	public static double Height = 1024;
	@Override

	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("QL_Sohokhau/thaydoisohokhau/thaydoinhankhau.fxml"));
			Scene scene = new Scene(root,Width,Height);
			primaryStage.setScene(scene);
			primaryStage.show();
			resize.letterbox(scene, root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
