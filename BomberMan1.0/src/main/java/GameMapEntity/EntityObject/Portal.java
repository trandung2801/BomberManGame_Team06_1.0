package GameMapEntity.EntityObject;

import GameMapEntity.GameEntity;
import GameSprite.GameSprite;

public class Portal extends GameEntity {

    public Portal(int x, int y) {
        super(x, y, GameSprite.portal.getFxImage());
    }

    @Override
    public void update() {}
}
