package interactable;

public abstract class InteractableEntity {
    protected int x;
    protected int y;
    protected int exp_dropped;
    protected int money_dropped;
    protected String name;

    public abstract int getHealth();

    public abstract int getDefence();

    public abstract int getDamage();

    public abstract String getName();

    public abstract String getFilePath();

    public int getExp_dropped(){
        return exp_dropped;
    }

    public int getMoney_dropped(){
        return money_dropped;
    }
    public int getHeight() {
        return 0;
    }

    public void setHeight() {}
}
