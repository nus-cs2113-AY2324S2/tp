package interactable.enemies;

import interactable.Enemy;
import static filereader.filepath.EnemiesDesignFilePath.GRYPHON_PATH;

public class Gryphon extends Enemy {

    public Gryphon(int dmg, int def, int hp, int xCoordinate, int yCoordinate, int exp, int money) {
        super(dmg, def, hp, xCoordinate, yCoordinate, exp, money);
        this.name = "Gryphon";
        this.filePath = GRYPHON_PATH;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
