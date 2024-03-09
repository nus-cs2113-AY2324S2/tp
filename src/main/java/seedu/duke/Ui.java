package seedu.duke;

public class Ui {
    private static final String DIVIDER = "__________________________________________________";

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("                     BudgetBuddy");
        System.out.println(DIVIDER);
        System.out.println("Welcome to BudgetBuddy, to start, please type \"menu\" [list number] " +
                "to view commands for the respective functions");
        System.out.println("To view all menu items again, type \"menu\".");
        System.out.println(DIVIDER);
        System.out.println("1. Manage Expenses     3. View Expenses");
        System.out.println("2. Manage Savings      4. View Savings");
        System.out.println(DIVIDER);
    }

    public void showGoodbye() {
        System.out.println(DIVIDER);
        System.out.println("Goodbye! Thank you for using BudgetBuddy.");
    }

}
