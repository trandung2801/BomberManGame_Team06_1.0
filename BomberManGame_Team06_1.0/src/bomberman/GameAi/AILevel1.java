package bomberman.GameAi;

import bomberman.GameEntities.GameMonster.Monster;

public class AILevel1 extends AI {
	private Monster enemy;
	
	public AILevel1(Monster e) {
		this.enemy = e;
		enemy.setDirection(2);
	}
	
    @Override
    public int setDirect() {
    	return 5 - enemy.getDirection() ;
    }
    
	public Monster getEnemy() {
		return enemy;
	}
	public void setEnemy(Monster enemy) {
		this.enemy = enemy;
	}
}
