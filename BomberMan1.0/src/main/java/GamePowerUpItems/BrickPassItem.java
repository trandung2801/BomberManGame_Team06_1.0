package GamePowerUpItems;

import GameSprite.GameSprite;

public class BrickPassItem extends Powerup {

  public BrickPassItem(int x, int y) {
    super(x, y, GameSprite.powerupWallpass.getFxImage());
  }
}
