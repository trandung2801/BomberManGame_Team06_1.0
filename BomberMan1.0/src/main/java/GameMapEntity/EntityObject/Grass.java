package GameMapEntity.EntityObject;

import GameMapEntity.GameEntity;
import javafx.scene.image.Image;
import GameSprite.GameSprite;

public class Grass extends GameEntity {
    private GameSprite[] sprites = {GameSprite.grass1, GameSprite.grass2, GameSprite.grass3, GameSprite.grass4,
            GameSprite.grass5, GameSprite.grass6, GameSprite.grass7};

    public Grass(int x, int y, int level) {
        super(x, y, null);
        this.setImg(sprites[level].getFxImage());
    }

    public Grass(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {}
}
