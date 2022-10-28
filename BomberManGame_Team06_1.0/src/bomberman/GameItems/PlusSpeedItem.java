package bomberman.GameItems;

import bomberman.GameSprite.GameSprite;

public class PlusSpeedItem extends Item {

	public PlusSpeedItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, GameSprite.powerup_speed.getFxImage());
	}

}
