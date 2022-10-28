package bomberman.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import bomberman.GameSprite.GameSprite;

public abstract class GameEntity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public GameEntity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * GameSprite.SCALED_SIZE;
        this.y = yUnit * GameSprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }


    public boolean checkCollision(GameEntity e1, GameEntity e2) {

        if (e1.getX() == e2.getX() && e1.getY() == e2.getY()) {
            return true;
        }
        return false;

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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public abstract void update();

    //xUnit, yUnit is coodinate calculated by tiles
    public int getXUnit() {
        return (int) (x + GameSprite.DEFAULT_SIZE) / GameSprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return (int) (y + GameSprite.DEFAULT_SIZE) / GameSprite.SCALED_SIZE;
    }
}
