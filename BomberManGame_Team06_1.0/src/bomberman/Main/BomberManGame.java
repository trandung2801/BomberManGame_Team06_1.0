package bomberman.Main;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import bomberman.GameGraphics.GameCanvas;
import bomberman.GameController.MenuGame;
import bomberman.GameController.PauseGame;
import bomberman.GameController.GameTutorial;
import bomberman.GameSprite.GameSprite;
import bomberman.GameSound.GameSound;

public class BomberManGame extends Application {

    //Game console
    public static final int WIDTH = 32;
    public static final int HEIGHT = 18;

    //Game contructor
    public Group root;
    private GraphicsContext gc;
    public static GameCanvas gameCanvas;
    public Stage windowStage;
    public static boolean mute = false;

    //Game controller
    public MenuGame menuGame;
    public PauseGame pauseGame;
    public GameTutorial gameTutorial;

    public boolean showMenu = true;
    public boolean showTutorial = false;

    public GameSound menuSound = new GameSound(GameSound.soundMenu);

    //Game Status
    public static int timeLiving = 300;
    public static int scores = 0;
    public static int lives = 3;

    @Override
    public void start(Stage stage) {
        windowStage = stage;
        initGame();

        AnimationTimer timer = new AnimationTimer() {
            @SuppressWarnings("static-access")
            @Override
            public void handle(long l) {

                if (showMenu) {
                    menuGame.showMenuGame(gc);
                    menuGame.update();

                    if (menuGame.isMute() == false && !menuSound.isRunning()) {
                        menuSound.play();
                    } else if (menuGame.isMute()) menuSound.stop();

                    handleSelection();

                } else if (showTutorial) {
                    gameTutorial.showTutorialGame(gc);
                    gameTutorial.update();
                    if (gameTutorial.isReturnMenu()) {
                        showMenu = true;
                        showTutorial = false;
                        gameTutorial.setReturnMenu(false);
                    }
                } else if (gameCanvas.getInput().pause == true) { //if pause

                    gameCanvas.getGame().timer.setPlay(false);
                    if (!gameCanvas.getGame().isPause()) {
                        gameCanvas.getGame().pauseSound();
                        gameCanvas.getGame().setPause(true);
                    }

                    pauseGame.showPauseGame(gc);
                    pauseGame.update();

                    //handle selections in pause game
                    if (pauseGame.getFinalSelected() == 2) { //return main menu
                        gameCanvas.getInput().pause = false;
                        showMenu = true;
                    } else if (pauseGame.getFinalSelected() == 1) { //if resume game
                        gameCanvas.getInput().pause = false;
                        gameCanvas.getGame().resumeSound();
                        gameCanvas.getGame().timer.setPlay(true);
                    }
                    gameCanvas.getGame().setPause(false);
                    pauseGame.setFinalSelected(-1);
                } else {
                    menuSound.stop();
                    gameCanvas.update();
                    gameCanvas.render();
                    if (gameCanvas.returnMenu()) { //khi win or loose se return menu chinh
                        showMenu = true;
                        gameCanvas.setReturnMenu(false);
                    }
                }
            }
        };
        timer.start();
    }

    private void initGame() {
        // Create Canvas
        gameCanvas = new GameCanvas(GameSprite.SCALED_SIZE * WIDTH, GameSprite.SCALED_SIZE * HEIGHT);
        gc = gameCanvas.getGraphicsContext2D();

        // Create RootContainer
        root = new Group();
        root.getChildren().add(gameCanvas);

        // Create scene
        Scene scene = new Scene(root);

        // Add scene in stage
        windowStage.setScene(scene);
        windowStage.setTitle(GameCanvas.TITTLE);
        windowStage.setResizable(false);
        windowStage.show();

        //init menu game
        menuGame = new MenuGame(gameCanvas.getInput());
        pauseGame = new PauseGame(gameCanvas.getInput());
        gameTutorial = new GameTutorial(gameCanvas.getInput());
    }

    private void handleSelection() {
        //handle selections in menu
        if (menuGame.isQuit()) {
            menuSound.stop();
            windowStage.close();
        } else if (menuGame.isStartGame()) {
            //create new map level 1
            gameCanvas.getGame().createNewGame();

            mute = menuGame.isMute();
            menuGame.setStartGame(false);
            showMenu = false;
            gameCanvas.setTransferLevel(true);
            if (gameCanvas.getInput().pause == true) { // truong hop an 'p' trong menu
                gameCanvas.getInput().pause = false;
            }
        } else if (menuGame.isShowTutorial()) {
            showTutorial = true;
            showMenu = false;
            menuGame.setShowTutorial(false);
        }
    }
}