package bomberman.GameEntities.GameCharacter;

import javafx.scene.image.Image;
import bomberman.GameEntities.GameAnimatedGameEntity;

public abstract class Character extends GameAnimatedGameEntity {
    protected int direction;// 0 Up, 1 Down, 2 Left, 3 Right
    protected boolean alive = true;
    protected boolean moving = false;
    protected int velocity;
    protected final int timeTransfer = 26;
    protected int timeShowDeath = 100;
    protected boolean startDie = false;

    public Character(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isStartDie() {
        return startDie;
    }

    public void setTimeShowDeath(int timeShowDeath) {
        this.timeShowDeath = timeShowDeath;
    }

    public void setStartDie(boolean startDie) {
        this.startDie = startDie;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getTimeTransfer() {
        return timeTransfer;
    }

    public abstract void update();

    public abstract void render();

    public abstract void calculateMove();

    public abstract boolean canMove();
}
