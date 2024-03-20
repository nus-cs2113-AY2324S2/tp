package InteractableEntity;

public class Enemy extends InteractableEntity{
    protected int damage;
    protected int defence;
    protected int health;

    public Enemy(int dmg, int def, int hp){
        this.damage = dmg;
        this.defence = def;
        this.health = hp;
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
}
