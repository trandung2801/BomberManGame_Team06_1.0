package bomberman.GameEntities.GameObject;

import bomberman.GameEntities.GameEntity;
import bomberman.GameSprite.GameSprite;

public class Grass extends GameEntity {

    private GameSprite[] spriteList = {GameSprite.grass1, GameSprite.grass2, GameSprite.grass3, GameSprite.grass4, GameSprite.grass5, GameSprite.grass6, GameSprite.grass7};

    public Grass(int x, int y, int level) {
        super(x, y, null);
        this.setImg(spriteList[level].getFxImage());
    }

    @Override
    public void update() {

    }
}
