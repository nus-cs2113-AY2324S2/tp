package player;

public class PlayerProfile {
    private final String name;
    private final String occupation;
    private final Health health;
    private final Asset asset;


    public PlayerProfile(String name, String occupation) {
        this.name = name;
        this.health = new Health();
        this.asset = new Asset(100000);
        this.occupation = occupation;
    }

    public void addAsset(int amount) {
        this.asset.addAsset(amount);
    }

    public void addHealth(int amount) {
        this.health.add(amount);
    }

    public void loseHealth() {
        this.health.deduct(1);
    }

    public void loseAsset() {
        this.asset.deductAsset(1);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Your name is :" + name + '\n'
                + "occupation :" + occupation + '\n'
                + "current health :" + health.outputHealth() + "\n"
                + "current asset: " + asset.outputAsset();
    }
}
