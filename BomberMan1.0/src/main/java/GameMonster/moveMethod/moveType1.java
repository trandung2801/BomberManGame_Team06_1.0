package GameMonster.moveMethod;

public class moveType1 extends MonsterMovement{

    /**
     * @param d
     * @return
     */
    @Override
    public int setDirection(int d) {
        if (d == 2){
            return 3;
        } else {
            return 2;
        }
    }
}
