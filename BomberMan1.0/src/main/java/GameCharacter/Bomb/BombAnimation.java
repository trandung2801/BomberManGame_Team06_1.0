package GameCharacter.Bomb;

import GameMapEntity.GameAnimationEntity;
import GameSprite.GameSprite;

public class BombAnimation extends GameAnimationEntity {
    private int direct;     // hướng nổ 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 CENTER
    private boolean last = false;

    /**
     * Generate BombAnimation.
     *
     * @param xUnit X position of flame
     * @param yUnit Y position of flame
     * @param direct Drirection of flame
     * @param last FLame time
     */
    public BombAnimation(int xUnit, int yUnit, int direct, boolean last) {
        super(xUnit, yUnit, null);
        this.direct = direct;
        this.last = last;
    }
    @Override
    public void update() {
        setAnimation();
        switch (direct) {
            case 0:
                if (last) {
                    setImg(
                            GameSprite.movingSprite(GameSprite.explosionVerticalTopLast, GameSprite.explosionVerticalTopLast1,
                                    GameSprite.explosionVerticalTopLast2, animation, timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosionVertical, GameSprite.explosionVertical1,
                            GameSprite.explosionVertical2, animation, timeTransfer).getFxImage());
                }
                break;
            case 1:
                if (last) {
                    setImg(GameSprite
                            .movingSprite(GameSprite.explosionVerticalDownLast, GameSprite.explosionVerticalDownLast1,
                                    GameSprite.explosionVerticalDownLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosionVertical, GameSprite.explosionVertical1,
                            GameSprite.explosionVertical2, animation, timeTransfer).getFxImage());
                }
                break;
            case 2:
                if (last) {
                    setImg(GameSprite
                            .movingSprite(GameSprite.explosionHorizontalLeftLast, GameSprite.explosionHorizontalLeftLast1,
                                    GameSprite.explosionHorizontalLeftLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosionHorizontal, GameSprite.explosionHorizontal1,
                            GameSprite.explosionHorizontal2, animation, timeTransfer).getFxImage());
                }
                break;
            case 3:
                if (last) {
                    setImg(GameSprite.movingSprite(GameSprite.explosionHorizontalRightLast,
                            GameSprite.explosionHorizontalRightLast1, GameSprite.explosionHorizontalRightLast2, animation,
                            timeTransfer).getFxImage());
                } else {
                    setImg(GameSprite.movingSprite(GameSprite.explosionHorizontal, GameSprite.explosionHorizontal1,
                            GameSprite.explosionHorizontal2, animation, timeTransfer).getFxImage());
                }
                break;
            case 4:
                setImg(GameSprite.movingSprite(GameSprite.bombExploded, GameSprite.bombExploded1, GameSprite.bombExploded2,
                        animation, timeTransfer).getFxImage());
                break;
            default:
                break;
        }
    }
}