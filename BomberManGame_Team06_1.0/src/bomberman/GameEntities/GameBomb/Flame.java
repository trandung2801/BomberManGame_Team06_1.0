package bomberman.GameEntities.GameBomb;

import bomberman.GameEntities.GameAnimatedGameEntity;
import bomberman.GameSprite.GameSprite;

public class Flame extends GameAnimatedGameEntity {
    private int direct; // 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 CENTER
    private boolean last = false;

    public Flame(int xUnit, int yUnit, int direct, boolean last) {
        super(xUnit, yUnit, null);
        this.direct = direct;
        this.last = last;
    }

    @Override
    public void update() {
        animate();
        switch (direct) {
            case 0:
                if (last) {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_vertical_top_last, GameSprite.explosion_vertical_top_last1, GameSprite.explosion_vertical_top_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_vertical, GameSprite.explosion_vertical1, GameSprite.explosion_vertical2, animate, timeTransfer).getFxImage());
                }
                break;
            case 1:
                if (last) {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_vertical_down_last, GameSprite.explosion_vertical_down_last1, GameSprite.explosion_vertical_down_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_vertical, GameSprite.explosion_vertical1, GameSprite.explosion_vertical2, animate, timeTransfer).getFxImage());
                }
                break;
            case 2:
                if (last) {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_horizontal_left_last, GameSprite.explosion_horizontal_left_last1, GameSprite.explosion_horizontal_left_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_horizontal, GameSprite.explosion_horizontal1, GameSprite.explosion_horizontal2, animate, timeTransfer).getFxImage());
                }
                break;
            case 3:
                if (last) {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_horizontal_right_last, GameSprite.explosion_horizontal_right_last1, GameSprite.explosion_horizontal_right_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosion_horizontal, GameSprite.explosion_horizontal1, GameSprite.explosion_horizontal2, animate, timeTransfer).getFxImage());
                }
                break;
            case 4:
                setImg(GameSprite.movingSprite(GameSprite.bomb_exploded, GameSprite.bomb_exploded1, GameSprite.bomb_exploded2, animate, timeTransfer).getFxImage());
                break;
        }
    }
}
