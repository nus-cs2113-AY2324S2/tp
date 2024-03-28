package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.FindExpensesCommand;
import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindExpensesCommandCreator extends CommandCreator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String DESCRIPTION_PREFIX = "d/";
    private static final String MINAMOUNT_PREFIX = "morethan/";
    private static final String MAXAMOUNT_PREFIX = "lessthan/";


    private ExpenseList expenses;
    private String input;

    public FindExpensesCommandCreator(String input, ExpenseList expenses) {
        this.input = input;
        this.expenses = expenses;
    }

    private static void checkForInvalidParameters(String input) {
        if (!input.contains("d/") || !input.contains("morethan/") || !input.contains("lessthan/")) {
            throw new IllegalArgumentException("Please Ensure that you include d/, morethan/ and lessthan/");
        }
    }

    public Double parseMaxAmount(String input) throws NumberFormatException{
        int indexOfMaxAmountPrefix = input.indexOf(MAXAMOUNT_PREFIX);
        int startIndexOfMaxAmount = indexOfMaxAmountPrefix + MAXAMOUNT_PREFIX.length();

        int endIndexOfMaxAmount = input.length();

        String maxAmountAsString = input.substring(startIndexOfMaxAmount, endIndexOfMaxAmount);

        if (maxAmountAsString.trim().isEmpty()) {
            return null;
        }

        Double maxAmount = Double.parseDouble(maxAmountAsString);

        return maxAmount;
    }

    public Double parseMinAmount(String input) {
        int indexOfMinAmountPrefix = input.indexOf(MINAMOUNT_PREFIX);
        int startIndexOfMinAmount = indexOfMinAmountPrefix + MINAMOUNT_PREFIX.length();

        int indexOfMaxAmountPrefix = input.indexOf(MAXAMOUNT_PREFIX);
        int endIndexOfMinAmount = indexOfMaxAmountPrefix - 1;

        String minAmountAsString = input.substring(startIndexOfMinAmount, endIndexOfMinAmount);

        if (minAmountAsString.trim().isEmpty()) {
            return null;
        }

        Double minAmount = Double.parseDouble(minAmountAsString);

        return minAmount;
    }
    public String parseDescription(String input) {

        int indexOfDescriptionPrefix = input.indexOf(DESCRIPTION_PREFIX);
        int startIndexOfDescription = indexOfDescriptionPrefix + DESCRIPTION_PREFIX.length();

        int indexOfMinAmountPrefix = input.indexOf(MINAMOUNT_PREFIX);
        int endIndexOfDescription = indexOfMinAmountPrefix - 1;

        String description = input.substring(startIndexOfDescription, endIndexOfDescription).trim();

        if (description.isEmpty()) {
            return null;
        }

        return description;
    }

    private static void checkForDuplicateParameters(String input, String parameter) {

        int count = 0;

        Pattern pattern = Pattern.compile(parameter);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            count++;
        }

        if (count > 1) {
            throw new IllegalArgumentException("The parameter '" + parameter + "' can only be used once.");
        }

    }

    private static void compareMinAndMaxAmount(Double minAmount, Double maxAmount) throws BudgetBuddyException{

        if (minAmount != null && maxAmount != null) {
            if (minAmount >= maxAmount) {
                throw new BudgetBuddyException("Ensure minimum amount is smaller than maximum amount");
            }
        }

    }

    /**
     * Parses the "find expenses" command, allowing for optional and combinable parameters.
     *
     * @param input The full user input string.
     * @param expenses The ExpenseList to search within.
     * @return A Command for executing the search, or null if the input is invalid.
     */
    public Command handleFindExpensesCommand(String input, ExpenseList expenses) {
        assert input != null : "Input cannot be null";
        assert !input.isEmpty() : "Input cannot be empty";
        assert input.startsWith("find expenses") : "Input must be a find expenses command";

        LOGGER.log(Level.INFO, "Begin parsing parameters in find expenses command");

        try {
            checkForInvalidParameters(input);
            checkForDuplicateParameters(input, "d/");
            checkForDuplicateParameters(input, "morethan/");
            checkForDuplicateParameters(input, "lessthan/");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            String description = parseDescription(input);
            Double minAmount = parseMinAmount(input);
            Double maxAmount = parseMaxAmount(input);

            compareMinAndMaxAmount(minAmount, maxAmount);

            return new FindExpensesCommand(expenses, description, minAmount, maxAmount);

        } catch (NumberFormatException e) {
            System.out.println("Please input a valid amount.");
            return null;
        } catch (BudgetBuddyException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Command createCommand() {
        return handleFindExpensesCommand(input, expenses);
    }
}
