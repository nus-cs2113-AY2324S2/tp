package InteractableEntity;

public class Enemy extends InteractableEntity{
    protected int damage;
    protected int defence;
    protected String currentImage;

    public Enemy(int dmg, int def, String img){
        this.damage = dmg;
        this.defence = def;
        this.currentImage = img;
    }
}
