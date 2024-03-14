package seedu.brokeculator;

public class ExitParser {
    public static Command parseInput(String userInput) {
        if (userInput.equals("exit")) {
            return new ExitCommand();
        }
        return null;
    }
}
