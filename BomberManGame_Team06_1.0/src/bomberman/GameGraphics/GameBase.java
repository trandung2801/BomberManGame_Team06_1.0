package bomberman.GameGraphics;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import bomberman.Main.BomberManGame;
import bomberman.GameEntities.*;
import bomberman.GameEntities.GameBomb.Bomb;
import bomberman.GameEntities.GameCharacter.Bomber;
import bomberman.GameEntities.GameMonster.*;
import bomberman.GameEntities.GameMonster.Monster;
import bomberman.GameEntities.GameObject.*;
import bomberman.GameSound.GameSound;
import bomberman.GameTimers.GameTimers;

import java.util.List;


public class GameBase {
    public String[] paths = {"res/levels/Level1.txt", "res/levels/Level2.txt", "res/levels/Level3.txt",
    		"res/levels/Level4.txt", "res/levels/Level5.txt", "res/levels/Level6.txt", "res/levels/Level7.txt"};
    public int WIDTH, HEIGHT;
    public boolean pause = false;

    
    //list to render in canvas
    private List<Grass> grassList;
    private List<GameEntity> entityList; // list to check collision
    private List<Bomb> bombList;
    private List<Monster> monsterList;

    //bomber
    public static Bomber bomberman = new Bomber(1, 1, new KeyboardInput());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new KeyboardInput());
    private Bomber originBomber;

    //level
    public GameMap gameMap = new GameMap();
    private int currentLevel = 1;
    private int timeShowTransferLevel = 150;
    private boolean TransferLevel = false;

    private boolean gameOver = false;
    private boolean returnMainMenu = false;

    public static GameTimers timer = new GameTimers();

    //Game Sound
    public GameSound soundGame = new GameSound(GameSound.soundGame);
    public GameSound soundLoseGame = new GameSound(GameSound.soundLoseGame);
    public GameSound soundWinGame = new GameSound(GameSound.soundWinGame);
    public GameSound soundLevel_up = new GameSound(GameSound.soundTransferLevel);
    public GameSound soundDead = new GameSound(GameSound.soundDead);
    private boolean musicReseted = false;

    public GameBase() {
    }

    public void createNewGame() {
    	gameOver = false;
        currentLevel = 1;
        BomberManGame.lives = 3;
        BomberManGame.scores = 0;
        GameTimers.delay += 400;
        bomberman = new Bomber(1, 1, new KeyboardInput());
        createMap();
        updateEnemy(bomberman);
    }

    private void updateEnemy(Bomber bomberman) {
        for (Monster e : monsterList) {
            e.setBomber(bomberman);
            if (e instanceof Oneal) {
                ((Oneal) e).updateBomberForAI();
            }
            if (e instanceof Dragon) {
                ((Dragon) e).updateBomberForAI();
            }
        }
    }

    public void createMap() {
        if (currentLevel > paths.length) return;

        gameMap.createMapLevel(paths[currentLevel - 1], currentLevel-1);
        WIDTH = gameMap.getWidth();
        HEIGHT = gameMap.getHeight();

        this.setGrassList(gameMap.getGrassList());

        //phuc hoi cac thuoc tinh bomber cua level truoc va set vi tri moi
        originBomber = gameMap.getBomber();
        if (currentLevel > 1) {
            bomberman.restoreBomber(bomberInPreLevel);
        }
        bomberman.setX(originBomber.getX());
        bomberman.setY(originBomber.getY());
        bomberman.setAlive(true);

        entityList = gameMap.getCollidableEntities();
        monsterList = gameMap.getMonsterList();
        updateEnemy(bomberman);

        timer.setInterval(BomberManGame.timeLiving);
        timer.setTime();
    }


    public void update() {
        if (!TransferLevel) {
            updateAllEntities();

            GameTimers.delay += 400;
            if (timeShowTransferLevel == 150) soundGame.play();
            soundLevel_up.stop();

        } else {
            soundLevel_up.play();
            soundGame.stop();

        }

        if (bomberman.isAlive() == false) {
            soundGame.stop();
            GameTimers.delay += 400;

            bomberman.canPassBom = false;
            bomberman.canPassFlame = false;
            if (!gameOver) {
                BomberManGame.lives -= 1;
                bomberman = new Bomber(1, 1, BomberManGame.gameCanvas.getInput());
            }
            bomberInPreLevel.restoreBomber(originBomber);
            this.createMap();
        } else {
            if (!TransferLevel) soundGame.play();
        }

        if (BomberManGame.lives == 0) gameOver = true;

        if (bomberman.canPassBom == false) bomberman.timeToStopBomb = 0;
        if (bomberman.canPassFlame == false) bomberman.timeToStopFlame = 0;

    }

    public void updateAllEntities() {
        if (musicReseted == false) {
            soundWinGame.stop();
            soundLoseGame.stop();
            musicReseted = true;
        }

        bomberman.update();

        for (GameEntity e : monsterList) {
            if (e.getImg() == null) {
                monsterList.remove(e);
                break;
            } else {
                e.update();
            }
        }

        for (GameEntity e : entityList) {

            if (e.getImg() == null) { // if img == null, thi xoa entity do
                if (e instanceof Brick) {
                    if (((Brick) e).isBrickHasPortal()) {
                        this.addEntity(new Portal(e.getXUnit(), e.getYUnit()));
                    }
                    if (((Brick) e).isBrickHasItem()) {
                        this.addEntity(((Brick) e).getItem());
                    }
                }
                entityList.remove(e);
                break;
            } else {
                e.update();
            }
        }

        if (monsterList.size() == 0) {
            bomberman.setKillAllEnemies((true));
        }

        if (bomberman.isCollideWithAPortal()) {
            bomberInPreLevel.restoreBomber(bomberman);
            currentLevel++;
            TransferLevel = true;

            if (currentLevel > paths.length) {
                TransferLevel = false;
                gameOver = true;
                return;
            }
            this.createMap();
        }
    }

    boolean resetTimer = false;

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        if (TransferLevel == false) {
            if (resetTimer) {
                timer.setInterval(BomberManGame.timeLiving);
                resetTimer = false;
            }
            renderInfoOfCurrentLevel(gc);
            grassList.forEach(g -> g.render(gc));
            entityList.forEach(e -> e.render(gc));

            monsterList.forEach(e -> {
                e.render(gc);
                e.setBomber(bomberman);
                if (e instanceof Oneal) ((Oneal) e).updateBomberForAI();
                if (e instanceof Kondoria) ((Kondoria) e).updateBomberForAI();
            });
            bomberman.bombRender(gc);
            bomberman.render(gc);
        } else if (TransferLevel == true) {
            if (timeShowTransferLevel-- > 0) {
                renderTransferLevelScreen(gc);
            } else {
                TransferLevel = false;
                timeShowTransferLevel = 150;
            }
            resetTimer = true;
        }

        if (gameOver) {
            musicReseted = false;
            soundGame.stop();
            soundDead.stop();
            bomberman.setAlive(false);
            boolean win = BomberManGame.lives > 0;
            if (!win) soundLoseGame.play();
            else if (win) soundWinGame.play();

            if (timeShowTransferLevel-- > 0) { // show gameover animation
                if (!win) {
                    renderGameOverScreen(gc);
                } else if (win) {
                    renderVictoryScreen(gc);
                }
            } else { // return main menu
                TransferLevel = false;
                gameOver = false;
                timeShowTransferLevel = 150;
                returnMainMenu = true;
                soundLoseGame.stop();
                soundWinGame.stop();
            }
        }
    }

    public GameEntity getEntityOnCoodinate(int x, int y) {
        for (GameEntity e : entityList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        for (Monster e : monsterList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        bombList = bomberman.getBombList();
        for (Bomb b : bombList) {
            if (b.getXUnit() == x && b.getYUnit() == y) {
                return b;
            }
        }
        return null;
    }

    public void renderInfoOfCurrentLevel(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 416, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 15));
        gc.fillText("Time left: " + formatTime(timer.getInterval()), 10, 440);
        gc.fillText("Level: " + currentLevel, 200, 440);
        gc.fillText("Lives: " + BomberManGame.lives, 300, 440);
        gc.fillText("Scores: " + BomberManGame.scores, 400, 440);

        if (bomberman.canPassFlame) {

            if (bomberman.timeToStopFlame-- > 0 && bomberman.timeToStopFlame / 37 > 0) {
                gc.fillText("Pass Flame in: " + formatTime(bomberman.timeToStopFlame / 37), 700, 440);
            } else {
                bomberman.canPassFlame = false;
            }
        }
        if (bomberman.canPassBom) {
            if (bomberman.timeToStopBomb-- > 0 && bomberman.timeToStopBomb / 37 > 0) {
                gc.fillText("Pass Bomb in: " + formatTime(bomberman.timeToStopBomb/37), 500, 440);
            } else {
                bomberman.canPassBom = false;
            }

        }

    }
    
    public String formatTime(int time) {
    	String res = (time/60) + ":";
    	int second = time % 60;
    	if (second < 10) res += "0" + second;
    	else res += second;
    	return res;
    }

    public void renderTransferLevelScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(60));
        gc.fillText("Level: " + currentLevel, 400, 250);
    }

    public void renderGameOverScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(60));
        gc.fillText("You Lose!\nGame Over :(", 350, 200);
        gc.setFill(Color.ORANGE);
        gc.fillText("Your score: " + BomberManGame.scores, 350, 350);
    }

    public void renderVictoryScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(60));
        gc.fillText("You win!\nCongrats!! :)", 350, 200);
        gc.setFill(Color.ORANGE);
        gc.fillText("Your score: " + BomberManGame.scores, 350, 350);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    public void pauseSound() {
        if (soundGame.isRunning()) {
            soundGame.pause();
        }
        if (soundLevel_up.isRunning()) {
            soundLevel_up.pause();
        }
        if (soundWinGame.isRunning()) {
            soundWinGame.pause();
        }
        if (soundLoseGame.isRunning()) {
            soundLoseGame.pause();
        }
    }

    public void resumeSound() {
        if (!soundGame.isRunning() && soundGame.getStatus().equals("pause")) {
            soundGame.resume();
        }
        if (!soundLevel_up.isRunning() && soundLevel_up.getStatus().equals("pause")) {
            soundLevel_up.resume();
        }
        if (!soundWinGame.isRunning() && soundWinGame.getStatus().equals("pause")) {
            soundWinGame.resume();
        }
        if (!soundLoseGame.isRunning() && soundLoseGame.getStatus().equals("pause")) {
            soundLoseGame.resume();
        }
    }

    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void addEntity(GameEntity e) {
        entityList.add(e);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setTransferLevel(boolean transferLevel) {
        TransferLevel = transferLevel;
    }

    public boolean isReturnMainMenu() {
        return returnMainMenu;
    }

    public void setReturnMainMenu(boolean returnMainMenu) {
        this.returnMainMenu = returnMainMenu;
    }

}

