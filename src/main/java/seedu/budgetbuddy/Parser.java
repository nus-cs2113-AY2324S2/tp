package seedu.budgetbuddy;

import seedu.budgetbuddy.command.AddExpenseCommand;
import seedu.budgetbuddy.command.AddSavingCommand;
import seedu.budgetbuddy.command.EditExpenseCommand;
import seedu.budgetbuddy.command.EditSavingCommand;
import seedu.budgetbuddy.command.ReduceSavingCommand;
import seedu.budgetbuddy.command.DeleteExpenseCommand;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;

import seedu.budgetbuddy.exception.BudgetBuddyException;

public class Parser {

    private String extractDetailsForAdd(String details, String prefix) {
        int startIndex = details.indexOf(prefix) + prefix.length();
        int endIndex = details.length();

        String[] nextPrefixes = { "c/", "a/", "d/" };
        for (String nextPrefix : nextPrefixes) {
            if (details.indexOf(nextPrefix, startIndex) != -1 && details.indexOf(nextPrefix, startIndex) < endIndex) {
                endIndex = details.indexOf(nextPrefix, startIndex);
            }
        }
        return details.substring(startIndex, endIndex).trim();
    }

    public Boolean isListCommand(String input) {
        return input.startsWith("list");
    }

    /**
     * Checks if the provided input starts with the word "menu" .
     *
     * @param input The user input string
     * @return true if user input starts with "menu", else returns false
     */
    public Boolean isMenuCommand(String input) {
        return input.startsWith("menu");
    }

    public Boolean isExitCommand(String input) {
        return input.startsWith("bye");
    }

    public Boolean isAddExpenseCommand(String input) {
        return input.startsWith("add expense");
    }

    public Boolean isAddSavingCommand(String input) {
        return input.startsWith("add savings");
    }

    public Boolean isEditExpenseCommand(String input) {
        return input.startsWith("edit expense");
    }

    public Boolean isEditSavingCommand(String input) {
        return input.startsWith("edit savings");
    }

    public Boolean isDeleteExpenseCommand(String input) {
        return input.startsWith("delete");
    }

    public Boolean isReduceSavingCommand(String input) {
        return input.startsWith("reduce");
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

    /**
     * Processes all menu commands and returns the corresponding Command object.
     * This method interprets the user's input and displays either the entire menu
     * or the associated menu item
     *
     * @param input The full user input string
     * @return A new MenuCommand object with the specified index, returns null if
     *         index is not an integer
     */
    public Command handleMenuCommand(String input) {
        if (input.trim().equals("menu")) {
            return new MenuCommand(0);
        }
        try {
            String indexAsString = input.substring(5);
            int index = Integer.parseInt(indexAsString);
            return new MenuCommand(index);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Command handleAddExpenseCommand(ExpenseList expenses, String input) {
        if (input == null || !input.contains("c/") || !input.contains("a/") || !input.contains("d/")) {
            System.out.println("Invalid command format.");
            return null;
        }
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Expense details are missing.");
            return null;
        }
        String details = parts[1];

        String category = extractDetailsForAdd(details, "c/");
        if (category.isEmpty()) {
            System.out.println("category is missing.");
            return null;
        }
        String amount = extractDetailsForAdd(details, "a/");
        if (amount.isEmpty()) {
            System.out.println("amount is missing.");
            return null;
        }

        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                throw new BudgetBuddyException(amount + " is not a valid amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return null;
        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
            return null;
        }

        String description = extractDetailsForAdd(details, "d/");
        if (description.isEmpty()) {
            System.out.println("description is missing.");
            return null;
        }
        return new AddExpenseCommand(expenses, category, amount, description);
    }

    public Command handleAddSavingCommand(SavingList savings, String input) {
        if (input == null || !input.contains(" ") || !input.contains("c/") || !input.contains("a/")){
            System.out.println("Invalid command format.");
            return null;
        }

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Saving details are missing.");
            return null;
        }

        String details = parts[1];
        String category = extractDetailsForAdd(details, "c/");
        if (category.isEmpty()){
            System.out.println("Category is missing.");
            return null;
        }
        
        String amount = extractDetailsForAdd(details, "a/");
        if (amount.isEmpty()) {
            System.out.println("amount is missing.");
            return null;
        }

        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                throw new BudgetBuddyException(amount + " is not a valid amount.");
            }
           
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return null;
        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new AddSavingCommand(savings, category, amount);
    }

    public Command handleEditExpenseCommand(ExpenseList expenses, String input) {
        String[] parts = input.split(" ");
        String category = null;
        int index = -1;
        double amount = -1;
        String description = null;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
            } else if (part.startsWith("i/")) {
                try {
                    index = Integer.parseInt(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid index format
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    return null;
                }
            } else if (part.startsWith("d/")) {
                description = part.substring(2);
            }
        }

        // Validate required fields
        if (category != null && index != -1 && amount != -1 && description != null) {
            return new EditExpenseCommand(expenses, category, index, amount, description);
        } else {
            // Handle incomplete command
            return null;
        }
    }

    public Command handleEditSavingCommand(SavingList savings, String input) {
        String[] parts = input.split(" ");
        String category = null;
        int index = -1;
        double amount = -1;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
            } else if (part.startsWith("i/")) {
                try {
                    index = Integer.parseInt(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid index format
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    return null;
                }
            }
        }

        // Validate required fields
        if (category != null && index != -1 && amount != -1) {
            return new EditSavingCommand(savings, category, index, amount);
        } else {
            // Handle incomplete command
            return null;
        }
    }

    public Command handleDeleteExpenseCommand(ExpenseList expenses, String input) {
        String[] parts = input.split("i/", 2);
        try {
            String indexAsString = parts[1].trim();
            int index = Integer.parseInt(indexAsString) - 1;
            return new DeleteExpenseCommand(expenses, index);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public Command handleReduceSavingCommand(SavingList savings, String input) {
        String description = input.replace("reduce", "").trim();

        if (description.contains("i/") && description.contains("a/")) {
            String[] parts = description.split("i/|a/", 3);

            String indexToReduceAsString = parts[1].trim();
            String amountToReduceAsString = parts[2].trim();
            int indexToReduce = Integer.parseInt(indexToReduceAsString) - 1;
            double amountToReduce = Double.parseDouble(amountToReduceAsString);

            return new ReduceSavingCommand(savings, indexToReduce, amountToReduce);
        }

        return null;

    }

    /**
     * Parses a string input into a Command object and returns the associated
     * command to handle the user input
     * 
     * @param input The user input string.
     * @return A Command object corresponding to the user input, or null if the
     *         input is invalid.
     */
    public Command parseCommand(ExpenseList expenses, SavingList savings, String input) {

        if (isMenuCommand(input)) {
            return handleMenuCommand(input);
        }

        if (isAddExpenseCommand(input)) {
            return handleAddExpenseCommand(expenses, input);
        }

        if (isAddSavingCommand(input)) {
            return handleAddSavingCommand(savings, input);
        }

        if (isEditExpenseCommand(input)) {
            return handleEditExpenseCommand(expenses, input);
        }

        if (isEditSavingCommand(input)) {
            return handleEditSavingCommand(savings, input);
        }

        if (isDeleteExpenseCommand(input)) {
            return handleDeleteExpenseCommand(expenses, input);
        }

        if (isReduceSavingCommand(input)) {
            return handleReduceSavingCommand(savings, input);
        }

        if (isListCommand(input)) {
            return handleListCommand(input, expenses, savings);
        }

        return null;
    }

}
