package bomberman.GameEntities.GameObject;

import bomberman.GameEntities.GameAnimatedGameEntity;
import bomberman.GameSprite.GameSprite;
import bomberman.GameItems.Item;

public class Brick extends GameAnimatedGameEntity {

    private boolean isDestroyed = false;
    private int timeAnimate = 30;//time show animation brick explosion
    private int timeTransfer = 40; // time transfer between img
    
    private boolean brickHasItem = false;
    private Item item;
    
    private boolean brickHasPortal = false;

    private GameSprite[] spriteList = {GameSprite.brick, GameSprite.brick2, GameSprite.brick3, GameSprite.brick4, GameSprite.brick5, GameSprite.brick6, GameSprite.brick7};

    public Brick(int x, int y, int level) {
        super(x, y, null);
        this.setImg(spriteList[level].getFxImage());
    }

    public boolean isBrickHasItem() {
        return brickHasItem;
    }

    public void setBrickHasItem(boolean brickHasItem, Item item) {
        this.brickHasItem = brickHasItem;
        this.item = item;
    }

    public boolean isBrickHasPortal() {
        return brickHasPortal;
    }

    public void setBrickHasPortal(boolean brickHasPortal) {
        this.brickHasPortal = brickHasPortal;
    }

    public void update() {
        if (isDestroyed) {
            animate();
            
            if (timeAnimate-- > 0) {
                setImg(GameSprite.movingSprite(GameSprite.brick_exploded, GameSprite.brick_exploded1, GameSprite.brick_exploded2, animate, timeTransfer).getFxImage());
            } else {
                this.setImg(null);
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

	public Item getItem() {
		return item;
	}

}
