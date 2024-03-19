package seedu.duke;

public class Parser {
    protected String userInput;

    public static class EndProgramException extends Exception {

    }

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public void handleUserInput() throws EndProgramException {
        String[] tokens = userInput.split(" ", 2);

        String command = tokens[0].toLowerCase().trim();
        String argument = "";
        if (tokens.length > 1) {
            argument = tokens[1].trim();
        }

        switch(command) {
        case "bye":
            throw new EndProgramException();
        case "help":
            // Help code here
            break;
        case "group":
            // Group code here
            break;
        case "member":
            // Member code here
            break;
        case "expense":
            String[] extractExpense = argument.split(" ", 2);
            String expense = extractExpense[0];
            int amount = Integer.parseInt(extractExpense[1]);
            Expense newExpense = new Expense(expense,amount);
            break;
        case "list":
            // List code here
            break;
        case "balance":
            // seedu.duke.Balance code here
            break;
        default:
            // Default clause
            break;
        }
    }
}
