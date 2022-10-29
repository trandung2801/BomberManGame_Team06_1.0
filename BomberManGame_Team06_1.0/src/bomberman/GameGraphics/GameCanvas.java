package bomberman.GameGraphics;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import bomberman.GameEntities.GameEntity;

public class GameCanvas extends Canvas {
    private GameBase gameBase = new GameBase();

	private KeyboardInput input = new KeyboardInput();
    public static final String TITTLE = "Bomberman";

    public GameCanvas(int width, int height) {
        super(width, height);

        //key Event
        this.requestFocus();
        this.setFocusTraversable(true);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyPressed(keyEvent);
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyReleased(keyEvent);
            }
        });
    }


    public void update() {
        if (gameBase.isGameOver()) {
            return;
        }
        gameBase.update();
    }

    public void render() {
        gameBase.render(this);
    }

    public KeyboardInput getInput() {
        return input;
    }
    public GameBase getGame() {
        return gameBase;
    }

    public GameEntity getEntityInCoodinate(int x, int y) {
        return gameBase.getEntityOnCoodinate(x, y);
    }

    public void setTransferLevel(boolean isTransfer) {
        gameBase.setTransferLevel(isTransfer);
    }

    public boolean returnMenu() {
        return gameBase.isReturnMainMenu();
    }

    public void setReturnMenu(boolean returnMenu) {
        gameBase.setReturnMainMenu(returnMenu);
    }
}
