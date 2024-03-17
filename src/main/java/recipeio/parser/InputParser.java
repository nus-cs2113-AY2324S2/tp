package recipeio.parser;

import recipeio.recipe.RecipeList;
import storage.Storage;

/**
 * Represents the parser of the program.
 */
public class InputParser {
    public static final int KEY = 0;
    public static final String SPACE = " ";
    private final String line;
    private final String processedInput;
    private final String command;
    private final Storage storage;
    private final RecipeList recipes;

    public InputParser(String input, Storage storage, RecipeList recipes) {
        this.line = input;
        this.storage = storage;
        this.recipes = recipes;
        this.processedInput = input.toLowerCase().trim();
        this.command = processedInput.split(SPACE, 2)[KEY];
    }
}