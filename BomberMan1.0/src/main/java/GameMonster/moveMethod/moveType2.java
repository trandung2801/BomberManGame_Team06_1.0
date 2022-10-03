package GameMonster.moveMethod;

public abstract class moveType2 extends MonsterMovement{

    @Override
    public int setDirection(int d) {
        return randomdirect.nextInt(4);
    }
}
