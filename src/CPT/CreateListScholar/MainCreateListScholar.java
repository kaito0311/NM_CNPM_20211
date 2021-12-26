package CPT.CreateListScholar;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class MainCreateListScholar extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/CPT/CreateListScholar/UI_CreateListScholar.fxml"));
			Scene scene = new Scene(root,1440,1024);
//			scene.getStylesheets().add(getClass().getResource("ChangeInfo.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setX(220);
			primaryStage.setY(0);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}