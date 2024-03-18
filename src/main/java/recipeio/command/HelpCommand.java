package recipeio.command;

import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

public class HelpCommand extends Command {
    @Override
    public void execute(RecipeList recipes, UI ui, Storage storage) throws Exception {
        ui.printHelpCommand();
    }
}
