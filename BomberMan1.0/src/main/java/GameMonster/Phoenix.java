package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class Phoenix extends Monster {
  private final moveType2 movetype = new moveType2();
  private int timeShowFire = 500;
  // private int gapTime = 250;
  // private DragonBreathe breathe;

  public Phoenix(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.dragonLeft1.getFxImage());
    direction = 2;
    velocity = 3;
  }

  /**
   * Move logic.
   */
  public void move() {
    int tempX = x;
    int tempY = y;
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
      default:
        break;
    }

    for (int i = 0; i < 4; i++) {
      int xx = tempX + AddToXToCheckCollision[i];
      int yy = tempY + AddToYToCheckCollision[i];
      if (!canMove(xx, yy) || recognizeBomb(xx, yy)) {
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
      if (direction == 0 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.phoenixUp1, GameSprite.phoenixUp2, GameSprite.phoenixUp3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.phoenixDown1, GameSprite.phoenixDown2,
                GameSprite.phoenixDown3, animation, timeTransfer).getFxImage());
      } else if (direction == 2 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.phoenixLeft1, GameSprite.phoenixLeft2,
                GameSprite.phoenixLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 3 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.phoenixRight1, GameSprite.phoenixRight2,
                GameSprite.phoenixRight3, animation, timeTransfer).getFxImage());
      } else if (flameHit == 1) {
        timeShowFire--;
        if (timeShowFire == 0) {
          flameHit--;
          timeShowFire = 500;
          velocity = 3;
        }
        this.setImg(
                GameSprite.movingSprite(GameSprite.fire1, GameSprite.fire2, GameSprite.fire3, animation, timeTransfer)
                .getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.phoenixDown1, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
