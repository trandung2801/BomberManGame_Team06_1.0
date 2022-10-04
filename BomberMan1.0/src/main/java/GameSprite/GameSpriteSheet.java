package GameSprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class GameSpriteSheet {
    private String path;
    public int size;
    protected int[] pixels;

    public static final GameSpriteSheet tiles = new GameSpriteSheet("src/resources/textures/classic.png", 256);

    public GameSpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        pixels = new int[this.size * this.size];
        load();
    }

    private void load(){
        BufferedImage bufferedImage;
        try{
            URL a = GameSpriteSheet.class.getResource(path);
            bufferedImage = ImageIO.read(a);
            int w = bufferedImage.getWidth();
            int h = bufferedImage.getHeight();
            bufferedImage.getRGB(0, 0, w, h, pixels, 0,w);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
