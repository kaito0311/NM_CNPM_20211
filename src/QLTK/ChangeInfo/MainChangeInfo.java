package QLTK.ChangeInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainChangeInfo extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/QLTK/ChangeInfo/UI_ChangeInfo.fxml"));
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
