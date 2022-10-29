package bomberman.GameGraphics;

import bomberman.GameEntities.*;
import bomberman.GameEntities.GameCharacter.Bomber;
import bomberman.GameEntities.GameMonster.*;
import bomberman.GameEntities.GameObject.*;
import bomberman.GameItems.*;
import bomberman.GameItems.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width, height;

    private List<GameEntity> collidableEntities;
    private List<Grass> grassList;
    private List<Monster> monsterList;
    private Bomber bomber;

    public void createMapLevel(String path, int level) {
        collidableEntities = new ArrayList<>();
        grassList = new ArrayList<>();
        monsterList = new ArrayList<>();
        try {
            FileReader in = new FileReader(path);
            BufferedReader br = new BufferedReader(in);

            String infoOfMap;
            infoOfMap = br.readLine();
            height = Integer.parseInt(infoOfMap.substring(2, 4));
            width = Integer.parseInt(infoOfMap.substring(5));

            for (int i = 0; i < height; i++) {
                String temp = br.readLine();
                GameEntity object;
                for (int j = 0; j < temp.length(); j++) {
                    object = new Grass(j, i, level);
                    grassList.add((Grass) object);

                    switch (temp.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, level);
                            collidableEntities.add(object);
                            break;
                        case '*':
                            object = new Brick(j, i, level);
                            collidableEntities.add(object);
                            break;
                        case 'x':
                            Brick object2 = new Brick(j, i, level);
                            object2.setBrickHasPortal(true);
                            collidableEntities.add((Brick) object2);
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
                        case 'b':
                            object = new Brick(j, i, level);
                            Item pbi = new PlusBombItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pbi);
                            collidableEntities.add((Brick) object);

                            pbi.setId("pbi");
                            break;
                        case 'f':
                            object = new Brick(j, i, level);
                            Item pfi = new PlusFlameItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pfi);
                            collidableEntities.add((Brick) object);

                            pfi.setId("pfi");
                            break;
                        case 's':
                            object = new Brick(j, i, level);
                            Item psi = new PlusSpeedItem(j, i);

                            ((Brick) object).setBrickHasItem(true, psi);
                            collidableEntities.add((Brick) object);

                            psi.setId("psi");
                            break;
                        case 'B':
                            object = new Brick(j, i, level);
                            Item bpi = new BombPassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, bpi);
                            collidableEntities.add((Brick) object);

                            bpi.setId("bpi");
                            break;
                        case 'F':
                            object = new Brick(j, i, level);
                            Item fpi = new FlamePassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, fpi);
                            collidableEntities.add((Brick) object);

                            fpi.setId("fpi");
                            break;
                        case 'W':
                            object = new Brick(j, i, level);
                            Item wpi = new BrickPassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, wpi);
                            collidableEntities.add((Brick) object);

                            wpi.setId("wpi");
                            break;
                        case 'l':
                            object = new Brick(j, i, level);
                            Item pli = new PlusLiveItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pli);
                            collidableEntities.add((Brick) object);

                            pli.setId("pli");
                            break;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
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


    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
