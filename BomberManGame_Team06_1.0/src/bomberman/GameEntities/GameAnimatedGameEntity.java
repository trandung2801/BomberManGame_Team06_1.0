package bomberman.GameEntities;

import javafx.scene.image.Image;

public abstract class GameAnimatedGameEntity extends GameEntity {
    protected int animate = 0;
    protected final int MAX_ANIMATE = 5000;

    protected int timeTransfer = 26;

    public GameAnimatedGameEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void animate() {
        if (animate > MAX_ANIMATE) {
            animate = 0;
        } else {
            animate += 1;
        }
    }
}
