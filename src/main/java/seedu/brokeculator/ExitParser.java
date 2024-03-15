package seedu.brokeculator;
public class ExitParser {
    private static final String INVALID_EXIT_COMMAND = "Invalid command. Please enter 'exit' to exit the program.";
    public static Command parseInput(String userInput) {

        if (userInput.equals(CommandsEnumerator.EXIT_COMMAND.getString())) {
            return new ExitCommand();
        } else {
            return InvalidCommand(INVALID_EXIT_COMMAND);
        }
    }
}
