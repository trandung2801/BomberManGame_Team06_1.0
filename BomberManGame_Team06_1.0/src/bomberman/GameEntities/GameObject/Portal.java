package bomberman.GameEntities.GameObject;

import bomberman.GameEntities.GameEntity;
import bomberman.GameSprite.GameSprite;

public class Portal extends GameEntity {

    public Portal(int x, int y) {
        super(x, y, GameSprite.portal.getFxImage());
    }

    @Override
    public void update() {

    }

}
