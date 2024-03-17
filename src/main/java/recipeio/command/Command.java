package recipeio.command;

import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

/**
 * A command class to keep track of different user actions.
 * Inspiration from addressbook-level 2 Github.
 */
public class Command {
    public abstract void execute(RecipeList recipes, UI ui, Storage storage) throws Exception;

    public boolean isExitCommand() {
        return false;
    }
}
