package bomberman.GameEntities.GameMonster;


import bomberman.GameAi.AILevel1;
import bomberman.GameSprite.GameSprite;

public class Balloon extends Monster {

    public Balloon(int x, int y) {
        super(x, y, GameSprite.balloom_left1.getFxImage());
        ai = new AILevel1(this);
        velocity = 1;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(GameSprite.movingSprite(GameSprite.balloom_dead, GameSprite.mob_dead1, GameSprite.mob_dead2, animate, timeTransfer).getFxImage());
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
                if (!canMove(xx, yy)) {
                	setDirection(ai.setDirect());
                    return;
                }
            }
            this.setX(tempX);
            this.setY(tempY);

        }

    }

    @Override
    public void update() {
        animate();
        if (!isAlive) {
            deadAnimation();
        } else {
            move();
            ifCollideWithItemOrFlame();
            if (direction == 0)
                this.setImg(GameSprite.movingSprite(GameSprite.balloom_left1, GameSprite.balloom_left2, GameSprite.balloom_left3, animate, timeTransfer).getFxImage());
            else if (direction == 1)
                this.setImg(GameSprite.movingSprite(GameSprite.balloom_right1, GameSprite.balloom_right2, GameSprite.balloom_right3, animate, timeTransfer).getFxImage());
            else if (direction == 2)
                this.setImg(GameSprite.movingSprite(GameSprite.balloom_left1, GameSprite.balloom_right1, GameSprite.balloom_left3, animate, timeTransfer).getFxImage());
            else if (direction == 3)
                this.setImg(GameSprite.movingSprite(GameSprite.balloom_right1, GameSprite.balloom_left2, GameSprite.balloom_right2, animate, timeTransfer).getFxImage());

        }
    }
}
