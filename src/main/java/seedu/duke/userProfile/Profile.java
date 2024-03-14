package seedu.duke.userProfile;

public class Profile {
    private final String name;
    private final String occupation;
    private final Health health;
    private final Asset asset;


    public Profile(String name, String occupation, int goal) {

        this.name = name;
        this.health = new Health();
        this.asset = new Asset(goal);
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "this user's name is :" + this.name + '\n'
                + "occupation :" + this.occupation + '\n'
                + "current health :" + health.outputHealth()
                + "current asset: " + asset.outputAsset();
    }
}
