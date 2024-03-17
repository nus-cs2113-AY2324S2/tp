package seedu.duke;

public class Parser {
    public static String mealDescription;
    public static int mealSize;
    public static String drinkDescription;
    public static int drinkSize;
    public static int waterSize;

    public void parseMeal(String command) {
        int descriptionIndex = command.indexOf("m/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        mealDescription = command.substring(descriptionIndex, sizeIndex - 2);
        mealSize = Integer.parseInt(command.substring(sizeIndex));
    }

    public void parseDrink(String command) {
        int descriptionIndex = command.indexOf("d/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        drinkDescription = command.substring(descriptionIndex, sizeIndex - 2).trim();
        drinkSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public void parseWater(String command) {
        int sizeIndex = command.indexOf("s/") + 2;
        waterSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }
}
