package GameSprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class GameSprite {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    public static final int TRANSPARENT_COLOR = 0xffff00ff;
    public int size;
    private int x;
    private int y;
    private int[] pixels;
    protected int realWidth;
    protected int realHeight;
    private GameSpriteSheet gameSpriteSheet;

    //public static final GameSprite grass = new GameSprite(DEFAULT_SIZE, 6, 0, GameSpriteSheet.tiles, 16, 16);
    public static final GameSprite wall = new GameSprite(DEFAULT_SIZE, 5, 0, 16, 16,  GameSpriteSheet.tiles );

    public GameSprite(int size, int x, int y, int realWidth, int realHeight, GameSpriteSheet gameSpriteSheet) {
        this.size = size;
        this.x = x;
        this.y = y;
        pixels = new int[this.size * this.size];
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.gameSpriteSheet = gameSpriteSheet;
        load();
    }

    public GameSprite(int size, int color) {
        this.size = size;
        pixels = new int[this.size * this.size];
        setColor(color);
    }

    private void load() {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                pixels[j+i*size] = gameSpriteSheet.pixels[(j + x) + (i +y)* gameSpriteSheet.size];
            }
        }
    }

    private void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    /*
    public static final Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate,
            int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static final Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }
     */

    public int getSize() {
        return size;
    }

    public int getPixels(int i) {
        return pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(size, size);
        PixelWriter pw = wr.getPixelWriter();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (pixels[i + j * size] == TRANSPARENT_COLOR) {
                    pw.setArgb(i, j, 0);
                } else {
                    pw.setArgb(i, j, pixels[i + j * size]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(W * S, H * S);

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                final int argb = reader.getArgb(j, i);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(j * S + dx, i * S + dy, argb);
                    }
                }
            }
        }
        return output;
    }
}
