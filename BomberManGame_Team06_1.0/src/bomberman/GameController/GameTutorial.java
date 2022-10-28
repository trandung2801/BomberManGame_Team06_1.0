package bomberman.GameController;

import bomberman.GameGraphics.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bomberman.GameSprite.GameSprite;

import java.io.FileInputStream;

public class GameTutorial {
    private KeyboardInput keyboardInput;
    private boolean returnMenu = false;

    public GameTutorial(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public void showTutorialGame(GraphicsContext gc) {
        try {
            Image Game_Tutorial = new Image(new FileInputStream("res/Controller/Game_Tutorial.png"));

            gc.setFill(Color.WHITE);
            gc.drawImage(Game_Tutorial, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyboardInput.back) {
            returnMenu = true;
        }
    }

    public boolean isReturnMenu() {
        return returnMenu;
    }

    public void setReturnMenu(boolean returnMenu) {
        this.returnMenu = returnMenu;
    }
}
