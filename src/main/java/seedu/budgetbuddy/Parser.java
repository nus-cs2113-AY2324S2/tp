package seedu.budgetbuddy;

public class Parser {

    public Boolean isListCommand(String input) {
        return input.startsWith("list");
    }


    public Command handleListCommand(String input, ExpenseList expenseList, SavingList savingList) {
        String[] parts = input.split(" ");
        String action = parts[0];

        switch (action) {
        case "list":
            if (parts.length == 2) {
                // List expenses or savings
                String listType = parts[1];
                if (listType.equalsIgnoreCase("expense")) {
                    return new ListExpenseCommand(expenseList);
                } else if (listType.equalsIgnoreCase("savings")) {
                    return new ListSavingsCommand(savingList, expenseList);
                }
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("expense")) {
                String filterCategory = parts[2];
                return new ListExpenseCommand(expenseList, filterCategory);
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("savings")) {
                String filterCategory = parts[2];
                return new ListSavingsCommand(savingList, expenseList, filterCategory); // Pass expenseList instance
            } else {
                return null;
            }
            break;
        // Add, edit, delete, and other commands...
        default:
            return null;
        }

        return null;


    }

    public Command parseCommand(String input, ExpenseList expenseList, SavingList savingList) {

        if (isListCommand(input)) {
            return handleListCommand(input, expenseList, savingList);
        }

        return null;
    }

}
