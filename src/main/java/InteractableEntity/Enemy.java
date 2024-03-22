package InteractableEntity;

public class Enemy extends InteractableEntity{
    protected int damage;
    protected int defence;
    protected int health;

    public Enemy(int dmg, int def, int hp, int xCoordinate, int yCoordinate){
        this.damage = dmg;
        this.defence = def;
        this.health = hp;
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    public int getHealth(){
        return health;
    }
    public void setDamage(int dmg){
        damage = dmg;
    }

    public void harmHealth(int dmg){
        health -= dmg;
    }

    public int getDefence(){
        return defence;
    }

    public int getDamage() {
        return damage;
    }

    public int getXCoordinate(){
        return this.x;
    }

    public int getYCoordinate(){
        return this.y;
    }
}
