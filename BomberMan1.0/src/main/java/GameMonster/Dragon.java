package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class Dragon extends Monster {
  private final moveType2 movetype = new moveType2();
  // private int timeShowFire = 100;
  // private int gapTime = 250;
  // private DragonBreathe breathe;

  public Dragon(int xUnit, int yUnit) {
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
        this.setImg(GameSprite.movingSprite(GameSprite.dragonUp1, GameSprite.dragonUp2, GameSprite.dragonUp3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.dragonDown1, GameSprite.dragonDown2, GameSprite.dragonDown3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.dragonLeft1, GameSprite.dragonLeft2, GameSprite.dragonLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3 && flameHit == 0) {
        this.setImg(GameSprite.movingSprite(GameSprite.dragonRight1, GameSprite.dragonRight2,
                GameSprite.dragonRight3, animation, timeTransfer).getFxImage());
      } else if (flameHit == 1) {
        this.setImg(
                GameSprite.movingSprite(GameSprite.fire1, GameSprite.fire2, GameSprite.fire3, animation, timeTransfer)
                .getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.dragonDown1, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
