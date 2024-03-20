package recipeio;

public class Constants {
    public static final int INDEX_COMMAND = 0;
    public static final int INDEX_ID = 1;
    public static final int MAX_RECIPES = 99999;

    public static final String RECIPE_NAME_PREFIX = "/name ";
    public static final String RECIPE_TIME_PREFIX = "/time ";
    public static final String RECIPE_DELIMETER = "/";

    public static final String EXIT_COMMAND = "exit";
    public static final String ADD_COMMAND = "add";
    public static final String DELETE_COMMAND = "delete";
    public static final String LIST_COMMAND = "list";
    public static final String HELP_COMMAND = "help";
    public static final String FIND_BY_NAME = "name";
    public static final String INVALID_TASK_FORMAT_ERROR_MESSAGE = "Sorry. I couldn't understand. Please follow the " +
            "correct format.";
    public static final String INTEGER_NEEDED_ERROR_MESSAGE = "Make sure you enter an integer for cook time and" +
            "calories!";
    public static final String MEAL_CATEGORY_ERROR_MESSAGE = "Please enter a valid meal category. Here are your" +
            "options:\n BREAKFAST, LUNCH, DINNER, APPETIZER, DESSERT";
    static final String REGEX_MATCHER = RECIPE_NAME_PREFIX + "|"
            + RECIPE_TIME_PREFIX;

}
