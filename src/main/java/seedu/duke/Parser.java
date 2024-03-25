package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private final String userInput;

    /**
     * List of parameters to extract from user input.
     * For example, "/amount (amount)".
     * Add new Keys to extract additional user parameters for future functionality.
     */
    private static final String[] paramKeys = {"amount", "paid", "user"};

    /**
     * First word of user input.
     */
    private String command = null;

    /**
     * Input between first word and '/' character.
     * For example, "(command) (argument) /(parameter) (parameter input)...".
     */
    private String argument = null;

    /**
     * Additional parameters provided by user.
     */
    private HashMap<String, ArrayList<String>> params = createParams();

    public static class EndProgramException extends Exception {

    }

    /**
     * Creates a new HashMap with Keys equal to additional parameters users might input.
     * Values are arrays that store user input.
     *
     * @return HashMap with Keys in 'additionalFields' and empty array Values.
     */
    private HashMap<String, ArrayList<String>> createParams() {
        HashMap<String, ArrayList<String>> additionalInfo = new HashMap<>();

        for(String paramKey : paramKeys){
            additionalInfo.put(paramKey, new ArrayList<>());
        }

        return additionalInfo;
    }

    /**
     * Constructor for Test purposes.
     */
    public Parser(String userInput, String command, String argument,
                  String[] amount, String[] paid, String[] user) {
        this.userInput = userInput;
        this.command = command;
        this.argument = argument;
        this.params.put("amount", new ArrayList<>(List.of(amount)));
        this.params.put("paid", new ArrayList<>(List.of(paid)));
        this.params.put("user", new ArrayList<>(List.of(user)));
    }

    public Parser(String userInput) {
        this.userInput = userInput;
        this.parseUserInput();
    }

    /**
     * Process the String userInput and populates corresponding fields of Parser object.
     */
    public void parseUserInput() {
        String[] tokens = userInput.split(" ", 2);
        this.command = tokens[0].toLowerCase().trim();

        if (tokens.length == 1){
            return;
        }

        String[] arguments = tokens[1].split("/");
        this.argument = arguments[0].trim();

        for(int i = 1; i < arguments.length; i++){
            String[] subTokens = arguments[i].split(" ", 2);
            if (subTokens.length == 1){
                continue;
            }

            String subCommand = subTokens[0].toLowerCase().trim();
            String subArgument = subTokens[1].trim();
            if (!subArgument.isEmpty() && params.containsKey(subCommand)){
                params.get(subCommand).add(subArgument);
            }
        }
    }

    /**
     * Returns String summarising contents of Parser object.
     * For easier debug printing.
     *
     * @return Contents of Parser object.
     */
    @Override
    public String toString(){
        StringBuilder parser = new StringBuilder();

        parser.append("command: ").append(command).append("\n");

        parser.append("argument: ").append(argument).append("\n");

        for(String paramKey : paramKeys){
            parser.append(paramKey).append(": ");
            for(String item : params.get(paramKey)){
                parser.append(item).append(" ");
            }
            parser.append("\n");
        }

        return parser.toString();
    }


    public void handleUserInput() throws EndProgramException, ExpensesException {
        String[] tokens = userInput.split(" ", 2);

        String command = tokens[0].toLowerCase().trim();
        String argument = "";
        if (tokens.length > 1) {
            argument = tokens[1].trim();
        }

        switch (command) {
        case "bye":
            throw new EndProgramException();
        case "help":
            // Help code here
            Help.printHelp();
            break;
        case "create":
            GroupCommand.createGroup(argument);
            break;
        case "member":
            GroupCommand.addMember(argument);
            break;
        case "exit":
            GroupCommand.exitGroup();
            break;
        case "expense":
            try {
                String[] removeExpenseTag = argument.split("/amount");
                if (removeExpenseTag.length == 1) {
                    throw new ExpensesException("No description for expenses! Add /amount /paid /user");
                }
                String[] extractAmount = removeExpenseTag[1].split("/paid");
                String amount = extractAmount[0];
                amount = removeWhitespaces(amount);

                try {
                    float totalAmount = Float.parseFloat(amount);
                    String[] payeeList = extractAmount[1].split("/user");
                    String payerName = removeWhitespaces(payeeList[0]);
                    for(int i = 0; i < payeeList.length; i++){
                        payeeList[i] = removeWhitespaces(payeeList[i]);
                    }
                    Expense newTransaction = new Expense(payerName,totalAmount, payeeList);
                } catch (NumberFormatException e) {
                    System.out.println("Re-enter expense with amount as a proper number.");
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Empty /amount, /paid or /user. Add expenses using the correct format.");
            }
            break;
        case "list":
            // List code here
            break;
        case "balance":
            // Balance code here
            break;
        default:
            // Default clause
            System.out.println("That is not a command. " +
                    "Please use one of the commands given here");
            Help.printHelp();
            break;
        }

    }
    private String removeWhitespaces(String item) {
        String itemWithoutWhitespaces = item.replaceAll("\\s+", " ").trim();
        return itemWithoutWhitespaces;
    }

}
