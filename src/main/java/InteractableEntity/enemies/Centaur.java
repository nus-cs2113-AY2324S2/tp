package InteractableEntity.enemies;

import InteractableEntity.Enemy;

public class Centaur extends Enemy {
    protected int height;

    public Centaur(int dmg, int def, int hp, int xCoordinate, int yCoordinate, int exp, int money) {
        super(dmg, def, hp, xCoordinate, yCoordinate, exp, money);
        this.height = 12;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
