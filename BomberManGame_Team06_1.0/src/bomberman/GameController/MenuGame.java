package bomberman.GameController;

import bomberman.GameGraphics.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bomberman.Main.BomberManGame;

import java.io.FileInputStream;

public class MenuGame {
    private boolean startGame = false;
    private boolean quit = false;
    private boolean showTutorial = false;

    private KeyboardInput keyboardInput;
    private int Selecting = 0;
    private final int[] CoodinateYOfPointer = {295, 355, 413, 470};

    public MenuGame(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public void showMenuGame(GraphicsContext gc) {
        try {
            FileInputStream file = new FileInputStream("res/Controller/Background_MenuGame.png");
            Image Background_MenuGame = new Image(file);

            FileInputStream Pointer = new FileInputStream("res/Controller/Game_Pointer.png");
            Image pointer = new Image(Pointer);

            gc.setFill(Color.WHITE);
            gc.setFont(new Font(35));
            gc.clearRect(0, 0, 1024, 576);
            gc.drawImage(Background_MenuGame, 0, 0);
            gc.drawImage(pointer, 370, CoodinateYOfPointer[Selecting]);

            gc.setFont(new Font("", 25));
            gc.fillText("Play Game", 460, 320);

            String sound = BomberManGame.mute ? "Off" : "On";;

            gc.fillText(sound, 413, 383);

            gc.fillText("Sound", 485, 383);

            gc.fillText("Tutorial", 480, 440);

            gc.fillText("Quit", 495, 495);

            //TODO: co the cai tien cho them high score
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!keyboardInput.release && keyboardInput.down && Selecting < 3) {
            Selecting++;
            keyboardInput.setRelease(true);
        }
        else if (!keyboardInput.release && keyboardInput.up && Selecting > 0) {
            Selecting--;
            keyboardInput.setRelease(true);
        }
        if ((keyboardInput.space || keyboardInput.enter) && !keyboardInput.release) {
            switch (Selecting) {
                case 0:
                    startGame = true;
                    break;
                case 1:
                    BomberManGame.mute = !BomberManGame.mute;
                    break;
                case 2:
                    showTutorial = true;
                    break;
                case 3:
                    quit = true;
                    break;
            }
            keyboardInput.setRelease(true);
        }
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public boolean isMute() {
        return BomberManGame.mute;
    }

    public boolean isQuit() {
        return quit;
    }

    public boolean isShowTutorial() {
        return showTutorial;
    }

    public void setShowTutorial(boolean showTutorial) {
        this.showTutorial = showTutorial;
    }
}
