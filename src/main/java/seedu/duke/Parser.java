package seedu.duke;

import seedu.duke.exception.IncompleteDrinkException;
import seedu.duke.exception.IncompleteMealException;
import seedu.duke.exception.IncompleteWaterException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.UnregisteredDrinkException;
import seedu.duke.exception.UnregisteredMealException;
import seedu.duke.user.User;

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
    private User user;

    public Parser() {
        this.user = new User();
    }
    public void handleCommand(String command) {
        try {
            if (command.equals("help")) {
                handleHelp();
            } else if (command.startsWith("ate")) {
                user.handleMeal(command);
            } else if (command.startsWith("drink")) {
                user.handleDrink(command);
            } else if (command.startsWith("water")) {
                user.handleWater(command);
            } else if (command.startsWith("infoMeal")) {
                Meal.handleInfoMeal(command);
            } else if (command.startsWith("infoDrink")) {
                Drink.handleInfoDrink(command);
            } else if (command.equals("viewCalories")) {
                user.handleViewCalories();
            } else if (command.equals("viewCarbohydrates")) {
                user.handleViewCarbohydrates();
            } else if (command.equals("viewProtein")) {
                user.handleViewProteins();
            } else if (command.equals("viewSugar")) {
                user.handleViewSugar();
            } else if (command.equals("viewFat")) {
                user.handleViewFat();
            } else if (command.equals("viewWater")) {
                user.handleViewWaterIntake();
            } else if (command.equals("viewFiber")) {
                user.handleViewFiber();
            } else if (command.equals("listMeals")) {
                user.handleListMeals();
            } else if (command.equals("listDrinks")) {
                user.handleListDrinks();
            } else if (command.equals("listEverything")) {
                user.handleListEverything();
            } else if (command.startsWith("editMealServingSize")) {
                //handleEditMealServingSize(command);
            } else if (command.startsWith("editDrinkServingSize")) {
                //handleEditDrinkServingSize(command);
            } else if (command.startsWith("editWaterIntake")) {
                //handleEditWaterIntake(command);
            } else if (command.startsWith("deleteMeal")) {
                user.handleDeleteMeal(command);
            } else if (command.startsWith("deleteDrink")) {
                user.handleDeleteDrink(command);
            } else if (command.equals("clear")) {
                user.handleClear();
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command, type [help] to view all commands.");
        } catch (IncompleteWaterException e) {
            System.out.println("Incomplete command, the format must be [water s/SERVING_SIZE].");
        } catch (IncompleteDrinkException e) {
            System.out.println("Incomplete command, the format must be [drink d/DRINK s/SERVING_SIZE].");
        } catch (IncompleteMealException e) {
            System.out.println("Incomplete command, the format must be [ate m/MEAL s/SERVING_SIZE].");
        } catch (UnregisteredDrinkException e) {
            System.out.println("Sorry that drink is not registered in the database.");
        } catch (UnregisteredMealException e) {
            System.out.println("Sorry that meal is not registered in the database.");
        }
    }

    public static void handleHelp() {
        System.out.println("Add a meal eaten: ate m/MEAL s/SERVING_SIZE");
        System.out.println("Add a drink: drink d/DRINK s/SERVING_SIZE");
        System.out.println("Add water: water s/SERVING_SIZE");
        System.out.println("Find the information about a certain meal: infoMeal MEAL");
        System.out.println("Find the information about a certain drink: infoDrink DRINK");
        System.out.println("View daily calories consumed: viewCalories");
        System.out.println("View daily carbohydrates consumed: viewCarbohydrates");
        System.out.println("View daily proteins consumed: viewProtein");
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

    public static void parseMeal(String command) throws IncompleteMealException, UnregisteredMealException {
        if (!command.contains("m/") || !command.contains("s/")) {
            throw new IncompleteMealException();
        }
        int descriptionIndex = command.indexOf("m/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        if (sizeIndex >= command.length()) {
            throw new IncompleteMealException();
        }
        mealDescription = command.substring(descriptionIndex, sizeIndex - 2).trim();
        if (mealDescription.isEmpty()) {
            throw new IncompleteMealException();
        }
        if (!Meal.getNutrientDetails().containsKey(mealDescription)) {
            throw new UnregisteredMealException();
        }
        mealSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static void parseDrink(String command) throws IncompleteDrinkException, UnregisteredDrinkException {
        if (!command.contains("d/") || !command.contains("s/")) {
            throw new IncompleteDrinkException();
        }
        int descriptionIndex = command.indexOf("d/") + 2;
        int sizeIndex = command.indexOf("s/") + 2;
        if (sizeIndex >= command.length()) {
            throw new IncompleteDrinkException();
        }
        drinkDescription = command.substring(descriptionIndex, sizeIndex - 2).trim();
        if (drinkDescription.isEmpty()) {
            throw new IncompleteDrinkException();
        }
        if (!Drink.getNutrientDetails().containsKey(drinkDescription)) {
            throw new UnregisteredDrinkException();
        }
        drinkSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static void parseWater(String command) throws IncompleteWaterException {
        if (!command.contains("s/")) {
            throw new IncompleteWaterException();
        }
        int sizeIndex = command.indexOf("s/") + 2;
        if (sizeIndex >= command.length()) {
            throw new IncompleteWaterException();
        }
        waterSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static String parseInfoMeal(String command) throws UnregisteredMealException {
        int mealIndex = 9;
        String infoMealDescription = command.substring(mealIndex).trim();
        if (!Meal.getNutrientDetails().containsKey(infoMealDescription)) {
            throw new UnregisteredMealException();
        }
        return infoMealDescription;
    }

    public static String parseInfoDrink(String command) throws UnregisteredDrinkException {
        int drinkIndex = 10;
        String infoDrinkDescription = command.substring(drinkIndex).trim();
        if (!Meal.getNutrientDetails().containsKey(infoDrinkDescription)) {
            throw new UnregisteredDrinkException();
        }
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
