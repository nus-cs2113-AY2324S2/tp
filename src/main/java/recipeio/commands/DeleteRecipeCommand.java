package recipeio.commands;

import recipeio.recipe.Recipe;
import java.util.ArrayList;

public class DeleteRecipeCommand {

    /**
     * Deletes the Recipe from the recipe list.
     *
     * @param recipeNumber The recipe number from the user.
     */
    public static void execute(int recipeNumber, ArrayList<Recipe> recipes) {
        recipeNumber = recipeNumber - 1;
        if (recipeNumber >= recipes.size() || recipeNumber < 0) {
            System.out.println("Sorry, there were no recipes with that number.");
        } else {
            Recipe selectedRecipe = recipes.get(recipeNumber);
            recipes.remove(recipeNumber);
            System.out.println("Deleted that recipe!");
        }
    }
}
