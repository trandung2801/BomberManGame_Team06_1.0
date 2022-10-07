package GameCharacter.Bomb;

import java.util.ArrayList;
import java.util.List;

import GameCharacter.BomberCharacter;
import Main.BomberManGame;
import GameMapEntity.GameAnimationEntity;
import GameMapEntity.GameEntity;
//import entities.monsters.Balloon;
//import entities.monsters.BatMonster;
//import entities.monsters.Doll;
//import entities.monsters.Dragon;
//import entities.monsters.Kondoria;
//import entities.monsters.Minvo;
//import entities.monsters.Monster;
//import entities.monsters.Oneal;
//import entities.monsters.Phoenix;
//import entities.player.Bomber;
//import entities.stillobjects.Brick;
//import entities.stillobjects.Portal;
//import entities.stillobjects.Wall;
import GameSprite.GameSprite;
import javafx.scene.canvas.GraphicsContext;
import GameSound.GameSound;

public class Bomb extends GameAnimationEntity {
    private int timeBeforeExplore = 100;    // thời gian trước khi phát nổ
    private int timeFlame = 14;             // thời gian chuyển Frame

    private static final int timeTransfer = 40;
    private boolean Explored;
    private boolean AllowPass = true;
    private BomberCharacter bomber;

    private int flameLength = 1;
    private List<BombAnimation> flameList = new ArrayList<>();

    private GameSound soundPlaceBomb = new GameSound(GameSound.PLACE_BOMB_SOUND);
    private GameSound soundExplode = new GameSound(GameSound.EXPLOSION_SOUND);

    /**
     * Create a bomb.
     */
    public Bomb(int x, int y, int flameLength, BomberCharacter bomber) {
        super(x, y, GameSprite.bomb.getFxImage());
        this.flameLength = flameLength;
        Explored = false;
        this.bomber = bomber;
        soundPlaceBomb.play_sound();
    }

    @Override
    public void update() {
        setAnimation();
        if (!Explored) {
            if (AllowPass) {
                int subX = bomber.getX() - getX();
                int subY = bomber.getY() - getY();
                if (subX < -20 || subX > 31 || subY > 33 || subY < -31) {
                    AllowPass = false;
                }
            }
            if (timeBeforeExplore-- > 0) {
                setImg(GameSprite.movingSprite(GameSprite.bomb, GameSprite.bomb1, GameSprite.bomb2, animation, timeTransfer)
                        .getFxImage());
            } else {
                Explored = true;
                explosion();
            }
        } else {

            if (timeFlame-- == 0) {
                setImg(null);
            }
        }
    }

    /**
     * Render bomb explosion.
     */
    private void explosion() {
        // init FlameList
        int x = getXUnit();
        int y = getYUnit();

        flameList.add(new BombAnimation(x, y, 4, false));// add center
        // truong hop bomber o tren qua bom
        GameEntity e = BomberManGame.getCanvasGame().getEntityInCoodinate(x, y);
        canPassThrough(e);

        // check left
        int il = 1;
        for (; il <= flameLength; il++) { // check tu 1 den FrameLen neu gap vat can break
            int xLeft = x - il;
            e = BomberManGame.getCanvasGame().getEntityInCoodinate(xLeft, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < il; i++) { // them flame tu 1 den do dai frame lon nhat co the
            if (i == il - 1) {
                flameList.add(new BombAnimation(x - i, y, 2, true));
            } else {
                flameList.add(new BombAnimation(x - i, y, 2, false));
            }
        }

        // check right
        int ir = 1;
        for (; ir <= flameLength; ir++) {
            int xRight = x + ir;
            e = BomberManGame.getCanvasGame().getEntityInCoodinate(xRight, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < ir; i++) {
            if (i == ir - 1) {
                flameList.add(new BombAnimation(x + i, y, 3, true));
            } else {
                flameList.add(new BombAnimation(x + i, y, 3, false));
            }
        }

        // check up
        int iu = 1;
        for (; iu <= flameLength; iu++) {
            int yUp = y - iu;
            e = BomberManGame.getCanvasGame().getEntityInCoodinate(x, yUp);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < iu; i++) {
            if (i == iu - 1) {
                flameList.add(new BombAnimation(x, y - i, 0, true));
            } else {
                flameList.add(new BombAnimation(x, y - i, 0, false));
            }
        }

        // check down
        int id = 1;
        for (; id <= flameLength; id++) {
            int yDown = y + id;
            e = BomberManGame.getCanvasGame().getEntityInCoodinate(x, yDown);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < id; i++) {
            if (i == id - 1) {
                flameList.add(new BombAnimation(x, y + i, 1, true));
            } else {
                flameList.add(new BombAnimation(x, y + i, 1, false));
            }
        }
        soundExplode.play_sound();
    }

    public void frameRender(GraphicsContext gc) {
        flameList.forEach(g -> g.render(gc));
    }

    public boolean isExplored() {
        return Explored;
    }

    /**
     * Determine what object can fire pass through.
     *
     * @param e Entity that we take in measure
     * @return True if fire can pass through and otherwise false
     */
//    public boolean canPassThrough(GameEntity e) {
//        int gotScore = 0;
//        if (e instanceof Brick brick) {
//            brick.setDestroyed(true);
//            gotScore = 5;
//            BomberManGame.setScore(BomberManGame.getScore() + gotScore);
//            return false;
//        }
//        if (e instanceof Wall || e instanceof Portal) {
//            return false;
//        }
//
//        if (e instanceof Monster monster) {
//            if (monster instanceof Dragon) {
//                if (monster.getHit() == 1) {
//                    monster.setAlive(false);
//                } else {
//                    monster.setHit(monster.getHit() + 1);
//                    monster.setVelocity(2);
//                    // System.out.println(monster.getHit());
//                }
//
//            } else if (monster instanceof Phoenix) {
//                if (monster.getHit() == 1) {
//                    monster.setAlive(false);
//                } else {
//                    monster.setHit(monster.getHit() + 1);
//                    monster.setVelocity(4);
//                    // System.out.println(monster.getHit());
//                }
//            } else {
//                monster.setAlive(false);
//            }
//            if (e instanceof Balloon)
//                gotScore = 10;
//            else if (e instanceof Oneal || e instanceof Doll)
//                gotScore = 20;
//            else if (e instanceof Minvo)
//                gotScore = 30;
//            else if (e instanceof Kondoria)
//                gotScore = 35;
//            else if (e instanceof Dragon)
//                gotScore = 50;
//            else if (e instanceof BatMonster)
//                gotScore = 40;
//            else if (e instanceof Phoenix)
//                gotScore = 60;
//            BomberManGame.setScore(BomberManGame.getScore() + gotScore);
//        }
//
//        if (e instanceof Bomb bomb) {
//            bomb.setTimeBeforeExplore(5);
//        }
//        return true;
//    }

    public List<BombAnimation> getFlameList() {
        return flameList;
    }

    public boolean isAllowPass() {
        return AllowPass;
    }

    public void setAllowPass(boolean allowPass) {
        this.AllowPass = allowPass;
    }

    public void setTimeBeforeExplore(int timeBeforeExplore) {
        this.timeBeforeExplore = timeBeforeExplore;
    }

}
