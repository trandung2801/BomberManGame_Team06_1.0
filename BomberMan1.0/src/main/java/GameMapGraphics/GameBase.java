package GameMapGraphics;  // class game

import GameCharacter.BomberCharacter;
import Main.BomberManGame;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class GameBase {
    private static String paths = "src/resources/Map/Map1.txt";
    
    private boolean pause_game = false;
    private boolean game_over = false;
    private boolean game_winner = false;
    private boolean returnMainMenu = false;
    /*
    private List<Grass> grassList;
    private List<Entity> entityList;
    private List<Monster> monsterList;
    private List<Bomb> bombList = new ArrayList<Bomb>(); */

    /* //bomber
    public static BomberCharacter = new BomberCharacter();
    */

    private GameMap gameMap = new GameMap();
    private int currentMap = 1;
    private boolean transferMap = false;

    public void createNewGame() {
        game_over = false;
        currentMap = 1;

        createMap();

    }

    public void createMap(){
        gameMap.CreateNewMap(paths, currentMap);
    }

    public void render(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


    }
}
