package bomberman.GameSprite;

import javafx.scene.image.*;


/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class GameSprite {
	
	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private final int TRANSPARENT_COLOR = 0xffff00ff;
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private GameSpriteSheet gameSpriteSheet;

	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
	public static GameSprite grass = new GameSprite(DEFAULT_SIZE, 6, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite brick = new GameSprite(DEFAULT_SIZE, 7, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite wall = new GameSprite(DEFAULT_SIZE, 5, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite portal = new GameSprite(DEFAULT_SIZE, 4, 0, GameSpriteSheet.tiles, 14, 14);
	
	/*
	|--------------------------------------------------------------------------
	| Bomber Sprites
	|--------------------------------------------------------------------------
	 */
	public static GameSprite player_up = new GameSprite(DEFAULT_SIZE, 0, 0, GameSpriteSheet.tiles, 12, 16);
	public static GameSprite player_down = new GameSprite(DEFAULT_SIZE, 2, 0, GameSpriteSheet.tiles, 12, 15);
	public static GameSprite player_left = new GameSprite(DEFAULT_SIZE, 3, 0, GameSpriteSheet.tiles, 10, 15);
	public static GameSprite player_right = new GameSprite(DEFAULT_SIZE, 1, 0, GameSpriteSheet.tiles, 10, 16);
	
	public static GameSprite player_up_1 = new GameSprite(DEFAULT_SIZE, 0, 1, GameSpriteSheet.tiles, 12, 16);
	public static GameSprite player_up_2 = new GameSprite(DEFAULT_SIZE, 0, 2, GameSpriteSheet.tiles, 12, 15);
	
	public static GameSprite player_down_1 = new GameSprite(DEFAULT_SIZE, 2, 1, GameSpriteSheet.tiles, 12, 15);
	public static GameSprite player_down_2 = new GameSprite(DEFAULT_SIZE, 2, 2, GameSpriteSheet.tiles, 12, 16);
	
	public static GameSprite player_left_1 = new GameSprite(DEFAULT_SIZE, 3, 1, GameSpriteSheet.tiles, 11, 16);
	public static GameSprite player_left_2 = new GameSprite(DEFAULT_SIZE, 3, 2, GameSpriteSheet.tiles, 12 ,16);
	
	public static GameSprite player_right_1 = new GameSprite(DEFAULT_SIZE, 1, 1, GameSpriteSheet.tiles, 11, 16);
	public static GameSprite player_right_2 = new GameSprite(DEFAULT_SIZE, 1, 2, GameSpriteSheet.tiles, 12, 16);
	
	public static GameSprite player_dead1 = new GameSprite(DEFAULT_SIZE, 4, 2, GameSpriteSheet.tiles, 14, 16);
	public static GameSprite player_dead2 = new GameSprite(DEFAULT_SIZE, 5, 2, GameSpriteSheet.tiles, 13, 15);
	public static GameSprite player_dead3 = new GameSprite(DEFAULT_SIZE, 6, 2, GameSpriteSheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Character
	|--------------------------------------------------------------------------
	 */
	//BALLOM
	public static GameSprite balloom_left1 = new GameSprite(DEFAULT_SIZE, 9, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite balloom_left2 = new GameSprite(DEFAULT_SIZE, 9, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite balloom_left3 = new GameSprite(DEFAULT_SIZE, 9, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite balloom_right1 = new GameSprite(DEFAULT_SIZE, 10, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite balloom_right2 = new GameSprite(DEFAULT_SIZE, 10, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite balloom_right3 = new GameSprite(DEFAULT_SIZE, 10, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite balloom_dead = new GameSprite(DEFAULT_SIZE, 9, 3, GameSpriteSheet.tiles, 16,16);
	
	//ONEAL
	public static GameSprite oneal_left1 = new GameSprite(DEFAULT_SIZE, 11, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite oneal_left2 = new GameSprite(DEFAULT_SIZE, 11, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite oneal_left3 = new GameSprite(DEFAULT_SIZE, 11, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite oneal_right1 = new GameSprite(DEFAULT_SIZE, 12, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite oneal_right2 = new GameSprite(DEFAULT_SIZE, 12, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite oneal_right3 = new GameSprite(DEFAULT_SIZE, 12, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite oneal_dead = new GameSprite(DEFAULT_SIZE, 11, 3, GameSpriteSheet.tiles, 16,16);
	
	//Doll
	public static GameSprite doll_left1 = new GameSprite(DEFAULT_SIZE, 13, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite doll_left2 = new GameSprite(DEFAULT_SIZE, 13, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite doll_left3 = new GameSprite(DEFAULT_SIZE, 13, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite doll_right1 = new GameSprite(DEFAULT_SIZE, 14, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite doll_right2 = new GameSprite(DEFAULT_SIZE, 14, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite doll_right3 = new GameSprite(DEFAULT_SIZE, 14, 2, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite doll_dead = new GameSprite(DEFAULT_SIZE, 13, 3, GameSpriteSheet.tiles, 16,16);
	
	//Minvo
	public static GameSprite minvo_left1 = new GameSprite(DEFAULT_SIZE, 8, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite minvo_left2 = new GameSprite(DEFAULT_SIZE, 8, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite minvo_left3 = new GameSprite(DEFAULT_SIZE, 8, 7, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite minvo_right1 = new GameSprite(DEFAULT_SIZE, 9, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite minvo_right2 = new GameSprite(DEFAULT_SIZE, 9, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite minvo_right3 = new GameSprite(DEFAULT_SIZE, 9, 7, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite minvo_dead = new GameSprite(DEFAULT_SIZE, 8, 8, GameSpriteSheet.tiles, 16,16);
	
	//Kondoria
	public static GameSprite kondoria_left1 = new GameSprite(DEFAULT_SIZE, 10, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite kondoria_left2 = new GameSprite(DEFAULT_SIZE, 10, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite kondoria_left3 = new GameSprite(DEFAULT_SIZE, 10, 7, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite kondoria_right1 = new GameSprite(DEFAULT_SIZE, 11, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite kondoria_right2 = new GameSprite(DEFAULT_SIZE, 11, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite kondoria_right3 = new GameSprite(DEFAULT_SIZE, 11, 7, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite kondoria_dead = new GameSprite(DEFAULT_SIZE, 10, 8, GameSpriteSheet.tiles, 16,16);
	
	//ALL
	public static GameSprite mob_dead1 = new GameSprite(DEFAULT_SIZE, 15, 0, GameSpriteSheet.tiles, 16,16);
	public static GameSprite mob_dead2 = new GameSprite(DEFAULT_SIZE, 15, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite mob_dead3 = new GameSprite(DEFAULT_SIZE, 15, 2, GameSpriteSheet.tiles, 16,16);
	
	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static GameSprite bomb = new GameSprite(DEFAULT_SIZE, 0, 3, GameSpriteSheet.tiles, 16,16);
	public static GameSprite bomb_1 = new GameSprite(DEFAULT_SIZE, 1, 3, GameSpriteSheet.tiles, 16,16);
	public static GameSprite bomb_2 = new GameSprite(DEFAULT_SIZE, 2, 3, GameSpriteSheet.tiles, 16,16);
	
	/*
	|--------------------------------------------------------------------------
	| FlameSegment Sprites
	|--------------------------------------------------------------------------
	 */
	public static GameSprite bomb_exploded = new GameSprite(DEFAULT_SIZE, 0, 4, GameSpriteSheet.tiles, 16,16);
	public static GameSprite bomb_exploded1 = new GameSprite(DEFAULT_SIZE, 0, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite bomb_exploded2 = new GameSprite(DEFAULT_SIZE, 0, 6, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_vertical = new GameSprite(DEFAULT_SIZE, 1, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical1 = new GameSprite(DEFAULT_SIZE, 2, 5, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical2 = new GameSprite(DEFAULT_SIZE, 3, 5, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_horizontal = new GameSprite(DEFAULT_SIZE, 1, 7, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal1 = new GameSprite(DEFAULT_SIZE, 1, 8, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal2 = new GameSprite(DEFAULT_SIZE, 1, 9, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_horizontal_left_last = new GameSprite(DEFAULT_SIZE, 0, 7, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal_left_last1 = new GameSprite(DEFAULT_SIZE, 0, 8, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal_left_last2 = new GameSprite(DEFAULT_SIZE, 0, 9, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_horizontal_right_last = new GameSprite(DEFAULT_SIZE, 2, 7, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal_right_last1 = new GameSprite(DEFAULT_SIZE, 2, 8, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_horizontal_right_last2 = new GameSprite(DEFAULT_SIZE, 2, 9, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_vertical_top_last = new GameSprite(DEFAULT_SIZE, 1, 4, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical_top_last1 = new GameSprite(DEFAULT_SIZE, 2, 4, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical_top_last2 = new GameSprite(DEFAULT_SIZE, 3, 4, GameSpriteSheet.tiles, 16,16);
	
	public static GameSprite explosion_vertical_down_last = new GameSprite(DEFAULT_SIZE, 1, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical_down_last1 = new GameSprite(DEFAULT_SIZE, 2, 6, GameSpriteSheet.tiles, 16,16);
	public static GameSprite explosion_vertical_down_last2 = new GameSprite(DEFAULT_SIZE, 3, 6, GameSpriteSheet.tiles, 16,16);
	
	/*
	|--------------------------------------------------------------------------
	| Brick FlameSegment
	|--------------------------------------------------------------------------
	 */
	public static GameSprite brick_exploded = new GameSprite(DEFAULT_SIZE, 7, 1, GameSpriteSheet.tiles, 16,16);
	public static GameSprite brick_exploded1 = new GameSprite(DEFAULT_SIZE, 7, 2, GameSpriteSheet.tiles, 16,16);
	public static GameSprite brick_exploded2 = new GameSprite(DEFAULT_SIZE, 7, 3, GameSpriteSheet.tiles, 16,162);
	
	/*
	|--------------------------------------------------------------------------
	| Powerups
	|--------------------------------------------------------------------------
	 */
	public static GameSprite powerup_bombs = new GameSprite(DEFAULT_SIZE, 0, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_flames = new GameSprite(DEFAULT_SIZE, 1, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_speed = new GameSprite(DEFAULT_SIZE, 2, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_wallpass = new GameSprite(DEFAULT_SIZE, 3, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_detonator = new GameSprite(DEFAULT_SIZE, 4, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_bombpass = new GameSprite(DEFAULT_SIZE, 5, 10, GameSpriteSheet.tiles, 16,16);
	public static GameSprite powerup_flamepass = new GameSprite(DEFAULT_SIZE, 6, 10, GameSpriteSheet.tiles, 16,16);

	/*
	|--------------------------------------------------------------------------
	| Boss
	|--------------------------------------------------------------------------
	 */
	public static GameSprite boss_down1 = new GameSprite(DEFAULT_SIZE, 0, 0, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_down2 = new GameSprite(DEFAULT_SIZE, 1, 0, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_down3 = new GameSprite(DEFAULT_SIZE, 2, 0, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_down4 = new GameSprite(DEFAULT_SIZE, 3, 0, GameSpriteSheet.dragon, 16,16);

	public static GameSprite boss_up1 = new GameSprite(DEFAULT_SIZE, 0, 3, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_up2 = new GameSprite(DEFAULT_SIZE, 1, 3, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_up3 = new GameSprite(DEFAULT_SIZE, 2, 3, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_up4 = new GameSprite(DEFAULT_SIZE, 3, 3, GameSpriteSheet.dragon, 16,16);

	public static GameSprite boss_right1 = new GameSprite(DEFAULT_SIZE, 0, 2, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_right2 = new GameSprite(DEFAULT_SIZE, 1, 2, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_right3 = new GameSprite(DEFAULT_SIZE, 2, 2, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_right4 = new GameSprite(DEFAULT_SIZE, 3, 2, GameSpriteSheet.dragon, 16,16);

	public static GameSprite boss_left1 = new GameSprite(DEFAULT_SIZE, 0, 1, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_left2 = new GameSprite(DEFAULT_SIZE, 1, 1, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_left3 = new GameSprite(DEFAULT_SIZE, 2, 1, GameSpriteSheet.dragon, 16,16);
	public static GameSprite boss_left4 = new GameSprite(DEFAULT_SIZE, 3, 1, GameSpriteSheet.dragon, 16,16);

	/*
	// new tiles map
	 */
	public static GameSprite grass1 = new GameSprite(DEFAULT_SIZE, 0, 0, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall1 = new GameSprite(DEFAULT_SIZE, 1,0, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick1 = new GameSprite(DEFAULT_SIZE, 2, 0, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass2 = new GameSprite(DEFAULT_SIZE, 0, 1, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall2 = new GameSprite(DEFAULT_SIZE, 1,1, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick2 = new GameSprite(DEFAULT_SIZE, 2, 1, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass3 = new GameSprite(DEFAULT_SIZE, 0, 2, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall3 = new GameSprite(DEFAULT_SIZE, 1,2, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick3 = new GameSprite(DEFAULT_SIZE, 2, 2, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass4 = new GameSprite(DEFAULT_SIZE, 0, 3, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall4 = new GameSprite(DEFAULT_SIZE, 1,3, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick4 = new GameSprite(DEFAULT_SIZE, 2, 3, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass5 = new GameSprite(DEFAULT_SIZE, 0, 4, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall5 = new GameSprite(DEFAULT_SIZE, 1,4, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick5 = new GameSprite(DEFAULT_SIZE, 2, 4, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass6 = new GameSprite(DEFAULT_SIZE, 0, 5, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall6 = new GameSprite(DEFAULT_SIZE, 1,5, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick6 = new GameSprite(DEFAULT_SIZE, 2, 5, GameSpriteSheet.tiles1, 16,16);

	public static GameSprite grass7 = new GameSprite(DEFAULT_SIZE, 3, 0, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite wall7 = new GameSprite(DEFAULT_SIZE, 4,0, GameSpriteSheet.tiles1, 16,16);
	public static GameSprite brick7 = new GameSprite(DEFAULT_SIZE, 5, 0, GameSpriteSheet.tiles1, 16,16);

	public GameSprite(int size, int x, int y, GameSpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		gameSpriteSheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}
	
	public GameSprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = gameSpriteSheet.pixels[(x + _x) + (y + _y) * gameSpriteSheet.SIZE];
			}
		}
	}
	
	public static GameSprite movingSprite(GameSprite normal, GameSprite x1, GameSprite x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;
		
		if(calc < diff) {
			return normal;
		}
			
		if(calc < diff * 2) {
			return x1;
		}
			
		return x2;
	}

	public static GameSprite movingSpriteFourImg(GameSprite normal, GameSprite x1, GameSprite x2, GameSprite x3, int animate, int time) {
		int calc = animate % time;
		int diff = time / 4;

		if(calc < diff) {
			return normal;
		}

		if(calc < diff * 2) {
			return x1;
		}

		if (calc < diff * 3) {
			return x2;
		}

		return x3;
	}
	
	public static GameSprite movingSprite(GameSprite x1, GameSprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2; 
	}
	
	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
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

		WritableImage output = new WritableImage(
				W * S,
				H * S
		);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
}
