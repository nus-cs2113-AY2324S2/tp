package seedu.lifetrack.calories.calorielist;

import seedu.lifetrack.Entry;
import seedu.lifetrack.calories.Food;

public class InputEntry extends Entry {
    
    private Food food;
    private int calories;
    private boolean doesFoodExist = false;

    public InputEntry(String description, int calories, String date) {
        super(description, date);
        this.calories = calories;
    }

    public InputEntry(String description, int calories, String date, Food food) {
        super(description, date);
        this.food = food;
        this.calories = calories;
        this.doesFoodExist = true;
    }

    public Food getFood() {
        return food;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return String.format(super.toString() + ", Calories: " + calories + (doesFoodExist ?
                " (C: " + food.getCarbohydrates() +
                ", P: " + food.getProteins() +
                ", F: " + food.getFats() + ")"
                : ""));
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";C_IN;" + calories +
                (doesFoodExist ? ";" + food.getCarbohydrates() + ";" + food.getProteins() + ";" + food.getFats()
                : ""));
    }
}
