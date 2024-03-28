package seedu.budgetbuddy;

import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.FindExpensesCommand;
import seedu.budgetbuddy.command.ListBudgetCommand;
import seedu.budgetbuddy.command.MenuCommand;

import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.RecurringExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.ChangeCurrencyCommandCreator;
import seedu.budgetbuddy.commandcreator.ListCommandCreator;
import seedu.budgetbuddy.commandcreator.AddExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.AddSavingCommandCreator;
import seedu.budgetbuddy.commandcreator.ListSplittedExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.SettleSplitExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.SplitExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.EditExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.EditSavingsCommandCreator;
import seedu.budgetbuddy.commandcreator.DeleteExpenseCommandCreator;
import seedu.budgetbuddy.commandcreator.ReduceSavingCommandCreator;
import seedu.budgetbuddy.commandcreator.SetBudgetCommandCreator;

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

    public Boolean isListSplitExpenseCommand(String input) {
        return input.contentEquals("check splitted expenses");
    }

    public Boolean isSetBudgetCommand(String input){
        return input.startsWith("set budget");
    }

    public boolean isListBudgetCommand(String input){
        return input.startsWith("budget print");
    }

    public Boolean isSettleSplitExpenseCommand(String input) {
        return input.startsWith("settle");
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
            CommandCreator commandCreator = new AddExpenseCommandCreator(expenses, input);
            return commandCreator.createCommand();
        }

        if (isAddSavingCommand(input)) {
            CommandCreator commandCreator = new AddSavingCommandCreator(savings, input);
            return commandCreator.createCommand();
        }

        if (isEditExpenseCommand(input)) {
            CommandCreator commandCreator = new EditExpenseCommandCreator(input, expenses);
            return commandCreator.createCommand();
        }

        if (isEditSavingCommand(input)) {
            CommandCreator commandCreator = new EditSavingsCommandCreator(input, savings);
            return commandCreator.createCommand();
        }

        if (isDeleteExpenseCommand(input)) {
            CommandCreator commandCreator = new DeleteExpenseCommandCreator(expenses, input);
            return commandCreator.createCommand();
        }

        if (isReduceSavingCommand(input)) {
            CommandCreator commandCreator = new ReduceSavingCommandCreator(savings, input);
            return commandCreator.createCommand();
        }

        if (isListCommand(input)) {
            CommandCreator commandCreator = new ListCommandCreator(expenses, savings, input);
            return commandCreator.createCommand();
        }

        if (isListSplitExpenseCommand(input)) {
            CommandCreator commandCreator = new ListSplittedExpenseCommandCreator(input, splitexpenses);
            return commandCreator.createCommand();
        }

        if (isFindExpensesCommand(input)) {
            return handleFindExpensesCommand(input, expenses);
        }

        if (isRecCommand(input)) {
            CommandCreator commandCreator = new RecurringExpenseCommandCreator(input, expensesList, expenses);
            return commandCreator.createCommand();
        }

        if (isConvertCurrencyCommand(input)) {
            CommandCreator commandCreator = new ChangeCurrencyCommandCreator(input, savings, expenses,
                    new CurrencyConverter());
            return commandCreator.createCommand();
        }

        if (isSplitExpenseCommand(input)) {
            CommandCreator commandCreator = new SplitExpenseCommandCreator(splitexpenses, input);
            return commandCreator.createCommand();
        }

        if (isSettleSplitExpenseCommand(input)) {
            CommandCreator commandCreator = new SettleSplitExpenseCommandCreator(input, splitexpenses);
            return commandCreator.createCommand();
        }
        
        if (isSetBudgetCommand(input)) {
            CommandCreator commandCreator = new SetBudgetCommandCreator(expenses, input);
            return commandCreator.createCommand();
        }

        if (isListBudgetCommand(input)){
            return handleListBudgetCommand(expenses);
        }
        return null;
    }
}
