package GameMapEntity.EntityObject;

import GameMapEntity.GameEntity;
import GameSprite.GameSprite;
import javafx.scene.image.Image;
import java.awt.*;

public class Wall extends GameEntity{

    private GameSprite[] sprites = {GameSprite.wall1, GameSprite.wall2, GameSprite.wall3,GameSprite.wall4, GameSprite.wall5,
            GameSprite.wall6, GameSprite.wall7};

    public Wall(int x, int y, int level) {
        super(x, y, null);
        img = sprites[level].getFxImage();
    }

    public Wall(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {}
}
