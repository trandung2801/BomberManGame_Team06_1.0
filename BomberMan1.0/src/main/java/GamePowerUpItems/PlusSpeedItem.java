package GamePowerUpItems;

import GameSprite.GameSprite;

public class PlusSpeedItem extends Powerup {

  public PlusSpeedItem(int x, int y) {
    super(x, y, GameSprite.powerupSpeed.getFxImage());
  }
}
