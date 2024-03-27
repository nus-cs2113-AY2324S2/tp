package interactable.enemies;

import interactable.Enemy;
import static filereader.filepath.EnemiesDesignFilePath.GOBLIN_PATH;

public class Goblin extends Enemy {

    public Goblin(int dmg, int def, int hp, int xCoordinate, int yCoordinate, int exp, int money) {
        super(dmg, def, hp, xCoordinate, yCoordinate, exp, money);
        this.name = "Goblin";
        this.filePath = GOBLIN_PATH;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
