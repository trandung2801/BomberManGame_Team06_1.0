package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class BatMonster extends Monster {
  private final moveType2 movetype = new moveType2();

  public BatMonster(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.batLeft1.getFxImage());
    direction = 3;
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
        this.setImg(GameSprite.movingSprite(GameSprite.batLeft1, GameSprite.batLeft2, GameSprite.batLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.batRight1, GameSprite.batRight2, GameSprite.batRight3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.batLeft1, GameSprite.batLeft1, GameSprite.batLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.batRight1, GameSprite.batRight2, GameSprite.batRight3,
            animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.batDown1, GameSprite.mobDead12, GameSprite.mobDead13,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
