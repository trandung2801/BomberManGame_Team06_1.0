package bomberman.GameAi;

public class AILevel2 extends AI {

    @Override
    public int setDirect() {
        return generate.nextInt(4);
    }

    
}
