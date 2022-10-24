package GameMapGraphics;

import javafx.scene.canvas.Canvas;
import GameMapEntity.GameEntity;

public class GameCanvas extends Canvas {
    private GameBase gameBase = new GameBase();
    private KeyboardInput keyboardInput = new KeyboardInput();

    /**
     * Create GameCanvas.
     */
    public GameCanvas(double width, double height) {
        super(width, height);

        // Key Event
        this.requestFocus();
        this.setFocusTraversable(true);
        this.setOnKeyPressed(keyEvent -> keyboardInput.KeyPressed(keyEvent));
        this.setOnKeyReleased(keyEvent -> keyboardInput.KeyReleased(keyEvent));
    }

    public void update() {
        gameBase.update();
    }

    public void render() {
        gameBase.render(this);
    }
    public KeyboardInput getInput() {
        return keyboardInput;
    }
    public GameEntity getEntityInCoodinate(int x, int y) {
        return gameBase.getEntityInCoodinate(x, y);
    }

    public GameEntity getGrass(int x, int y) {
        return gameBase.getGrass(x, y);
    }

    public GameBase getGameBase() {
        return gameBase;
    }
}
