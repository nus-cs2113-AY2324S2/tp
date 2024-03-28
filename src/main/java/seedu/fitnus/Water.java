package seedu.fitnus;

public class Water {
    private static Water instance = null;
    private static int waterIntake = 0;
    private static String dateAdded;

    public Water(int amount, String dateAdded) {
        waterIntake = amount;
        this.dateAdded = dateAdded;
    }

    public static void getInstance(int amount, String dateAdded) {
        if (instance == null) {
            instance = new Water(amount, dateAdded);
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

    public static String getDate() {
        return dateAdded;
    }
}
