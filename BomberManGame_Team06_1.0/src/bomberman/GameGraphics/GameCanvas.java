package bomberman.GameGraphics;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import bomberman.GameEntities.GameEntity;

public class GameCanvas extends Canvas {
    private GameBase game = new GameBase();
    public GameBase getGame() {
		return game;
	}

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
        if (game.isGameOver()) {
            return;
        }
        game.update();
    }

    public void render() {
        game.render(this);
    }

    public KeyboardInput getInput() {
        return input;
    }

    public GameEntity getEntityInCoodinate(int x, int y) {
        return game.getEntityOnCoodinate(x, y);
    }

    public void setTransferLevel(boolean isTransfer) {
        game.setTransferLevel(isTransfer);
    }

    public boolean returnMenu() {
        return game.isReturnMainMenu();
    }

    public void setReturnMenu(boolean returnMenu) {
        game.setReturnMainMenu(returnMenu);
    }
}
