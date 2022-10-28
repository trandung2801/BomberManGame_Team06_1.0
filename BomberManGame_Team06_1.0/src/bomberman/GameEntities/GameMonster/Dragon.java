package bomberman.GameEntities.GameMonster;

import bomberman.GameAi.AILevel4;
import bomberman.GameEntities.GameBomb.Bomb;
import bomberman.GameSprite.GameSprite;

import java.util.List;


public class Dragon extends Monster {
    protected int max_Steps = GameSprite.SCALED_SIZE / 2; // 2 is velocity
    protected int steps_now = max_Steps;

    public Dragon(int x, int y) {
        super(x, y, GameSprite.boss_down1.getFxImage());
        ai = new AILevel4(bomber, this);
        velocity = 2;
        direction = -1;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(GameSprite.movingSprite(GameSprite.boss_down1, GameSprite.mob_dead1, GameSprite.mob_dead2, animate, timeTransfer).getFxImage());
        } else {
            this.removeFromGame();
        }
    }


    public void move() {

        if (steps_now == max_Steps) {
            setDirection(ai.setDirect());
            steps_now = 0;
        }

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

        this.setX(tempX);
        this.setY(tempY);
        steps_now++;

    }

    @Override
    public void update() {

        if (!isAlive) {
            deadAnimation();
        } else {
            animate();
            move();
            ifCollideWithItemOrFlame();
            updateImg();
            CollideWithBomb();
        }
    }

    private void updateImg() {
        if (direction == 0) {
            this.setImg(GameSprite.movingSpriteFourImg(GameSprite.boss_up1, GameSprite.boss_up2, GameSprite.boss_up3, GameSprite.boss_up4, animate, timeTransfer).getFxImage());
        } else if (direction == 1) {
            this.setImg(GameSprite.movingSpriteFourImg(GameSprite.boss_down1, GameSprite.boss_down2, GameSprite.boss_down3, GameSprite.boss_down4, animate, timeTransfer).getFxImage());
        } else if (direction == 2) {
            this.setImg(GameSprite.movingSpriteFourImg(GameSprite.boss_left1, GameSprite.boss_left2, GameSprite.boss_left3, GameSprite.boss_left4, animate, timeTransfer).getFxImage());
        } else if (direction == 3) {
            this.setImg(GameSprite.movingSpriteFourImg(GameSprite.boss_right1, GameSprite.boss_right2, GameSprite.boss_right3, GameSprite.boss_right4, animate, timeTransfer).getFxImage());
        }
    }

    public void updateBomberForAI() {
        ((AILevel4) ai).updateBomber(bomber);
    }

    public void CollideWithBomb() {
        List<Bomb> bombs = bomber.getBombList();
        for (Bomb b : bombs) {
            if (b.getXUnit() == this.getXUnit() && b.getYUnit() == this.getYUnit()) {
                setVelocity(0);
                break;
            }
        }
    }
}