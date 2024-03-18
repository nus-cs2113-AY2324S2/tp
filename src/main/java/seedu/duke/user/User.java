package seedu.duke.user;

import seedu.duke.Drink;
import seedu.duke.Meal;
import seedu.duke.Parser;
import seedu.duke.Water;

import java.util.ArrayList;

public class User {
    protected static ArrayList<Meal> mealList;
    protected static ArrayList<Drink> drinkList;
    private static ArrayList<Water> totalWaterIntake;

    public User() {
        mealList = new ArrayList<>();
        drinkList = new ArrayList<>();
        totalWaterIntake = new ArrayList<>();
    }

    public static void handleMeal(String command) {
        Parser.parseMeal(command);
        String mealName = Parser.mealDescription;
        int servingSize = Parser.mealSize;

        mealList.add(new Meal(mealName, servingSize));
        System.out.println("Added " + servingSize + " serving of " + mealName);
    }

    public static void handleDrink(String command) {
        Parser.parseDrink(command);
        String drinkName = Parser.drinkDescription;
        int servingSize = Parser.drinkSize;

        drinkList.add(new Drink(drinkName, servingSize)); //TODO: Drink constructor needs 8 arguments
        System.out.println("Added " + servingSize + " serving of " + drinkName);
    }

    public static void handleViewCalories() {
        int caloriesCount = 0;
        for (Meal meal: mealList) {
            caloriesCount += meal.getCalories();
        }
        for (Drink drink: drinkList) {
            caloriesCount += drink.getCalories();
        }
        System.out.println("Total Calories: " + caloriesCount);
    }

    public static void handleViewCarbohydrates() {
        int carbohydratesCount = 0;
        for (Meal meal: mealList) {
            carbohydratesCount += meal.getCarbs();
        }
        for (Drink drink: drinkList) {
            carbohydratesCount += drink.getCarbs();
        }
        System.out.println("Total Carbohydrates: " + carbohydratesCount);
    }

    public static void handleViewProteins() {
        int proteinCount = 0;
        for (Meal meal: mealList) {
            proteinCount += meal.getProtein();
        }
        for (Drink drink: drinkList) {
            proteinCount += drink.getProtein();
        }
        System.out.println("Total Proteins: " + proteinCount);
    }

    public static void viewTotalWaterIntake() {
        int waterIntake = 0;
        for (Water water: totalWaterIntake) {
            waterIntake += water.getWater();
        }
        System.out.println("Total water intake: " + waterIntake);
    }

    public static void handleViewFiber() {
        int fibreCount = 0;
        for (Meal meal: mealList) {
            fibreCount += meal.getFiber();
        }
        System.out.println("Total Proteins: " + fibreCount);
    }

    public static void printMealList(int startingIndex) {
        for (int i = startingIndex; i <= mealList.size(); i++) {
            Meal currentMeal = mealList.get(i);
            System.out.print(i + ". " + currentMeal.getName());
        }
    }
    public static void handleListMeals() {
        System.out.println("here's what you have eaten today");
        if (mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printMealList(1);
        }
    }

    public static void printDrinkList(int startingIndex) {
        for (int i = startingIndex; i <= drinkList.size(); i++) {
            Drink currentDrink = drinkList.get(i);
            System.out.print(i + ". " + currentDrink.getName());
        }
    }

    public static void handleListDrinks() {
        System.out.println("here's what you have drank today");
        if (drinkList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printDrinkList(1);
            viewTotalWaterIntake();
        }
    }

    public static void handleListEverything() {
        System.out.println("here's what you have drank today");
        if (drinkList.isEmpty() && mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printMealList(1);
            printDrinkList(mealList.size() + 1);
            viewTotalWaterIntake();
        }
    }

    public static void handleDeleteMeal(String command) {
        int mealIndex = Integer.parseInt(command.substring(11));
        String mealName = mealList.get(mealIndex).getName();
        mealList.remove(mealIndex);

        System.out.println("Removed " + mealName + " from Meals");
    }

    public static void handleDeleteDrink(String command) {
        int drinkIndex = Integer.parseInt(command.substring(12));
        String drinkName = drinkList.get(drinkIndex).getName();
        drinkList.remove(drinkIndex);
        System.out.println("Removed " + drinkName + " from Meals");
    }

    public static void handleClear() {
        mealList.clear();
        drinkList.clear();
        System.out.println("All entries have been deleted");
    }

}
