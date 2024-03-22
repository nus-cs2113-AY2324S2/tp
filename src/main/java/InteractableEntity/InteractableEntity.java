package InteractableEntity;

public abstract class InteractableEntity {
    protected int x;
    protected int y;

    protected String name;

    public abstract int getHealth();

    public abstract int getDefence();

    public abstract int getDamage();
}
