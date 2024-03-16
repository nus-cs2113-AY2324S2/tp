package brokeculator.parser;

import brokeculator.command.DeleteCommand;
import brokeculator.expense.ExpenseManager;

public class DeleteParser {
    private ExpenseManager expenseManager;

    public DeleteParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public DeleteCommand parseInput(String userInput) {
        String[] userInputAsArray = userInput.split(" ");
        int indexToDelete = 0;
        if (userInputAsArray.length == 1) {
            //TODO proper error handling when delete command has no index
            return new DeleteCommand(0, expenseManager);
        } else {
            indexToDelete = Integer.parseInt(userInputAsArray[1]);
        }

        return new DeleteCommand(indexToDelete, expenseManager);
    }
}
