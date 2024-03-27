package interactable.enemies;

import interactable.Enemy;
import static filereader.filepath.EnemiesDesignFilePath.DEMON_PATH;

public class Demon extends Enemy {

    public Demon(int dmg, int def, int hp, int xCoordinate, int yCoordinate, int exp, int money) {
        super(dmg, def, hp, xCoordinate, yCoordinate, exp, money);
        this.name = "Demon";
        this.filePath = DEMON_PATH;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
