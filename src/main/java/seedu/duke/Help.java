package seedu.duke;

public class Help {
    private static final String prompt =
            "Welcome, here is a list of commands:\n" +
                    "help: Access help menu.\n" +
                    "create <name>: Create a group.\n" +
                    "exit <name>: Exit current group.\n" +
                    "member <name> : Add a member to the group.\n" +
                    "expense /amount <amount> /paid <paid_by> /user <user_1> /user <user_2> ...: Add an expense.\n" +
                    "list: List all expenses in the group.\n" +
                    "balance <user_name>: Show user's balance.";

    static void printHelp() {
        System.out.println(prompt);
    }
}
