package recipeio.core;

import recipeio.command.Command;
import recipeio.enums.MealCategory;
import recipeio.parser.InputParser;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;
import recipeio.storage.Storage;
import recipeio.ui.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeIO {
    /**
     * Main entry-point for the Recipe.IO application.
     */
    private static final String FILEPATH = "data/recipeio.txt";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Just a heads up, there was no file found to access your " +
            "saved recipe book.";
    private static Storage storage;
    private static RecipeList recipes;
    private static UI ui;

    public RecipeIO() throws FileNotFoundException {
        storage = new Storage();
        recipes = new RecipeList();
        try {
            storage.loadFile(recipes);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(FILE_NOT_FOUND_ERROR_MESSAGE);
        };
    }

    public void run() {
        Scanner inputGetter = new Scanner(System.in);
        UI.sayHi();
        boolean isExitCommand = false;
        while (!isExitCommand) {
            try {
                String fullCommand = inputGetter.nextLine();
                System.out.println("this is the full command: " + fullCommand);
                Command command = InputParser.parseCommand(fullCommand);
                command.execute(recipes, ui, storage);
                isExitCommand = command.isExitCommand();
            } catch (Exception e) {
                UI.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new RecipeIO().run();
    }
}
