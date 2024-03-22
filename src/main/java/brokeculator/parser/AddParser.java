package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.enumerators.CommandErrorMessages;
import brokeculator.expense.Expense;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddParser {
    public static final String[] ADD_COMMAND_OPTIONS = {" /n ", " /d ", " /a ", " /c "};
    public static final int NAME_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;
    public static final int AMOUNT_INDEX = 2;
    public static final int CATEGORY_INDEX = 3;
    public static final String DEFAULT_EXPENSE_CATEGORY = "null";
    public static final String AMOUNT_PATTERN = "^\\d+(\\.\\d+)?$";
    public static Command parseInput(String userInput) {
        if(!isOptionsPresent(userInput) || !isOptionsAppearOnce(userInput)) {
            return new InvalidCommand(CommandErrorMessages.INVALID_ADD_COMMAND.getString());
        }
        String[] userInputAsArray = userInput.trim().split("\\s+");
        try {
            String expenseDescription = getOptionField(userInputAsArray, ADD_COMMAND_OPTIONS[NAME_INDEX]);
            String expenseDate = getOptionField(userInputAsArray, ADD_COMMAND_OPTIONS[DESCRIPTION_INDEX]);
            String expenseAmountAsString = getOptionField(userInputAsArray, ADD_COMMAND_OPTIONS[AMOUNT_INDEX]);
            String expenseCategory = "null";
            if (userInput.contains(" /c ")) {
                expenseCategory = getOptionField(userInputAsArray, ADD_COMMAND_OPTIONS[CATEGORY_INDEX]);
                expenseCategory = expenseCategory.isBlank() ? "null" : expenseCategory;
            }
            boolean isDesiredFieldsEmpty = isFieldsEmpty(expenseDescription, expenseDate, expenseAmountAsString);
            boolean isAmountNumeric = isAmountNumericString(expenseAmountAsString);
            if (isDesiredFieldsEmpty || !isAmountNumeric) {
                return new InvalidCommand("Expense description, date and amount cannot be empty");
            }
            double expenseAmount = Double.parseDouble(expenseAmountAsString);
            Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
            return new AddCommand(expenseToAdd);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Expense amount cannot be empty or non-numeric");
        }
    }
    private static boolean isFieldsEmpty(String expenseDescription, String expenseDate, String expenseAmountAsString) {
        return expenseDescription.isBlank() || expenseDate.isBlank() || expenseAmountAsString.isBlank();
    }
    private static boolean isOptionsPresent(String userInput) {
        return userInput.contains(" /n ") && userInput.contains(" /d ") && userInput.contains(" /a ");
    }
    private static boolean isAmountNumericString(String expenseAmountAsString) {
        Pattern pattern = Pattern.compile(AMOUNT_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expenseAmountAsString);
        return matcher.find();
    }
    private static boolean isOptionsAppearOnce(String userInput) {
        for (String option : ADD_COMMAND_OPTIONS) {
            if (userInput.indexOf(option) != userInput.lastIndexOf(option)) {
                return false;
            }
        }
        return true;
    }
    private static String getOptionField(String[] userInputArray, String option) {
        StringBuilder optionField = new StringBuilder();
        boolean startAppending = false;
        for (String s : userInputArray) {
            if (s.equals(option.trim())) {
                startAppending = true;
                continue;
            }
            if (isWordOption(s) && startAppending) {
                return optionField.toString().trim();
            }
            if (startAppending) {
                optionField.append(s).append(" ");
            }
        }
        return optionField.toString().trim();
    }
    private static boolean isWordOption(String word) {
        for (String option : ADD_COMMAND_OPTIONS) {
            if (word.equals(option.trim())) {
                return true;
            }
        }
        return false;
    }
}
