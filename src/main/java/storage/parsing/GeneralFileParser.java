package storage.parsing;

import command.AddExpenseFromFileCommand;
import command.Command;
import command.InvalidCommand;
import seedu.expense.Expense;
import seedu.expense.ExpenseManager;

public class GeneralFileParser {
    private ExpenseManager expenseManager;
    public GeneralFileParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public Command getCommandFromFileInput(String fileString) {
        String firstWord = (fileString.split(" "))[0];
        switch (firstWord) {
        case FileKeyword.EXPENSE:
            return new AddExpenseFromFileCommand(fileString, expenseManager);
        default:
            return new InvalidCommand();
        }
    }
}
