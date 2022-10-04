package GameMapEntity.EntityObject;

import GameMapEntity.GameEntity;
import GameSprite.GameSprite;
import javafx.scene.image.Image;
import java.awt.*;

public class Wall extends GameEntity {

    private GameSprite[] WallSprite = {GameSprite.wall};

    public Wall(int x, int y, int gamemap){
        super(x, y, null);
        img = WallSprite[gamemap].getFxImage();
    }

    public Wall(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {

    }
}
