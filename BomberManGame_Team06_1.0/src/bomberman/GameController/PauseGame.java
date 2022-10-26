package bomberman.GameController;

import bomberman.GameGraphics.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bomberman.Main.BomberManGame;

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
        String sound = BomberManGame.mute ? "off" : "on";
       
        gc.setFill(Color.OLIVE);
        gc.fillRect(0, 0, 992, 448);
        gc.setFont(new Font(60));
        gc.setFill(Color.WHITE);
        switch (selecting) {
            case 0:
                gc.fillText("Sound: " + sound, 350, 200);
                gc.fillText("Main menu", 350, 260);
                gc.setFill(Color.RED);
                gc.fillText("Resume", 350, 140);
                break;
            case 1:
                gc.fillText("Resume", 350, 140);
                gc.fillText("Main menu", 350, 260);
                gc.setFill(Color.RED);
                gc.fillText("Sound: " + sound, 350, 200);
                break;
            case 2:
                gc.fillText("Resume", 350, 140);
                gc.fillText("Sound: " + sound, 350, 200);
                gc.setFill(Color.RED);
                gc.fillText("Main menu", 350, 260);
                break;
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
                case 1:
                    BomberManGame.mute = !BomberManGame.mute;
//                    BombermanGame.mute = mute;
                    break;
                case 2:
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
