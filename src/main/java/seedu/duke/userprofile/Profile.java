package seedu.duke.userprofile;

public class Profile {
    private final String name;
    private final String occupation;
    private final Health health;
    private final Asset asset;


    public Profile(String name, String occupation) {
        this.name = name;
        this.health = new Health();
        this.asset = new Asset(100000);
        this.occupation = occupation;
    }

    public String outputName() {
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
