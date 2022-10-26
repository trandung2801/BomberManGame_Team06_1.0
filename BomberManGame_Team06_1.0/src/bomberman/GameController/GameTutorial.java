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
            Image tutorialImg = new Image(new FileInputStream("res/textures/tutorialGame.png"));
            Image movingKb = new Image(new FileInputStream("res/textures/movingKeyboard.png"));
            Image spaceKb = new Image(new FileInputStream("res/textures/space.png"));
            Image pKb = new Image(new FileInputStream("res/textures/keyboard_key_p.png"));

            gc.setFill(Color.BLACK);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(tutorialImg, 0, 0);

            gc.setFont(new Font(22));
            gc.fillText("Game:", 5, 100);

            gc.setFont(new Font(18));
            gc.fillText("Use navigation keys ", 100, 100);
            gc.drawImage(movingKb, 260, 80);
            gc.fillText(" to moving bomber around map.", 360, 100);

            gc.fillText("Press ", 100, 130);
            gc.drawImage(spaceKb, 150, 115);
            gc.fillText("to put bomb.", 290, 130);

            gc.fillText("Press ", 100, 164);
            gc.drawImage(pKb, 150, 145);
            gc.fillText(" to pause game", 180, 164);

            gc.setFont(new Font(22));
            gc.fillText("List items:", 5, 200);
            gc.setFont(new Font(18));

            gc.drawImage(GameSprite.powerup_speed.getFxImage(), 30, 220);
            gc.fillText(": increasing speed of bomber by 1", 70, 240);

            gc.drawImage(GameSprite.powerup_bombs.getFxImage(), 30, 260);
            gc.fillText(": increasing max bombs by 1", 70, 280);

            gc.drawImage(GameSprite.powerup_flames.getFxImage(), 30, 300);
            gc.fillText(": increasing length of flame in explosion by 1", 70, 320);

            gc.drawImage(GameSprite.powerup_detonator.getFxImage(), 30, 340);
            gc.fillText(": add 1 to lives of bomber", 70, 360);

            gc.drawImage(GameSprite.powerup_flamepass.getFxImage(), 550, 220);
            gc.fillText(": bomber can pass flame in a period time", 590, 240);

            gc.drawImage(GameSprite.powerup_bombpass.getFxImage(), 550, 260);
            gc.fillText(": bomber can pass bombs in a period time", 590, 280);

            gc.drawImage(GameSprite.powerup_flamepass.getFxImage(), 550, 300);
            gc.fillText(": bomber can go across the brick", 590, 320);

            gc.setFill(Color.RED);
            gc.setFont(new Font(14));
            gc.fillText("Press Backspace to back", 750, 420);
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
