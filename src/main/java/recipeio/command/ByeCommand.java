package recipeio.command;

import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(RecipeList tasks, UI ui, Storage storage) {
        ui.bye();
    }
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
