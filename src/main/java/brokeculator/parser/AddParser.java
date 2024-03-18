package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.expense.Expense;

public class AddParser {
    public static Command parseInput(String userInput) {
        int expenseCategoryBeginIndex = userInput.indexOf("/c") + 2;
        int expenseAmountBeginIndex = userInput.indexOf("/a") + 2;
        int expenseAmountEndIndex = userInput.contains("/c") ?  expenseCategoryBeginIndex- 2 : userInput.length();
        int expenseDateBeginIndex = userInput.indexOf("/d") + 2;
        int expenseDateEndIndex = expenseAmountBeginIndex - 2;
        int expenseDescriptionBeginIndex = userInput.indexOf("/n") + 2;
        int expenseDescriptionEndIndex = expenseDateBeginIndex - 2;
        try {
            String expenseDescription = userInput.substring(expenseDescriptionBeginIndex, expenseDescriptionEndIndex);
            String expenseDate = userInput.substring(expenseDateBeginIndex, expenseDateEndIndex);
            String expenseAmountAsString = userInput.substring(expenseAmountBeginIndex, expenseAmountEndIndex);
            double expenseAmount = Double.parseDouble(expenseAmountAsString.trim());
            String expenseCategory = "null";
            if (userInput.contains("/c")) {
                String tempExpenseCategory = userInput.substring(expenseCategoryBeginIndex);
                expenseCategory = tempExpenseCategory.isEmpty() ? "null" : tempExpenseCategory;
            }
            if (isFieldsEmpty(expenseDescription, expenseDate, expenseAmountAsString)) {
                return new InvalidCommand("Expense description, date and amount cannot be empty");
            }
            Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
            return new AddCommand(expenseToAdd);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand("Invalid input format for add command."
                    + "Please use the format: add /n <description> /d <date> /a <amount> /c <category>");
        } catch (NumberFormatException e) {
            return new InvalidCommand("Expense amount cannot be empty or non-numeric");
        }
    }

    private static boolean isFieldsEmpty(String expenseDescription, String expenseDate, String expenseAmountAsString) {
        return expenseDescription.isBlank() || expenseDate.isBlank() || expenseAmountAsString.isBlank();
    }
}
