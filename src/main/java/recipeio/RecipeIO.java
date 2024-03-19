package recipeio;

import recipeio.recipe.RecipeList;
import recipeio.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Main entry-point for the Recipe.IO application.
 */
public class RecipeIO {
    private static UI ui;
    private static final Logger logger = Logger.getLogger("RecipeIO Logger");
    private final RecipeList recipeList;

    public RecipeIO() {
        ui = new UI();
        recipeList = new RecipeList();
    }

    public void run() {
        UI.sayHi();
        runCommandLoopUntilExitCommand();
        UI.bye();
    }

    public void runCommandLoopUntilExitCommand() {
        logger.log(Level.INFO, "asking for first input from user.");
        String userInput = ui.getUserInput();
        String parsedCommand = InputParser.parseCommand(userInput);

        while (!parsedCommand.equals(Constants.EXIT_COMMAND)) {
            recipeList.executeCommand(parsedCommand, userInput);
            userInput = ui.getUserInput();
            parsedCommand = InputParser.parseCommand(userInput);
        }
    }

    public static void main(String[] args) {
        new RecipeIO().run();
    }
}
