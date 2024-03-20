package seedu.duke;

public class Help {
    private static final String prompt =
            "Welcome\n" +
            "help: Access help menu.\n" +
            "group <name>: Create or enter a group.\n" +
            "member <name>: Add a member to the group.\n" +
            "expense /amount <amount> /paid <paid_by> /user <user_1> /user <user_2> ...: Add an expense.\n" +
            "list: List all expenses in the group.\n" +
            "balance <user_name>: Show user's balance.";

    static void printHelp() {
        System.out.println(prompt);
    }
}
