package seedu.duke;

import java.util.Optional;

public class Parser {
    protected String userInput;

    public static class EndProgramException extends Exception {

    }

    public Parser(String userInput) {
        this.userInput = userInput;
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
        case "exit":
            Group.exitGroup();
            break;
        case "help":
            // Help code here
            Help.printHelp();
            break;
        case "create":
            try {
                Optional<Group> optionalGroup = Group.getOrCreateGroup(argument);
                if (optionalGroup.isEmpty()) {
                    System.out.println("The group does not exist.");
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage()); // Print the message instructing to exit current group.
            }
            break;
        case "member":
            try {
                String[] memberDetails = argument.split("/group");
                if (memberDetails.length == 1) {
                    throw new ExpensesException("No group name for user! Add /group <group name>");
                }
                String memberName = memberDetails[0].trim();
                if (memberName.isEmpty()) {
                    throw new ExpensesException("No name for user! Add a name for the user");
                }
                String groupNameForUser = memberDetails[1].trim();
                User newUser = new User(memberName);
                Optional<Group> optionalGroup = Group.getOrCreateGroup(groupNameForUser);
                if (optionalGroup.isPresent()) {
                    Group group = optionalGroup.get();
                    group.addUsers(newUser);
                } else {
                    System.out.println("The group does not exist.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
                    String[] extractPayer = extractAmount[1].split("/user");
                    String payerName = extractPayer[0];
                    Expense newTransaction = new Expense(payerName, totalAmount, extractPayer);
                } catch (NumberFormatException e) {
                    System.out.println("Re-enter expense with amount as a proper number.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
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
            break;
        }
    }

    private String removeWhitespaces(String item) {
        String itemWithoutWhitespaces = item.replaceAll("\\s+", " ").trim();
        return itemWithoutWhitespaces;
    }
}
