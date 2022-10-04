package Main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMain {
    private static final int WIDTH_SCREEN = 1600;
    private static final int HEIGHT_SCREEN = 900;
    private Stage stage;

    public GameMain(Stage stage, String namePlayer) {
        this.stage = stage;
        Group root = new Group();
        Scene theScene = new Scene(root, WIDTH_SCREEN, HEIGHT_SCREEN, Color.GRAY);

        Canvas canvas = new Canvas(WIDTH_SCREEN, HEIGHT_SCREEN);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();


    }
}
