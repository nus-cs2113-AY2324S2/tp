package recipeio.command;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

public class AddRecipeCommand extends Command{
    public static final String COMMAND_WORD = "add";
    private static final String ADDING_ERROR_MESSAGE = "Oops, for some reason this recipe could not be added to " +
            "your recipe book";
    private static final String SAVING_ERROR_MESSAGE = "Sorry, there was an error trying to save your recipe book.";
    private final Recipe toAdd;

    /**
     * Constructs an add command with the recipe to add.
     *
     * @param toAdd the recipe to add.
     */
    public AddRecipeCommand(Recipe toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Adds a recipe to the recipe book and saves it to storage.
     *
     * @param recipes the list of tasks.
     * @param storage the storage.
     * @throws Exception if there was an error adding the task or saving it to storage.
     */
    @Override
    public void execute(RecipeList recipes, Storage storage) throws Exception {
        try {
            recipes.addRecipe(toAdd);
        } catch (Exception e) {
            throw new Exception(ADDING_ERROR_MESSAGE);
        }

        try {
            storage.saveFile(recipes);
        } catch (Exception e) {
            throw new Exception(SAVING_ERROR_MESSAGE);
        }

        UI.addRecipePrinter(toAdd, recipes.getSize());
    }
}



