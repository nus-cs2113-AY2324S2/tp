package recipeio.parser;

import recipeio.command.HelpCommand;
import recipeio.command.ByeCommand;
import recipeio.command.AddRecipeCommand;
import recipeio.command.Command;
import recipeio.command.DeleteRecipeCommand;
import recipeio.enums.MealCategory;
import recipeio.recipe.RecipeList;
import recipeio.recipe.Recipe;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the parser of the program.
 */
public class InputParser {
    public static final int KEY = 0;
    public static final String SPACE = " ";
    private static final String INVALID_TASK_FORMAT_ERROR_MESSAGE = "Sorry. I couldn't " +
            "understand. Please follow the correct format.";
    private static final String INTEGER_NEEDED_ERROR_MESSAGE = "Make sure you enter an integer for cook time and" +
            "calories!";
    private static final String MEAL_CATEGORY_ERROR_MESSAGE = "Please enter a valid meal category. Here are your" +
            "options:\n BREAKFAST, LUNCH, DINNER, APPETIZER, DESSERT";
    private final String line;
    private final String[] processedInput;
    private final String command;
    private final RecipeList recipes;

    public InputParser(String input, RecipeList recipes) {
        this.line = input;
        this.recipes = recipes;
        this.processedInput = input.toLowerCase().trim().split(SPACE, 2);
        this.command = processedInput.length > 1 ? processedInput[KEY] : "";
    }
    public static Command parseCommand(String command) throws Exception {
        String keyword = command.split(" ")[0];
        switch (keyword) {
        case AddRecipeCommand.COMMAND_WORD:
            Recipe recipe = parseAddCommand(command);
            return new AddRecipeCommand(recipe);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case DeleteRecipeCommand.COMMAND_WORD:
            int recipeToDelete = parseDeleteCommand(command);
            return new DeleteRecipeCommand(recipeToDelete);
        default:
            return new HelpCommand();
        }
    }

    public static Recipe parseAddCommand(String command) throws Exception {
        String[] splitByDelimeter = command.trim().split(" / ");
        if (splitByDelimeter.length != 6) {
            throw new Exception(INVALID_TASK_FORMAT_ERROR_MESSAGE);
        }
        String recipeName = splitByDelimeter[0].split("add")[1];
        try {
            Integer.parseInt(splitByDelimeter[1].trim());
            Integer.parseInt(splitByDelimeter[2].trim());
        } catch (NumberFormatException e){
            throw new Exception(INTEGER_NEEDED_ERROR_MESSAGE);
        }
        int cookTime = Integer.parseInt(splitByDelimeter[1].trim());
        int calories = Integer.parseInt(splitByDelimeter[2].trim());
        String[] allergies = splitByDelimeter[3].trim().split(", ");
        ArrayList<String> allergiesList = new ArrayList<>();
        Collections.addAll(allergiesList, allergies);
        try {
            MealCategory.valueOf(splitByDelimeter[4].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception(MEAL_CATEGORY_ERROR_MESSAGE);
        }
        MealCategory category = MealCategory.valueOf(splitByDelimeter[4].trim().toUpperCase());
        String url = splitByDelimeter[5].trim();
        Recipe newRecipe = new Recipe(recipeName, cookTime, calories, allergiesList, category, url);
        return newRecipe;
    }

    public static int parseDeleteCommand(String command) throws Exception {
        String[] splitByDelimeter = command.trim().split(" ");
        if (splitByDelimeter.length != 2) {
            throw new Exception(INVALID_TASK_FORMAT_ERROR_MESSAGE);
        }
        String recipeNumberString = splitByDelimeter[1].trim();
        try {
            Integer.parseInt(recipeNumberString);
        } catch (NumberFormatException e){
            throw new Exception(INTEGER_NEEDED_ERROR_MESSAGE);
        }
        int recipeNumberInt = Integer.parseInt(recipeNumberString);
        return recipeNumberInt;
    }
}
