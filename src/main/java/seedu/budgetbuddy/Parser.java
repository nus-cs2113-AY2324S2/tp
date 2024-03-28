package seedu.budgetbuddy;

import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.ListBudgetCommand;

import seedu.budgetbuddy.commandcreator.CommandCreator;
import seedu.budgetbuddy.commandcreator.FindExpensesCommandCreator;
import seedu.budgetbuddy.commandcreator.MenuCommandCreator;
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
            CommandCreator commandCreator = new MenuCommandCreator(input);
            return commandCreator.createCommand();
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
            CommandCreator commandCreator = new FindExpensesCommandCreator(input, expenses);
            return commandCreator.createCommand();
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
