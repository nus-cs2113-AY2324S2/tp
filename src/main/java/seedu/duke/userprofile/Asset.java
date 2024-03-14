package seedu.duke.userprofile;

public class Asset {
    private static int totalAsset = 5000;
    private final int goal;

    public Asset(int goal) {
        this.goal = goal;
    }

    public void addAsset(int amount) {
        totalAsset += amount;
    }

    public void deductAsset(int amount) {
        totalAsset -= amount;
    }

    public boolean checkGoal() {
        return totalAsset >= this.goal;
    }

    public boolean checkBankrupt() {
        return totalAsset <= 0;
    }

    public String outputAsset() {
        return "" + totalAsset;
    }


}
