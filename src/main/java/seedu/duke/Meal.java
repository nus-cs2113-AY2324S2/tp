package seedu.duke;

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
        calories = nutrients[0];
        carbs = nutrients[1];
        protein = nutrients[2];
        fat = nutrients[3];
        fiber = nutrients[4];
        sugar = nutrients[5];
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories * servingSize;
    }

    public int getCarbs() {
        return carbs * servingSize;
    }

    public int getProtein() {
        return protein * servingSize;
    }

    public int getFat() {
        return fat * servingSize;
    }

    public int getFiber() {
        return fiber * servingSize;
    }

    public int getSugar() {
        return sugar * servingSize;
    }

    // Method to print all meal info
    public void infoMeal() {
        System.out.println("Meal: " + name);
        System.out.println("Serving Size: " + servingSize);
        System.out.println("Calories: " + getCalories());
        System.out.println("Carbs: " + getCarbs());
        System.out.println("Protein: " + getProtein());
        System.out.println("Fat: " + getFat());
        System.out.println("Fiber: " + getFiber());
        System.out.println("Sugar: " + getSugar());
    }

}
