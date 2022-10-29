package bomberman.GameTimers;

import bomberman.Main.BomberManGame;
import bomberman.GameGraphics.GameBase;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimers {
    private boolean play = false;
    Timer timers;
    private int interval;
    public static int delay = 1000;

    public int period = 1000;
    private int check; //kiem tra xem dem nguoc thoi gian cho doi tuong nao
    public void setTime() {
        timers = new Timer();
        setPlay(true);
        timers.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                setInterval();
            }

        }, delay, period);

    }
    
    public  int fixTime = 0;
    public final int setInterval() {
        if (interval <= 1) {
            timers.cancel();
            if (check == BomberManGame.timeLiving) GameBase.bomberman.setAlive(false);
            setPlay(false);

        }
        if (play) {
            --interval;
        }
        return interval;
    }
    public boolean isPlay() {
        return play;
    }
    public void setPlay(boolean play) {
        this.play = play;
    }


    public int getInterval() {
        return interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
        check = interval;
    }
}
