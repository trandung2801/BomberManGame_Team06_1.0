package GameMonster;

import GameMonster.moveMethod.moveType1;
import GameSprite.GameSprite;

public class Balloon extends Monster {
  private final moveType1 movetype = new moveType1();

  public Balloon(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.balloomLeft1.getFxImage());
    direction = 2;
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
        tempY = y + velocity;
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
        this.setImg(GameSprite.movingSprite(GameSprite.balloomLeft1, GameSprite.balloomLeft2,
                GameSprite.balloomLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.balloomRight1, GameSprite.balloomRight2,
                GameSprite.balloomRight3, animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.balloomLeft1, GameSprite.balloomRight1,
                GameSprite.balloomLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.balloomRight1, GameSprite.balloomLeft2,
                GameSprite.balloomRight2, animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.balloomDead, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
