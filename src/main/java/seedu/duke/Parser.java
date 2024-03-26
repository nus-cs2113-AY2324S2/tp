package seedu.duke;

import java.util.*;

public class Parser {

    /**
     * List of parameters to extract from user input.
     * For example, "/amount (amount)".
     * Add new Keys to extract additional user parameters for future functionality.
     */
    private static final String[] paramKeys = {"amount", "paid", "user"};

    private final String userInput;
    private static Map<String, Group> groups = new HashMap<>();
    private static Optional<Group> currentGroup = Optional.empty();

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
     * Process the String userInput
     * populates corresponding fields of Parser object.
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
            if (!subArgument.isEmpty() &&
                    params.containsKey(subCommand)){
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


    public void handleUserInput() throws EndProgramException,
            ExpensesException {
        switch (command) {
        case "bye":
            throw new EndProgramException();
        case "help":
            // Help code here
            Help.printHelp();
            break;
        case "create":
            currentGroup = Optional.of(currentGroup.map(group -> {
                System.out.printf(
                        "Please exit %s before creating a new one%n",
                        group);
                    return group;
                }).orElseGet(() -> {
                String groupName = argument;
                boolean isGroupCreated = groups.containsKey(groupName);
                if (isGroupCreated) {
                    Group queriedGroup = groups.get(groupName);
                    System.out.println("Group already exists! " +
                            "You are now in " + queriedGroup);
                    return queriedGroup;
                }
                Group createdGroup = Group.createGroup(groupName);
                groups.put(groupName, createdGroup);
                System.out.println("Creating new group! " +
                        "You are now in " + createdGroup);
                return createdGroup;
            }));
            break;
        case "member":
            String memberName = argument;
            currentGroup.ifPresentOrElse(group -> group.addMember(memberName),
                    () -> System.out.println("You are not in a group!!"));
            break;
        case "exit":
            currentGroup.ifPresentOrElse(
                    group -> {
                        System.out.printf("Exiting %s%n", group);
                        currentGroup = Optional.empty();
                    },
                    () -> currentGroup = Optional.empty());
            break;
        case "expense":

            // Checks for missing Expense Parameters
            String[] expenseParams = {"amount", "paid", "user"};
            for(String expenseParam : expenseParams){
                if(params.get(expenseParam).isEmpty()){
                    String exceptionMessage =
                            "No description for expenses! Add /" +
                                    expenseParam;
                    throw new ExpensesException(exceptionMessage);
                }
            }

            // Checks if amount is a valid Float
            float totalAmount;
            try {
                totalAmount = Float.parseFloat(params.get("amount").get(0));
            } catch (NumberFormatException e) {
                String exceptionMessage =
                        "Re-enter expense with amount as a proper number.";
                throw new ExpensesException(exceptionMessage);
            }

            // Obtain necessary information from 'params', create new Expense
            ArrayList<String> payeeList = params.get("user");
            String payerName = params.get("paid").get(0);
            payeeList.add(0, payerName);

            Expense newTransaction = new Expense(payerName,
                    totalAmount, payeeList.toArray(new String[0]));
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
}
