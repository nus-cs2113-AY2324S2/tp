package recipeio.core;

import recipeio.command.Command;
import recipeio.enums.MealCategory;
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

    public RecipeIO() throws FileNotFoundException {
        storage = new Storage();
        try {
            storage.loadFile(recipes);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(FILE_NOT_FOUND_ERROR_MESSAGE);
        };
    }

    public void run() {
        String input;
        Scanner inputGetter = new Scanner(System.in);
        UI.sayHi();
        boolean isExitCommand = false;
        while (!isExitCommand) {
            try {
                String fullCommand = inputGetter.nextLine();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(recipes, UI, storage);
                isExitCommand = command.isExit();
            } catch (Exception e) {
                UI.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> testAllergies = new ArrayList<>();
        testAllergies.add("Dairy");
        testAllergies.add("Egg");
        testAllergies.add("Gluten");
        String testURL = "https://www.bbcgoodfood.com/recipes/ultimate-spaghetti-carbonara-recipe";
        Recipe testRecipe = new Recipe("Spaghetti Carbonara", 60, 1000,
                testAllergies, MealCategory.LUNCH, testURL);
        System.out.println("Hello");
        System.out.println(testRecipe);

        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
