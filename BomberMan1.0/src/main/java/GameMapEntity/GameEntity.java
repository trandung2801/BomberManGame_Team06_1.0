package GameMapEntity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import GameSprite.GameSprite;

public abstract class GameEntity {
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    /**
     * Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas.
     */
    public GameEntity(int xUnit, int yUnit, Image img) {
        x = xUnit * GameSprite.SCALED_SIZE;
        y = yUnit * GameSprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getXUnit() {
        return (x + GameSprite.DEFAULT_SIZE) / GameSprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return (y + GameSprite.DEFAULT_SIZE) / GameSprite.SCALED_SIZE;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public void removeFromGame() {
        this.setImg(null);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
