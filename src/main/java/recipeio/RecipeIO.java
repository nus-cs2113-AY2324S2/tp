package recipeio;

import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;
import storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class RecipeIO {
    /**
     * Main entry-point for the Recipe.IO application.
     */
    public static void main(String[] args) {
        ArrayList<String> testAllergies = new ArrayList<>();
        testAllergies.add("Dairy");
        testAllergies.add("Egg");
        testAllergies.add("Gluten");
        String testURL = "https://www.bbcgoodfood.com/recipes/ultimate-spaghetti-carbonara-recipe";
        Recipe testRecipe = new Recipe("Spaghetti Carbonara", 60, 1000,
                testAllergies, MealCategory.LUNCH, testURL);
        System.out.println("Hello");


        RecipeList testRecipeList = new RecipeList();
        try {
            Storage.loadFile(testRecipeList);
        } catch (Exception e) {
            testRecipeList.addRecipe(testRecipe);
            System.out.println(e.getMessage());
        }

        try {
            Storage.saveFile(testRecipeList);
        } catch (Exception e) {
            System.out.println("Error saving list!");
        }

        testRecipeList.findAllergy("xxx");
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}