package Main;

import GameSprite.GameSprite;
import GameMapEntity.GameEntity;
import GameController.MenuGame;
import GameController.PauseGame;
import GameSound.GameSound;
import GameMapGraphics.GameCanvas;
import GameMapGraphics.HighScore;
import GameMapGraphics.GameBase;
import GameMapGraphics.GameMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;



public class BomberManGame extends Application {
    public static final int WIDTH = 31; //Width screen
    public static final int HEIGHT = 14;//Height screen

    private GraphicsContext gc;
    private static GameCanvas canvas;
    private MenuGame menuGame;
    private PauseGame pauseGame;

    private List<GameEntity> stillObject = new ArrayList<>();

    // Status Player
    public static final int timeLiving = 300;
    private static int score = 0;
    private static int lives = 3;

    public boolean showMenu = true;
    private static boolean mute = false;

    public GameSound menuSound = new GameSound(GameSound.MENU_SOUND);

    @Override
    public void start(Stage stage) {
        // Create CanvasGame
        canvas = new GameCanvas(GameSprite.SCALED_SIZE * WIDTH * 1.0, GameSprite.SCALED_SIZE * HEIGHT * 1.0);
        gc = canvas.getGraphicsContext2D();

        // Create RootContainer
        Group root = new Group();
        root.getChildren().add(canvas);

        stage.setResizable(false);
        stage.setTitle("Bomberman");
        Image icon = new Image("./resources/textures/icon.png");
        stage.getIcons().add(icon);

        // Create Scene
        Scene scene = new Scene(root);

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        // Add scene in stage
        stage.setScene(scene);
        stage.show();

        menuGame = new MenuGame(canvas.getInput());
        pauseGame = new PauseGame(canvas.getInput());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (showMenu) {
                    canvas.getGameBase().getTimers().setPlay(false);
                    menuGame.showMenu(gc);
                    menuGame.update();

                    if (!menuGame.isMuted() && !menuSound.isRunning()) {
                        menuSound.play_sound();
                    } else if (menuGame.isMuted()) {
                        menuSound.stop_sound();
                    }

                    if (menuGame.isQuit()) {
                        Platform.exit();
                        System.exit(0);
                    } else if (menuGame.showTutorial()) {
                        renderTutorial(gc);
                        if (canvas.getInput().backspace) {
                            showMenu = true;
                            // System.out.println("Backspace");
                            menuGame.setShowTutorial(false);
                        }

                    } else if (menuGame.showHighScore()) {
                        renderHighScore(gc);
                        if (canvas.getInput().backspace) {
                            showMenu = true;
                            // System.out.println("Backspace");
                            menuGame.setShowHighScore(false);
                        }
                    } else if (menuGame.isStartGame()) {
                        // create new map at level 1
                        canvas.getGameBase().createNewGame();
                        mute = menuGame.isMuted();
                        menuGame.setStartGame(false);
                        showMenu = false;
                        canvas.getGameBase().setTransferLevel(true);
                    }


                } else if (canvas.getInput().pause) {
                    canvas.getGameBase().getTimers().setPlay(false);
                    if (!canvas.getGameBase().isPause()) {
                        canvas.getGameBase().pauseSound();
                        canvas.getGameBase().setPause(true);
                    }
                    pauseGame.showPauseGame(gc);
                    pauseGame.update();

                    // handle selections while in game pause
                    if (pauseGame.getSelected() == 2) {
                        canvas.getInput().pause = false;
                        canvas.getGameBase().getTimers().setPlay(false);
                        canvas.getGameBase().pauseSound();
                        showMenu = true;
                    } else if (pauseGame.getSelected() == 1) {
                        canvas.getInput().pause = false;
                        canvas.getGameBase().resumeSound();
                        canvas.getGameBase().getTimers().setPlay(true);
                    }
                    canvas.getGameBase().setPause(false);
                    pauseGame.setSelected(-1);
                } else {
                    menuSound.stop_sound();
                    canvas.update();
                    canvas.render();
                    if (canvas.getGameBase().isReturnMainMenu()) {
                        showMenu = true;
                        canvas.getGameBase().setReturnMainMenu(false);
                    }
                    if (canvas.getGameBase().isOver() && canvas.getGameBase().getTimeShowGameOver() == 0) {
                        canvas.getGameBase().stopSound();
                        showMenu = true;
                        canvas.getGameBase().setTimeShowGameOver();
                        canvas.getGameBase().setReturnMainMenu(false);
                    }
                }
            }
        };
        timer.start();
    }


    /**
     * Create the map.
     */
    public void createMap() {
        canvas.getGameBase().createMap();
        stillObject.addAll(canvas.getGameBase().getGrassList());
        stillObject.addAll(canvas.getGameBase().getCollidableEntities());
    }

    /**
     * Render game tutorial.
     */
    public void renderTutorial(GraphicsContext gc) {
        FileInputStream file;
        try {
            file = new FileInputStream("src/resources/textures/Tutorial.png");
            final Image backgroundLevel = new Image(file);
            gc.setFill(Color.WHITE);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(backgroundLevel, 0, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("MenuGame.renderTutorial()");
        }
    }

    public void renderHighScore(GraphicsContext gc) {
        FileInputStream file;
        try {
            file = new FileInputStream("src/resources/textures/victory.png");
            final Image backgroundLevel = new Image(file);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(backgroundLevel, 0, 0);
            gc.setFont(Font.font("Impact", 60));
            gc.setFill(Color.RED);
            // int score = BombermanGame.getScore();
            gc.fillText("Record: " + HighScore.getHighScore(), 350, 440);
            gc.setFont(Font.font("Impact", 20));
            gc.setFill(Color.WHEAT);
            gc.fillText("Press Backspace to return to main menu", 10, 30);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("MenuGame.renderHighscore");
        }
    }


    public static int getScore() {
        return score;
    }

    public static void setScore(final int score) {
        BomberManGame.score = score;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(final int lives) {
        BomberManGame.lives = lives;
    }

    public static GameCanvas getGameCanvas() {
        return canvas;
    }

    public static boolean getMuted() {
        return mute;
    }

    public static void setMuted(boolean muted) {
        mute = muted;
    }

}
