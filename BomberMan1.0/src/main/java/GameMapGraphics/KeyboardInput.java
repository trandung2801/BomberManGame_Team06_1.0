package GameMapGraphics;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean skill = false;
    public boolean pause = false;
    public boolean release = false;

    public void KeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
            up = true;
        }
        if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
            down = true;
        }
        if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
            left = true;
        }
        if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
            right = true;
        }
        if (e.getCode() == KeyCode.TAB) {
            pause = true;
        }
        release = false;
    }

    public void KeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
            up = false;
        }
        if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
            down = false;
        }
        if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
            left = false;
        }
        if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
            right = false;
        }
        if (e.getCode() == KeyCode.TAB) {
            pause = false;
        }
        release = true;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
}
