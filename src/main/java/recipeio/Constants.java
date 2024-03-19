package recipeio;

public class Constants {
    public static final int INDEX_COMMAND = 0;
    public static final int INDEX_ID = 1;

    public static final String RECIPE_NAME_PREFIX = "/name ";
    public static final String RECIPE_TIME_PREFIX = "/time ";

    public static final String EXIT_COMMAND = "exit";
    public static final String ADD_COMMAND = "add";
    public static final String DELETE_COMMAND = "delete";
    public static final String LIST_COMMAND = "list";


    static final String REGEX_MATCHER = RECIPE_NAME_PREFIX + "|"
            + RECIPE_TIME_PREFIX;

}
