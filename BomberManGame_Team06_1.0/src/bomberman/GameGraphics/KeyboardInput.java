package bomberman.GameGraphics;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {
    public boolean up, down, left, right, space, pause, enter, back, levelup, win, lose;
    public boolean release = false;// su dung de dieu huong lua chon trong menu game

    public KeyboardInput() {
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
        pause = false;
        enter = false;
        back = false;
        levelup = false;
        win = false;
        lose = false;
    }

    public void updateKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.UP) {
            up = true;
        }
        if (e.getCode() == KeyCode.DOWN) {
            down = true;
        }
        if (e.getCode() == KeyCode.LEFT) {
            left = true;
        }
        if (e.getCode() == KeyCode.RIGHT) {
            right = true;
        }
        if (e.getCode() == KeyCode.SPACE) {
            space = true;
        }
        if (e.getCode() == KeyCode.P ) {
            pause = true;
        }
        if (e.getCode() == KeyCode.ENTER) {
            enter = true;
        }
        if (e.getCode() == KeyCode.BACK_SPACE) {
            back = true;
        }
        if (e.getCode() == KeyCode.J) {
            levelup = true;
        }
        if (e.getCode() == KeyCode.K) {
            win = true;
        }
        if (e.getCode() == KeyCode.L) {
            lose = true;
        }
            release = false;
    }

    public void updateKeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.UP) {
            up = false;
        }
        if (e.getCode() == KeyCode.DOWN) {
            down = false;
        }
        if (e.getCode() == KeyCode.LEFT) {
            left = false;
        }
        if (e.getCode() == KeyCode.RIGHT) {
            right = false;
        }
        if (e.getCode() == KeyCode.SPACE) {
            space = false;
        }
        if (e.getCode() == KeyCode.ENTER) {
            enter = false;
        }
        if (e.getCode() == KeyCode.BACK_SPACE) {
            back = false;
        }
        if (e.getCode() == KeyCode.J) {
            levelup = false;
        }
        if (e.getCode() == KeyCode.K) {
            win = false;
        }
        if (e.getCode() == KeyCode.L) {
            lose = false;
        }
        release = true;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
}
