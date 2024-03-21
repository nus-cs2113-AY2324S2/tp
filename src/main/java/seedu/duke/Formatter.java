package seedu.duke;
import java.util.logging.Logger;
public class Formatter {
    private static final Logger logger = Logger.getLogger(Formatter.class.getName());

    public static void printGoalBeforeShot(int roundCount) {
        assert roundCount > 0 : "Round count should be greater than zero.";
        logger.info("Round " + roundCount);
        System.out.println("Round " + roundCount);
        System.out.println("_______________________________");
        System.out.println("|         |         |         |");
        System.out.println("|    0    |    1    |    2    |");
        System.out.println("|         |         |         |");
        System.out.println("|         |         |         |");
        System.out.println("\nSelect direction to shoot : [0-2]");
    }
    public static void printGoalAfterShot(boolean goalScored) {
        if (goalScored) {
            logger.info("GOAL!!!!");
            System.out.println("_______________________________");
            System.out.println("| *    *  |  *  *   | *      *|");
            System.out.println("|    *    |       * |     *   |");
            System.out.println("|*   *    | *   *   |  *   *  |");
            System.out.println("|      *  |    *    |*      * |");
            System.out.println("GOAL!!!!");
        }
        else {
            logger.info("No goal :((((");
            System.out.println("_______________________________");
            System.out.println("\\         \\         \\         \\");
            System.out.println(" \\         \\         \\         \\");
            System.out.println("  \\         \\         \\         \\");
            System.out.println("   \\         \\         \\         \\");
            System.out.println("no goal :((((");
        }
    }

    /**
     * Prints an error message indicating wrong command is entered
     */
    public static void printErrorWrongCommand() {
        System.out.println("\t CommandParser: Command not found ");
    }

    /**
     * Prints a error message indicating command failed to execute
     */
    public static void printErrorExecutionFail() {
        System.out.println("\t Ui: Command could not be executed ");
        System.out.println("\t Try again");
    }

    /**
     * Prints an error message indicating an unknown error has occured
     */
    public static void printErrorUnknown() {
        System.out.println("\t Ui: Unexpected error ");
    }

    /**
     * Prints an error message indicating there is at least one bad argument provided
     */
    public static void printErrorBadTokens() {
        System.out.println("\t Ui: Bad Token Error, please check your arguments");
    }

    /**
     * Prints an error message stating the number of arguments given versus the number of arguments expected for given command
     *
     * @param correctArgumentCount the expected number of arguments for given command
     * @param userArgumentCount    the given number of arguments read from user input
     * @param userCommandName      the command name read from user input
     */
    public static void printErrorArgumentsMismatch(String userCommandName, int userArgumentCount, int correctArgumentCount) {
        System.out.printf("\t SyntaxAnalyser: Command %s contains too %s arguments. Given: %d - Expected: %d\n",
                userCommandName,
                userArgumentCount < correctArgumentCount ? "few" : "many",
                userArgumentCount, correctArgumentCount);
    }

    /**
     * Prints an error message indicating that the regex pattern failed to match at a given argument position for a given command name
     *
     * @param argumentPosition the index position of the argument which failed to match the correct type
     * @param regex            the correct type for the argument
     * @param COMMAND_NAME     the name of the given command
     */
    public static void printErrorWrongArgumentType(String COMMAND_NAME, String regex, int argumentPosition) {
        int userRanking = argumentPosition + 1;
        String rankingSuffix;
        switch (userRanking) {
        case 1:
            rankingSuffix = "st";
            break;
        case 2:
            rankingSuffix = "nd";
            break;
        case 3:
            rankingSuffix = "rd";
            break;
        default:
            rankingSuffix = "th";
            break;
        }
        System.out.printf("\t SyntaxAnalyser: %s expects the %d%s argument to be %s\n",
                COMMAND_NAME, userRanking, rankingSuffix, regex);
    }

    /**
     * Prints an error message indicating the selected index is not in range
     */
    public static void printErrorIndexOutOfRange() {
        System.out.println("\t List: Selected index not in range. Try again.");
    }

    /**
     * Prints an error message indicating the cache file is corrupted at a given line
     *
     * @param lineNum the line number which does not follow the file format of cache file
     */
    public static void printFileCorruptionError(int lineNum) {
        System.out.println("Cache file is corrupted at line " + lineNum);
        System.out.println("Failed to load cache");
        System.out.println("Program will start with empty list");
    }

}

