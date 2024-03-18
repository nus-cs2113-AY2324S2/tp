package seedu.duke;

import java.util.HashMap;

public class Drink {
    private static HashMap<String, int[]> nutrientDetails = new HashMap<>();
    private String name;
    private int drinkVolume;
    private int calories;
    private int carbs;
    private int sugar;
    private int protein;
    private int fat;
    private int sodium;

    public Drink(String name, int volume) {
        this.name = name;
        this.drinkVolume = volume;
        setNutrientValues(name);
    }

    // Add nutrient details per 100 milliliter to the static HashMap
    static {
        nutrientDetails.put("sprite", new int[]{40, 50, 30, 20, 2, 5});
        nutrientDetails.put("lemon tea", new int[]{150, 30, 25, 1, 20, 3});
        nutrientDetails.put("milk coffee", new int[]{20, 27, 25, 4, 3, 2});
    }

    private void setNutrientValues(String name) {
        int[] nutrients = nutrientDetails.get(name);
        calories = nutrients[0] * drinkVolume / 100;
        carbs = nutrients[1] * drinkVolume / 100;
        sugar = nutrients[2] * drinkVolume / 100;
        protein = nutrients[3] * drinkVolume / 100;
        fat = nutrients[4] * drinkVolume / 100;
        sodium = nutrients[5]  * drinkVolume / 100;
    }

    public void infoDrink() {
        System.out.println("Drink: " + name);
        System.out.println("Volume: " + drinkVolume);
        System.out.println("Calories: " + getCalories());
        System.out.println("Carbs: " + getCarbs());
        // Sugar is part of Carbohydrates
        System.out.println("    Sugar: " + getSugar());
        System.out.println("Protein: " + getProtein());
        System.out.println("Fat: " + getFat());
        System.out.println("Sodium: " + getSodium());
    }

    public String getName() {
        return name;
    }

    public int getDrinkVolumeSize() {
        return drinkVolume;
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getSugar() {
        return sugar;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }
}
