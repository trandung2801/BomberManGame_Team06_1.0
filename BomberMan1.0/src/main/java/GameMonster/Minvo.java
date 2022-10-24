package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class Minvo extends Monster {
  private final moveType2 movetype = new moveType2();

  public Minvo(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.minvoLeft1.getFxImage());
    direction = 1;
    velocity = 2;
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
        direction = movetype.setDirection(direction);
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
        this.setImg(GameSprite.movingSprite(GameSprite.minvoLeft1, GameSprite.minvoLeft2, GameSprite.minvoLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.minvoRight1, GameSprite.minvoRight2, GameSprite.minvoRight3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.minvoLeft1, GameSprite.minvoRight1, GameSprite.minvoLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.minvoRight1, GameSprite.minvoLeft2, GameSprite.minvoRight2,
            animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.minvoDead, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }

  }
}
