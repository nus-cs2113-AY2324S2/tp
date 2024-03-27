package seedu.budgetbuddy;

import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.DeleteExpenseCommand;
import seedu.budgetbuddy.command.EditExpenseCommand;
import seedu.budgetbuddy.command.EditSavingCommand;
import seedu.budgetbuddy.command.FindExpensesCommand;
import seedu.budgetbuddy.command.ListBudgetCommand;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.commandcreator.AddExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.AddSavingCommandCreator;
import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.SplitExpenseCommandCreator;
import seedu.budgetbuddy.command.ListSplitExpenseCommand;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.command.ReduceSavingCommand;
import seedu.budgetbuddy.command.SetBudgetCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    protected ArrayList<String> expenseCategories;
    protected ArrayList<String> savingsCategories;

    public Parser() {
        this.expenseCategories = new ArrayList<>(Arrays.asList("Housing",
                "Groceries", "Utility", "Transport", "Entertainment", "Others"));
        this.savingsCategories = new ArrayList<>(Arrays.asList("Salary",
                "Investments", "Gifts", "Others"));
    }

    private String extractDetailsForFind(String input, String splitter) {
        int startIndex = input.indexOf(splitter) + splitter.length();
        int endIndex = input.length();

        String[] nextPrefixes = { "d/", "morethan/", "lessthan/" };
        for (String nextPrefix : nextPrefixes) {
            if (input.indexOf(nextPrefix, startIndex) != -1 && input.indexOf(nextPrefix, startIndex) < endIndex) {
                endIndex = input.indexOf(nextPrefix, startIndex);
            }
        }
        return input.substring(startIndex, endIndex).trim();
    }

    public Boolean isFindExpensesCommand(String input) {
        return input.startsWith("find expenses");
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
        LOGGER.log(Level.INFO, "Checking if Input is a Menu Command");
        return input.startsWith("menu");
    }

    /**
     * Checks if the provided input starts with the word "bye" .
     *
     * @param input The user input string
     * @return true if user input starts with "bye", else returns false
     */
    public Boolean isExitCommand(String input) {
        return input.startsWith("bye");
    }

    /**
     * Checks if the provided input starts with the word "add expense" .
     *
     * @param input The user input string
     * @return true if user input starts with "add expense", else returns false
     */
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

    public Boolean isSplitExpenseCommand(String input) {
        return input.startsWith("split expenses");
    }
    public Boolean isSetBudgetCommand(String input){
        return input.startsWith("set budget");
    }

    public boolean isListBudgetCommand(String input){
        return input.startsWith("budget print");
    }

    /**
     * Parses the "find expenses" command, allowing for optional and combinable
     * parameters.
     *
     * @param input    The full user input string.
     * @param expenses The ExpenseList to search within.
     * @return A Command for executing the search, or null if the input is invalid.
     */
    public Command handleFindExpensesCommand(String input, ExpenseList expenses) {
        assert input != null : "Input cannot be null";
        assert !input.isEmpty() : "Input cannot be empty";
        assert input.startsWith("find expenses") : "Input must be a find expenses command";

        String description = null;
        Double minAmount = null;
        Double maxAmount = null;

        LOGGER.log(Level.INFO, "Begin parsing parameters in find expenses command");

        if (!input.contains("d/") && !input.contains("morethan/") && !input.contains("lessthan/")) {
            LOGGER.log(Level.WARNING, "Input does not contain any parameters");

            System.out.println("Please Ensure that you include d/, morethan/ or lessthan/");
            return null;
        }

        if (input.contains("d/")) {
            description = extractDetailsForFind(input, "d/");
        }

        if (input.contains("morethan/")) {
            String minAmountAsString = extractDetailsForFind(input, "morethan/");
            try {
                minAmount = Double.parseDouble(minAmountAsString);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Detected a String when expecting a Number in minAmount");

                System.out.println("Invalid format for amount.");
                return null;
            }
        }

        if (input.contains("lessthan/")) {
            String maxAmountAsString = extractDetailsForFind(input, "lessthan/");
            try {
                maxAmount = Double.parseDouble(maxAmountAsString);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Detected a String when expecting a Number in maxAmount");

                System.out.println("Invalid format for amount.");
                return null;
            }
        }

        if (minAmount != null && maxAmount != null && minAmount > maxAmount) {
            LOGGER.log(Level.WARNING, "Detected Minimum Amount Larger than Maximum Amount");

            System.out.println("Maximum Amount cannot be Smaller than Minimum Amount");
            return null;
        }

        return new FindExpensesCommand(expenses, description, minAmount, maxAmount);
    }

    /**
     * Parses the "list" command, allowing for optional category filtering.
     *
     * @param input The full user input string.
     * @param expenseList The ExpenseList to list from.
     * @param savingList The SavingList to list from.
     * @return A Command for executing the list, or null if the input is invalid.
     */
    
    public Command handleListCommand(String input, ExpenseList expenseList, SavingList savingList, 
            SplitExpenseList splitexpenseList) {
        assert input != null : "Input should not be null";
        assert !input.isEmpty() : "Input should not be empty";

        String[] parts = input.split(" ");
        assert parts.length >= 1 : "At least one part should be present in the input";

        String action = parts[0];
        assert !action.isEmpty() : "Action should not be empty";

        switch (action) {
        case "list":
            if (parts.length == 2) {
                // List expenses or savings
                String listType = parts[1];
                assert !listType.isEmpty() : "List type should not be empty";

                if (listType.equalsIgnoreCase("expenses")) {
                    return new ListExpenseCommand(expenseList);
                } else if (listType.equalsIgnoreCase("savings")) {
                    return new ListSavingsCommand(savingList, expenseList);
                }
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("expenses")) {
                String filterCategory = parts[2];
                try {
                    // Checks for valid category input
                    if (filterCategory != null) {
                        boolean isValidCategory = isValidExpenseCategory(filterCategory);
                        if (!isValidCategory) {
                            LOGGER.warning("Invalid category inputted: " + filterCategory);
                            System.out.println("Invalid category: " + filterCategory);
                            return null;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid category inputted: " + filterCategory, e);
                }
                return new ListExpenseCommand(expenseList, filterCategory);
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("splitted") 
                    && parts[2].equalsIgnoreCase("expenses")) {
                return new ListSplitExpenseCommand(splitexpenseList);
            } else if (parts.length == 3 && parts[1].equalsIgnoreCase("savings")) {
                String filterCategory = parts[2];
                try {
                    // Checks for valid category input
                    if (filterCategory != null) {
                        boolean isValidCategory = isValidSavingsCategory(filterCategory);
                        if (!isValidCategory) {
                            LOGGER.warning("Invalid category inputted: " + filterCategory);
                            System.out.println("Invalid category: " + filterCategory);
                            return null;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid category inputted: " + filterCategory, e);
                }
                return new ListSavingsCommand(savingList, expenseList, filterCategory); // Pass expenseList instance
            } else {
                return null;
            }
            break;
        default:
            return null;
        }return null;

    }


    private boolean isValidExpenseCategory(String category) {

        assert category != null : "Category should not be null";
        assert !category.isEmpty() : "Category should not be empty";

        for (String validCategory : expenseCategories) {
            if (validCategory.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidSavingsCategory(String category) {

        assert category != null : "Category should not be null";
        assert !category.isEmpty() : "Category should not be empty";

        for (String validCategory : savingsCategories) {
            if (validCategory.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
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
        assert input != null : "Input should not be empty";
        assert input.startsWith("menu") : "Input should be a menu command";

        LOGGER.log(Level.INFO, "Start processing for Menu Command");

        if (input.trim().equals("menu")) {
            LOGGER.log(Level.INFO, "Menu Command has no parameters");
            return new MenuCommand(0);
        }
        try {
            String indexAsString = input.substring(5);
            int index = Integer.parseInt(indexAsString);

            LOGGER.log(Level.INFO, "Menu Command has found parameter" + index);

            return new MenuCommand(index);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Index found to not be an Integer");
            return null;
        }
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
                    System.out.println("Invalid Amount. Amount should be a numerical value.");
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
                    System.out.println("Invalid index");
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    System.out.println("Invalid amount. Amount should be a numerical value");
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
        LOGGER.log(Level.INFO, "Processing handleDeleteExpenseCommand");

        assert expenses != null : "Expense list cannot be null";
        assert input != null : "Input string cannot be null";

        String[] parts = input.split("i/", 2);
        // Check if the input format is correct (i.e., contains "i/")
        if (parts.length < 2) {
            LOGGER.log(Level.WARNING, "Invalid command format. Expected format: <command> i/<index>");
            System.out.println("Error: Invalid command format. Expected format: <command> i/<index>");
            return null;
        }

        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            // Check if the index is within the bounds of the expense list.
            if (index < 0 || index >= expenses.size()) {
                LOGGER.log(Level.WARNING, "Index is out of bounds.");
                System.out.println("Error: Index is out of bounds.");
                return null;
            }
            LOGGER.log(Level.INFO, "Successfully processed DeleteExpenseCommand");
            // If the index is valid, return a new DeleteExpenseCommand.
            return new DeleteExpenseCommand(expenses, index);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Index is not a valid number.");
            // Catch the NumberFormatException if the part after "i/" isn't a valid integer.
            System.out.println("Error: Index is not a valid number.");
            return null;
        }
    }

    public Command handleReduceSavingCommand(SavingList savings, String input) {
        LOGGER.log(Level.INFO, "Processing handleReduceSavingCommand");

        assert savings != null : "Savings list cannot be null";
        assert input != null : "Input string cannot be null";

        String description = input.replace("reduce", "").trim();

        if(description.contains("i/") && description.contains("a/")) {
            try {
                String[] parts = description.split("i/|a/", 3);

                String indexToReduceAsString = parts[1].trim();
                String amountToReduceAsString = parts[2].trim();
                int indexToReduce = Integer.parseInt(indexToReduceAsString) - 1;
                double amountToReduce = Double.parseDouble(amountToReduceAsString);

                // Validate the index range.
                if (indexToReduce < 0 || indexToReduce >= savings.size()) {
                    LOGGER.log(Level.WARNING, "Index is out of bounds.");
                    System.out.println("Error: Index is out of bounds.");
                    return null;
                }
                LOGGER.log(Level.INFO, "Successfully processed ReduceSavingCommand!");
                return new ReduceSavingCommand(savings, indexToReduce, amountToReduce);
            } catch (NumberFormatException e){
                LOGGER.log(Level.SEVERE, "Index and amount must be valid numbers.");
                // Catch and handle incorrect number formats for index or amount.
                System.out.println("Error: Index and amount must be valid numbers.");
                return null;
            }
        } else {
            LOGGER.log(Level.WARNING, "Invalid command format. Expected format: reduce i/<index> a/<amount>");
            // Handle the case where the input does not contain the required markers.
            System.out.println("Error: Invalid command format. Expected format: reduce i/<index> a/<amount>");
            return null;
        }
    }

    private Command handleSetBudgetCommand(ExpenseList expenses, String input) {
        LOGGER.log(Level.INFO, "Entering handleSetBudgetCommand with input: " + input);
        String[] parts = input.split(" ");
        String category = null;
        double budget = -1;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
                LOGGER.log(Level.INFO, "Category extracted: " + category);
            } else if (part.startsWith("b/")) {
                try {
                    budget = Double.parseDouble(part.substring(2));
                    LOGGER.log(Level.INFO, "Budget extracted: " + budget);
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.SEVERE, "Invalid budget format. Budget should be a number.", e);
                    System.out.println("Invalid budget format. Budget should be a number");
                    return null;
                }
            }
        }

        if (category == null || budget == -1) {
            LOGGER.log(Level.WARNING, "Invalid command format or missing values for category/budget");
            System.out.println("Invalid command format.");
            System.out.println("Expected format: set budget c/<category> b/<budget>");
            return null;
        }

        boolean isValidCategory = isValidExpenseCategory(category);
        if (!isValidCategory) {
            LOGGER.log(Level.WARNING, "Invalid category: " + category);
            System.out.println("Invalid category: " + category);
            System.out.println("Valid categories: Housing, Groceries, Utility, Transport, Entertainment, Others");
            return null;
        }

        LOGGER.log(Level.INFO, "Exiting handleSetBudgetCommand. Command ready for execution.");
        return new SetBudgetCommand(expenses, category, budget);
    }

    public Command handleListBudgetCommand(ExpenseList expenseList) {
        return new ListBudgetCommand(expenseList);
    }

    /**
     * Parses a string input into a Command object and returns the associated
     * command to handle the user input
     *
     * @param input The user input string.
     * @return A Command object corresponding to the user input, or null if the
     *         input is invalid.
     */
    public Command parseCommand(ExpenseList expenses, SavingList savings, SplitExpenseList 
            splitexpenses, String input) {
        
        if(isMenuCommand(input)) {
            LOGGER.log(Level.INFO, "Confirmed that input is a menu command");
            return handleMenuCommand(input);
        }

        if (isAddExpenseCommand(input)) {
            CommandCreator commandCreator = new AddExpenseCommandCreator(expenses, input);
            return commandCreator.createCommand();
        }

        if (isAddSavingCommand(input)) {
            CommandCreator commandCreator = new AddSavingCommandCreator(savings, input);
            return commandCreator.createCommand();
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
            return handleListCommand(input, expenses, savings, splitexpenses);
        }

        if (isFindExpensesCommand(input)) {
            return handleFindExpensesCommand(input, expenses);
        }

        if (isSplitExpenseCommand(input)) {
            CommandCreator commandCreator = new SplitExpenseCommandCreator(splitexpenses, input);
            return commandCreator.createCommand();
        }
        
        if (isSetBudgetCommand(input)) {
            return handleSetBudgetCommand(expenses, input);
        }

        if (isListBudgetCommand(input)){
            return handleListBudgetCommand(expenses);
        }
        return null;
    }
}
