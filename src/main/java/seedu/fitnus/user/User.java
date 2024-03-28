package seedu.fitnus.user;

import seedu.fitnus.Date;
import seedu.fitnus.Drink;
import seedu.fitnus.Exercise;
import seedu.fitnus.ExerciseIntensity;
import seedu.fitnus.Meal;
import seedu.fitnus.Parser;
import seedu.fitnus.Water;
import seedu.fitnus.exception.IncompleteDrinkException;
import seedu.fitnus.exception.IncompleteExerciseException;
import seedu.fitnus.exception.IncompleteMealException;
import seedu.fitnus.exception.UnregisteredDrinkException;
import seedu.fitnus.exception.UnregisteredExerciseException;
import seedu.fitnus.exception.UnregisteredMealException;
import seedu.fitnus.exception.invalidIndexException;
import seedu.fitnus.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    protected static ArrayList<Meal> mealList;
    protected static ArrayList<Drink> drinkList;
    protected static ArrayList<Exercise> exerciseList;


    public User(Storage mealStorage, Storage drinkStorage) {
        mealList = new ArrayList<>();
        drinkList = new ArrayList<>();
        exerciseList = new ArrayList<>();
        loadMeal(mealStorage);
        loadDrink(drinkStorage);
    }

    public void loadMeal(Storage mealStorage) {
        try {
            ArrayList<String> mealStorageList = mealStorage.readFile();
            if (!mealStorageList.isEmpty()) {
                for (String s : mealStorageList) {
                    Parser.parseMealStorage(s);
                    String mealDescription = Parser.mealStorageDescription;
                    int mealSize = Parser.mealStorageSize;
                    String currentDate = Parser.mealStorageDate;
                    mealList.add(new Meal(mealDescription, mealSize, currentDate));
                }
            }
        } catch (FileNotFoundException e) {
            mealStorage.createFile();
        }
    }

    public void loadDrink(Storage drinkStorage) {
        try {
            ArrayList<String> drinkStorageList = drinkStorage.readFile();
            if (!drinkStorageList.isEmpty()) {
                for (String s : drinkStorageList) {
                    Parser.parseDrinkStorage(s);
                    String drinkDescription = Parser.drinkStorageDescription;
                    String drinkDate = Parser.drinkStorageDate;
                    int drinkSize = Parser.drinkStorageSize;
                    if (drinkDescription.equals("water")) {
                        Water.getInstance(drinkSize);
                    } else {
                        drinkList.add(new Drink(drinkDescription, drinkSize, drinkDate));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            drinkStorage.createFile();
        }
    }

    public void saveMeal(Storage mealStorage) {
        for (Meal meal : mealList) {
            String mealSavedData = meal.getName() + "," + meal.getServingSize() + "," + meal.getDate();
            mealStorage.appendTextContent(mealSavedData);
        }
        try {
            mealStorage.writeFile(mealStorage.textContent);
        } catch (IOException e) {
            System.out.println("Failed saving meal: " + e.getMessage());
        }
    }

    public void saveDrink(Storage drinkStorage) {
        String waterSavedData = "water" + "," + Water.getWater();
        drinkStorage.appendTextContent(waterSavedData);
        for (Drink drink : drinkList) {
            String drinkSavedData = drink.getName() + "," + drink.getDrinkVolumeSize() + "," + drink.getDate();
            drinkStorage.appendTextContent(drinkSavedData);
        }
        try {
            drinkStorage.writeFile(drinkStorage.textContent);
        } catch (IOException e) {
            System.out.println("Failed saving drink: " + e.getMessage());
        }
    }

    public void handleMeal(String command) throws IncompleteMealException, UnregisteredMealException {
        Parser.parseMeal(command);
        String mealName = Parser.mealDescription;
        int servingSize = Parser.mealSize;

        Date currentDate = new Date();

        mealList.add(new Meal(mealName, servingSize, currentDate.getDate()));
        assert !mealList.isEmpty(): "failed to add meal";

        System.out.println("Added " + servingSize + " serving of " + mealName);
    }

    public void handleDrink(String command) throws IncompleteDrinkException, UnregisteredDrinkException {
        Parser.parseDrink(command);
        String drinkName = Parser.drinkDescription;
        int servingSize = Parser.drinkSize;

        Date currentDate = new Date();

        if (drinkName.equals("water")) {
            Water.getInstance(servingSize);
        } else {
            drinkList.add(new Drink(drinkName, servingSize, currentDate.getDate()));
        }
        System.out.println("Added " + servingSize + " ml of " + drinkName);
    }

    public void handleViewCalories() {
        int caloriesCount = 0;
        for (Meal meal: mealList) {
            caloriesCount += meal.getCalories();
        }
        for (Drink drink: drinkList) {
            caloriesCount += drink.getCalories();
        }
        System.out.println("Total Calories: " + caloriesCount);
    }

    public void handleViewCarbohydrates() {
        int carbohydratesCount = 0;
        for (Meal meal: mealList) {
            carbohydratesCount += meal.getCarbs();
        }
        for (Drink drink: drinkList) {
            carbohydratesCount += drink.getCarbs();
        }
        System.out.println("Total Carbohydrates: " + carbohydratesCount);
    }

    public void handleViewProteins() {
        int proteinCount = 0;
        for (Meal meal: mealList) {
            proteinCount += meal.getProtein();
        }
        for (Drink drink: drinkList) {
            proteinCount += drink.getProtein();
        }
        System.out.println("Total Proteins: " + proteinCount);
    }

    public void handleViewWaterIntake() {
        int waterIntake = 0;
        waterIntake += Water.getWater();
        System.out.println("Total water intake: " + waterIntake + " ml");
    }

    public void handleViewFiber() {
        int fibreCount = 0;
        for (Meal meal: mealList) {
            fibreCount += meal.getFiber();
        }
        System.out.println("Total Fiber: " + fibreCount);
    }

    public void handleViewFat() {
        int fatCount = 0;
        for (Meal meal: mealList) {
            fatCount += meal.getFat();
        }
        for (Drink drink: drinkList) {
            fatCount += drink.getFat();
        }
        System.out.println("Total Fat: " + fatCount);
    }

    public void handleViewSugar() {
        int sugarCount = 0;
        for (Meal meal: mealList) {
            sugarCount += meal.getSugar();
        }
        for (Drink drink: drinkList) {
            sugarCount += drink.getSugar();
        }
        System.out.println("Total Sugar: " + sugarCount);
    }

    public void printMealList(int startIndex) {
        for (int i = 0; i < mealList.size(); i++) {
            Meal currentMeal = mealList.get(i);
            System.out.println((startIndex+i) + ". " + currentMeal.getName() + " (serving size: "
                    + currentMeal.getServingSize() + ")");
        }
    }

    public void printExerciseList() {
        for (int i = 0; i < exerciseList.size(); i++) {
            Exercise currentExercise = exerciseList.get(i);
            System.out.println((i+1) + ". " + currentExercise.getName() + "duration:" + currentExercise.getDuration() +
                    " (intensity: " + currentExercise.getIntensity() + ")");
        }
    }
    public void handleListMeals() {
        System.out.println("here's what you have eaten today");
        if (mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printMealList(1);
        }
    }

    public void printDrinkList(int startIndex) {
        for (int i = 0; i < drinkList.size(); i++) {
            Drink currentDrink = drinkList.get(i);
            System.out.println((startIndex+i) + ". " + currentDrink.getName() + " (volume: "
                    + currentDrink.getDrinkVolumeSize() + "ml)");
        }
    }

    public void handleListDrinks() {
        System.out.println("here's what you have drank today");
        if (drinkList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printDrinkList(1);
            System.out.println();
            handleViewWaterIntake();
        }
    }

    public void handleListEverything() {
        System.out.println("here's what you have consumed today");
        if (drinkList.isEmpty() && mealList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
            System.out.println();
            handleViewWaterIntake();
        } else {
            printMealList(1);
            printDrinkList(mealList.size()+1);
            System.out.println();
            handleViewWaterIntake();
        }
    }

    public static void handleEditMealServingSize(String command) throws invalidIndexException {
        Parser.parseEditMeal(command);
        assert Parser.editMealIndex != 0: "meal index out of bounds";
        if (Parser.editMealIndex >= mealList.size()) {
            throw new invalidIndexException();
        }

        String mealName = mealList.get(Parser.editMealIndex).getName();
        String mealDate = mealList.get(Parser.editMealIndex).getDate();
        Meal updatedMeal = new Meal(mealName, Parser.editMealSize, mealDate);
        mealList.set(Parser.editMealIndex, updatedMeal);
        System.out.println(mealName + " has been edited to " + Parser.editMealSize + " serving(s)");
    }

    public static void handleEditDrinkServingSize(String command) throws invalidIndexException {
        Parser.parseEditDrink(command);
        assert Parser.editDrinkIndex != 0: "drink index out of bounds";

        if (Parser.editDrinkIndex >= drinkList.size()) {
            throw new invalidIndexException();
        }
        String drinkName = drinkList.get(Parser.editDrinkIndex).getName();
        String drinkDate = drinkList.get(Parser.editDrinkIndex).getDate();

        Drink updatedDrink = new Drink(drinkName, Parser.editDrinkSize, drinkDate);
        drinkList.set(Parser.editDrinkIndex, updatedDrink);
        System.out.println(drinkName + " has been edited to " + Parser.editDrinkSize + " ml");
    }

    public static void handleEditWaterIntake(String command) throws invalidIndexException {
        Parser.parseEditWater(command);
        Water.editWaterIntake(Parser.editWaterSize);
        System.out.println("Total water intake has been edited to " + Parser.editWaterSize + " ml");
    }

    public void handleDeleteMeal(String command) {
        int mealIndex = Integer.parseInt(command.substring(11)) - 1;
        assert mealIndex >= 0: "meal index out of bounds";
        String mealName = mealList.get(mealIndex).getName();
        mealList.remove(mealIndex);
        System.out.println("Removed " + mealName + " from meals");
    }

    public void handleDeleteDrink(String command) {
        int drinkIndex = Integer.parseInt(command.substring(12)) - 1;
        assert drinkIndex >= 0: "drink index out of bounds";
        String drinkName = drinkList.get(drinkIndex).getName();
        drinkList.remove(drinkIndex);
        System.out.println("Removed " + drinkName + " from drinks");
    }

    public void handleExercise(String command) throws IncompleteExerciseException, UnregisteredExerciseException {
        Parser.parseExercise(command);
        String exerciseType = Parser.exerciseDescription;
        int duration = Parser.exerciseDuration;
        ExerciseIntensity intensity = Parser.exerciseIntensity;
        exerciseList.add(new Exercise(exerciseType, duration, intensity));
        assert !exerciseList.isEmpty(): "failed to track exercise";

        System.out.println("Tracked " + duration + " minutes of " + exerciseType);
    }

    public void handleClear() {
        mealList.clear();
        drinkList.clear();
        Water.editWaterIntake(0);
        assert mealList.isEmpty(): "clearing of meal list failed";
        assert drinkList.isEmpty(): "clearing of drink list failed";

        System.out.println("All entries have been deleted");
    }

    public void handleCaloriesBurnt() {
        int caloriesBurnt = 0;
        for (Exercise exercise: exerciseList) {
            caloriesBurnt += exercise.getCaloriesBurnt();
        }
        System.out.println("Total calories burnt: " + caloriesBurnt);
    }

    public void handleListExercises() {
        System.out.println("here's the exercises you've done today");
        if (exerciseList.isEmpty()) {
            System.out.println("  >> nothing so far :o");
        } else {
            printExerciseList();
        }
    }
}
