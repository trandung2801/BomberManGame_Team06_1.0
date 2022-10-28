package bomberman.GameItems;

import bomberman.GameSprite.GameSprite;

public class BrickPassItem extends Item {

	public BrickPassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, GameSprite.powerup_wallpass.getFxImage());
	}

}
