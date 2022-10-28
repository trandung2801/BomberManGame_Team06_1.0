package bomberman.GameSprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class GameSpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	public BufferedImage image;

	public static GameSpriteSheet tiles = new GameSpriteSheet("/Controller/classic.png", 256);
	public static GameSpriteSheet dragon = new GameSpriteSheet("/Controller/dragon.png", 64);
	public static GameSpriteSheet tiles1 = new GameSpriteSheet("/Controller/TilesMap.png", 96);
	public GameSpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL a = GameSpriteSheet.class.getResource(path);
			image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
