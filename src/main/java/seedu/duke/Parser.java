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

    public static void handleCommand(String command) {
        if (command.equals("help")) {
            handleHelp();
        } else if (command.startsWith("ate")) {
            //handleMeal(command);
        } else if (command.startsWith("drink")) {
            //handleDrink(command);
        } else if (command.startsWith("infoMeal")) {
            //handleInfoMeal(command);
        } else if (command.startsWith("infoDrink")) {
            //handleInfoDrink(command);
        } else if (command.equals("viewCalories")) {
            //handleViewCalories();
        } else if (command.equals("viewCarbohydrates")) {
            //handleViewCarbohydrates();
        } else if (command.equals("viewProteins")) {
            //handleViewProteins();
        } else if (command.equals("viewWater")) {
            //handleViewWater();
        } else if (command.equals("viewFiber")) {
            //handleViewFiber();
        } else if (command.equals("listMeals")) {
            //handleListMeals();
        } else if (command.equals("listDrinks")) {
            //handleListDrinks();
        } else if (command.equals("listEverything")) {
            //handleListEverything();
        } else if (command.startsWith("editMealServingSize")) {
            //handleEditMealServingSize(command);
        } else if (command.startsWith("editDrinkServingSize")) {
            //handleEditDrinkServingSize(command);
        } else if (command.startsWith("editWaterIntake")) {
            //handleEditWaterIntake(command);
        } else if (command.startsWith("deleteMeal")) {
            //handleDeleteMeal(command);
        } else if (command.startsWith("deleteDrink")) {
            //handleDeleteDrink(command);
        } else if (command.equals("clear")) {
            //handleClear();
        } else if (command.equals("exit")) {
            //handleExit();
        } else {
            System.out.println("Invalid command");
        }
    }

    public static void handleHelp() {
        System.out.println("Add a meal eaten: ate m/MEAL s/SERVING_SIZE");
        System.out.println("Add a drink: d/DRINK s/SERVING_SIZE");
        System.out.println("Add water: water s/SERVING_SIZE");
        System.out.println("Find the information about a certain meal: infoMeal MEAL");
        System.out.println("Find the information about a certain drink: infoDrink DRINK");
        System.out.println("View daily calories consumed: viewCalories");
        System.out.println("View daily carbohydrates consumed: viewCarbohydrates");
        System.out.println("View daily proteins consumed: viewProteins");
        System.out.println("View daily fat consumed: viewFat");
        System.out.println("View daily sugar consumed: viewSugar");
        System.out.println("View daily water consumption: viewWater");
        System.out.println("View daily fiber consumed: viewFiber");
        System.out.println("List meal intake: listMeals");
        System.out.println("List drink intake: listDrinks");
        System.out.println("List entire food intake for the day: listEverything");
        System.out.println("Edit an existing meal after inserted: editMealServingSize INDEX s/NEW_SERVING_SIZE");
        System.out.println("Edit an existing drink after inserted: editDrinkServingSize INDEX s/NEW_SERVING_SIZE");
        System.out.println("Edit water intake after inserted: editWaterIntake s/TOTAL_WATER_INTAKE");
        System.out.println("Delete certain meal entry: deleteMeal INDEX");
        System.out.println("Delete certain drink entry: deleteDrink INDEX");
        System.out.println("Clear all entries: clear");
        System.out.println("Exit the app: exit ");
    }

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
