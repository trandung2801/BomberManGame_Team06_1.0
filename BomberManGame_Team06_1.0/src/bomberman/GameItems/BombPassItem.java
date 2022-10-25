package bomberman.GameItems;

import bomberman.GameSprite.GameSprite;

public class BombPassItem extends Item {

	public BombPassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, GameSprite.powerup_bombpass.getFxImage());
	}

}
