package GameSound;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import Main.BomberManGame;

public class GameSound {
    public static final String GAME_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String TRANSFER_LEVEL_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String EAT_POWERUP_SOUND ="src/resources/sounds//gameOver.wav";
    public static final String MENU_SOUND = "src/resources/sounds//Backgound.wav";
    public static final String EXPLOSION_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String DEAD_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String LOSE_GAME_SOUND ="src/resources/sounds//gameOver.wav";
    public static final String WIN_GAME_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String MOVING_SOUND = "src/resources/sounds//gameOver.wav";
    public static final String PLACE_BOMB_SOUND = "src/resources/sounds//gameOver.wav";

    private String path;
    private boolean isRunning = false;
    Clip clip;
    private long currentFrame;

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

    /**
     * Play sound.
     */
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
        statusGame = SoundsStatusGame.PLAY;
    }

    /**
     * Stop sound.
     */
    public void stop_sound(){
        clip.stop();
        isRunning = false;
        statusGame = SoundsStatusGame.STOP;
    }

    /**
     * Pause sound.
     */
    public void pause_sound(){
        clip.stop();
        isRunning = false;
        currentFrame = clip.getMicrosecondPosition();
        statusGame = SoundsStatusGame.PAUSE;
    }

    /**
     * Resume sound from pause.
     */
    public void resume_sound() {
        if (statusGame == SoundsStatusGame.PAUSE) {
            if (isRunning || BomberManGame.getMuted()) {
                return;
            }
            clip.close();
            File file = new File(path);
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(ais);
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                e.printStackTrace();
                System.out.println("Sound.Sound()");
            }
            if (path.equals(GAME_SOUND) || path.equals(MENU_SOUND)) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.setMicrosecondPosition(currentFrame);
            clip.start();
            isRunning = true;
            statusGame = SoundsStatusGame.PLAY;
        }
    }

    public boolean isRunning() { return  isRunning;}
    public void setRunning(boolean running) { isRunning = running;}
    public SoundsStatusGame getStatusGame() {return statusGame;}
}
