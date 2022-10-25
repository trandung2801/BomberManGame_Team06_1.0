package bomberman.GameItems;
import bomberman.GameSprite.GameSprite;

public class FlamePassItem extends Item {

	public FlamePassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, GameSprite.powerup_flamepass.getFxImage());
	}

}
