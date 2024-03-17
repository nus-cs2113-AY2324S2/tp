package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.expense.Expense;
import brokeculator.expense.ExpenseManager;

import java.time.LocalDateTime;

public class AddParser {
    private ExpenseManager expenseManager;

    public AddParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public AddCommand parseInput(String userInput) {
        int expenseCategoryBeginIndex = userInput.indexOf("/c");
        int expenseAmountBeginIndex = userInput.indexOf("/a");
        int expenseAmountEndIndex = expenseCategoryBeginIndex - 2;
        int expenseDateBeginIndex = userInput.indexOf("/d");
        int expenseDateEndIndex = expenseAmountBeginIndex - 2;
        int expenseDescriptionBeginIndex = userInput.indexOf("/n");
        int expenseDescriptionEndIndex = expenseDateEndIndex - 2;

        String expenseDescription = userInput.substring(expenseDescriptionBeginIndex, expenseDescriptionEndIndex);
        String expenseDate = userInput.substring(expenseDateBeginIndex, expenseDateEndIndex);
        String expenseAmountAsString = userInput.substring(expenseAmountBeginIndex, expenseAmountEndIndex);
        double expenseAmount = Double.parseDouble(expenseAmountAsString);
        String expenseCategory = userInput.substring(expenseCategoryBeginIndex);

        Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
        return new AddCommand(expenseToAdd, expenseManager);
    }
}
