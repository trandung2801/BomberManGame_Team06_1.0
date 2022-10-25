package bomberman.GameAi;


import bomberman.Main.BomberManGame;
import bomberman.GameEntities.GameEntity;
import bomberman.GameEntities.GameBomb.Bomb;
import bomberman.GameEntities.GameCharacter.Bomber;
import bomberman.GameEntities.GameMonster.Monster;
import bomberman.GameEntities.GameMonster.Kondoria;
import bomberman.GameEntities.GameObject.Brick;
import bomberman.GameEntities.GameObject.Portal;
import bomberman.GameEntities.GameObject.Wall;

public class AILevel3 extends AI {
    private Bomber bomberman;
    private Monster e;

    public AILevel3(Bomber b, Monster e) {
        this.bomberman = b;
        this.e = e;
    }

    public boolean recognizeBomberman() {
        //tra ve true, neu bomber va enemy cach nhau 3 don vi
        double distance = Math.pow(e.getXUnit() - bomberman.getXUnit(), 2) +
                Math.pow(e.getYUnit() - bomberman.getYUnit(), 2);

        if (Math.sqrt(distance) < 3 || e.getXUnit() == bomberman.getX() || e.getYUnit() == bomberman.getYUnit()) {
            return true;
        }
        return false;
    }

    @Override
    public int setDirect() {
    	int direct = -1;
        if (!recognizeBomberman() || !bomberman.isMoving()) {
            direct = randomDirect();
        } else {
            int randomCheckDir = generate.nextInt(2); //check Row or Col first?
            if (randomCheckDir == 0) {        //checkRow first
                int horizon = checkRow();
                if (horizon == -1) direct = checkCol();
                else direct = horizon;
            } else {
                int vertical = checkCol();
                if (vertical == -1) direct = checkRow();
                else direct = vertical;
                
            }
            
            if (isCollideWithObstacles(direct)) direct = randomDirect();
        }
        return direct;
    }

    private int checkRow() {
        if (e.getXUnit() > bomberman.getXUnit()) {
        	return 2;
        }
        else if (e.getXUnit() < bomberman.getXUnit()) {
        	return 3;
        }
        return -1; //equal
    }

    private int checkCol() {
        if (e.getYUnit() > bomberman.getYUnit()) {
        	return 0;
        }
        else if (e.getYUnit() < bomberman.getYUnit()) {
        	return 1;
        }
        return -1; //equal
    }

    public void updateBomber(Bomber bomberman) {
        this.bomberman = bomberman;
    }
    
    public int randomDirect() {
        boolean cantMove = true;
        int t = -1;
        while (cantMove) { // while util random direct can move
            t = generate.nextInt(4);
            
            if (isCollideWithObstacles(t)) {
                continue;
            } else {
                cantMove = false;
            }
        }
        return t;
    }
    
    public boolean isCollideWithObstacles(int direct) {
    	int x = e.getXUnit();
        int y = e.getYUnit();
    	switch (direct) {
	        case 0:
	            y -= 1;
	            break;
	        case 1:
	            y += 1;
	            break;
	        case 2:
	            x -= 1;
	            break;
	        case 3:
	            x += 1;
	            break;
    	}
	    GameEntity en = BomberManGame.canvas.getEntityInCoodinate(x, y);
	    if (en instanceof Brick && e instanceof Kondoria) return false;
	    if (en instanceof Wall || en instanceof Brick || en instanceof Portal || en instanceof Bomb) {
	        return true;
	    }
	    return false;
    }
    
}
