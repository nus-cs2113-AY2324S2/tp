package recipeio.command;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

public class DeleteRecipeCommand extends Command{
    private final int toDelete;
    public static final String COMMAND_WORD = "delete";
    private final String DELETE_ERROR_MESSAGE = "Oops, for some reason this recipe could not be deleted.";
    private final String SAVING_ERROR_MESSAGE = "Sorry, there was an error trying to save your recipe book.";

    public DeleteRecipeCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * Adds a recipe to the recipe book and saves it to storage.
     *
     * @param recipes the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws Exception if there was an error adding the task or saving it to storage.
     */
    @Override
    public void execute(RecipeList recipes, UI ui, Storage storage) throws Exception {
        Recipe deleted = recipes.get(toDelete - 1);
        try {
            recipes.deleteRecipe(toDelete);
        } catch (Exception e) {
            throw new Exception(DELETE_ERROR_MESSAGE);
        }

        try {
            storage.saveFile(recipes);
        } catch (Exception e) {
            throw new Exception(SAVING_ERROR_MESSAGE);
        }
        ui.deleteRecipePrinter(deleted, recipes.getSize());
    }
}



