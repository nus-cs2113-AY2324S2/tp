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
        case "group":
            // Group code here
        case "member":
            // Member code here
        case "expense":
            String[] extractExpense = argument.split(" ", 2);
            String expense = extractExpense[0];
            int amount = Integer.parseInt(extractExpense[1]);
            ExpenseAdder newExpense = new ExpenseAdder(expense,amount);
        case "list":
            // List code here
        case "balance":
            // Balance code here
        }
    }
}
