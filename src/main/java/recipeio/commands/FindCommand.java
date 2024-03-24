package recipeio.commands;
import recipeio.recipe.Recipe;
import recipeio.ui.UI;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FindCommand {
    private static final Logger logr = Logger.getLogger("FindCommand");
    public static void execute(String command, ArrayList<Recipe> recipes) {
        logr.log(Level.INFO, "split the command into words separated by space");
        String[] commandWords = command.split(" ");
        assert commandWords.length >= 3;

        logr.log(Level.INFO, "trim the spaces at both ends in each word");
        for (int i = 0; i < commandWords.length; i++) {
            commandWords[i] = commandWords[i].trim();
        }

        logr.log(Level.INFO, "concatenate words to form user's search data");
        String searchData = "";
        for (int i = 2; i < commandWords.length; i++){
            searchData = searchData + " " + commandWords[i];
        }
        if (commandWords[1].equals("name")) {
            logr.log(Level.INFO, "find by recipe name");
            FindByNameCommand.execute(command, recipes);
        } else if (commandWords[1].equals("allergy")) {
            logr.log(Level.INFO, "find by allergy");
            FindByAllergyCommand.execute(command, recipes);
        } else if (commandWords[1].equals("date")) {
            //add find by date command here
        } else { //if the find command is invalid
            logr.log(Level.WARNING, "Invalid find command option");
            UI.printMessage("Sorry chef, I can't understand what you are trying to find!");
        }
    }
}
