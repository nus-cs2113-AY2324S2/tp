package recipeio.recipe;

import recipeio.enums.MealCategory;

import java.util.ArrayList;

/**
 * The Recipe class represents a recipe containing various attributes such as name, cook time,
 * calories, allergies, category, and URL.
 */
public class Recipe {
    public String name = "";
    public int cookTime = 0;
    public int calories = 0;
    public ArrayList<String> allergies;
    public MealCategory category;
    public String url;

    public Recipe(String name, int cookTime, int calories, ArrayList<String> allergies,
                  MealCategory category, String url) {
        this.name = name;
        this.cookTime = cookTime;
        this.calories = calories;
        this.allergies = allergies;
        this.category = category;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + " / " + category;
    };
}
