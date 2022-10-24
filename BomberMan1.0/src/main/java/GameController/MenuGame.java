package GameController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import Main.BomberManGame;
import GameMapGraphics.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuGame {
    private boolean startGame = false;
    private boolean highscore = false;
    private boolean tutorial = false;
    private boolean quit = false;

    private KeyboardInput keyboardInput;
    private int selecting = 0;
    private final int[] pointerCoordinate = {185, 235, 285, 335, 385};

    public MenuGame(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    /**
     * Show menu.
     */
    public void showMenu(GraphicsContext gc) {
        FileInputStream file;
        try {
            file = new FileInputStream("src/resources/textures/menubackground.png");
            final Image backgroundImage = new Image(file);
            file = new FileInputStream("src/resources/textures/menupointer3.png");
            final Image pointer = new Image(file);
            gc.setFill(Color.WHITE);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(backgroundImage, 0, 0);
            gc.drawImage(pointer, 350, pointerCoordinate[selecting]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("MenuGame.showMenu()");
        }
        gc.setFont(Font.font("Impact", 30)); // Font
        gc.fillText("New Game", 400, 230);
        String sound = BomberManGame.getMuted() ? "off" : "on";
        gc.fillText("Sound : " + sound, 400, 280);

        gc.fillText("HighScore", 400, 330);

        gc.fillText("Tutorial", 400, 380);

        gc.fillText("Quit", 400, 430);
    }

    public void update() {
        if (!keyboardInput.release && keyboardInput.down && selecting < 4) {
            selecting++;
            keyboardInput.setRelease(true);
        } else if (!keyboardInput.release && keyboardInput.up && selecting > 0) {
            selecting--;
            keyboardInput.setRelease(true);
        }
        if ((keyboardInput.space || keyboardInput.enter) && !keyboardInput.release) {
            switch (selecting) {
                case 0:
                    startGame = true;
                    break;
                case 1:
                    BomberManGame.setMuted(!BomberManGame.getMuted());
                    break;
                case 2:
                    highscore = true;
                    break;
                case 3:
                    tutorial = true;
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    break;
            }
            keyboardInput.setRelease(true);
        }
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean start) {
        startGame = start;
    }

    public boolean isMuted() {
        return BomberManGame.getMuted();
    }

    public boolean isQuit() {
        return quit;
    }

    public boolean showTutorial() {
        return tutorial;
    }

    public void setShowTutorial(boolean p) {
        this.tutorial = p;
    }

    public boolean showHighScore() {
        return highscore;
    }

    public void setShowHighScore(boolean p) {
        this.highscore = p;
    }
}
