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

    //frame game
    public static int WIDTH = 31;
    public static int HEIGHT = 14;
    public Group root;
    private GraphicsContext gc;
    public static GameCanvas canvas;
    public static Stage window;
    public MenuGame menuGame;
    public PauseGame pauseGame;
    public GameTutorial tutorialGame;

    //thong so game
    public static int timeLiving = 300;
    public static int scores = 0;
    public static int lives = 3;

    public boolean showMenu = true;
    public static boolean mute = false;
    public boolean showTutorial = false;

    public GameSound menuSound = new GameSound(GameSound.soundMenu);

    @Override
    public void start(Stage stage) {
        window = stage;
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
                    tutorialGame.showTutorialGame(gc);
                    tutorialGame.update();
                    if (tutorialGame.isReturnMenu()) {
                        showMenu = true;
                        showTutorial = false;
                        tutorialGame.setReturnMenu(false);
                    }
                } else if (canvas.getInput().pause == true) { //if pause

                    canvas.getGame().timer.setPlay(false);
                    if (!canvas.getGame().isPause()) {
                        canvas.getGame().pauseSound();
                        canvas.getGame().setPause(true);
                    }

                    pauseGame.showPauseGame(gc);
                    pauseGame.update();

                    //handle selections in pause game
                    if (pauseGame.getFinalSelected() == 2) { //return main menu
                        canvas.getInput().pause = false;
                        showMenu = true;
                    } else if (pauseGame.getFinalSelected() == 1) { //if resume game
                        canvas.getInput().pause = false;
                        canvas.getGame().resumeSound();
                        canvas.getGame().timer.setPlay(true);
                    }
                    canvas.getGame().setPause(false);
                    pauseGame.setFinalSelected(-1);
                } else {
                    menuSound.stop();
                    canvas.update();
                    canvas.render();
                    if (canvas.returnMenu()) { //khi win or loose se return menu chinh
                        showMenu = true;
                        canvas.setReturnMenu(false);
                    }
                }
            }
        };
        timer.start();
    }

    private void initGame() {
        // Tao Canvas
        canvas = new GameCanvas(GameSprite.SCALED_SIZE * WIDTH, GameSprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        window.setScene(scene);
        window.setTitle(GameCanvas.TITTLE);
        window.setResizable(false);
        window.show();

        //init menu game
        menuGame = new MenuGame(canvas.getInput());
        pauseGame = new PauseGame(canvas.getInput());
        tutorialGame = new GameTutorial(canvas.getInput());
    }

    private void handleSelection() {
        //handle selections in menu
        if (menuGame.isQuit()) {
            menuSound.stop();
            window.close();
        } else if (menuGame.isStartGame()) {
            //create new map level 1
            canvas.getGame().createNewGame();

            mute = menuGame.isMute();
            menuGame.setStartGame(false);
            showMenu = false;
            canvas.setTransferLevel(true);
            if (canvas.getInput().pause == true) { // truong hop an 'p' trong menu
                canvas.getInput().pause = false;
            }
        } else if (menuGame.isShowTutorial()) {
            showTutorial = true;
            showMenu = false;
            menuGame.setShowTutorial(false);
        }
    }
}