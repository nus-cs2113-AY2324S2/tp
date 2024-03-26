package seedu.fitnus;

public class Water {
    private static Water instance = null;
    private static int waterIntake = 0;

    public Water(int amount) {
        waterIntake = amount;
    }

    public static void getInstance(int amount) {
        if (instance == null) {
            instance = new Water(amount);
        } else {
            addWaterIntake(amount);
        }
    }

    public static int getWater() {
        return waterIntake;
    }

    public static void addWaterIntake(int amount) {
        waterIntake += amount;
    }

    public static void editWaterIntake(int amount) {
        waterIntake = amount;
    }
}
