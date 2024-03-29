package bomberman.GameEntities.GameBomb;

import javafx.scene.canvas.GraphicsContext;
import bomberman.Main.BomberManGame;
import bomberman.GameEntities.GameAnimatedGameEntity;
import bomberman.GameEntities.GameEntity;
import bomberman.GameEntities.GameCharacter.Bomber;
import bomberman.GameEntities.GameMonster.*;
import bomberman.GameEntities.GameMonster.Monster;
import bomberman.GameEntities.GameObject.*;
import bomberman.GameSprite.GameSprite;
import bomberman.GameSound.GameSound;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends GameAnimatedGameEntity {
    private int timeBeforeExplore = 100;
    private int timeFlame = 14;
    private final int timeTransfer = 40;
    private boolean explored;
    private boolean allowPass = true;
    private Bomber bomber;

    private int flameLen = 1;
    private List<BombAnimation> bombAnimationList = new ArrayList<>();

    public GameSound soundExplode = new GameSound(GameSound.soundExplosion);
    public Bomb(int x, int y, int flameLen, Bomber bomber) {
        super(x, y, GameSprite.bomb.getFxImage());
        this.flameLen = flameLen;
        explored = false;
        this.bomber = bomber;
    }
    @Override
    public void update() {
        animate();
        if (explored == false) {
            if (allowPass == true) {
                int subX = bomber.getX() - getX();
                int subY = bomber.getY() - getY();
                if (subX < -20 || subX > 31 || subY > 25 || subY < -31) {
                    allowPass = false;
                }
            }
            if (timeBeforeExplore-- > 0) {
                setImg(GameSprite.movingSprite(GameSprite.bomb, GameSprite.bomb_1, GameSprite.bomb_2, animate, timeTransfer).getFxImage());
            } else {
                explored = true;
                explosion();
            }
        } else {

        	if (soundExplode.isRunning() == false) soundExplode.play();
            if (timeFlame-- == 0) {
                setImg(null);
            }
        }
    }

    private void explosion() {//init FlameList
    	flameLen = bomber.getFrameLen();
        int x = getXUnit();
        int y = getYUnit();

        bombAnimationList.add(new BombAnimation(x, y, 4, false));// add center
        //truong hop bomber o tren qua bom
        GameEntity e = BomberManGame.gameCanvas.getEntityInCoodinate(x, y);
        canPassThrough(e);
        
        
        
        //check left
        int il = 1;
        for (; il <= flameLen; il++) { //check tu 1 den FrameLen neu gap vat can break
            int xLeft = x - il;
            e = BomberManGame.gameCanvas.getEntityInCoodinate(xLeft, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < il; i++) { // them flame tu 1 den do dai frame lon nhat co the
            if (i == il - 1) {
                bombAnimationList.add(new BombAnimation(x - i, y, 2, true));
            } else {
                bombAnimationList.add(new BombAnimation(x - i, y, 2, false));
            }
        }

        //check right
        int ir = 1;
        for (; ir <= flameLen; ir++) {
            int xRight = x + ir;
            e = BomberManGame.gameCanvas.getEntityInCoodinate(xRight, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < ir; i++) {
            if (i == ir - 1) {
                bombAnimationList.add(new BombAnimation(x + i, y, 3, true));
            } else {
                bombAnimationList.add(new BombAnimation(x + i, y, 3, false));
            }
        }

        //check up
        int iu = 1;
        for (; iu <= flameLen; iu++) {
            int yUp = y - iu;
            e = BomberManGame.gameCanvas.getEntityInCoodinate(x, yUp);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < iu; i++) {
            if (i == iu - 1) {
                bombAnimationList.add(new BombAnimation(x, y - i, 0, true));
            } else {
                bombAnimationList.add(new BombAnimation(x, y - i, 0, false));
            }
        }

        //check down
        int id = 1;
        for (; id <= flameLen; id++) {
            int yDown = y + id;
            e = BomberManGame.gameCanvas.getEntityInCoodinate(x, yDown);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < id; i++) {
            if (i == id - 1) {
                bombAnimationList.add(new BombAnimation(x, y + i, 1, true));
            } else {
                bombAnimationList.add(new BombAnimation(x, y + i, 1, false));
            }
        }
    }

    public void frameRender(GraphicsContext gc) {
        bombAnimationList.forEach(g -> g.render(gc));
    }

    public boolean isExplored() {
        return explored;
    }

    public boolean canPassThrough(GameEntity e) { // return false if ko truyen qua dc e, true if truyen qua dc

        int gotScore = 0;
        if (e instanceof Brick) {
            ((Brick) e).setDestroyed(true);
            gotScore = 5;
            BomberManGame.scores += gotScore;
            return false;
        }
        if (e instanceof Wall || e instanceof Portal) {
            return false;
        }
        if (e instanceof Monster) {
            ((Monster) e).setAlive(false);
            
            if (e instanceof Balloon) gotScore = 10;
            else if (e instanceof Oneal || e instanceof Doll) gotScore = 20;
            else if (e instanceof Minvo) gotScore = 30;
            else if (e instanceof Kondoria) gotScore = 35;
            else if (e instanceof Dragon) gotScore = 50;
            BomberManGame.scores += gotScore;
        }
        if (e instanceof Bomber) {
            if (((Bomber) e).isCanPassFlame() == false) ((Bomber) e).setAlive(false);
        }
        if (e instanceof Bomb) {
            ((Bomb) e).setTimeBeforeExplore(5);
        }
        return true;
    }

    public List<BombAnimation> getBombAnimationList() {
        return bombAnimationList;
    }

    public boolean isAllowPass() {
        return allowPass;
    }

    public void setAllowPass(boolean allowPass) {
        this.allowPass = allowPass;
    }

    public void setTimeBeforeExplore(int timeBeforeExplore) {
        this.timeBeforeExplore = timeBeforeExplore;
    }

}