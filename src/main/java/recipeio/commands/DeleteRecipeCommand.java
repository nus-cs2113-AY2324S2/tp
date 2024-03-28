package recipeio.commands;

import recipeio.exceptions.InvalidIndexException;
import recipeio.recipe.Recipe;
import recipeio.ui.UI;

import java.util.ArrayList;

public class DeleteRecipeCommand {

    /**
     * Deletes the Recipe from the recipe list.
     *
     * @param recipeNumber The recipe number from the user.
     */
    public static void execute(int recipeNumber, ArrayList<Recipe> recipes) throws InvalidIndexException {
        if (recipeNumber > recipes.size() || recipeNumber < 1) {
            throw new InvalidIndexException("Sorry, there is no recipe at index: " + recipeNumber);
        }
        Recipe selectedRecipe = recipes.get(recipeNumber - 1);
        recipes.remove(recipeNumber - 1);
        UI.printDeleteMessage(selectedRecipe, recipes.size());
    }
}
