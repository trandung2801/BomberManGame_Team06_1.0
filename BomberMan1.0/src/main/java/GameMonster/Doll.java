package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class Doll extends Monster {
  private moveType2 moveType = new moveType2();

  public Doll(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.dollLeft1.getFxImage());
    direction = 0;
    velocity = 1;
  }

  /**
   * Move logic.
   */
  public void move() {
    int tempX = x;
    int tempY = y;
    switch (direction) {
      case 0:
        tempY = y + velocity;
        break;
      case 1:
        tempY = y - velocity;
        break;
      case 2:
        tempX = x - velocity;
        break;
      case 3:
        tempX = x + velocity;
        break;
      default:
        break;
    }

    for (int i = 0; i < 4; i++) {
      int xx = tempX + AddToXToCheckCollision[i];
      int yy = tempY + AddToYToCheckCollision[i];
      if (!canMove(xx, yy)) {
        setDirection(moveType.setDirection(direction));
        return;
      }
    }

    this.setX(tempX);
    this.setY(tempY);
  }

  @Override
  public void update() {

    if (!isAlive) {
      deadAnimation();
    } else {
      move();
      animate();
      ifCollideWithPowerupOrFlame();
      if (direction == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.dollLeft1, GameSprite.dollLeft2, GameSprite.dollLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.dollRight1, GameSprite.dollRight2, GameSprite.dollRight3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.dollLeft1, GameSprite.dollRight1, GameSprite.dollLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.dollRight1, GameSprite.dollLeft2, GameSprite.dollRight2,
            animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.dolllDead, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
