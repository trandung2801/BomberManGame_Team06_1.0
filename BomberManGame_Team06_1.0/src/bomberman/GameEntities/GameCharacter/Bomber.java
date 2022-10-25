package bomberman.GameEntities.GameCharacter;

import javafx.scene.canvas.GraphicsContext;
import bomberman.Main.BomberManGame;
import bomberman.GameEntities.GameEntity;
import bomberman.GameEntities.GameBomb.*;
import bomberman.GameEntities.GameMonster.Monster;
import bomberman.GameEntities.GameObject.*;
import bomberman.GameGraphics.KeyboardInput;
import bomberman.GameSprite.GameSprite;
import bomberman.GameItems.*;
import bomberman.GameSound.*;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private List<Bomb> bombList = new ArrayList<>();
    private KeyboardInput input;
    private int maxBom = 1;
    private int frameLen = 1;
	public boolean canPassBom = false;
    public boolean canPassFlame = false;
    private boolean canPassBrick = false;
    
    public int timeToStopFlame = 0;
    public int timeToStopBomb = 0;


    private boolean killAllEnemies = false;
    private boolean collideWithAPortal = false;

    private final int[] AddToXToCheckCollision = {0, GameSprite.SCALED_SIZE - 10, GameSprite.SCALED_SIZE - 10, 0};
    private final int[] AddToYToCheckCollision = {7, 7, GameSprite.SCALED_SIZE - 1, GameSprite.SCALED_SIZE - 1};

    public GameSound soundPlaceBomb = new GameSound(GameSound.soundPlaceBomb);
    public GameSound soundMoving = new GameSound(GameSound.soundMoving);


    public Bomber(int x, int y, KeyboardInput kb) {
        super(x, y, GameSprite.player_down.getFxImage()); // luc dau mac dinh la huong xuong
        this.input = kb;
        direction = 1;
        velocity = 2;
        input = kb;
        frameLen = 1;
        maxBom = 1;
        canPassBrick = false;
        canPassBom = false;
        canPassFlame = false;
    }

    //copy cac thuoc tinh cua bomber vao 1 bomber moi
    public void restoreBomber(Bomber newBomber) {
        reset();

        this.input = newBomber.input;
        this.velocity = newBomber.velocity;
        this.maxBom = newBomber.maxBom;
        this.frameLen = newBomber.frameLen;
        this.canPassBom = newBomber.canPassBom;
        this.canPassBrick = newBomber.canPassBrick;
        this.canPassFlame = newBomber.canPassFlame;
    }

    public void reset() {
//        this.setX(0);
//        this.setY(0);
        this.setImg(GameSprite.player_down.getFxImage());
        this.direction = 1;
        this.bombList = new ArrayList<>();
        this.killAllEnemies = false;
        this.collideWithAPortal = false;
    }

    public void update() {
        animate();

        bombUpdate();

        ifCollisionWithFlameOrEnemyOrItem();

        input = BomberManGame.canvas.getInput();

        if (isStartDie()) {
            if (timeShowDeath-- > 0) {
                this.setImg(GameSprite.movingSprite(GameSprite.player_dead1, GameSprite.player_dead2, GameSprite.player_dead3, animate, 30).getFxImage());
            } else {
                setAlive(false);
                setStartDie(false);
                setTimeShowDeath(100);
            }
        } else {

            if (input.space) {
                if (bombList.size() < maxBom) {
                    GameEntity e = BomberManGame.canvas.getEntityInCoodinate(getXUnit(), getYUnit());
                    if (e == null) {
                        bombList.add(new Bomb(getXUnit(), getYUnit(), frameLen, this));

                        if (!soundPlaceBomb.isRunning()) soundPlaceBomb.play();
                    }
                }
            } else {
                soundPlaceBomb.stop();
            }

            if (input.up || input.right || input.left || input.down) {
                setMoving(true);
            } else {
                setMoving(false);
                switch (direction) {
                    case 0:
                        this.setImg(GameSprite.player_up.getFxImage());
                        break;
                    case 1:
                        this.setImg(GameSprite.player_down.getFxImage());
                        break;
                    case 2:
                        this.setImg(GameSprite.player_left.getFxImage());
                        break;
                    case 3:
                        this.setImg(GameSprite.player_right.getFxImage());
                        break;
                }
            }
            if (isMoving()) {
                if (!soundMoving.isRunning()) soundMoving.play();
                calculateMove();
            }
            else {
                soundMoving.stop();
            }
        }

    }

    public void render() {

    }

    public List<Bomb> getBombList() {
        return bombList;
    }
//
//    public void setCanPassBom(boolean canPassBom) {
//        this.canPassBom = canPassBom;
//    }
//
//    public boolean isCanPassBom() {
//        return canPassBom;
//    }

    public void bombRender(GraphicsContext gc) {
        for (Bomb b : bombList) {
            if (b.isExplored()) {
                b.frameRender(gc);
            } else {
                b.render(gc);
            }
        }
    }

    public void bombUpdate() {
        bombList.forEach(b -> b.update());
        for (Bomb b : bombList) {
            if (b.getImg() == null) {
                bombList.remove(b);
                break;
            } else {
                for (Flame fl : b.getFlameList()) {
                    fl.update();
                }
            }
        }
    }

    public void calculateMove() {

        if (input.up) {
            y -= velocity;
            if (!canMove()) {
                y += velocity;
            }
            setDirection(0);
            this.setImg(
                    GameSprite.movingSprite(GameSprite.player_up, GameSprite.player_up_1, GameSprite.player_up_2, animate, timeTransfer)
                            .getFxImage());
        } else if (input.down) {
            y += velocity;
            if (!canMove()) {
                y -= velocity;
            }
            setDirection(1);
            this.setImg(GameSprite
                    .movingSprite(GameSprite.player_down, GameSprite.player_down_1, GameSprite.player_down_2, animate, timeTransfer)
                    .getFxImage());
        } else if (input.left) {
            x -= velocity;
            if (!canMove()) {
                x += velocity;
            }
            setDirection(2);
            this.setImg(GameSprite
                    .movingSprite(GameSprite.player_left, GameSprite.player_left_1, GameSprite.player_left_2, animate, timeTransfer)
                    .getFxImage());
        } else {
            x += velocity;
            if (!canMove()) {
                x -= velocity;
            }
            setDirection(3);
            this.setImg(GameSprite.movingSprite(GameSprite.player_right, GameSprite.player_right_1, GameSprite.player_right_2, animate,
                    timeTransfer).getFxImage());
        }
    }

    public boolean canMove() {
        for (int i = 0; i < 4; i++) { //check collision for 4 corners
            int newX = (getX() + AddToXToCheckCollision[i]) / GameSprite.SCALED_SIZE;
            int newY = (getY() + AddToYToCheckCollision[i]) / GameSprite.SCALED_SIZE;
            GameEntity e = BomberManGame.canvas.getEntityInCoodinate(newX, newY);

            if (e instanceof Wall || (e instanceof Brick && canPassBrick == false)) {
                return false;
            }
            if (e instanceof Portal) {
                if (killAllEnemies) {
                    collideWithAPortal = true;
                    return true;
                } else
                    return false;
            }
            if (e instanceof Monster) {
                setStartDie(false);
                return true;
            }
            if (e instanceof Bomb) {
                if (canPassBom) {
                    ((Bomb) e).setAllowPass(true); // set nhu nay de khi update tiep tranh bi loi xuyen tuong nhu o duoi
                } else {
                     /*khi check lan luot 4 goc, neu i = 0 la goc trai tren
                    se gap loi di xuyen tuong neu di sang phai vi check goc trai tren neu dang o tren bom nen van di dc
                    ma ben phai lai gap vat can, do vay phai xet du 4 goc neu dang o tren bom tranh loi xuyen tuong*/
                    if (((Bomb) e).isAllowPass()) {
                        continue;
                    } else return false;
                }
            }
        }
        return true;
    }

    public void ifCollisionWithFlameOrEnemyOrItem() {
        int x = getXUnit();
        int y = getYUnit();
        for (Bomb b : bombList) {
            List<Flame> fl = b.getFlameList();
            for (Flame f : fl) {
                if (f.getXUnit() == x && f.getYUnit() == y) {
                    if (!canPassFlame && !startDie) {
                        setStartDie(true);
                        new GameSound(GameSound.soundDead).play();
                    }
                    break;
                }
            }
        }
        GameEntity e = BomberManGame.canvas.getEntityInCoodinate(x, y);
        if (e instanceof Monster && !startDie) {
            setStartDie(true);
            new GameSound(GameSound.soundDead).play();
        }

        if (e instanceof Item) {
            new GameSound(GameSound.soundEatingItem).play();
            switch (((Item) e).getId()) {
                case "psi":
                    if (velocity < 3) { //velocity max = 3
                        velocity++;
                    }
                    break;
                case "pbi":
                    if (maxBom < 5) { //maxbom highest = 5
                        maxBom++;
                    }
                    break;
                case "pfi":
                    if (frameLen < 4) { //len max = 4
                        frameLen++;
                    }
                    break;
                case "bpi":
                    canPassBom = true;
                    timeToStopBomb += 50*37;
                    
                    break;
                case "fpi":
                    canPassFlame = true;
                    timeToStopFlame += 50*37;
                    break;
                case "wpi":
                    canPassBrick = true; //hiem
                    break;
                case "pli":
                    BomberManGame.lives += 1;
                    break;
            }

            e.setImg(null);

        }
    }

    public void setKillAllEnemies(boolean killAllEnemies) {
        this.killAllEnemies = killAllEnemies;
    }

    public boolean isCollideWithAPortal() {
        return collideWithAPortal;
    }


    public boolean isCanPassFlame() {
        return canPassFlame;
    }

//    public void setCanPassFlame(boolean canPassFlame) {
//        this.canPassFlame = canPassFlame;
//    }
    

    public int getFrameLen() {
		return frameLen;
	}
}