package bomberman.GameEntities.GameMonster;

import javafx.scene.image.Image;
import bomberman.Main.BomberManGame;
import bomberman.GameAi.AI;
import bomberman.GameEntities.GameAnimatedGameEntity;
import bomberman.GameEntities.GameEntity;
import bomberman.GameEntities.GameBomb.Bomb;
import bomberman.GameEntities.GameBomb.BombAnimation;
import bomberman.GameEntities.GameCharacter.Bomber;
import bomberman.GameEntities.GameObject.*;
import bomberman.GameGraphics.KeyboardInput;
import bomberman.GameSprite.GameSprite;
import bomberman.GameItems.Item;

import java.util.List;


public abstract class Monster extends GameAnimatedGameEntity {

    protected int velocity = 0;

    protected boolean isAlive = true;
    protected int direction = 0;
    //0 up, 1 down, 2 left, 3 right

    protected AI ai;

    protected Bomber bomber = new Bomber(0, 0, new KeyboardInput());
    protected int timeDead = 20;
    public static final int[] AddToXToCheckCollision = {2, GameSprite.SCALED_SIZE - 2, GameSprite.SCALED_SIZE - 2, 2};
    public static final int[] AddToYToCheckCollision = {2, 2, GameSprite.SCALED_SIZE - 2, GameSprite.SCALED_SIZE - 2};

    public Monster(int x, int y, Image img) {
        super(x, y, img);
    }


    public boolean canMove(int x, int y) {
        int xUnit = (int) x / GameSprite.SCALED_SIZE;
        int yUnit = (int) y / GameSprite.SCALED_SIZE;
        GameEntity e = BomberManGame.gameCanvas.getEntityInCoodinate(xUnit, yUnit);
        if (this instanceof Kondoria && e instanceof Brick) {
        	return true;
        }
        if (e instanceof Wall || e instanceof Brick || e instanceof Bomb || e instanceof Portal) {
            return false;
        }
        
        return true;
    }

    private int tempX = 0, tempY = 0;

    public boolean isMoving() {
        if (isAlive) {
            if (tempX != this.getXUnit() && tempY != this.getYUnit()) {
                tempX = this.getXUnit();
                tempY = this.getYUnit();
                return true;
            } else return false;
        }
        return false;
    }

    public abstract void deadAnimation();

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }


    public Bomber getBomber() {
        return bomber;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void ifCollideWithItemOrFlame() {
        int x = getXUnit();
        int y = getYUnit();
        //enemy gap bat ky item auto se tang speed
        GameEntity e = BomberManGame.gameCanvas.getEntityInCoodinate(x, y);
        if (e instanceof Item) {
//            if (!(this instanceof Dragon)) {
//                setVelocity(velocity + 1);
//            }
            e.setImg(null);
        }
        List<Bomb> bombList = bomber.getBombList();
        for (Bomb b : bombList) {
            List<BombAnimation> fl = b.getBombAnimationList();
            for (BombAnimation f : fl) {
                if (f.getXUnit() == x && f.getYUnit() == y) {
                    setAlive(false);
                    break;
                }
            }
        }
    }
    public void CollideWithBomb() {
        List<Bomb> bombs = bomber.getBombList();
        for (Bomb b : bombs) {
            if (b.getXUnit() == this.getXUnit() && b.getYUnit() == this.getYUnit()) {
                setVelocity(0);
                break;
            }
        }
    }

    public int getAnimate() {
        return animate;
    }


	public int getVelocity() {
		return velocity;
	}
    
}
