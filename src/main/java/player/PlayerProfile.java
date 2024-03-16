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
