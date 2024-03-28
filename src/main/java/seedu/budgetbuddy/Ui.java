package seedu.budgetbuddy;

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
        System.out.println("5. Find Expenses       6. Split Expenses");
        System.out.println("7. Manage Recurring Bills");
        System.out.println("8. Change Currency");

        System.out.println(DIVIDER);
    }

    public void printDivider() {
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
        System.out.println("5. Find Expenses       6. Split Expenses");
        System.out.println("7. Manage Recurring Bills               ");
        System.out.println("8. Change Currency");
        System.out.println("Use 'menu INDEX' to select an option");
        System.out.println(DIVIDER);
    }

    /**
     * Displays the menu item based on the given index.
     * @param index The index of the menu item to display.
     */
    public void showMenuItem(int index) {
        assert index >= 0 : "Index must be a positive integer";

        System.out.println(DIVIDER);
        switch (index) {
        case 1:
            System.out.println("Manage Expenses");
            System.out.println("add expense c/CATEGORY a/AMOUNT d/DESCRIPTION");
            System.out.println("edit expense c/CATEGORY i/INDEX a/AMOUNT d/DESCRIPTION");
            System.out.println("delete expense i/INDEX");
            System.out.println("set budget c/CATEGORY b/BUDGET");
            System.out.println("budget print");
            break;
        case 2:
            System.out.println("Manage Savings");
            System.out.println("add savings c/CATEGORY a/AMOUNT");
            System.out.println("edit savings c/CATEGORY i/INDEX a/AMOUNT");
            System.out.println("reduce savings i/INDEX a/AMOUNT");
            break;
        case 3:
            System.out.println("View Expenses");
            System.out.println("list expenses [CATEGORY]");
            break;
        case 4:
            System.out.println("View Savings");
            System.out.println("list savings [CATEGORY]");
            break;
        case 5:
            System.out.println("Find Expenses");
            System.out.println("find expenses d/DESCRIPTION morethan/MINAMOUNT lessthan/MAXAMOUNT " +
                    "(Choose the parameters according to what you wish to search for)");
            break;
        case 6:
            System.out.println("Split Expenses");
            System.out.println("split expenses a/AMOUNT n/NUMBER d/DESCRIPTION");
            System.out.println("check splitted expenses");
            System.out.println("settle i/INDEX");
            break;
        case 7:
            System.out.println("Recurring Bills");
            System.out.println("rec newlist LISTNAME");
            System.out.println("rec removelist LISTNUMBER");
            System.out.println("rec viewlists");
            System.out.println("rec newexpense to/LISTNUMBER c/CATEGORY a/AMOUNT d/DESCRIPTION");
            System.out.println("rec viewexpenses LISTNUMBER");
            System.out.println("rec addrec LISTNUMBER");
            break;
        case 8:
            System.out.println("Change Currency");
            System.out.println("change currency [CURRENCY_CODE]");
            System.out.println("Default Currency: SGD");
            System.out.println("Choose from the following currency codes:");
            System.out.println("USD    SGD    EUR");
            System.out.println("JPY    KRW    MYR");
            System.out.println("CNY    HKD");
            break;
        default:
            System.out.println("Invalid menu index.");
            break;
        }
        System.out.println(DIVIDER);
    }
}
