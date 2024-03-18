package recipeio;

import recipeio.command.Command;
import recipeio.parser.InputParser;
import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class RecipeIO {
    /**
     * Main entry-point for the Recipe.IO application.
     */
    private static final String FILEPATH = "data/recipeio.txt";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Just a heads up, there was no file found to access your"
            + " saved recipe book.";
    private static Storage storage;
    private static RecipeList recipes;

    public RecipeIO() {
        storage = new Storage();
        recipes = new RecipeList();
        try {
            storage.loadFile(recipes);
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/recipe.txt").getParent());
            } catch (IOException except) {
                except.printStackTrace();
            }
        }
    }

    public void run() {
        Scanner inputGetter = new Scanner(System.in);
        UI.sayHi();
        boolean isExitCommand = false;
        while (!isExitCommand) {
            try {
                String fullCommand = inputGetter.nextLine();
                Command command = InputParser.parseCommand(fullCommand);
                command.execute(recipes, storage);
                isExitCommand = command.isExitCommand();
            } catch (Exception e) {
                UI.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new RecipeIO().run();
    }
}
