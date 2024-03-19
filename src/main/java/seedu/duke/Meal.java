package seedu.duke;

import seedu.duke.exception.UnregisteredMealException;

import java.util.HashMap;

public class Meal {
    private static HashMap<String, int[]> nutrientDetails = new HashMap<>();
    private String name;
    private int servingSize;
    private int calories;
    private int carbs;
    private int protein;
    private int fat;
    private int fiber;
    private int sugar;

    // Constructor with only serving size and meal name
    public Meal(String name, int servingSize) {
        this.name = name;
        this.servingSize = servingSize;
        setNutrientValues(name); // Assign nutrient values based on the name
    }

    // Add nutrient details to the static HashMap
    static {
        nutrientDetails.put("chicken rice", new int[]{400, 50, 30, 20, 10, 5});
        nutrientDetails.put("fried rice", new int[]{500, 60, 25, 15, 20, 3});
        nutrientDetails.put("pizza", new int[]{600, 70, 20, 25, 30, 2});
    }

    // Method to set nutrient values based on meal name
    private void setNutrientValues(String name) {
        int[] nutrients = nutrientDetails.get(name);
        calories = nutrients[0] * servingSize;
        carbs = nutrients[1] * servingSize;
        protein = nutrients[2] * servingSize;
        fat = nutrients[3] * servingSize;
        fiber = nutrients[4] * servingSize;
        sugar = nutrients[5] * servingSize;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getFiber() {
        return fiber;
    }

    public int getSugar() {
        return sugar;
    }

    // Method to print all meal info
    public static void handleInfoMeal(String command) throws UnregisteredMealException {
        String name = Parser.parseInfoMeal(command);
        int[] nutrients = nutrientDetails.get(name);
        System.out.println("Meal: " + name + " (per serving)");
        System.out.println("Calories: " + nutrients[0]);
        System.out.println("Carbs: " + nutrients[1]);
        System.out.println("Protein: " + nutrients[2]);
        System.out.println("Fat: " + nutrients[3]);
        System.out.println("Fiber: " + nutrients[4]);
        System.out.println("Sugar: " + nutrients[5]);
    }

    // Print all the available meals registered in the database
    public static void printAvailableMeals() {
        System.out.print("Available meals: ");
        for (String meal : nutrientDetails.keySet()) {
            System.out.print(meal);
            System.out.print(", ");
        }
        System.out.print("etc.");
        System.out.println();
    }

    public static HashMap<String, int[]> getNutrientDetails() {
        return nutrientDetails;
    }

}
