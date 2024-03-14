package seedu.brokeculator;
public class ExitParser {
    public static Command parseInput(String userInput) {
        private static final String INVALID_EXIT_COMMAND = "Invalid command. Please enter 'exit' to exit the program.";
        if (userInput.equals("exit")) {
            return new ExitCommand();
        } else {
            return InvalidCommand(INVALID_EXIT_COMMAND);
        }
    }
}
