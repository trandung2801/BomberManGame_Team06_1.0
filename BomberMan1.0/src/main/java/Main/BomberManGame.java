package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class BomberManGame extends Application {
    public static final int WIDTH = 1600; //Width screen
    public static final int HEIGHT = 900;//Height screen

    private GraphicsContext gc;
    private static CanvasGame canvas;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("BomberMan1.0");
        FXMLLoader fxmlLoader = new FXMLLoader(BomberManGame.class.getResource("Star-Controller.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
