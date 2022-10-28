package bomberman.GameItems;

import bomberman.GameSprite.GameSprite;

public class PlusLiveItem extends Item {

	public PlusLiveItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, GameSprite.powerup_detonator.getFxImage());
	}

}
