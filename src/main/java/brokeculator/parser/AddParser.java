package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.enumerators.CommandErrorMessages;
import brokeculator.expense.Expense;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddParser {
    public static final String AMOUNT_PATTERN = "^\\d+(\\.\\d+)?$";
    public static Command parseInput(String userInput) {
        if(!isOptionsPresent(userInput)) {
            return new InvalidCommand(CommandErrorMessages.INVALID_ADD_COMMAND.getString());
        }
        int expenseCategoryBeginIndex = userInput.indexOf(" /c ") + 4;
        int expenseAmountBeginIndex = userInput.indexOf(" /a ") + 4;
        int expenseAmountEndIndex = userInput.contains(" /c ") ?  expenseCategoryBeginIndex - 4 : userInput.length();
        int expenseDateBeginIndex = userInput.indexOf(" /d ") + 4;
        int expenseDateEndIndex = expenseAmountBeginIndex - 4;
        int expenseDescriptionBeginIndex = userInput.indexOf(" /n ") + 4;
        int expenseDescriptionEndIndex = expenseDateBeginIndex - 4;
        try {
            String expenseDescription = userInput.substring(expenseDescriptionBeginIndex, expenseDescriptionEndIndex)
                                        .trim();
            String expenseDate = userInput.substring(expenseDateBeginIndex, expenseDateEndIndex)
                                 .trim();
            String expenseAmountAsString = userInput.substring(expenseAmountBeginIndex, expenseAmountEndIndex)
                                            .replace("/c", "").trim();
            String expenseCategory = "null";
            if (userInput.contains(" /c ")) {
                String tempExpenseCategory = userInput.substring(expenseCategoryBeginIndex);
                expenseCategory = tempExpenseCategory.isBlank() ? "null" : tempExpenseCategory;
            }
            boolean isDesiredFieldsEmpty = isFieldsEmpty(expenseDescription, expenseDate, expenseAmountAsString);
            boolean isAmountNumeric = isAmountNumericString(expenseAmountAsString);
            if (isDesiredFieldsEmpty || !isAmountNumeric) {
                return new InvalidCommand("Expense description, date and amount cannot be empty");
            }
            double expenseAmount = Double.parseDouble(expenseAmountAsString);
            Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
            return new AddCommand(expenseToAdd);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(CommandErrorMessages.INVALID_ADD_COMMAND.getString());
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
}
