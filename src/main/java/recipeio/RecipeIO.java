package recipeio;

import recipeio.recipe.RecipeList;
import recipeio.ui.UI;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
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
            logger.log(Level.INFO, "not an exit command.");
            recipeList.executeCommand(parsedCommand, userInput);
            userInput = ui.getUserInput();
            parsedCommand = InputParser.parseCommand(userInput);
        }
    }

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("logger.log", false);
            fileHandler.setLevel(Level.INFO);
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
            assert logger.getHandlers().length > 0 : "File handler not added to logger";
        } catch (IOException e){
            logger.log(Level.SEVERE, "File logger not working");
        }
    }

    public static void main(String[] args) {
        setUpLogger();
        new RecipeIO().run();
    }
}
