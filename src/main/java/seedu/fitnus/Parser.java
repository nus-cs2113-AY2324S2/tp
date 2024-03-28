package seedu.fitnus;

import seedu.fitnus.exception.IncompleteDrinkException;
import seedu.fitnus.exception.IncompleteMealException;
import seedu.fitnus.exception.IncompleteExerciseException;
import seedu.fitnus.exception.InvalidCommandException;
import seedu.fitnus.exception.UnregisteredDrinkException;
import seedu.fitnus.exception.UnregisteredMealException;
import seedu.fitnus.exception.invalidIndexException;
import seedu.fitnus.exception.UnregisteredExerciseException;
import seedu.fitnus.user.User;

public class Parser {
    public static String mealDescription;
    public static int mealSize;
    public static String drinkDescription;
    public static int drinkSize;
    public static int editMealIndex;
    public static int editMealSize;
    public static int editDrinkIndex;
    public static int editDrinkSize;
    public static int editWaterSize;
    public static String mealStorageDescription;
    public static int mealStorageSize;
    public static String mealStorageDate;

    public static String drinkStorageDescription;
    public static int drinkStorageSize;
    public static String drinkStorageDate;

    public static String exerciseDescription;
    public static int exerciseDuration;

    public static ExerciseIntensity exerciseIntensity;
    private User user;

    public Parser(User user) {
        this.user = user;
    }

    public void handleCommand(String command) {
        try {
            if (command.equals("help")) {
                handleHelp();
            } else if (command.startsWith("ate")) {
                user.handleMeal(command);
            } else if (command.startsWith("drink")) {
                user.handleDrink(command);
            } else if (command.startsWith("exercise")) {
                user.handleExercise(command);
            } else if (command.startsWith("infoMeal")) {
                Meal.handleInfoMeal(command);
            } else if (command.startsWith("infoDrink")) {
                Drink.handleInfoDrink(command);
            } else if (command.startsWith("infoExercise")) {
                Exercise.handleInfoExercise(command);
            } else if (command.equals("calories")) {
                user.handleViewCalories();
            } else if (command.equals("carbs")) {
                user.handleViewCarbohydrates();
            } else if (command.equals("protein")) {
                user.handleViewProteins();
            } else if (command.equals("sugar")) {
                user.handleViewSugar();
            } else if (command.equals("fat")) {
                user.handleViewFat();
            } else if (command.equals("viewWater")) {
                user.handleViewWaterIntake();
            } else if (command.equals("fiber")) {
                user.handleViewFiber();
            } else if (command.equals("listMeals")) {
                user.handleListMeals();
            } else if (command.equals("listDrinks")) {
                user.handleListDrinks();
            } else if (command.equals("listEverything")) {
                user.handleListEverything();
            }else if (command.equals("listExercises")) {
                user.handleListExercises();
            } else if (command.startsWith("editMeal")) {
                User.handleEditMealServingSize(command);
            } else if (command.startsWith("editDrink")) {
                User.handleEditDrinkServingSize(command);
            } else if (command.startsWith("editWater")) {
                User.handleEditWaterIntake(command);
            } else if (command.startsWith("deleteMeal")) {
                user.handleDeleteMeal(command);
            } else if (command.startsWith("deleteDrink")) {
                user.handleDeleteDrink(command);
            } else if (command.equals("caloriesBurnt")) {
                user.handleCaloriesBurnt();
            } else if (command.equals("clear")) {
                user.handleClear();
            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command, type [help] to view all commands.");
        } catch (IncompleteDrinkException e) {
            System.out.println("Incomplete command, the format must be [drink d/DRINK s/SERVING_SIZE].");
        } catch (IncompleteMealException e) {
            System.out.println("Incomplete command, the format must be [ate m/MEAL s/SERVING_SIZE].");
        } catch (IncompleteExerciseException e) {
            System.out.println("Incomplete command, the format must be [exercise e/MEAL d/SERVING_SIZE].");
        } catch (UnregisteredDrinkException e) {
            System.out.println("Sorry that drink is not registered in the database.");
        } catch (UnregisteredMealException e) {
            System.out.println("Sorry that meal is not registered in the database.");
        } catch (invalidIndexException e) {
            System.out.println("Sorry the index you provided is invalid, check [listMeals or listDrinks] " +
                    "to view valid " + "indexes.");
        } catch (UnregisteredExerciseException e) {
            System.out.println("Sorry that exercise is not registered in the database.");
        }
    }

    public static void handleHelp() {
        System.out.println("here's all the valid commands i recognise: ");
        System.out.println("- Add a meal eaten: ate m/MEAL s/SERVING_SIZE");
        System.out.println("- Add a drink: drink d/DRINK s/VOLUME(ML)");
        System.out.println("- Track and exercise: exercise e/EXERCISE d/DURATION(MINUTES) " +
                "i/INTENSITY(HIGH, MEDIUM, LOW)");
        System.out.println("- Find the information about a certain meal: infoMeal MEAL");
        System.out.println("- Find the information about a certain drink: infoDrink DRINK");
        System.out.println("- Find the information about a certain exercise: infoExercise EXERCISE");
        System.out.println("- View daily calories consumed: calories");
        System.out.println("- View daily carbohydrates consumed: carbs");
        System.out.println("- View daily proteins consumed: protein");
        System.out.println("- View daily fat consumed: fat");
        System.out.println("- View daily sugar consumed: sugar");
        System.out.println("- View daily fiber consumed: fiber");
        System.out.println("- View daily water consumption: viewWater");
        System.out.println("- View daily calories burnt: caloriesBurnt");
        System.out.println("- List meal intake: listMeals");
        System.out.println("- List drink intake: listDrinks");
        System.out.println("- List exercises done: listExercises");
        System.out.println("- List entire food intake for the day: listEverything");
        System.out.println("- Edit an existing meal after inserted: editMeal INDEX s/NEW_SERVING_SIZE");
        System.out.println("- Edit an existing drink after inserted: editDrink INDEX s/NEW_SERVING_SIZE");
        System.out.println("- Edit total water intake after inserted: editWater s/TOTAL_WATER_INTAKE");
        System.out.println("- Delete certain meal entry: deleteMeal INDEX");
        System.out.println("- Delete certain drink entry: deleteDrink INDEX");
        System.out.println("- Clear all entries: clear");
        System.out.println("- Exit the app: exit ");
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
        mealDescription = command.substring(descriptionIndex, sizeIndex - 2).trim().toLowerCase();
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
        drinkDescription = command.substring(descriptionIndex, sizeIndex - 2).trim().toLowerCase();
        if (drinkDescription.isEmpty()) {
            throw new IncompleteDrinkException();
        }
        if (!Drink.getNutrientDetails().containsKey(drinkDescription) && !drinkDescription.equals("water")) {
            throw new UnregisteredDrinkException();
        }
        drinkSize = Integer.parseInt(command.substring(sizeIndex).trim());
    }

    public static String parseInfoMeal(String command) throws UnregisteredMealException {
        int mealIndex = 9;
        String infoMealDescription = command.substring(mealIndex).trim();
        if (!Meal.getNutrientDetails().containsKey(infoMealDescription)) {
            throw new UnregisteredMealException();
        }
        return infoMealDescription;
    }

    public static String parseInfoExercise(String command) throws UnregisteredExerciseException {
        int exerciseIndex = 13;
        String infoExerciseDescription = command.substring(exerciseIndex).trim();
        if (!Exercise.getExerciseDetails().containsKey(infoExerciseDescription)) {
            throw new UnregisteredExerciseException();
        }
        return infoExerciseDescription;
    }

    public static String parseInfoDrink(String command) throws UnregisteredDrinkException {
        int drinkIndex = 10;
        String infoDrinkDescription = command.substring(drinkIndex).trim();
        if (!Drink.getNutrientDetails().containsKey(infoDrinkDescription)) {
            throw new UnregisteredDrinkException();
        }
        return infoDrinkDescription;
    }

    public static void parseEditMeal(String command) {
        int mealSizePosition = command.indexOf("/");
        editMealIndex = Integer.parseInt(command.substring(9, mealSizePosition - 2).trim()) - 1;
        editMealSize = Integer.parseInt(command.substring(mealSizePosition + 1).trim());
    }

    public static void parseEditDrink(String command) {
        int drinkSizePosition = command.indexOf("/");
        editDrinkIndex = Integer.parseInt(command.substring(10, drinkSizePosition - 2).trim()) - 1;
        editDrinkSize = Integer.parseInt(command.substring(drinkSizePosition + 1).trim());
    }

    public static void parseEditWater(String command) {
        int waterSizePosition = command.indexOf("s/") + 2;
        editWaterSize = Integer.parseInt(command.substring(waterSizePosition).trim());
    }

    public static void parseMealStorage(String data) {
        String delimiter = ",";
        String[] arrayOfMealData = data.split(delimiter);
        mealStorageDescription = arrayOfMealData[0];
        mealStorageSize = Integer.parseInt(arrayOfMealData[1]);
    }

    public static void parseDrinkStorage(String data) {
        String delimiter = ",";
        String[] arrayOfDrinkData = data.split(delimiter);
        drinkStorageDescription = arrayOfDrinkData[0];
        drinkStorageSize = Integer.parseInt(arrayOfDrinkData[1]);
    }

    public static void parseExercise(String command) throws IncompleteExerciseException, UnregisteredExerciseException {
        if (!command.contains("e/") || !command.contains("d/") || !command.contains("i/")) {
            throw new IncompleteExerciseException();
        }
        int descriptionIndex = command.indexOf("e/") + 2;
        int durationIndex = command.indexOf("d/") + 2;
        int intensityIndex = command.indexOf("i/") + 2;
        if (intensityIndex >= command.length()) {
            throw new IncompleteExerciseException();
        }
        exerciseDescription = command.substring(descriptionIndex, durationIndex - 2).trim().toLowerCase();
        if (exerciseDescription.isEmpty()) {
            throw new IncompleteExerciseException();
        }
        if (!Exercise.getExerciseDetails().containsKey(exerciseDescription)) {
            throw new UnregisteredExerciseException();
        }

        exerciseDuration = Integer.parseInt(command.substring(durationIndex, intensityIndex - 2).trim());

        String intensityString = command.substring(intensityIndex).trim().toUpperCase();
        try {
            exerciseIntensity = ExerciseIntensity.valueOf(intensityString);
        } catch (IllegalArgumentException e) {
            throw new IncompleteExerciseException(); // Invalid intensity
        }
    }
}
