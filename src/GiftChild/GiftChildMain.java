package GiftChild;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;


public class GiftChildMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("UI_GiftChild.fxml"));
			Scene scene = new Scene(root,1440,1000);
			scene.getStylesheets().add(getClass().getResource("GiftChild.css").toExternalForm());
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