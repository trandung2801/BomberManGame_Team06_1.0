package GameMapGraphics;

import javafx.scene.canvas.Canvas;

public class GameCanvas extends Canvas {
    private GameBase gameBase = new GameBase();

    public GameCanvas(double width, double height) {
        super(width, height);
    }

    public void render() {
        gameBase.render(this);
    }

    public GameBase getGameBase() {
        return gameBase;
    }
}
