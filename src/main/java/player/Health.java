package player;

public class Health {
    private static int healthBar = 100;

    public void add(int amount) {
        healthBar += amount;
    }

    public void deduct(int amount) {
        healthBar -= amount;
    }

    public boolean checkHealth() {
        return healthBar <= 0;
    }

    public String outputHealth() {
        return "" + healthBar;
    }


}
