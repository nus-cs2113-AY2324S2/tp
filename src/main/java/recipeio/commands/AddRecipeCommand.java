package recipeio.commands;

import recipeio.recipe.Recipe;
import recipeio.ui.UI;

import java.util.ArrayList;

public class AddRecipeCommand {

    /**
     * Adds a recipe to the list.
     *
     * @param recipe The new recipe to be added.
     */
    public static void execute(Recipe recipe, ArrayList<Recipe> recipes) {
        //this try-catch block can be helpful when there's not enough memory for a new recipe in the list
        try {
            //TaskList<T> add method only throw IndexOutOfBound exception for the overload add(int, T).
            recipes.add(recipe);
        } catch (Exception e) {
            UI.printMessage(e.toString());
        }
    }
}
