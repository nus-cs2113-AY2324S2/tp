package recipeio.recipe;

import recipeio.enums.MealCategory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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
    public LocalDateTime dateTimeAdded;

    public Recipe(String name, int cookTime, int calories, ArrayList<String> allergies,
                  MealCategory category, String url) {
        this.name = name;
        this.cookTime = cookTime;
        this.calories = calories;
        this.allergies = allergies;
        this.url = url;
        this.category = Objects.requireNonNullElse(category, MealCategory.GENERAL);
        this.dateTimeAdded = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return name + " / " + category;
    };
}
