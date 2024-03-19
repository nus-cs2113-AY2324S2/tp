package seedu.fitnus;

import seedu.fitnus.exception.UnregisteredDrinkException;
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

    public Drink(String name, int volume) {
        this.name = name;
        this.drinkVolume = volume;
        setNutrientValues(name);
    }

    // Add nutrient details per 100 milliliter to the static HashMap
    static {
        nutrientDetails.put("sprite", new int[]{40, 50, 30, 20, 2});
        nutrientDetails.put("lemon tea", new int[]{150, 30, 25, 1, 20});
        nutrientDetails.put("milk coffee", new int[]{20, 27, 25, 4, 3});
    }

    private void setNutrientValues(String name) {
        int[] nutrients = nutrientDetails.get(name);
        calories = nutrients[0] * drinkVolume / 100;
        carbs = nutrients[1] * drinkVolume / 100;
        sugar = nutrients[2] * drinkVolume / 100;
        protein = nutrients[3] * drinkVolume / 100;
        fat = nutrients[4] * drinkVolume / 100;
    }
    public static void handleInfoDrink(String command) throws UnregisteredDrinkException {
        String name = Parser.parseInfoDrink(command);
        int[] nutrients = nutrientDetails.get(name);
        System.out.println("Drink: " + name + " (100 ml)");
        System.out.println("Calories: " + nutrients[0]);
        System.out.println("Carbs: " + nutrients[1]);
        // Sugar is part of Carbohydrates
        System.out.println("Sugar: " + nutrients[2]);
        System.out.println("Protein: " + nutrients[3]);
        System.out.println("Fat: " + nutrients[4]);
    }

    public static void printAvailableDrinks() {
        System.out.print("Available drinks: ");
        for (String drink : nutrientDetails.keySet()) {
            System.out.print(drink);
            System.out.print(", ");
        }
        System.out.print("etc.");
        System.out.println();
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

    public static HashMap<String, int[]> getNutrientDetails() {
        return nutrientDetails;
    }

}
