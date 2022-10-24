package GameSprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import GameSprite.GameSpriteSheet;

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

    public static final GameSprite grass = new GameSprite(DEFAULT_SIZE, 6, 0, 16, 16,GameSpriteSheet.tiles);
    public static final GameSprite brick = new GameSprite(DEFAULT_SIZE, 7, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite wall = new GameSprite(DEFAULT_SIZE, 5, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite portal = new GameSprite(DEFAULT_SIZE, 4, 0, 14, 14, GameSpriteSheet.tiles);

    /*
     * New sprites for every level
     */
    public static final GameSprite grass1 =
            new GameSprite(DEFAULT_SIZE, 0, 0, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall1 = new GameSprite(DEFAULT_SIZE, 1, 0, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick1 =
            new GameSprite(DEFAULT_SIZE, 2, 0, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass2 =
            new GameSprite(DEFAULT_SIZE, 0, 1, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall2 = new GameSprite(DEFAULT_SIZE, 1, 1, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick2 =
            new GameSprite(DEFAULT_SIZE, 2, 1, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass3 =
            new GameSprite(DEFAULT_SIZE, 0, 2, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall3 = new GameSprite(DEFAULT_SIZE, 1, 2, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick3 =
            new GameSprite(DEFAULT_SIZE, 2, 2, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass4 =
            new GameSprite(DEFAULT_SIZE, 0, 3, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall4 = new GameSprite(DEFAULT_SIZE, 1, 3, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick4 =
            new GameSprite(DEFAULT_SIZE, 2, 3, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass5 =
            new GameSprite(DEFAULT_SIZE, 0, 4, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall5 = new GameSprite(DEFAULT_SIZE, 1, 4, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick5 =
            new GameSprite(DEFAULT_SIZE, 2, 4, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass6 =
            new GameSprite(DEFAULT_SIZE, 0, 5, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall6 = new GameSprite(DEFAULT_SIZE, 1, 5, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick6 =
            new GameSprite(DEFAULT_SIZE, 2, 5, 32, 32, GameSpriteSheet.newtiles);

    public static final GameSprite grass7 =
            new GameSprite(DEFAULT_SIZE, 3, 0, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite wall7 = new GameSprite(DEFAULT_SIZE, 4, 0, 32, 32, GameSpriteSheet.newtiles);
    public static final GameSprite brick7 =
            new GameSprite(DEFAULT_SIZE, 5, 0, 32, 32, GameSpriteSheet.newtiles);

    /*
     * Bomb Sprites
     */
    public static final GameSprite bomb = new GameSprite(DEFAULT_SIZE, 0, 3, 15, 15, GameSpriteSheet.tiles);
    public static final GameSprite bomb1 = new GameSprite(DEFAULT_SIZE, 1, 3, 13, 15, GameSpriteSheet.tiles);
    public static final GameSprite bomb2 = new GameSprite(DEFAULT_SIZE, 2, 3, 12, 14, GameSpriteSheet.tiles);

    /*
     * FlameSegment Sprites
     */
    public static final GameSprite bombExploded =
            new GameSprite(DEFAULT_SIZE, 0, 4, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite bombExploded1 =
            new GameSprite(DEFAULT_SIZE, 0, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite bombExploded2 =
            new GameSprite(DEFAULT_SIZE, 0, 6, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionVertical =
            new GameSprite(DEFAULT_SIZE, 1, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVertical1 =
            new GameSprite(DEFAULT_SIZE, 2, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVertical2 =
            new GameSprite(DEFAULT_SIZE, 3, 5, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionHorizontal =
            new GameSprite(DEFAULT_SIZE, 1, 7, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontal1 =
            new GameSprite(DEFAULT_SIZE, 1, 8, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontal2 =
            new GameSprite(DEFAULT_SIZE, 1, 9, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionHorizontalLeftLast =
            new GameSprite(DEFAULT_SIZE, 0, 7, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontalLeftLast1 =
            new GameSprite(DEFAULT_SIZE, 0, 8, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontalLeftLast2 =
            new GameSprite(DEFAULT_SIZE, 0, 9, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionHorizontalRightLast =
            new GameSprite(DEFAULT_SIZE, 2, 7, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontalRightLast1 =
            new GameSprite(DEFAULT_SIZE, 2, 8, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionHorizontalRightLast2 =
            new GameSprite(DEFAULT_SIZE, 2, 9, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionVerticalTopLast =
            new GameSprite(DEFAULT_SIZE, 1, 4, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVerticalTopLast1 =
            new GameSprite(DEFAULT_SIZE, 2, 4, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVerticalTopLast2 =
            new GameSprite(DEFAULT_SIZE, 3, 4, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite explosionVerticalDownLast =
            new GameSprite(DEFAULT_SIZE, 1, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVerticalDownLast1 =
            new GameSprite(DEFAULT_SIZE, 2, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite explosionVerticalDownLast2 =
            new GameSprite(DEFAULT_SIZE, 3, 6, 16, 16, GameSpriteSheet.tiles);
    /*
     * Bomber Sprites
     */
    public static final GameSprite playerUp = new GameSprite(DEFAULT_SIZE, 0, 0, 12, 16, GameSpriteSheet.tiles);
    public static final GameSprite playerDown =
            new GameSprite(DEFAULT_SIZE, 2, 0, 12, 15, GameSpriteSheet.tiles);
    public static final GameSprite playerLeft =
            new GameSprite(DEFAULT_SIZE, 3, 0, 10, 15, GameSpriteSheet.tiles);
    public static final GameSprite playerRight =
            new GameSprite(DEFAULT_SIZE, 1, 0, 10, 16, GameSpriteSheet.tiles);

    public static final GameSprite playerUp1 =
            new GameSprite(DEFAULT_SIZE, 0, 1, 12, 16, GameSpriteSheet.tiles);
    public static final GameSprite playerUp2 =
            new GameSprite(DEFAULT_SIZE, 0, 2, 12, 15, GameSpriteSheet.tiles);

    public static final GameSprite playerDown1 =
            new GameSprite(DEFAULT_SIZE, 2, 1, 12, 15, GameSpriteSheet.tiles);
    public static final GameSprite playerDown2 =
            new GameSprite(DEFAULT_SIZE, 2, 2, 12, 16, GameSpriteSheet.tiles);

    public static final GameSprite playerLeft1 =
            new GameSprite(DEFAULT_SIZE, 3, 1, 11, 16, GameSpriteSheet.tiles);
    public static final GameSprite playerLeft2 =
            new GameSprite(DEFAULT_SIZE, 3, 2, 12, 16, GameSpriteSheet.tiles);

    public static final GameSprite playerRight1 =
            new GameSprite(DEFAULT_SIZE, 1, 1, 11, 16, GameSpriteSheet.tiles);
    public static final GameSprite playerRight2 =
            new GameSprite(DEFAULT_SIZE, 1, 2, 12, 16, GameSpriteSheet.tiles);

    public static final GameSprite playerDead1 =
            new GameSprite(DEFAULT_SIZE, 4, 2, 14, 16, GameSpriteSheet.tiles);
    public static final GameSprite playerDead2 =
            new GameSprite(DEFAULT_SIZE, 5, 2, 13, 15, GameSpriteSheet.tiles);
    public static final GameSprite playerDead3 =
            new GameSprite(DEFAULT_SIZE, 6, 2, 16, 16, GameSpriteSheet.tiles);

    /*
     * Brick FlameSegment
     */
    public static final GameSprite brickExploded =
            new GameSprite(DEFAULT_SIZE, 7, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite brickExploded1 =
            new GameSprite(DEFAULT_SIZE, 7, 2, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite brickExploded2 =
            new GameSprite(DEFAULT_SIZE, 7, 3, 16, 16, GameSpriteSheet.tiles);

    /*
     * Powerups
     */
    public static final GameSprite powerupBombs =
            new GameSprite(DEFAULT_SIZE, 0, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupFlames =
            new GameSprite(DEFAULT_SIZE, 1, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupSpeed =
            new GameSprite(DEFAULT_SIZE, 2, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupWallpass =
            new GameSprite(DEFAULT_SIZE, 3, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupDetonator =
            new GameSprite(DEFAULT_SIZE, 4, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupBombpass =
            new GameSprite(DEFAULT_SIZE, 5, 10, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite powerupFlamepass =
            new GameSprite(DEFAULT_SIZE, 6, 10, 16, 16, GameSpriteSheet.tiles);

    /*
     * Characters
     */
    // BALLOM
    public static final GameSprite balloomLeft1 =
            new GameSprite(DEFAULT_SIZE, 9, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite balloomLeft2 =
            new GameSprite(DEFAULT_SIZE, 9, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite balloomLeft3 =
            new GameSprite(DEFAULT_SIZE, 9, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite balloomRight1 =
            new GameSprite(DEFAULT_SIZE, 10, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite balloomRight2 =
            new GameSprite(DEFAULT_SIZE, 10, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite balloomRight3 =
            new GameSprite(DEFAULT_SIZE, 10, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite balloomDead =
            new GameSprite(DEFAULT_SIZE, 9, 3, 16, 16, GameSpriteSheet.tiles);

    // ONEAL
    public static final GameSprite onealLeft1 =
            new GameSprite(DEFAULT_SIZE, 11, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite onealLeft2 =
            new GameSprite(DEFAULT_SIZE, 11, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite onealLeft3 =
            new GameSprite(DEFAULT_SIZE, 11, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite onealRight1 =
            new GameSprite(DEFAULT_SIZE, 12, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite onealRight2 =
            new GameSprite(DEFAULT_SIZE, 12, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite onealRight3 =
            new GameSprite(DEFAULT_SIZE, 12, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite onealDead =
            new GameSprite(DEFAULT_SIZE, 11, 3, 16, 16, GameSpriteSheet.tiles);

    // Doll
    public static final GameSprite dollLeft1 =
            new GameSprite(DEFAULT_SIZE, 13, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite dollLeft2 =
            new GameSprite(DEFAULT_SIZE, 13, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite dollLeft3 =
            new GameSprite(DEFAULT_SIZE, 13, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite dollRight1 =
            new GameSprite(DEFAULT_SIZE, 14, 0, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite dollRight2 =
            new GameSprite(DEFAULT_SIZE, 14, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite dollRight3 =
            new GameSprite(DEFAULT_SIZE, 14, 2, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite dolllDead =
            new GameSprite(DEFAULT_SIZE, 13, 3, 16, 16, GameSpriteSheet.tiles);

    // Minvo
    public static final GameSprite minvoLeft1 =
            new GameSprite(DEFAULT_SIZE, 8, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite minvoLeft2 =
            new GameSprite(DEFAULT_SIZE, 8, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite minvoLeft3 =
            new GameSprite(DEFAULT_SIZE, 8, 7, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite minvoRight1 =
            new GameSprite(DEFAULT_SIZE, 9, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite minvoRight2 =
            new GameSprite(DEFAULT_SIZE, 9, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite minvoRight3 =
            new GameSprite(DEFAULT_SIZE, 9, 7, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite minvoDead =
            new GameSprite(DEFAULT_SIZE, 8, 8, 16, 16, GameSpriteSheet.tiles);

    // Kondoria
    public static final GameSprite kondoriaLeft1 =
            new GameSprite(DEFAULT_SIZE, 10, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite kondoriaLeft2 =
            new GameSprite(DEFAULT_SIZE, 10, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite kondoriaLeft3 =
            new GameSprite(DEFAULT_SIZE, 10, 7, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite kondoriaRight1 =
            new GameSprite(DEFAULT_SIZE, 11, 5, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite kondoriaRight2 =
            new GameSprite(DEFAULT_SIZE, 11, 6, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite kondoriaRight3 =
            new GameSprite(DEFAULT_SIZE, 11, 7, 16, 16, GameSpriteSheet.tiles);

    public static final GameSprite kondoriaDead =
            new GameSprite(DEFAULT_SIZE, 10, 8, 16, 16, GameSpriteSheet.tiles);

    // Phoenix monster.
    public static GameSprite phoenixDown1 = new GameSprite(DEFAULT_SIZE, 0, 0, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixDown2 = new GameSprite(DEFAULT_SIZE, 1, 0, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixDown3 = new GameSprite(DEFAULT_SIZE, 2, 0, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixDown4 = new GameSprite(DEFAULT_SIZE, 3, 0, 16, 16, GameSpriteSheet.phoenix);

    public static GameSprite phoenixUp1 = new GameSprite(DEFAULT_SIZE, 0, 3, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixUp2 = new GameSprite(DEFAULT_SIZE, 1, 3, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixUp3 = new GameSprite(DEFAULT_SIZE, 2, 3, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixUp4 = new GameSprite(DEFAULT_SIZE, 3, 3, 16, 16, GameSpriteSheet.phoenix);

    public static GameSprite phoenixRight1 =
            new GameSprite(DEFAULT_SIZE, 0, 2, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixRight2 =
            new GameSprite(DEFAULT_SIZE, 1, 2, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixRight3 =
            new GameSprite(DEFAULT_SIZE, 2, 2, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixRight4 =
            new GameSprite(DEFAULT_SIZE, 3, 2, 16, 16, GameSpriteSheet.phoenix);

    public static GameSprite phoenixLeft1 = new GameSprite(DEFAULT_SIZE, 0, 1, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixLeft2 = new GameSprite(DEFAULT_SIZE, 1, 1, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixLeft3 = new GameSprite(DEFAULT_SIZE, 2, 1, 16, 16, GameSpriteSheet.phoenix);
    public static GameSprite phoenixLeft4 = new GameSprite(DEFAULT_SIZE, 3, 1, 16, 16, GameSpriteSheet.phoenix);

    // dragon monster.
    public static GameSprite dragonDown1 = new GameSprite(DEFAULT_SIZE, 0, 0, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonDown2 = new GameSprite(DEFAULT_SIZE, 1, 0, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonDown3 = new GameSprite(DEFAULT_SIZE, 2, 0, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonDown4 = new GameSprite(DEFAULT_SIZE, 3, 0, 16, 16, GameSpriteSheet.dragon);

    public static GameSprite dragonUp1 = new GameSprite(DEFAULT_SIZE, 0, 3, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonUp2 = new GameSprite(DEFAULT_SIZE, 1, 3, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonUp3 = new GameSprite(DEFAULT_SIZE, 2, 3, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonUp4 = new GameSprite(DEFAULT_SIZE, 3, 3, 16, 16, GameSpriteSheet.dragon);

    public static GameSprite dragonRight1 = new GameSprite(DEFAULT_SIZE, 0, 2, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonRight2 = new GameSprite(DEFAULT_SIZE, 1, 2, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonRight3 = new GameSprite(DEFAULT_SIZE, 2, 2, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonRight4 = new GameSprite(DEFAULT_SIZE, 3, 2, 16, 16, GameSpriteSheet.dragon);

    public static GameSprite dragonLeft1 = new GameSprite(DEFAULT_SIZE, 0, 1, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonLeft2 = new GameSprite(DEFAULT_SIZE, 1, 1, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonLeft3 = new GameSprite(DEFAULT_SIZE, 2, 1, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite dragonLeft4 = new GameSprite(DEFAULT_SIZE, 3, 1, 16, 16, GameSpriteSheet.dragon);

    public static GameSprite fire1 = new GameSprite(DEFAULT_SIZE, 0, 0,  16, 16, GameSpriteSheet.dragon);
    public static GameSprite fire2 = new GameSprite(DEFAULT_SIZE, 1, 0, 16, 16, GameSpriteSheet.dragon);
    public static GameSprite fire3 = new GameSprite(DEFAULT_SIZE, 2, 0, 16, 16, GameSpriteSheet.dragon);

    // bat monster.
    public static GameSprite batDown1 = new GameSprite(DEFAULT_SIZE, 0, 0, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batDown2 = new GameSprite(DEFAULT_SIZE, 1, 0, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batDown3 = new GameSprite(DEFAULT_SIZE, 2, 0, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batDown4 = new GameSprite(DEFAULT_SIZE, 3, 0, 16, 16, GameSpriteSheet.batMonster);

    public static GameSprite batUp1 = new GameSprite(DEFAULT_SIZE, 0, 3, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batUp2 = new GameSprite(DEFAULT_SIZE, 1, 3, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batUp3 = new GameSprite(DEFAULT_SIZE, 2, 3, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batUp4 = new GameSprite(DEFAULT_SIZE, 3, 3, 16, 16, GameSpriteSheet.batMonster);

    public static GameSprite batRight1 = new GameSprite(DEFAULT_SIZE, 0, 1, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batRight2 = new GameSprite(DEFAULT_SIZE, 1, 1, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batRight3 = new GameSprite(DEFAULT_SIZE, 2, 1, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batRight4 = new GameSprite(DEFAULT_SIZE, 3, 1, 16, 16, GameSpriteSheet.batMonster);

    public static GameSprite batLeft1 = new GameSprite(DEFAULT_SIZE, 0, 2, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batLeft2 = new GameSprite(DEFAULT_SIZE, 1, 2, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batLeft3 = new GameSprite(DEFAULT_SIZE, 2, 2, 16, 16, GameSpriteSheet.batMonster);
    public static GameSprite batLeft4 = new GameSprite(DEFAULT_SIZE, 3, 2, 16, 16, GameSpriteSheet.batMonster);

    // ALL
    public static final GameSprite mobDead11 =
            new GameSprite(DEFAULT_SIZE, 15, 0,16, 16, GameSpriteSheet.tiles);
    public static final GameSprite mobDead12 =
            new GameSprite(DEFAULT_SIZE, 15, 1, 16, 16, GameSpriteSheet.tiles);
    public static final GameSprite mobDead13 =
            new GameSprite(DEFAULT_SIZE, 15, 2, 16, 16, GameSpriteSheet.tiles);

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

    /**
     * Animate the sprite.
     *
     * @param time Time of the animation
     * @return Final sprite at new position
     */
    public static final GameSprite  movingSprite(GameSprite  normal, GameSprite  x1, GameSprite  x2, int animate,
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

    public static final GameSprite movingSprite(GameSprite  x1, GameSprite  x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

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
