package seedu.duke;

public class Parser {
    public static String mealDescription;
    public static int mealSize;
    public static String drinkDescription;
    public static int drinkSize;
    public static int waterSize;
    public static int editMealIndex;
    public static int editMealSize;
    public static int editDrinkIndex;
    public static int editDrinkSize;
    public static int editWaterSize;

    public static void parseMeal(String command) {
        int descriptionIndex = command.indexOf("m/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        mealDescription = command.substring(descriptionIndex, sizeIndex - 2).trim();
        mealSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static void parseDrink(String command) {
        int descriptionIndex = command.indexOf("d/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        drinkDescription = command.substring(descriptionIndex, sizeIndex - 2).trim();
        drinkSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static void parseWater(String command) {
        int sizeIndex = command.indexOf("s/") + 2;
        waterSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static String parseInfoMeal(String command) {
        int mealIndex = 9;
        return command.substring(mealIndex).trim();
    }

    public static String parseInfoDrink(String command) {
        int drinkIndex = 10;
        return command.substring(drinkIndex).trim();
    }

    public static void parseEditMeal(String command) {
        int mealSizePosition = command.indexOf("s/");
        int mealIndexPosition = 20;
        editMealIndex = Integer.parseInt(command.substring(mealIndexPosition, mealSizePosition).trim());
        editMealSize = Integer.parseInt(command.substring(mealSizePosition + 2).trim());
    }

    public static void parseEditDrink(String command) {
        int drinkSizePosition = command.indexOf("s/");
        int drinkIndexPosition = 21;
        editDrinkIndex = Integer.parseInt(command.substring(drinkIndexPosition, drinkSizePosition).trim());
        editDrinkSize = Integer.parseInt(command.substring(drinkSizePosition + 2).trim());
    }

    public static void parseEditWater(String command) {
        int waterSizePosition = command.indexOf("s/") + 2;
        editWaterSize = Integer.parseInt(command.substring(waterSizePosition).trim());
    }
}
