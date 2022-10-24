package GameMapGraphics;

import GameCharacter.BomberCharacter;
import GameMapEntity.EntityObject.Grass;
import GameMapEntity.EntityObject.Brick;
import GameMapEntity.EntityObject.Portal;
import GameMapEntity.GameEntity;
import GameMonster.Monster;
import GameCharacter.Bomber;
import GameTime.Timers;
import Main.BomberManGame;
import GameSound.GameSound;
import GameSound.GameSound.SoundsStatusGame;
import GameBomb.Bomb;
import GameMapGraphics.GameMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameBase {
    private static String[] paths = {"src/resources/levels/Level1.txt",
            "src/resources/levels/Level2.txt", "src/resources/levels/Level3.txt",
            "src/resources/levels/Level4.txt", "src/resources/levels/Level5.txt",
            "src/resources/levels/Level6.txt", "src/resources/levels/Level7.txt"};
    private static String[] levelName = {"SCARY MEADOW", "NORTH POLE", "STEEL PRISON", "WHOLE CAKE",
            "MARINE FORCE", "DEADLY DESERT", "CRAZY HYDRA"};
    
    private boolean pause_game = false;
    private boolean game_over = false;
    private boolean game_winner = false;
    private int timeShowGameOver = 250;
    private boolean returnMainMenu = false;

    private List<Grass> grassList;
    private List<GameEntity> entityList;
    private List<Monster> monsterList;
    private List<Bomb> bombList = new ArrayList<>();

    // Bomber
    public static Bomber bomberman = new Bomber(1, 1, new KeyboardInput());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new KeyboardInput());
    public Bomber originBomber;

    // Level GameMap
    private GameMap gameMap = new GameMap();
    private int currentMap = 1;
    private int timeShowTransferMap = 140;
    private boolean transferMap = false;

    private Timers timers = new Timers();

    // sounds
    private GameSound soundGame = new GameSound(GameSound.GAME_SOUND);
    private GameSound soundLoseGame = new GameSound(GameSound.LOSE_GAME_SOUND);
    private GameSound soundWinGame = new GameSound(GameSound.WIN_GAME_SOUND);
    private GameSound soundLevelup = new GameSound(GameSound.TRANSFER_LEVEL_SOUND);

    /**
     * Create new game.
     */
    public void createNewGame() {
        game_over = false;
        currentMap = 1;
        BomberManGame.setLives(100);
        BomberManGame.setScore(0);
        Timers.setDelay(3000);
        bomberman = new Bomber(1, 1, new KeyboardInput());
        createMap();
        updateEnemy(bomberman);

    }

    /**
     * Create map for each level.
     */
    public void createMap(){
        if (currentMap <= paths.length) {
            timers = new Timers();

            Timers.setDelay(3000);

            gameMap.CreateNewMap(paths[currentMap - 1], currentMap - 1);

            originBomber = gameMap.getBomber();
            if (currentMap > 1) {
                // TODO: Thêm các trạng thái cũ vào bomber ở level mới
                bomberman.restoreBomber(bomberInPreLevel);
                // timers.stop();
            }
            bomberman.setX(originBomber.getX());
            bomberman.setY(originBomber.getY());
            bomberman.setAlive(true);

            grassList = gameMap.getGrassList();
            entityList = gameMap.getCollidableEntities();
            monsterList = gameMap.getEnemyList();

            updateEnemy(bomberman);

            timers.setInterval(BomberManGame.timeLiving);
            timers.setTime();
        }
    }

    public void update() {
        if (!transferMap) {
            updateAllEntities();
            Timers.setDelay(Timers.getDelay());
            if (timeShowTransferMap == 140) {
                soundGame.play_sound();
            }
            soundLevelup.stop_sound();
            if (!bomberman.isAlive()) {
                bomberman.setX(originBomber.getX());
                bomberman.setY(originBomber.getY());
                bomberman.setAlive(true);
            }
        } else {
            soundGame.stop_sound();
            soundLevelup.play_sound();
        }
    }

    /**
     * Draw all entities.
     */
    public void updateAllEntities() {
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
                if (e instanceof Brick brick) {
                    if (brick.hasPortal()) {
                        this.addEntity(new Portal(e.getXUnit(), e.getYUnit()));
                    }
                    if (brick.hasPowerup()) {
                        this.addEntity(brick.getPowerup());
                    }
                }
                entityList.remove(e);
                break;
            } else {
                e.update();
            }
        }

        if (monsterList.isEmpty()) {
            bomberman.setKillAllEnemies((true));
        }

        if (bomberman.isCollideWithAPortal()) {
            bomberInPreLevel.restoreBomber(bomberman);
            currentMap++;
            // timers.stop();
            timers.setPlay(false);
            transferMap = true;

            if (currentMap > paths.length) {
                transferMap = false;
                game_over = true;
                return;
            }
            this.createMap();
        }
        if (BomberManGame.getLives() == 0) {
            game_over = true;
            // System.out.println("get live");
        }
    }

    private void updateEnemy(Bomber bomberman) {
        for (Monster e : monsterList) {
            e.setBomber(bomberman);
        }
    }

    /**
     * Get enitites coordinate for collsion check.
     *
     * @param x position x
     * @param y position x
     * @return
     */
    public GameEntity getEntityInCoodinate(int x, int y) {
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

    /**
     * Get grass list.
     */
    public GameEntity getGrass(int x, int y) {
        for (GameEntity e : grassList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        return null;
    }

    public List<GameEntity> getCollidableEntities() {
        return entityList;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    /**
     * Render map.
     */
    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (!transferMap) {
            renderInfoOfCurrentLevel(gc);
            grassList.forEach(e -> e.render(gc));
            entityList.forEach(e -> e.render(gc));
            monsterList.forEach(e -> e.render(gc));
            bomberman.bombRender(gc);
            bomberman.render(gc);
        } else {
            if (timeShowTransferMap-- > 0) {
                renderTransferLevelScreen(gc);
            } else {
                transferMap = false;
                timeShowTransferMap = 140;
            }
        }
        if (game_over) {
            soundGame.stop_sound();
            if (timeShowGameOver-- > 0) {
                if (BomberManGame.getLives() > 0) {
                    soundWinGame.play_sound();
                } else {
                    soundLoseGame.play_sound();
                }
                renderGameOver(gc);
            }
        }
    }

    public void renderTransferLevelScreen(GraphicsContext gc) {
        FileInputStream file;
        try {
            file = new FileInputStream("src/resources/textures/levelbackground.png");
            final Image backgroundLevel = new Image(file);
            gc.setFill(Color.WHITE);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(backgroundLevel, 0, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Game.renderTransferLevelScreen()");
        }
        gc.setFont(Font.font("Impact", 60));
        gc.fillText("Level: " + currentMap, 250, 200);
        gc.setFill(Color.RED);
        gc.fillText(levelName[currentMap - 1], 250, 300);
    }

    /**
     * Render living time, score and buffs of player.
     */
    public void renderInfoOfCurrentLevel(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 416, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 15));
        gc.fillText("Time left: " + formatTime(timers.getInterval()), 10, 440);
        gc.fillText("Level: " + currentMap, 200, 440);
        gc.fillText("Lives: " + BomberManGame.getLives(), 300, 440);
        gc.fillText("Scores: " + BomberManGame.getScore(), 400, 440);
        if (bomberman.canPassFlame) {

            if (bomberman.timeToStopFlame-- > 0 && bomberman.timeToStopFlame / 37 > 0) {
                gc.fillText("Pass Flame in: " + formatTime(bomberman.timeToStopFlame / 37), 700, 440);
            } else {
                bomberman.canPassFlame = false;
            }
        }
        if (bomberman.canPassBom) {
            if (bomberman.timeToStopBomb-- > 0 && bomberman.timeToStopBomb / 37 > 0) {
                gc.fillText("Pass Bomb in: " + formatTime(bomberman.timeToStopBomb / 37), 500, 440);
            } else {
                bomberman.canPassBom = false;
            }

        }
    }

    /**
     * Format the displayed time.
     *
     * @param time time argument
     * @return a String to display time in format
     */
    public String formatTime(int time) {
        String res = (time / 60) + ":";
        int second = time % 60;
        if (second < 10) {
            res += "0" + second;
        } else {
            res += second;
        }
        return res;
    }

    public void renderGameOver(GraphicsContext gc) {
        FileInputStream file;
        if (BomberManGame.getLives() <= 0) {
            try {
                file = new FileInputStream("src/resources/textures/gameover.png");
                final Image backgroundLevel = new Image(file);
                gc.setFill(Color.WHITE);
                gc.clearRect(0, 0, 992, 448);
                gc.drawImage(backgroundLevel, 0, 0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Game.renderGameOver()");
            }
            gc.setFont(Font.font("Impact", 60));
            int score = BomberManGame.getScore();
            gc.fillText("Your score: " + score, 200, 300);
            HighScore.setNewHighScore(score);
            gc.setFill(Color.RED);
        }

        if (BomberManGame.getLives() > 0) {
            try {
                file = new FileInputStream("src/resources/textures/victory.png");
                final Image backgroundLevel = new Image(file);
                gc.setFill(Color.WHITE);
                gc.clearRect(0, 0, 992, 448);
                gc.drawImage(backgroundLevel, 0, 0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Game.renderGameOver()");
            }
            gc.setFont(Font.font("Impact", 60));
            gc.setFill(Color.RED);
            int score = BomberManGame.getScore();
            HighScore.setNewHighScore(score);
            gc.fillText("New record: " + score, 350, 440);
        }
        // gc.setFont(Font.font("Impact", 60));
        // String result = (BombermanGame.getLives() > 0) ? "You win" : "You lose";
        // gc.fillText("Game Over!\n" + result, 250, 200);
        // gc.setFill(Color.RED);
    }

    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void addEntity(GameEntity e) {
        entityList.add(e);
    }

    public void setTransferLevel(boolean transferLevel) {
        this.transferMap = transferLevel;
    }


    public boolean isReturnMainMenu() {
        return returnMainMenu;
    }

    public void setReturnMainMenu(boolean returnMainMenu) {
        this.returnMainMenu = returnMainMenu;
    }

    public void setPause(boolean pause) {
        this.pause_game = pause;
    }

    public boolean isPause() {
        return pause_game;
    }

    public void pauseSound() {
        if (soundGame.isRunning()) {
            soundGame.pause_sound();
        }
        if (soundLevelup.isRunning()) {
            soundLevelup.pause_sound();
        }
        if (soundWinGame.isRunning()) {
            soundWinGame.pause_sound();
        }
        if (soundLoseGame.isRunning()) {
            soundLoseGame.pause_sound();
        }
    }

    public void stopSound() {
        if (soundGame.isRunning()) {
            soundGame.stop_sound();
        }
        if (soundLevelup.isRunning()) {
            soundLevelup.stop_sound();
        }
        if (soundWinGame.isRunning()) {
            soundWinGame.stop_sound();
        }
        if (soundLoseGame.isRunning()) {
            soundLoseGame.stop_sound();
        }
    }

    public void resumeSound() {
        if (soundWinGame.getStatusGame() == SoundsStatusGame.PAUSE) {
            if (!soundGame.isRunning()) {
                soundGame.resume_sound();
            }
            if (!soundLevelup.isRunning()) {
                soundLevelup.resume_sound();
            }
            if (!soundWinGame.isRunning()) {
                soundWinGame.resume_sound();
            }
            if (!soundLoseGame.isRunning()) {
                soundLoseGame.resume_sound();
            }
        }
    }

    public Timers getTimers() {
        return timers;
    }

    public boolean isOver() {
        return game_over;
    }

    public int getTimeShowTransferLevel() {
        return timeShowTransferMap;
    }

    public int getTimeShowGameOver() {
        return timeShowGameOver;
    }

    public void setTimeShowGameOver() {
        timeShowGameOver = 250;
    }
}
