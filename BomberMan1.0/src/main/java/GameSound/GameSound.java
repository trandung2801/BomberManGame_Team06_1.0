package GameSound;

import Main.BomberManGame;
import javafx.scene.media.AudioClip;
import javafx.scene.media.AudioSpectrumListener;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameSound {
    public static final String GAME_SOUND = "src/resources/sounds/inGame.wav";
    public static final String TRANSFER_LEVEL_SOUND = "src/resources/sounds/inGame.wav";
    public static final String EAT_POWERUP_SOUND = "src/resources/sounds/inGame.wav";
    public static final String MENU_SOUND = "src/resources/sounds/inGame.wav";
    public static final String EXPLOSION_SOUND = "src/resources/sounds/inGame.wav";
    public static final String DEAD_SOUND = "src/resources/sounds/inGame.wav";
    public static final String LOSE_GAME_SOUND = "src/resources/sounds/inGame.wav";
    public static final String WIN_GAME_SOUND = "src/resources/sounds/inGame.wav";
    public static final String MOVING_SOUND = "src/resources/sounds/inGame.wav";
    public static final String PLACE_BOMB_SOUND = "src/resources/sounds/inGame.wav";

    private String path;
    private boolean isRunning = false;
    Clip clip;

    public enum SoundsStatusGame{
        PLAY,
        STOP,
        PAUSE
    }

    private SoundsStatusGame statusGame;

    public GameSound(String path) {
        this.path = path;
        File file = new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Sound.Sound()");
        }
    }

    public void play_sound() {
        if (isRunning || BomberManGame.getMuted()) {
            return;
        }
        clip.setFramePosition(0);
        if (path.equals(GAME_SOUND) || path.equals(MENU_SOUND)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        if (path.equals(EXPLOSION_SOUND)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }
        clip.start();
        isRunning = true;
        status = SoundsStatusGame.PLAY;     // status ở đâu trong file gốc ??
    }

    public void stop_sound(){
        clip.stop();
        isRunning = false;
        statusGame = SoundsStatusGame.STOP;
    }

    public void pause_sound(){
        clip.stop();
        isRunning = false;
        statusGame = SoundsStatusGame.PAUSE;
    }

    public boolean isRunning() { return  isRunning;}
    public void setRunning(boolean running) { isRunning = running;}
    public SoundsStatusGame getStatusGame() {return statusGame;}
}
