package seedu.duke.userprofile;

public class Health {
    private static int healthBar = 100;

    public void addhealth(int amount) {
        healthBar += amount;
    }

    public void deductAmount(int amount) {
        healthBar -= amount;
    }

    public boolean checkHealth() {
        return healthBar <= 0;
    }

    public String outputHealth() {
        return "" + healthBar;
    }
}
