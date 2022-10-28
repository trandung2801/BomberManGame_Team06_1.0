package bomberman.GameEntities.GameMonster;

import bomberman.GameAi.AILevel2;
import bomberman.GameSprite.GameSprite;


public class Minvo extends Monster {
	
    public Minvo(int x, int y) {
        super(x, y, GameSprite.minvo_left1.getFxImage());
        ai = new AILevel2();
        velocity = 2;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(GameSprite.movingSprite(GameSprite.minvo_dead, GameSprite.mob_dead1, GameSprite.mob_dead2, animate, timeTransfer).getFxImage());
        } else {
            this.removeFromGame();
        }
    }


    public void move() {

        if (isAlive) {
            int tempX = x, tempY = y;
            switch (direction) {
                case 0:
                    tempY = y - velocity;
                    break;
                case 1:
                    tempY = y + velocity;
                    break;
                case 2:
                    tempX = x - velocity;
                    break;
                case 3:
                    tempX = x + velocity;
                    break;
            }

            for (int i = 0; i < 4; i++) {
                int xx = tempX + AddToXToCheckCollision[i];
                int yy = tempY + AddToYToCheckCollision[i];
                if (!canMove(xx, yy) || ai.wantToChangeDirect) {
                    setDirection(ai.setDirect());
                    ai.setWantToChangeDirect(false);
                    return;
                }
            }
            this.setX(tempX);
            this.setY(tempY);

        }

    }

    @Override
    public void update() {

        if (!isAlive) {
            deadAnimation();
        } else {
            animate();
            move();
            ifCollideWithItemOrFlame();

            if (animate % (3 * GameSprite.SCALED_SIZE) == 0 && !ai.wantToChangeDirect) ai.setWantToChangeDirect(true);
            else ai.setWantToChangeDirect(false);

            if (direction == 0) {
                this.setImg(GameSprite.movingSprite(GameSprite.minvo_left1, GameSprite.minvo_left2, GameSprite.minvo_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 1) {
                this.setImg(GameSprite.movingSprite(GameSprite.minvo_right1, GameSprite.minvo_right2, GameSprite.minvo_right3, animate, timeTransfer).getFxImage());
            } else if (direction == 2) {
                this.setImg(GameSprite.movingSprite(GameSprite.minvo_left1, GameSprite.minvo_right1, GameSprite.minvo_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 3) {
                this.setImg(GameSprite.movingSprite(GameSprite.minvo_right1, GameSprite.minvo_left2, GameSprite.minvo_right2, animate, timeTransfer).getFxImage());
            }

        }
    }

}
