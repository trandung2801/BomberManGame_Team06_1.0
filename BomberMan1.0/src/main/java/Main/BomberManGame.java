package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class BomberManGame extends Application {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello World!");
        Button button = new Button();
        button.setText("Say Hello World");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(button);
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
