package GameMonster;

import javafx.scene.image.Image;

public abstract class MonsterAnimation extends Entity {

    protected int animation = 0;
    public static final int MAX_ANIMATE = 3000;

    protected int timeTransfer = 26;

    public MonsterAnimation(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void animate() {
        if (animation > MAX_ANIMATE) {
            animation = 0;
        } else {
            animation += 1;
        }
    }
}
