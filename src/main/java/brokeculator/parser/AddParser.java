package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.expense.Expense;

public class AddParser {
    public static AddCommand parseInput(String userInput) {
        int expenseCategoryBeginIndex = userInput.indexOf("/c") + 2;
        int expenseAmountBeginIndex = userInput.indexOf("/a") + 2;
        int expenseAmountEndIndex = expenseCategoryBeginIndex - 2;
        int expenseDateBeginIndex = userInput.indexOf("/d") + 2;
        int expenseDateEndIndex = expenseAmountBeginIndex - 2;
        int expenseDescriptionBeginIndex = userInput.indexOf("/n") + 2;
        int expenseDescriptionEndIndex = expenseDateBeginIndex - 2;

        String expenseDescription = userInput.substring(expenseDescriptionBeginIndex, expenseDescriptionEndIndex);
        String expenseDate = userInput.substring(expenseDateBeginIndex, expenseDateEndIndex);
        String expenseAmountAsString = userInput.substring(expenseAmountBeginIndex, expenseAmountEndIndex);
        double expenseAmount = Double.parseDouble(expenseAmountAsString.trim());
        String expenseCategory = userInput.substring(expenseCategoryBeginIndex);

        Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
        return new AddCommand(expenseToAdd);
    }
}
