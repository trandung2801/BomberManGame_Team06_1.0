package GameMapEntity;

import javafx.scene.image.Image;

public abstract class GameAnimationEntity extends GameEntity {
    protected int animation = 0;

    public static final int MAX_ANIMATE = 3000;

    protected int timeTransfer = 26;

    public GameAnimationEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void setAnimation() {
        if (animation > MAX_ANIMATE){
            animation = 0;
        } else {
            animation += 1;
        }
    }
}
