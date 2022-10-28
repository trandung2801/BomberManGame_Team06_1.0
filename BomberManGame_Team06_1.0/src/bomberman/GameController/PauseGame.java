package bomberman.GameController;

import bomberman.GameGraphics.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bomberman.Main.BomberManGame;

import java.io.FileInputStream;

public class PauseGame {
    private KeyboardInput keyboardInput;
    private int selecting;

    public int finalSelected;

    public PauseGame(KeyboardInput kb) {
        keyboardInput = kb;
        selecting = 0;
        finalSelected = -1;
    }

    public void showPauseGame(GraphicsContext gc) {
        try {
            FileInputStream file = new FileInputStream("res/Controller/Background_PauseGame.png");
            Image Background_PauseGame = new Image(file);

            String sound = BomberManGame.mute ? "Off" : "On";

            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 1024, 576);
            gc.setFont(new Font(25));
            gc.setFill(Color.WHITE);
            gc.drawImage(Background_PauseGame, 0, 0);
            switch (selecting) {
                case 0:
                    gc.fillText(sound, 413, 440);
                    gc.fillText("Sound", 485, 440);
                    gc.fillText("Option", 485, 380);;
                    gc.setFill(Color.RED);
                    gc.fillText("Resume", 480, 320);
                    break;
                case 2:
                    gc.fillText("Resume", 480, 320);
                    gc.fillText("Option", 485, 380);
                    gc.setFill(Color.RED);
                    gc.fillText(sound, 413, 440);
                    gc.fillText("Sound", 485, 440);
                    break;
                case 1:
                    gc.fillText("Resume", 480, 320);
                    gc.fillText(sound, 413, 440);
                    gc.fillText("Sound", 485, 440);
                    gc.setFill(Color.RED);
                    gc.fillText("Option", 485, 380);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!keyboardInput.release && keyboardInput.down && selecting < 2) {
            selecting++;
            keyboardInput.setRelease(true);
        } else if (!keyboardInput.release && keyboardInput.up && selecting > 0) {
            selecting--;
            keyboardInput.setRelease(true);
        }
        if ((keyboardInput.enter || keyboardInput.space) && !keyboardInput.release) {
            switch (selecting) {
                case 0:
                    finalSelected = 1;
                    break;
                case 2:
                    BomberManGame.mute = !BomberManGame.mute;
//                    BombermanGame.mute = mute;
                    break;
                case 1:
                    finalSelected = 2;
                    break;
            }
            if (selecting == 2) {
                selecting = 0; //reset selecting
            }
            keyboardInput.setRelease(true);
        }
    }

    public int getFinalSelected() {
        return finalSelected;
    }

    public void setFinalSelected(int finalSelected) {
        this.finalSelected = finalSelected;
    }
}
