package GameMonster;

import GameMonster.moveMethod.moveType1;
import GameSprite.GameSprite;

public class Oneal extends Monster {
  private moveType1 moveType = new moveType1();

  public Oneal(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.onealLeft1.getFxImage());
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
        this.setImg(GameSprite.movingSprite(GameSprite.onealLeft1, GameSprite.onealLeft2, GameSprite.onealLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.onealRight1, GameSprite.onealRight2, GameSprite.onealRight3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.onealLeft1, GameSprite.onealRight1, GameSprite.onealLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.onealRight1, GameSprite.onealLeft2, GameSprite.onealRight2,
            animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.onealDead, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
