package GameMonster;

import GameMonster.moveMethod.moveType2;
import GameSprite.GameSprite;

public class Kondoria extends Monster {
  private moveType2 moveType = new moveType2();

  public Kondoria(int xUnit, int yUnit) {
    super(xUnit, yUnit, GameSprite.kondoriaLeft1.getFxImage());
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
        this.setImg(GameSprite.movingSprite(GameSprite.kondoriaLeft1, GameSprite.kondoriaLeft2,
                GameSprite.kondoriaLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(GameSprite.movingSprite(GameSprite.kondoriaRight1, GameSprite.kondoriaRight2,
                GameSprite.kondoriaRight3, animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(GameSprite.movingSprite(GameSprite.kondoriaLeft1, GameSprite.kondoriaRight1,
                GameSprite.kondoriaLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(GameSprite.movingSprite(GameSprite.kondoriaRight1, GameSprite.kondoriaLeft2,
                GameSprite.kondoriaRight2, animation, timeTransfer).getFxImage());
      }

    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub
    if (timeDead-- > 0) {
      this.setImg(GameSprite.movingSprite(GameSprite.kondoriaDead, GameSprite.mobDead11, GameSprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }

}
