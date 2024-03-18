package InteractableEntity;

public class Enemy extends InteractableEntity{
    protected int damage;
    protected int defence;

    public Enemy(int dmg, int def){
        this.damage = dmg;
        this.defence = def;
    }
}
