package GameMapGraphics;

import GameCharacter.Bomber;
import GameMapEntity.GameEntity;
import GameMonster.Monster;
import GameMonster.BatMonster;
import GameMonster.Balloon;
import GameMonster.Doll;
import GameMonster.Dragon;
import GameMonster.Kondoria;
import GameMonster.Minvo;
import GameMonster.Oneal;
import GameMonster.Phoenix;
import GameMapEntity.EntityObject.Grass;
import GameMapEntity.EntityObject.Brick;
import GameMapEntity.EntityObject.Wall;
import GamePowerUpItems.Powerup;
import GamePowerUpItems.BombPassItem;
import GamePowerUpItems.BrickPassItem;
import GamePowerUpItems.FlamePassItem;
import GamePowerUpItems.PlusBombItem;
import GamePowerUpItems.PlusFlameItem;
import GamePowerUpItems.PlusLiveItem;
import GamePowerUpItems.PlusSpeedItem;
import GameMapGraphics.GameBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private List<GameEntity> collidableEntities = new ArrayList<>();
    private List<Grass> grassList = new ArrayList<>();
    private List<Monster> monsterList = new ArrayList<>();
    private Bomber bomber;

    public void CreateNewMap(String path, int CurrentMap){
        collidableEntities.clear();
        grassList.clear();
        monsterList.clear();
        try(FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader)) {
            String map_info = br.readLine();
            height = Integer.parseInt(map_info.substring(2, 4));
            width = Integer.parseInt((map_info.substring(5)));

            for (int i = 0; i < height; i++) {
                String temp = br.readLine();
                GameEntity object;
                for (int j = 0; j < temp.length(); j++) {
                    object = new Grass(j, i, CurrentMap);
                    grassList.add((Grass) object);

                    switch (temp.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, CurrentMap);
                            collidableEntities.add(object);
                            break;
                        case '*':
                            object = new Brick(j, i, CurrentMap);
                            collidableEntities.add(object);
                            break;
                        case 'x':
                            Brick object2 = new Brick(j, i, CurrentMap);
                            object2.setPortal(true);
                            collidableEntities.add(object2);
                            break;
                        case 'p':
                            bomber = new Bomber(j, i, new KeyboardInput());
                            break;
                        case '1':
                            object = new Balloon(j, i);
                            monsterList.add((Balloon) object);
                            break;
                        case '2':
                            object = new Oneal(j, i);
                            monsterList.add((Oneal) object);
                            break;
                        case '3':
                            object = new Doll(j, i);
                            monsterList.add((Doll) object);
                            break;
                        case '4':
                            object = new Minvo(j, i);
                            monsterList.add((Minvo) object);
                            break;
                        case '5':
                            object = new Kondoria(j, i);
                            monsterList.add((Kondoria) object);
                            break;
                        case '6':
                            object = new Dragon(j, i);
                            monsterList.add((Dragon) object);
                            break;
                        case '7':
                            object = new BatMonster(j, i);
                            monsterList.add((BatMonster) object);
                            break;
                        case '8':
                            object = new Phoenix(j, i);
                            monsterList.add((Phoenix) object);
                            break;
                        case 'b':
                            object = new Brick(j, i, CurrentMap);
                            Powerup pbi = new PlusBombItem(j, i);

                            ((Brick) object).setPowerUp(true, pbi);
                            collidableEntities.add(object);

                            pbi.setId("pbi");
                            break;
                        case 'f':
                            object = new Brick(j, i, CurrentMap);
                            Powerup pfi = new PlusFlameItem(j, i);

                            ((Brick) object).setPowerUp(true, pfi);
                            collidableEntities.add(object);

                            pfi.setId("pfi");
                            break;
                        case 's':
                            object = new Brick(j, i, CurrentMap);
                            Powerup psi = new PlusSpeedItem(j, i);

                            ((Brick) object).setPowerUp(true, psi);
                            collidableEntities.add(object);

                            psi.setId("psi");
                            break;
                        case 'B':
                            object = new Brick(j, i, CurrentMap);
                            Powerup bpi = new BombPassItem(j, i);

                            ((Brick) object).setPowerUp(true, bpi);
                            collidableEntities.add(object);

                            bpi.setId("bpi");
                            break;
                        case 'F':
                            object = new Brick(j, i, CurrentMap);
                            Powerup fpi = new FlamePassItem(j, i);

                            ((Brick) object).setPowerUp(true, fpi);
                            collidableEntities.add(object);

                            fpi.setId("fpi");
                            break;
                        case 'W':
                            object = new Brick(j, i, CurrentMap);
                            Powerup wpi = new BrickPassItem(j, i);

                            ((Brick) object).setPowerUp(true, wpi);
                            collidableEntities.add(object);

                            wpi.setId("wpi");
                            break;
                        case 'l':
                            object = new Brick(j, i, CurrentMap);
                            Powerup pli = new PlusLiveItem(j, i);

                            ((Brick) object).setPowerUp(true, pli);
                            collidableEntities.add(object);

                            pli.setId("pli");
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<GameEntity> getCollidableEntities() {
        return collidableEntities;
    }

  public List<Grass> getGrassList() {
    return grassList;
  }

  public Bomber getBomber() {
    return bomber;
  }

  public List<Monster> getEnemyList() {
    return monsterList;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
