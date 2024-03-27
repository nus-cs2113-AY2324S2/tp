package interactable.enemies;

import interactable.Enemy;
import static filereader.filepath.EnemiesDesignFilePath.DRAGON_PATH;

public class Dragon extends Enemy {

    public Dragon(int dmg, int def, int hp, int xCoordinate, int yCoordinate, int exp, int money) {
        super(dmg, def, hp, xCoordinate, yCoordinate, exp, money);
        this.name = "Dragon";
        this.filePath = DRAGON_PATH;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}