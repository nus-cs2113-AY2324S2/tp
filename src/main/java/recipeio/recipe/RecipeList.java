package recipeio.recipe;

import recipeio.Constants;
import recipeio.InputParser;
import recipeio.commands.AddRecipeCommand;
import recipeio.commands.DeleteRecipeCommand;
import recipeio.commands.FindByAllergyCommand;
import recipeio.commands.FindCommand;
import recipeio.commands.ListRecipeCommand;
import recipeio.ui.UI;

import java.util.ArrayList;

import static recipeio.Constants.MAX_RECIPES;
import static recipeio.InputParser.parseAdd;

public class RecipeList {
    /**
     * Represents the user's list of recipes (ie their recipe book).
     */
    private final ArrayList<Recipe> recipes;

    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    /**
     * Returns the size of the list (recipe book).
     */
    public int getSize() {
        return recipes.size();
    }

    /**
     * Returns the recipe at the specified index.
     *
     * @param index The index of the recipe.
     * @return The recipe at the specified index.
     */
    public Recipe get(int index) {
        return recipes.get(index);
    }

    public void add(String userInput) {
        assert(recipes.size() < MAX_RECIPES);
        try {
            Recipe newRecipe = parseAdd(userInput);
            AddRecipeCommand.execute(newRecipe, recipes);
        } catch (Exception e){
            UI.printMessage(e.getMessage());
        }
    }

    public void add(Recipe recipe) {
        AddRecipeCommand.execute(recipe, recipes);
    }

    public void delete (String userInput) {
        int index = InputParser.parseID(userInput);
        DeleteRecipeCommand.execute(index, recipes);
    }

    public void delete (int index) {
        DeleteRecipeCommand.execute(index, recipes);
    }

    public void listRecipes() {
        ListRecipeCommand.execute(recipes);
    }

    public void find(String input) {
        FindCommand.execute(input, recipes);
    }

    public String findAllergy(String allergy) {
        return FindByAllergyCommand.execute(allergy, recipes);
    }

    public void executeCommand(String command, String userInput){
        switch (command) {
        case Constants.LIST_COMMAND:
            listRecipes();
            break;
        case Constants.ADD_COMMAND:
            add(userInput);
            break;
        case Constants.DELETE_COMMAND:
            delete(userInput);
            break;
        case Constants.FIND_COMMAND:
            find(userInput);
            break;
        default:
            System.out.println("try another command");
        }
    }
}
