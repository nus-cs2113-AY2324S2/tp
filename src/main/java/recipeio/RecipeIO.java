package recipeio;

import recipeio.recipe.RecipeList;
import recipeio.ui.UI;
/**
 * Main entry-point for the Recipe.IO application.
 */
public class RecipeIO {
    private static UI ui;
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
