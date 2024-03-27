package seedu.budgetbuddy;

import seedu.budgetbuddy.command.AddExpenseCommand;
import seedu.budgetbuddy.command.AddSavingCommand;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.DeleteExpenseCommand;
import seedu.budgetbuddy.command.EditExpenseCommand;
import seedu.budgetbuddy.command.EditSavingCommand;
import seedu.budgetbuddy.command.FindExpensesCommand;
import seedu.budgetbuddy.command.ListBudgetCommand;
import seedu.budgetbuddy.command.ListExpenseCommand;
import seedu.budgetbuddy.command.ListSavingsCommand;
import seedu.budgetbuddy.command.SplitExpenseCommand;
import seedu.budgetbuddy.command.ListSplitExpenseCommand;
import seedu.budgetbuddy.command.MenuCommand;
import seedu.budgetbuddy.command.ReduceSavingCommand;
import seedu.budgetbuddy.command.SetBudgetCommand;
import seedu.budgetbuddy.command.ChangeCurrencyCommand;
import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.RecurringExpenseCommandCreator;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
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

    private String extractDetailsForCommand(String input, String splitter, CommandPrefix type) {
        int startIndex = input.indexOf(splitter) + splitter.length();
        int endIndex = input.length();

        String[] nextPrefixes = type.getNextPrefixes();

        for (String nextPrefix : nextPrefixes) {
            if (input.indexOf(nextPrefix, startIndex) != -1 && input.indexOf(nextPrefix, startIndex) < endIndex) {
                endIndex = input.indexOf(nextPrefix, startIndex);
            }
        }
        return input.substring(startIndex, endIndex).trim();
    }

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


    public Boolean isRecCommand(String input) {
        return input.startsWith("rec ");
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
    public Boolean isConvertCurrencyCommand(String input) {
        return input.startsWith("change currency");
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
            description = extractDetailsForCommand(input, "d/", CommandPrefix.FIND);
        }

        if (input.contains("morethan/")) {
            String minAmountAsString = extractDetailsForCommand(input, "morethan/", CommandPrefix.FIND);
            try {
                minAmount = Double.parseDouble(minAmountAsString);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Detected a String when expecting a Number in minAmount");

                System.out.println("Invalid format for amount.");
                return null;
            }
        }

        if (input.contains("lessthan/")) {
            String maxAmountAsString = extractDetailsForCommand(input, "lessthan/" , CommandPrefix.FIND);
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

    public Command handleChangeCurrencyCommand(String input, SavingList savingList, ExpenseList expenseList,
                                               CurrencyConverter currencyConverter) {
        if (input.startsWith("change currency")) {
            String[] parts = input.split(" ");
            assert parts.length > 1 : "Input should contain currency code";

            if (parts.length == 3) {
                String currencyCode = parts[2];
                assert !currencyCode.isEmpty() : "Currency code should not be empty";

                try {
                    Currency newCurrency = Currency.getInstance(currencyCode.toUpperCase());
                    assert newCurrency != null : "Currency code should be valid";
                    LOGGER.log(Level.INFO, "Default currency changed to " + newCurrency);
                    System.out.println("Default currency changed to " + newCurrency);
                    return new ChangeCurrencyCommand(newCurrency, savingList, expenseList, currencyConverter);
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid currency code: " + currencyCode);
                    System.out.println("Invalid currency code.");
                    return null;
                }
            } else {
                LOGGER.log(Level.WARNING, "Invalid command format. Use 'change currency <currency_code>'.");
                System.out.println("Invalid command format. Use 'change currency <currency_code>'.");
                return null;
            }
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

        String category = extractDetailsForCommand(details, "c/", CommandPrefix.ADD);
        if (category.isEmpty()) {
            System.out.println("category is missing.");
            return null;
        }
        String amount = extractDetailsForCommand(details, "a/", CommandPrefix.ADD);
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

        String description = extractDetailsForCommand(details, "d/", CommandPrefix.ADD);
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
        String category = extractDetailsForCommand(details, "c/", CommandPrefix.ADD);
        if (category.isEmpty()){
            System.out.println("Category is missing.");
            return null;
        }
        
        String amount = extractDetailsForCommand(details, "a/", CommandPrefix.ADD);

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

    public Command handleSplitExpenseCommand(SplitExpenseList splitexpenses, String input) {
        if (input == null || !input.contains("a/") || !input.contains("n/") || !input.contains("d/")) {
            System.out.println("Invalid command format.");
            return null;
        }
    
        // Extract details directly using the prefixes
        String amount = extractDetail(input, "a/");
        String numberOfPeople = extractDetail(input, "n/");
        String description = extractDetail(input, "d/");
    
        // Validation for each part

        if (amount.isEmpty() || numberOfPeople.isEmpty() || description.isEmpty()) {
            System.out.println("Missing details.");
            return null;
        }
    
        try {
            double amountValue = Double.parseDouble(amount);
            if (amountValue <= 0) {
                throw new BudgetBuddyException(amount + " is not a valid amount.");
            }
        } catch (NumberFormatException | BudgetBuddyException e) {
            System.out.println("Invalid amount format.");
            return null;
        }
    
        try {
            int numberValue = Integer.parseInt(numberOfPeople);
            if (numberValue <= 0) {
                throw new BudgetBuddyException(numberOfPeople + " is not a valid number.");
            }
        } catch (NumberFormatException | BudgetBuddyException e) {
            System.out.println("Invalid number format.");
            return null;
        }
    
        return new SplitExpenseCommand(splitexpenses, amount, numberOfPeople, description);
    }

    private String extractDetail(String input, String prefix) {
        try {
            int startIndex = input.indexOf(prefix) + prefix.length();
            int endIndex = input.indexOf(" ", startIndex);
            endIndex = endIndex == -1 ? input.length() : endIndex; // Handle last detail case
            return input.substring(startIndex, endIndex);
        } catch (Exception e) {
            return ""; // Return empty string if any error occurs
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
            splitexpenses, RecurringExpensesList expensesList, String input) {
        
        if(isMenuCommand(input)) {
            LOGGER.log(Level.INFO, "Confirmed that input is a menu command");
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
            return handleListCommand(input, expenses, savings, splitexpenses);
        }

        if (isFindExpensesCommand(input)) {
            return handleFindExpensesCommand(input, expenses);
        }

        if (isRecCommand(input)) {
            CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenses);
            return commandCreator.createCommand();
        }

        if (isConvertCurrencyCommand(input)) {
            return handleChangeCurrencyCommand(input, savings, expenses, new CurrencyConverter());
        }

        if (isSplitExpenseCommand(input)) {
            return handleSplitExpenseCommand(splitexpenses, input);
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
