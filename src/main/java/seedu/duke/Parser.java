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
            Help.printHelp();
            break;
        case "group":
            // Group code here
            break;
        case "member":
            // Member code here
            break;
        case "expense":
            String[] removeExpenseTag = argument.split("/amount");
            String[] extractAmount = removeExpenseTag[1].split("/paid");
            String amount = extractAmount[0];
            String[] extractPayer = extractAmount[1].split("/user");
            String payer_name = extractPayer[0];
            Expense newTransaction = new Expense(payer_name,amount,extractPayer);
            break;
        case "list":
            // List code here
            break;
        case "balance":
            // Balance code here
            break;
        default:
            // Default clause
            break;
        }
    }
}
