package bomberman.GameEntities.GameObject;

import bomberman.GameEntities.GameEntity;
import bomberman.GameSprite.GameSprite;

public class Wall extends GameEntity {

    private GameSprite[] spriteList = {GameSprite.wall1, GameSprite.wall2, GameSprite.wall3, GameSprite.wall4, GameSprite.wall5, GameSprite.wall6, GameSprite.wall7};

    public Wall(int x, int y, int level) {
        super(x, y, null);
        this.setImg(spriteList[level].getFxImage());
    }

    @Override
    public void update() {

    }
}
