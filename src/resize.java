import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


import java.io.IOException;

/* Main JavaFX application class */
public class resize {

  public static void letterbox(final Scene scene, final TabPane contentPane) {
    final double initWidth  = scene.getWidth();
    final double initHeight = scene.getHeight();
    // System.out.println(initHeight + " " +initWidth);
    final double ratio      = initWidth / initHeight;

    SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
    scene.widthProperty().addListener(sizeListener);
    scene.heightProperty().addListener(sizeListener);

  }

  private static class SceneSizeChangeListener implements ChangeListener<Number> {
    private final Scene scene;
    private final double ratio;
    private final double initHeight;
    private final double initWidth;
    private final TabPane contentPane;

    public SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth, TabPane contentPane) {
      this.scene = scene;
      this.ratio = ratio;
      this.initHeight = initHeight;
      this.initWidth = initWidth;
      this.contentPane = contentPane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
      final double newWidth  = scene.getWidth();
      final double newHeight = scene.getHeight();
      double scale_ngang = newWidth/initWidth; 
      double scale_doc = newHeight/initHeight;

      
    
        Scale scale = new Scale(scale_ngang, scale_doc);
        scale.setPivotX(0);
        scale.setPivotY(0);
        scene.getRoot().getTransforms().setAll(scale);
        
        contentPane.setPrefWidth (newWidth  / scale_ngang);
        contentPane.setPrefHeight(newHeight / scale_doc);
        Main.Width = scene.getWidth();
        Main.Height = scene.getHeight();
    }
  }
}