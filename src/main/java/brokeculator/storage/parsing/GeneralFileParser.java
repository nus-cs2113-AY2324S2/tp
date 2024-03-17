package brokeculator.storage.parsing;

import brokeculator.command.AddExpenseFromFileCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.expense.ExpenseManager;

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
