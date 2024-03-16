package brokeculator.parser;

import brokeculator.command.AddCommand;
import brokeculator.command.Command;
import seedu.expense.Expense;
import seedu.expense.ExpenseManager;

import java.time.LocalDateTime;

public class AddParser {
    private ExpenseManager expenseManager;

    public AddParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public Command parseInput(String userInput) {
        int expenseDescriptionEndIndex = userInput.indexOf(":");
        int expenseDateBeginIndex = userInput.indexOf("(");
        int expenseDateEndIndex = userInput.indexOf(")");
        int expenseAmountBeginIndex = userInput.indexOf("$");
        int expenseAmountEndIndex = expenseDateBeginIndex - 2;
        int expenseCategoryBeginIndex = userInput.indexOf("[");
        int expenseCategoryEndIndex = userInput.indexOf("]");

        String expenseDescription = userInput.substring(0, expenseDescriptionEndIndex);
        String expenseDateAsString = userInput.substring(expenseDateBeginIndex, expenseDateEndIndex);
        LocalDateTime expenseDate = LocalDateTime.parse(expenseDateAsString);
        String expenseAmountAsString = userInput.substring(expenseAmountBeginIndex, expenseAmountEndIndex);
        double expenseAmount = Double.parseDouble(expenseAmountAsString);
        String expenseCategory = userInput.substring(expenseCategoryBeginIndex, expenseCategoryEndIndex);

        Expense expenseToAdd = new Expense(expenseDescription, expenseAmount, expenseDate, expenseCategory);
        return new AddCommand(expenseToAdd, expenseManager);
    }
}
