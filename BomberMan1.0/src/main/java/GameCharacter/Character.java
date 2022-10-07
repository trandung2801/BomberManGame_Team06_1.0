package GameCharacter;

import GameMapEntity.GameAnimationEntity;
import GameMapGraphics.KeyboardInput;
import javafx.scene.image.Image;


public abstract class Character extends GameAnimationEntity {
    protected int direction;    // 0 Up, 1 Down, 2 Left, 3 Right
    protected boolean alive = true;
    protected boolean moving = false;
    protected int velocity;
    protected int timeShowDeath = 100;
    protected boolean startDie = false;

    public Character(int x, int y, Image image){
        super(x, y, image);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getTimeShowDeath() {
        return timeShowDeath;
    }

    public int getTimeTransfer() {
        return timeTransfer;        // in GameAnimationEntity
    }

    public abstract void update();

    public abstract void render();

    public abstract void calculateMove();

    public abstract boolean canMove();

    public abstract void setPrecision(KeyboardInput input);

    public boolean isStartDie() {
        return startDie;
    }

    public void setTimeShowDeath(int timeShowDeath) {
        this.timeShowDeath = timeShowDeath;
    }

    public void setStartDie(boolean startDie) {
        this.startDie = startDie;
    }
}
