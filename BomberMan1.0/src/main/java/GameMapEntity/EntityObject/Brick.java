package GameMapEntity.EntityObject;

import GameMapEntity.GameAnimationEntity;
import GameSprite.GameSprite;
import GamePowerUpItems.Powerup;
import javafx.scene.image.Image;

public class Brick extends GameAnimationEntity {
    private boolean isDestroyed = false;
    private int timeAnimate = 30;

    private boolean hasPower = false;
    private Powerup powerup;

    private boolean hasPortal = false;

    private GameSprite[] sprites = {GameSprite.brick, GameSprite.brick2, GameSprite.brick3, GameSprite.brick4,
            GameSprite.brick5, GameSprite.brick6, GameSprite.brick7};

    public Brick(int x, int y, Image image) {
        super(x, y, image);
    }

    public Brick(int x, int y, int level) {
        super(x, y, null);
        img = sprites[level].getFxImage();
    }

    public boolean hasPowerup() {
        return hasPower;
    }

    public boolean hasPortal() {
        return hasPortal;
    }

    public void setPowerUp(boolean hasPower, Powerup powerup) {
        this.hasPower = hasPower;
        this.powerup = powerup;
    }

    public void setPortal(boolean hasPortal) {
        this.hasPortal = hasPortal;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroy) {
        isDestroyed = destroy;
    }

    public Powerup getPowerup() {
        return powerup;
    }

    @Override
    public void update() {
        if (isDestroyed) {
            animate();
            if (timeAnimate-- > 0) {
                img = GameSprite.movingSprite(GameSprite.brickExploded, GameSprite.brickExploded1,
                        GameSprite.brickExploded2, animation, timeTransfer).getFxImage();
            } else {
                img = null;
            }
        }
    }
}
