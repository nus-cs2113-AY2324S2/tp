package seedu.duke;

public class Ui {

    private static final String DIVIDER = "__________________________________________________";

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("                     BudgetBuddy");
        System.out.println(DIVIDER);
        System.out.println("Welcome to BudgetBuddy, to start, please type \"menu INDEX\" " +
                "to view commands for the respective functions");
        System.out.println("To view all menu items again, type \"menu\".");
        System.out.println(DIVIDER);
        System.out.println("1. Manage Expenses     3. View Expenses");
        System.out.println("2. Manage Savings      4. View Savings");
        System.out.println(DIVIDER);
    }

    public void showGoodbye() {
        System.out.println("Goodbye! Thank you for using BudgetBuddy.");
    }

    public void showMenuTitles() {
        System.out.println(DIVIDER);
        System.out.println("Menu Options:");
        System.out.println("1. Manage Expenses     3. View Expenses");
        System.out.println("2. Manage Savings      4. View Savings");
        System.out.println("Use 'menu INDEX' to select an option");
        System.out.println(DIVIDER);
    }

    public void showMenuItem(int index) {
        System.out.println(DIVIDER);
        switch (index) {
        case 1:
            System.out.println("Manage Expenses");
            System.out.println("add expense c/CATEGORY a/AMOUNT d/DESCRIPTION");
            System.out.println("edit expense c/CATEGORY i/INDEX a/AMOUNT");
            System.out.println("delete expense c/CATEGORY i/INDEX");
            break;
        case 2:
            System.out.println("Manage Savings");
            System.out.println("add savings c/CATEGORY a/AMOUNT");
            System.out.println("edit savings c/CATEGORY i/INDEX a/AMOUNT");
            System.out.println("reduce savings c/CATEGORY i/INDEX a/AMOUNT");
            break;
        case 3:
            System.out.println("View Expenses");
            System.out.println("list expense [CATEGORY]");
            break;
        case 4:
            System.out.println("View Savings");
            System.out.println("list savings [CATEGORY]");
            break;
        default:
            System.out.println("Invalid menu index.");
            break;
        }
        System.out.println(DIVIDER);
    }
}
