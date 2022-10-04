package GameMapGraphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    //private List<Entity> collidableEntities = new ArrayList<>();


    public void CreateNewMap(String path, int CurrentMap){
        try(FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader)) {
            String map_info = br.readLine();
            height = Integer.parseInt(map_info.substring(2, 4));
            width = Integer.parseInt((map_info.substring(5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* public List<Entity> getCollidableEntities() {
    return collidableEntities;
  }

  public List<Grass> getGrassList() {
    return grasses;
  }

  public Bomber getBomber() {
    return bomber;
  }

  public List<Monster> getEnemyList() {
    return monsters;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}*/
}
